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
val adamantiteUpgradeTemplate = SmithingTemplateItem(
    Text.literal("Netherite Equipment").formatted(Formatting.BLUE),
    Text.literal("Adamantite Ingot").formatted(Formatting.BLUE),
    Text.literal("Adamantite Upgrade").formatted(Formatting.GRAY),
    Text.literal("Add netherite armor, weapon, or tool"),
    Text.literal("Add Adamantite Ingot"),
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
).register("adamantite_upgrade_template")

private val adamantiteToolMaterial = object : ToolMaterial {
    override fun getDurability(): Int = ToolMaterials.NETHERITE.durability
    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.NETHERITE.miningSpeedMultiplier + 1
    override fun getAttackDamage(): Float = ToolMaterials.NETHERITE.attackDamage + 1
    override fun getMiningLevel(): Int = ToolMaterials.NETHERITE.miningLevel
    override fun getEnchantability(): Int = ToolMaterials.NETHERITE.enchantability
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(adamantiteIngot)
}

val adamantiteSword = SwordItem(adamantiteToolMaterial, 3, -2.4f, FabricItemSettings()).register("adamantite_sword")
val adamantiteShovel = ShovelItem(adamantiteToolMaterial, 1.5f, -3f, FabricItemSettings()).register("adamantite_shovel")
val adamantitePickaxe =
    PickaxeItem(adamantiteToolMaterial, 1, -2.8f, FabricItemSettings()).register("adamantite_pickaxe")
val adamantiteAxe = AxeItem(adamantiteToolMaterial, 5f, -3f, FabricItemSettings()).register("adamantite_axe")
val adamantiteHoe = HoeItem(adamantiteToolMaterial, -4, 0f, FabricItemSettings()).register("adamantite_hoe")

private val adamantiteArmorMaterial = object : ArmorMaterial {
    override fun getDurability(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getDurability(type)
    override fun getProtection(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getProtection(type)
    override fun getEnchantability(): Int = ArmorMaterials.NETHERITE.enchantability
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

private fun <T : Item> T.register(name: String): T {
    val id = Adamantite.id(name)
    return Registry.register(Registries.ITEM, id, this)
}

fun initItems() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register { content: FabricItemGroupEntries ->
        content.add(adamantiteIngot)
        content.add(titaniumIngot)
        content.add(adamantiteUpgradeTemplate)
    }
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { content: FabricItemGroupEntries ->
        content.add(adamantiteSword)

        content.add(adamantiteHelmet)
        content.add(adamantiteChestplate)
        content.add(adamantiteLeggings)
        content.add(adamantiteBoots)
    }
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register { content: FabricItemGroupEntries ->
        content.add(adamantiteShovel)
        content.add(adamantitePickaxe)
        content.add(adamantiteAxe)
        content.add(adamantiteHoe)
    }
}
