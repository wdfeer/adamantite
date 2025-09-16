package wdfeer.adamantite

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

val deepslateAdamantiteOre: Block = Registry.register(
    Registries.BLOCK, Adamantite.id("deepslate_adamantite_ore"),
    Block(FabricBlockSettings.create())
)

fun initBlocks() {
    deepslateAdamantiteOre
}