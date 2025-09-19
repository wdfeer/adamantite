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
            enableGeneration = map["enableGeneration"]?.toBooleanStrictOrNull() ?: enableGeneration
            interval = map["interval"]?.toIntOrNull() ?: interval
            triesPerChunk = map["triesPerChunk"]?.toIntOrNull() ?: triesPerChunk
            minHeight = map["minHeight"]?.toIntOrNull() ?: minHeight
            maxHeight = map["maxHeight"]?.toIntOrNull() ?: maxHeight
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