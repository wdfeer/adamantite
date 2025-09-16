package wdfeer.adamantite

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.util.math.ChunkPos
import net.minecraft.world.GameMode

val generated: MutableSet<ChunkPos> = mutableSetOf() // TODO: make persistent

fun initOreGeneration() {
    ServerTickEvents.END_SERVER_TICK.register { server ->
        val shouldGenerate = true // TODO: set based on Ender Dragon defeat status
        if (shouldGenerate) {
            val world = server.overworld

            val chunksToGenerate =
                world.players.filter { it.isAlive && it.interactionManager.gameMode != GameMode.SPECTATOR }
                    .map { it.chunkPos } - generated

            chunksToGenerate.forEach {
                generated.add(it)
                // TODO: generate ores
            }
        }
    }
}