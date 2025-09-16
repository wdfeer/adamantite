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
                world.players.filter { it.isAlive && it.interactionManager.gameMode != GameMode.SPECTATOR }.map {
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
                }.flatten().filter { world.chunkManager.isChunkLoaded(it.x, it.z) } - generated

            chunksToGenerate.forEach {
                generated.add(it)
                var count = 0
                repeat(TRIES_PER_CHUNK) { _ ->
                    val pos = BlockPos(it.x * 16 + Random.nextInt(16), HEIGHT_RANGE.random(), it.x + Random.nextInt(16))
                    if (world.getBlockState(pos).block == Blocks.DEEPSLATE) {
                        world.setBlockState(pos, deepslateAdamantiteOre.defaultState)
                        count++
                    }
                }
                Adamantite.logger.info("Generated $count deepslate adamantite ore blocks in the chunk ${it.x},${it.z}.")
            }
        }
    }
}