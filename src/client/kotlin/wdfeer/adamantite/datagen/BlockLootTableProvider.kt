package wdfeer.adamantite.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Item
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.MatchToolLootCondition
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.predicate.NumberRange
import net.minecraft.predicate.item.EnchantmentPredicate
import net.minecraft.predicate.item.EnchantmentsPredicate
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.predicate.item.ItemSubPredicateTypes
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import wdfeer.adamantite.*
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricBlockLootTableProvider(dataOutput, registryLookup) {
    private fun FabricBlockLootTableProvider.addOre(block: Block, item: Item) {
        val enchantments = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
        val fortune = enchantments.getOrThrow(Enchantments.FORTUNE)
        val silkTouch = enchantments.getOrThrow(Enchantments.SILK_TOUCH)

        // entry without silk touch, with fortune bonuses
        val itemEntry = ItemEntry.builder(item)
        itemEntry.apply(ApplyBonusLootFunction.oreDrops(fortune))

        // silk touch entry (demon code)
        val silkTouchItemEntry = ItemEntry.builder(block)
        val silkTouchIntRange = NumberRange.IntRange.atLeast(1)
        val silkTouchSubSubPredicate = EnchantmentPredicate(silkTouch, silkTouchIntRange)
        val silkTouchSubPredicate = EnchantmentsPredicate.enchantments(listOf(silkTouchSubSubPredicate))
        val silkTouchPredicate =
            ItemPredicate.Builder.create().subPredicate(ItemSubPredicateTypes.ENCHANTMENTS, silkTouchSubPredicate)
        val silkTouchCondition = MatchToolLootCondition.builder(silkTouchPredicate)
        silkTouchItemEntry.conditionally(silkTouchCondition)

        // combine the two entries
        val entry = AlternativeEntry.builder(silkTouchItemEntry, itemEntry)

        val oneProvider = ConstantLootNumberProvider.create(1f)
        val lootPool = LootPool.builder().with(entry).rolls(oneProvider)

        val lootTable = LootTable.builder().pool(lootPool)

        addDrop(block, lootTable)
    }

    override fun generate() {
        addOre(deepslateAdamantiteOre, adamantiteNugget)
        addOre(deepslateTitaniumOre, titaniumNugget)
        addDrop(adamantiteBlock, adamantiteBlock)
        addDrop(titaniumBlock, titaniumBlock)
    }
}