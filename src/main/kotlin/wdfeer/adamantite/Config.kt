package wdfeer.adamantite

import net.fabricmc.loader.api.FabricLoader

object Config {
    var enableGeneration: Boolean = true
    var interval: Int = 220
    var triesPerChunk: Int = 3
    var minHeight: Int = -60
    var maxHeight: Int = -40

    fun load() {
        val path = FabricLoader.getInstance().configDir.resolve("adamantite.cfg")
        val file = path.toFile()
        if (file.exists()) {
            val lines = file.readLines()
            // TODO: read properties from lines
        } else {
            file.writeText(defaultConfig)
        }
    }

    private val defaultConfig: String = """enableGeneration = $enableGeneration
        |interval = $interval
        |triesPerChunk = $triesPerChunk
        |minHeight = $minHeight
        |maxHeight = $maxHeight
    """.trimMargin()
}