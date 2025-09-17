package wdfeer.adamantite

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
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

private const val INTERVAL = 220
private const val TRIES_PER_CHUNK = 10
private val HEIGHT_RANGE = -60..-20
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

fun initOreGeneration() = ServerTickEvents.END_SERVER_TICK.register(::tick)

private fun tick(server: MinecraftServer) {
    val enderDragonDead =
        server.getWorld(World.END)?.let { it.enderDragonFight?.hasPreviouslyKilled() ?: true } ?: false
    if (!enderDragonDead) return

    val world = server.overworld
    if (world.time % INTERVAL != 0L) return

    val validPlayers = world.players.filter { it.isAlive && it.interactionManager.gameMode != GameMode.SPECTATOR }
    if (validPlayers.isEmpty()) return

    val state = world.persistentStateManager.getOrCreate(
        ::AdamantiteGenerationState,
        { AdamantiteGenerationState(mutableSetOf()) },
        ADAMANTITE_GENERATION_STATE_ID
    )

    val chunksToGenerate = validPlayers.map {
        listOf(
            it.chunkPos,
            it.chunkPos.run { ChunkPos(x + 1, z) },
            it.chunkPos.run { ChunkPos(x - 1, z) },
            it.chunkPos.run { ChunkPos(x, z + 1) },
            it.chunkPos.run { ChunkPos(x, z - 1) },
            it.chunkPos.run { ChunkPos(x + 1, z + 1) },
            it.chunkPos.run { ChunkPos(x - 1, z + 1) },
            it.chunkPos.run { ChunkPos(x + 1, z - 1) },
            it.chunkPos.run { ChunkPos(x - 1, z - 1) },
        )
    }.flatten().filter { world.chunkManager.isChunkLoaded(it.x, it.z) } - state.generated

    if (chunksToGenerate.isEmpty()) return

    chunksToGenerate.forEach { chunk ->
        state.generated.add(chunk)
        state.markDirty()

        var count = 0
        repeat(TRIES_PER_CHUNK) {
            count += tryGenerateVein(world, chunk)
        }
        Adamantite.logger.info("Generated $count deepslate adamantite ore blocks in the chunk ${chunk.x},${chunk.z}.")
    }
}

private fun tryGenerateVein(world: ServerWorld, chunk: ChunkPos): Int {
    var count = 0
    val pos =
        BlockPos(chunk.x * 16 + Random.nextInt(16), HEIGHT_RANGE.random(), chunk.z * 16 + Random.nextInt(16))
    if (world.getBlockState(pos).block == Blocks.DEEPSLATE) {
        val extraCount = Random.nextInt(3)
        world.setBlockState(pos, deepslateAdamantiteOre.defaultState)
        count++
        repeat(extraCount) {
            val range = -1..1
            val pos = pos.add(range.random(), range.random(), range.random())
            if (world.getBlockState(pos).block == Blocks.DEEPSLATE) {
                world.setBlockState(pos, deepslateAdamantiteOre.defaultState)
                count++
            }
        }
    }
    return count
}