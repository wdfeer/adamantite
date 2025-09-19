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
        // TODO: get properties from the config file or generate new file
    }
}