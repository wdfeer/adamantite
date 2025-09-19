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
            val map: Map<String, String> = lines.map { line ->
                line.split("=").map { it.trim() }
            }.associate { it[0] to it[1] }
            enableGeneration = map["enableGeneration"]?.toBooleanStrictOrNull() ?: run {
                Adamantite.logger.error("Config \"enableGeneration\" property invalid! Using default.")
                enableGeneration
            }
            interval = map["interval"]?.toIntOrNull() ?: run {
                Adamantite.logger.error("Config \"interval\" property invalid! Using default.")
                interval
            }
            triesPerChunk = map["triesPerChunk"]?.toIntOrNull() ?: run {
                Adamantite.logger.error("Config \"triesPerChunk\" property invalid! Using default.")
                triesPerChunk
            }
            minHeight = map["minHeight"]?.toIntOrNull() ?: run {
                Adamantite.logger.error("Config \"minHeight\" property invalid! Using default.")
                minHeight
            }
            maxHeight = map["maxHeight"]?.toIntOrNull() ?: run {
                Adamantite.logger.error("Config \"maxHeight\" property invalid! Using default.")
                maxHeight
            }
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