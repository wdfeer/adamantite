package wdfeer.adamantite

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

val deepslateAdamantiteOre = Block(
    FabricBlockSettings.create().hardness(Blocks.DEEPSLATE_DIAMOND_ORE.hardness)
).register("deepslate_adamantite_ore")
val deepslateTitaniumOre = Block(
    FabricBlockSettings.create().hardness(Blocks.DEEPSLATE_DIAMOND_ORE.hardness)
).register("deepslate_titanium_ore")

val adamantiteBlock = Block(
    FabricBlockSettings.create().hardness(Blocks.NETHERITE_BLOCK.hardness)
).register("adamantite_block")
val titaniumBlock = Block(
    FabricBlockSettings.create().hardness(Blocks.NETHERITE_BLOCK.hardness)
).register("titanium_block")

private fun Block.register(name: String): Block {
    val id = Adamantite.id(name)
    val blockItem = BlockItem(this, FabricItemSettings())
    Registry.register<Item?, BlockItem?>(Registries.ITEM, id, blockItem)
    return Registry.register(Registries.BLOCK, id, this)
}

fun initBlocks() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register { content: FabricItemGroupEntries ->
        content.add(deepslateAdamantiteOre)
        content.add(deepslateTitaniumOre)
    }
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register { content: FabricItemGroupEntries ->
        content.add(adamantiteBlock)
        content.add(titaniumBlock)
    }
}