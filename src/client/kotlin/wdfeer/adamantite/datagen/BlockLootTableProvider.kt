package wdfeer.adamantite.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Item
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import wdfeer.adamantite.*
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricBlockLootTableProvider(dataOutput, registryLookup) {
    fun FabricBlockLootTableProvider.addWithFortune(block: Block, item: Item) {
        val enchantments = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
        val fortune = enchantments.getOrThrow(Enchantments.FORTUNE)

        val itemEntryB = ItemEntry.builder(item)

        val baseNumberP = ConstantLootNumberProvider.create(1f)
        val lootPoolB = LootPool.builder().with(itemEntryB).rolls(baseNumberP)

        val lootTableB = LootTable.builder().pool(lootPoolB)
        lootTableB.apply(ApplyBonusLootFunction.oreDrops(fortune))

        addDrop(block, lootTableB)
    }

    override fun generate() {
        addWithFortune(deepslateAdamantiteOre, adamantiteNugget)
        addWithFortune(deepslateTitaniumOre, titaniumNugget)
        addDrop(adamantiteBlock, adamantiteBlock)
        addDrop(titaniumBlock, titaniumBlock)
    }
}