package wdfeer.adamantite

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.Block
import net.minecraft.item.AxeItem
import net.minecraft.item.HoeItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ShovelItem
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.item.ToolMaterials
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey

fun initTools() {
    // also required, to init this file!

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

// Adamantite Tools
private val adamantiteToolMaterial = object : ToolMaterial {
    override fun getDurability(): Int = ToolMaterials.NETHERITE.durability
    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.NETHERITE.miningSpeedMultiplier + 1
    override fun getAttackDamage(): Float = ToolMaterials.NETHERITE.attackDamage + 1
    override fun getInverseTag(): TagKey<Block> {
        // blocks requiring a higher mining level
        return BlockTags.AIR
    }
    override fun getEnchantability(): Int = ToolMaterials.NETHERITE.enchantability + 1
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(adamantiteIngot)
}
val adamantiteSword = SwordItem(adamantiteToolMaterial, Item.Settings()).register("adamantite_sword")
val adamantiteShovel = ShovelItem(adamantiteToolMaterial, Item.Settings()).register("adamantite_shovel")
val adamantitePickaxe = PickaxeItem(adamantiteToolMaterial, Item.Settings()).register("adamantite_pickaxe")
val adamantiteAxe = AxeItem(adamantiteToolMaterial, Item.Settings()).register("adamantite_axe")
val adamantiteHoe = HoeItem(adamantiteToolMaterial, Item.Settings()).register("adamantite_hoe")


// Titanium Tools
private val titaniumToolMaterial = object : ToolMaterial {
    override fun getDurability(): Int = ToolMaterials.NETHERITE.durability
    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.NETHERITE.miningSpeedMultiplier + 1
    override fun getAttackDamage(): Float = ToolMaterials.NETHERITE.attackDamage + 1
    override fun getInverseTag(): TagKey<Block?>? {
        // blocks requiring a higher mining level
        return BlockTags.AIR
    }
    override fun getEnchantability(): Int = ToolMaterials.NETHERITE.enchantability + 1
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(titaniumIngot)
}
val titaniumSword = SwordItem(titaniumToolMaterial, Item.Settings()).register("titanium_sword")
val titaniumShovel = ShovelItem(titaniumToolMaterial, Item.Settings()).register("titanium_shovel")
val titaniumPickaxe = PickaxeItem(titaniumToolMaterial, Item.Settings()).register("titanium_pickaxe")
val titaniumAxe = AxeItem(titaniumToolMaterial, Item.Settings()).register("titanium_axe")
val titaniumHoe = HoeItem(titaniumToolMaterial, Item.Settings()).register("titanium_hoe")