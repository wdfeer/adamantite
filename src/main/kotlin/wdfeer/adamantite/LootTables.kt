package wdfeer.adamantite

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTables
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.util.Identifier

fun initLootTables() {
    LootTableEvents.MODIFY.register { _, _, id, builder, source ->
        if (!source.isBuiltin) return@register

        val chance = chances[id] ?: return@register

        builder.pool(
            LootPool.builder().with(ItemEntry.builder(adamantiteUpgradeTemplate))
                .conditionally(RandomChanceLootCondition.builder(chance))
        )
    }
}

private val chances: Map<Identifier, Float> = mapOf(
    LootTables.ANCIENT_CITY_CHEST to 0.15f,
    LootTables.WOODLAND_MANSION_CHEST to 0.15f,
    LootTables.DESERT_PYRAMID_CHEST to 0.02f,
    LootTables.JUNGLE_TEMPLE_CHEST to 0.02f,
    LootTables.ABANDONED_MINESHAFT_CHEST to 0.02f,
)
