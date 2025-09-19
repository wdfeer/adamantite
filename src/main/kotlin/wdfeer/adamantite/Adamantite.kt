package wdfeer.adamantite

import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Adamantite : ModInitializer {
    val logger: Logger = LoggerFactory.getLogger("adamantite")

    fun id(name: String): Identifier = Identifier("adamantite", name)

    override fun onInitialize() {
        Config.load()
        initBlocks()
        initItems()
        initOreGeneration()
        initLootTables()
        logger.info("Adamantite initialized!")
    }
}