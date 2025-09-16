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
    override fun getRepairIngredient(): Ingredient = ToolMaterials.NETHERITE.repairIngredient // TODO: use adamantite ingot
}

val adamantiteSword =
    SwordItem(adamantiteMaterial, 3, -2.4f, FabricItemSettings()).register("adamantite_sword")

private fun Item.register(name: String): Item {
    val id = Adamantite.id(name)
    return Registry.register(Registries.ITEM, id, this)
}

fun initItems() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { content: FabricItemGroupEntries ->
        content.add(adamantiteSword)
    }
}
