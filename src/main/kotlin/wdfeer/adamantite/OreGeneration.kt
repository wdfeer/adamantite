package wdfeer.adamantite

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.world.GameMode
import kotlin.random.Random

private const val INTERVAL = 1000
private const val TRIES_PER_CHUNK = 10
private val HEIGHT_RANGE = -58..-18

val generated: MutableSet<ChunkPos> = mutableSetOf() // TODO: make persistent

fun initOreGeneration() {
    ServerTickEvents.END_SERVER_TICK.register { server ->
        val shouldGenerate = true // TODO: set based on Ender Dragon defeat status
        if (shouldGenerate) {
            val world = server.overworld

            if (world.time % INTERVAL != 0L) return@register

            val chunksToGenerate =
                world.players.filter { it.isAlive && it.interactionManager.gameMode != GameMode.SPECTATOR }
                    .map { it.chunkPos }
                    .filter { world.chunkManager.isChunkLoaded(it.x, it.z) } - generated

            chunksToGenerate.forEach {
                generated.add(it)
                repeat(TRIES_PER_CHUNK) { _ ->
                    val pos = BlockPos(it.x * 16 + Random.nextInt(16), HEIGHT_RANGE.random(), it.x + Random.nextInt(16))
                    if (world.getBlockState(pos).block == Blocks.DEEPSLATE)
                        world.setBlockState(pos, deepslateAdamantiteOre.defaultState)
                }
            }
        }
    }
}