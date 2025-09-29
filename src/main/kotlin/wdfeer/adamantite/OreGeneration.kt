package wdfeer.adamantite

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.world.GameMode
import net.minecraft.world.PersistentState
import net.minecraft.world.World
import kotlin.random.Random

private const val ADAMANTITE_GENERATION_STATE_ID = "adamantite_generation"

private data class AdamantiteGenerationState(var generated: MutableSet<ChunkPos>) : PersistentState() {
    constructor(tag: NbtCompound) : this(mutableSetOf()) {
        generated = tag.keys.map { it.split(",").map { n -> n.toInt() } }.map { ChunkPos(it[0], it[1]) }.toMutableSet()
    }

    override fun writeNbt(nbt: NbtCompound): NbtCompound {
        generated.forEach {
            nbt.putByte("${it.x},${it.z}", 0)
        }
        return nbt
    }
}

fun initOreGeneration() {
    if (Config.enableGeneration) ServerTickEvents.END_SERVER_TICK.register(::tick)
}

private fun tick(server: MinecraftServer) {
    val enderDragonDead =
        server.getWorld(World.END)?.let { it.enderDragonFight?.hasPreviouslyKilled() ?: true } ?: false
    if (!enderDragonDead) return

    val world = server.overworld
    if (world.time % Config.interval != 0L) return

    val validPlayers = world.players.filter { it.isAlive && it.interactionManager.gameMode != GameMode.SPECTATOR }
    if (validPlayers.isEmpty()) return

    val state = world.persistentStateManager.getOrCreate(
        ::AdamantiteGenerationState,
        { AdamantiteGenerationState(mutableSetOf()) },
        ADAMANTITE_GENERATION_STATE_ID
    )

    val chunksToGenerate = validPlayers.flatMap { player ->
        buildList {
            val radius = 2
            for (x in -radius..radius) {
                for (z in -radius..radius) {
                    add(ChunkPos(player.chunkPos.x + x, player.chunkPos.z + z))
                }
            }
        }
    }.filter { world.chunkManager.isChunkLoaded(it.x, it.z) } - state.generated

    if (chunksToGenerate.isEmpty()) return

    chunksToGenerate.forEach { chunk ->
        state.generated.add(chunk)
        state.markDirty()

        repeat(Config.triesPerChunk) {
            tryGenerateVein(world, chunk)
        }
    }
}

private fun getOre(world: ServerWorld): Block =
    if (Random(world.seed).nextBoolean()) deepslateAdamantiteOre else deepslateTitaniumOre

private fun tryGenerateVein(world: ServerWorld, chunk: ChunkPos) {
    val ore = getOre(world)
    val pos =
        BlockPos(
            chunk.x * 16 + Random.nextInt(16),
            (Config.minHeight..Config.maxHeight).random(),
            chunk.z * 16 + Random.nextInt(16)
        )
    if (world.getBlockState(pos).block == Blocks.DEEPSLATE) {
        val extraCount = Random.nextInt(3)
        world.setBlockState(pos, ore.defaultState)
        repeat(extraCount) {
            val range = -1..1
            val pos = pos.add(range.random(), range.random(), range.random())
            if (world.getBlockState(pos).block == Blocks.DEEPSLATE) {
                world.setBlockState(pos, ore.defaultState)
            }
        }
    }
}