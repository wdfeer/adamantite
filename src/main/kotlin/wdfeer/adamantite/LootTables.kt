package wdfeer.adamantite

import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.item.Item
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.registry.RegistryKey

fun initLootTables() {
    LootTableEvents.MODIFY.register { key, builder, _, _ ->
        val pair = chances[key] ?: return@register

        builder.pool(
            LootPool.builder().with(ItemEntry.builder(pair.first))
                .conditionally(RandomChanceLootCondition.builder(pair.second))
        )
    }
}

private val chances: Map<RegistryKey<LootTable>, Pair<Item, Float>> = mapOf(
    LootTables.END_CITY_TREASURE_CHEST to (adamantiteUpgradeTemplate to 0.2f),
    LootTables.STRONGHOLD_CORRIDOR_CHEST to (adamantiteUpgradeTemplate to 0.04f),
    LootTables.STRONGHOLD_CROSSING_CHEST to (adamantiteUpgradeTemplate to 0.04f),

    LootTables.ANCIENT_CITY_CHEST to (titaniumUpgradeTemplate to 0.2f),
)
