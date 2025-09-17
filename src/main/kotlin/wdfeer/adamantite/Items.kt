package wdfeer.adamantite

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.*
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent

val adamantiteIngot = Item(FabricItemSettings()).register("adamantite_ingot")
val adamantiteUpgradeTemplate = Item(FabricItemSettings()).register("adamantite_upgrade_template")

private val adamantiteToolMaterial = object : ToolMaterial {
    override fun getDurability(): Int = ToolMaterials.NETHERITE.durability
    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.NETHERITE.miningSpeedMultiplier + 1
    override fun getAttackDamage(): Float = ToolMaterials.NETHERITE.attackDamage + 1
    override fun getMiningLevel(): Int = ToolMaterials.NETHERITE.miningLevel
    override fun getEnchantability(): Int = ToolMaterials.NETHERITE.enchantability
    override fun getRepairIngredient(): Ingredient =
        Ingredient.ofItems(adamantiteIngot)
}

val adamantiteSword =
    SwordItem(adamantiteToolMaterial, 3, -2.4f, FabricItemSettings()).register("adamantite_sword")
val adamantiteShovel =
    ShovelItem(adamantiteToolMaterial, 1.5f, -3f, FabricItemSettings()).register("adamantite_shovel")
val adamantitePickaxe =
    PickaxeItem(adamantiteToolMaterial, 1, -2.8f, FabricItemSettings()).register("adamantite_pickaxe")
val adamantiteAxe =
    AxeItem(adamantiteToolMaterial, 5f, -3f, FabricItemSettings()).register("adamantite_axe")
val adamantiteHoe =
    HoeItem(adamantiteToolMaterial, -4, 0f, FabricItemSettings()).register("adamantite_hoe")

private val adamantiteArmorMaterial = object : ArmorMaterial {
    override fun getDurability(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getDurability(type)
    override fun getProtection(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getProtection(type)
    override fun getEnchantability(): Int = ArmorMaterials.NETHERITE.enchantability
    override fun getEquipSound(): SoundEvent = ArmorMaterials.IRON.equipSound
    override fun getName(): String = "adamantite"
    override fun getToughness(): Float = ArmorMaterials.NETHERITE.toughness + 1
    override fun getKnockbackResistance(): Float = ArmorMaterials.NETHERITE.knockbackResistance
    override fun getRepairIngredient(): Ingredient =
        Ingredient.ofItems(adamantiteIngot)
}

val adamantiteHelmet =
    ArmorItem(adamantiteArmorMaterial, ArmorItem.Type.HELMET, FabricItemSettings()).register("adamantite_helmet")
val adamantiteChestplate =
    ArmorItem(adamantiteArmorMaterial, ArmorItem.Type.CHESTPLATE, FabricItemSettings()).register("adamantite_chestplate")
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
