package wdfeer.adamantite

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier

val adamantiteIngot = Item(Item.Settings()).register("adamantite_ingot")
val titaniumIngot = Item(Item.Settings()).register("titanium_ingot")

private fun smithingTemplate(name: String) = SmithingTemplateItem(
    Text.literal("Netherite Equipment").formatted(Formatting.BLUE),
    Text.literal("$name Ingot").formatted(Formatting.BLUE),
    Text.literal("$name Upgrade").formatted(Formatting.GRAY),
    Text.literal("Add netherite armor, weapon, or tool"),
    Text.literal("Add $name Ingot"),
    listOf(
        Identifier.of("item/empty_armor_slot_helmet"),
        Identifier.of("item/empty_armor_slot_chestplate"),
        Identifier.of("item/empty_armor_slot_leggings"),
        Identifier.of("item/empty_armor_slot_boots"),
        Identifier.of("item/empty_slot_hoe"),
        Identifier.of("item/empty_slot_axe"),
        Identifier.of("item/empty_slot_sword"),
        Identifier.of("item/empty_slot_shovel"),
        Identifier.of("item/empty_slot_pickaxe")
    ),
    listOf(Identifier.of("item/empty_slot_ingot"))
)

val adamantiteUpgradeTemplate = smithingTemplate("Adamantite").register("adamantite_upgrade_template")
val titaniumUpgradeTemplate = smithingTemplate("Titanium").register("titanium_upgrade_template")

val adamantiteNugget = Item(Item.Settings()).register("adamantite_nugget")
val titaniumNugget = Item(Item.Settings()).register("titanium_nugget")

fun <T : Item> T.register(name: String): T {
    val id = Adamantite.id(name)
    return Registry.register(Registries.ITEM, id, this)
}

fun initItems() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register { content: FabricItemGroupEntries ->
        content.add(adamantiteIngot)
        content.add(adamantiteNugget)
        content.add(adamantiteUpgradeTemplate)
        content.add(titaniumIngot)
        content.add(titaniumNugget)
        content.add(titaniumUpgradeTemplate)
    }

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { content: FabricItemGroupEntries ->
        content.add(adamantiteSword)
        content.add(adamantiteHelmet)
        content.add(adamantiteChestplate)
        content.add(adamantiteLeggings)
        content.add(adamantiteBoots)
        content.add(titaniumSword)
        content.add(titaniumHelmet)
        content.add(titaniumChestplate)
        content.add(titaniumLeggings)
        content.add(titaniumBoots)
    }

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register { content: FabricItemGroupEntries ->
        content.add(adamantiteShovel)
        content.add(adamantitePickaxe)
        content.add(adamantiteAxe)
        content.add(adamantiteHoe)
        content.add(titaniumShovel)
        content.add(titaniumPickaxe)
        content.add(titaniumAxe)
        content.add(titaniumHoe)
    }

    initCrossbows()
}
