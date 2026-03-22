package wdfeer.adamantite

import net.fabric_extras.ranged_weapon.api.CustomCrossbow
import net.fabric_extras.ranged_weapon.api.RangedConfig
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.recipe.Ingredient

// only assign crossbows on init, if ranged_weapon_api loaded
var adamantiteCrossbow: Item? = null
var titaniumCrossbow: Item? = null

fun initCrossbows() {
    if (FabricLoader.getInstance().isModLoaded("ranged_weapon_api")) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { content: FabricItemGroupEntries ->
            content.add(adamantiteCrossbow)
            content.add(titaniumCrossbow)
        }

        adamantiteCrossbow = CustomCrossbow(
            Item.Settings().maxCount(1).maxDamage(930), RangedConfig(
                RangedConfig.CROSSBOW.damage,
                RangedConfig.CROSSBOW.pull_time_bonus - 0.25f,
                RangedConfig.CROSSBOW.velocity_bonus
            )
        ) { Ingredient.ofItems(adamantiteIngot) }.register("adamantite_crossbow")

        titaniumCrossbow = CustomCrossbow(
            Item.Settings().maxCount(1).maxDamage(930), RangedConfig(
                RangedConfig.CROSSBOW.damage + 2f,
                RangedConfig.CROSSBOW.pull_time_bonus,
                RangedConfig.CROSSBOW.velocity_bonus
            )
        ) { Ingredient.ofItems(titaniumIngot) }.register("titanium_crossbow")
    }
}