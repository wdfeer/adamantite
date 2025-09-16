package wdfeer.adamantite

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.*
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

private val adamantiteMaterial = object : ToolMaterial {
    override fun getDurability(): Int = ToolMaterials.NETHERITE.durability
    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.NETHERITE.miningSpeedMultiplier + 1
    override fun getAttackDamage(): Float = ToolMaterials.NETHERITE.attackDamage + 1
    override fun getMiningLevel(): Int = ToolMaterials.NETHERITE.miningLevel
    override fun getEnchantability(): Int = ToolMaterials.NETHERITE.enchantability
    override fun getRepairIngredient(): Ingredient =
        ToolMaterials.NETHERITE.repairIngredient // TODO: use adamantite ingot
}

val adamantiteSword =
    SwordItem(adamantiteMaterial, 3, -2.4f, FabricItemSettings()).register("adamantite_sword")
val adamantiteShovel =
    ShovelItem(adamantiteMaterial, 1.5f, -3f, FabricItemSettings()).register("adamantite_shovel")
val adamantitePickaxe =
    PickaxeItem(adamantiteMaterial, 1, -2.8f, FabricItemSettings()).register("adamantite_pickaxe")
val adamantiteAxe =
    AxeItem(adamantiteMaterial, 5f, -3f, FabricItemSettings()).register("adamantite_axe")
val adamantiteHoe =
    HoeItem(adamantiteMaterial, -4, 0f, FabricItemSettings()).register("adamantite_hoe")

private fun Item.register(name: String): Item {
    val id = Adamantite.id(name)
    return Registry.register(Registries.ITEM, id, this)
}

fun initItems() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { content: FabricItemGroupEntries ->
        content.add(adamantiteSword)
    }
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register { content: FabricItemGroupEntries ->
        content.add(adamantiteShovel)
        content.add(adamantitePickaxe)
        content.add(adamantiteAxe)
        content.add(adamantiteHoe)
    }
}
