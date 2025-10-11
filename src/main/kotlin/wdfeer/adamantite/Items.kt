package wdfeer.adamantite

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.*
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier

val adamantiteIngot = Item(FabricItemSettings()).register("adamantite_ingot")
val titaniumIngot = Item(FabricItemSettings()).register("titanium_ingot")

private fun smithingTemplate(name: String) = SmithingTemplateItem(
    Text.literal("Netherite Equipment").formatted(Formatting.BLUE),
    Text.literal("$name Ingot").formatted(Formatting.BLUE),
    Text.literal("$name Upgrade").formatted(Formatting.GRAY),
    Text.literal("Add netherite armor, weapon, or tool"),
    Text.literal("Add $name Ingot"),
    listOf(
        Identifier("item/empty_armor_slot_helmet"),
        Identifier("item/empty_armor_slot_chestplate"),
        Identifier("item/empty_armor_slot_leggings"),
        Identifier("item/empty_armor_slot_boots"),
        Identifier("item/empty_slot_hoe"),
        Identifier("item/empty_slot_axe"),
        Identifier("item/empty_slot_sword"),
        Identifier("item/empty_slot_shovel"),
        Identifier("item/empty_slot_pickaxe")
    ),
    listOf(Identifier("item/empty_slot_ingot"))
)

val adamantiteUpgradeTemplate = smithingTemplate("Adamantite").register("adamantite_upgrade_template")
val titaniumUpgradeTemplate = smithingTemplate("Titanium").register("titanium_upgrade_template")

private val adamantiteToolMaterial = object : ToolMaterial {
    override fun getDurability(): Int = ToolMaterials.NETHERITE.durability
    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.NETHERITE.miningSpeedMultiplier + 1
    override fun getAttackDamage(): Float = ToolMaterials.NETHERITE.attackDamage + 1
    override fun getMiningLevel(): Int = ToolMaterials.NETHERITE.miningLevel
    override fun getEnchantability(): Int = ToolMaterials.NETHERITE.enchantability + 1
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(adamantiteIngot)
}

val adamantiteSword = SwordItem(adamantiteToolMaterial, 3, -2.4f, FabricItemSettings()).register("adamantite_sword")
val adamantiteShovel = ShovelItem(adamantiteToolMaterial, 1.5f, -3f, FabricItemSettings()).register("adamantite_shovel")
val adamantitePickaxe =
    PickaxeItem(adamantiteToolMaterial, 1, -2.8f, FabricItemSettings()).register("adamantite_pickaxe")
val adamantiteAxe = AxeItem(adamantiteToolMaterial, 5f, -3f, FabricItemSettings()).register("adamantite_axe")
val adamantiteHoe = HoeItem(adamantiteToolMaterial, -4, 0f, FabricItemSettings()).register("adamantite_hoe")

private val titaniumToolMaterial = object : ToolMaterial {
    override fun getDurability(): Int = ToolMaterials.NETHERITE.durability
    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.NETHERITE.miningSpeedMultiplier + 1
    override fun getAttackDamage(): Float = ToolMaterials.NETHERITE.attackDamage + 1
    override fun getMiningLevel(): Int = ToolMaterials.NETHERITE.miningLevel
    override fun getEnchantability(): Int = ToolMaterials.NETHERITE.enchantability + 1
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(titaniumIngot)
}

val titaniumSword = SwordItem(titaniumToolMaterial, 3, -2.4f, FabricItemSettings()).register("titanium_sword")
val titaniumShovel = ShovelItem(titaniumToolMaterial, 1.5f, -3f, FabricItemSettings()).register("titanium_shovel")
val titaniumPickaxe =
    PickaxeItem(titaniumToolMaterial, 1, -2.8f, FabricItemSettings()).register("titanium_pickaxe")
val titaniumAxe = AxeItem(titaniumToolMaterial, 5f, -3f, FabricItemSettings()).register("titanium_axe")
val titaniumHoe = HoeItem(titaniumToolMaterial, -4, 0f, FabricItemSettings()).register("titanium_hoe")

private val adamantiteArmorMaterial = object : ArmorMaterial {
    override fun getDurability(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getDurability(type)
    override fun getProtection(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getProtection(type)
    override fun getEnchantability(): Int = ArmorMaterials.NETHERITE.enchantability + 1
    override fun getEquipSound(): SoundEvent = ArmorMaterials.IRON.equipSound
    override fun getName(): String = "adamantite"
    override fun getToughness(): Float = ArmorMaterials.NETHERITE.toughness + 1
    override fun getKnockbackResistance(): Float = ArmorMaterials.NETHERITE.knockbackResistance
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(adamantiteIngot)
}

val adamantiteHelmet =
    ArmorItem(adamantiteArmorMaterial, ArmorItem.Type.HELMET, FabricItemSettings()).register("adamantite_helmet")
val adamantiteChestplate = ArmorItem(
    adamantiteArmorMaterial, ArmorItem.Type.CHESTPLATE, FabricItemSettings()
).register("adamantite_chestplate")
val adamantiteLeggings =
    ArmorItem(adamantiteArmorMaterial, ArmorItem.Type.LEGGINGS, FabricItemSettings()).register("adamantite_leggings")
val adamantiteBoots =
    ArmorItem(adamantiteArmorMaterial, ArmorItem.Type.BOOTS, FabricItemSettings()).register("adamantite_boots")

private val titaniumArmorMaterial = object : ArmorMaterial {
    override fun getDurability(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getDurability(type)
    override fun getProtection(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getProtection(type)
    override fun getEnchantability(): Int = ArmorMaterials.NETHERITE.enchantability + 1
    override fun getEquipSound(): SoundEvent = ArmorMaterials.IRON.equipSound
    override fun getName(): String = "titanium"
    override fun getToughness(): Float = ArmorMaterials.NETHERITE.toughness + 1
    override fun getKnockbackResistance(): Float = ArmorMaterials.NETHERITE.knockbackResistance
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(titaniumIngot)
}

val titaniumHelmet =
    ArmorItem(titaniumArmorMaterial, ArmorItem.Type.HELMET, FabricItemSettings()).register("titanium_helmet")
val titaniumChestplate = ArmorItem(
    titaniumArmorMaterial, ArmorItem.Type.CHESTPLATE, FabricItemSettings()
).register("titanium_chestplate")
val titaniumLeggings =
    ArmorItem(titaniumArmorMaterial, ArmorItem.Type.LEGGINGS, FabricItemSettings()).register("titanium_leggings")
val titaniumBoots =
    ArmorItem(titaniumArmorMaterial, ArmorItem.Type.BOOTS, FabricItemSettings()).register("titanium_boots")

val adamantiteCrossbow = CrossbowItem(FabricItemSettings()).register("adamantite_crossbow")
val titaniumCrossbow = CrossbowItem(FabricItemSettings()).register("titanium_crossbow")

private fun <T : Item> T.register(name: String): T {
    val id = Adamantite.id(name)
    return Registry.register(Registries.ITEM, id, this)
}

fun initItems() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register { content: FabricItemGroupEntries ->
        content.add(adamantiteIngot)
        content.add(adamantiteUpgradeTemplate)
        content.add(titaniumIngot)
        content.add(titaniumUpgradeTemplate)
    }
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { content: FabricItemGroupEntries ->
        content.add(adamantiteSword)
        content.add(adamantiteCrossbow)
        content.add(adamantiteHelmet)
        content.add(adamantiteChestplate)
        content.add(adamantiteLeggings)
        content.add(adamantiteBoots)
        content.add(titaniumSword)
        content.add(titaniumCrossbow)
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
}
