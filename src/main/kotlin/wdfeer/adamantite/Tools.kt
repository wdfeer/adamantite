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

// === Adamantite Tools ===

private object AdamantiteToolMaterial : ToolMaterial {
    override fun getDurability(): Int = ToolMaterials.NETHERITE.durability
    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.NETHERITE.miningSpeedMultiplier + 1
    override fun getAttackDamage(): Float = ToolMaterials.NETHERITE.attackDamage + 1
    override fun getInverseTag(): TagKey<Block> {
        // blocks requiring a higher mining level
        return BlockTags.INCORRECT_FOR_NETHERITE_TOOL
    }

    override fun getEnchantability(): Int = ToolMaterials.NETHERITE.enchantability + 1
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(adamantiteIngot)
}

val adamantiteSword = SwordItem(
    AdamantiteToolMaterial,
    Item.Settings().attributeModifiers(
        SwordItem.createAttributeModifiers(
            AdamantiteToolMaterial,
            3,
            -2.4f
        )
    )
).register("adamantite_sword")

val adamantiteShovel = ShovelItem(
    AdamantiteToolMaterial,
    Item.Settings().attributeModifiers(
        ShovelItem.createAttributeModifiers(
            AdamantiteToolMaterial,
            1.5f,
            -3f
        )
    )
).register("adamantite_shovel")

val adamantitePickaxe = PickaxeItem(
    AdamantiteToolMaterial,
    Item.Settings().attributeModifiers(
        PickaxeItem.createAttributeModifiers(
            AdamantiteToolMaterial,
            1f,
            -2.8f
        )
    )
).register("adamantite_pickaxe")

val adamantiteAxe = AxeItem(
    AdamantiteToolMaterial,
    Item.Settings().attributeModifiers(
        AxeItem.createAttributeModifiers(
            AdamantiteToolMaterial,
            5f,
            -3f
        )
    )
).register("adamantite_axe")

val adamantiteHoe = HoeItem(
    AdamantiteToolMaterial,
    Item.Settings().attributeModifiers(
        HoeItem.createAttributeModifiers(
            AdamantiteToolMaterial,
            -4f,
            0f
        )
    )
).register("adamantite_hoe")

// === Titanium Tools ===

private object TitaniumToolMaterial : ToolMaterial {
    override fun getDurability(): Int = ToolMaterials.NETHERITE.durability
    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.NETHERITE.miningSpeedMultiplier + 1
    override fun getAttackDamage(): Float = ToolMaterials.NETHERITE.attackDamage + 1
    override fun getInverseTag(): TagKey<Block?>? {
        // blocks requiring a higher mining level
        return BlockTags.INCORRECT_FOR_NETHERITE_TOOL
    }

    override fun getEnchantability(): Int = ToolMaterials.NETHERITE.enchantability + 1
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(titaniumIngot)
}

val titaniumSword = SwordItem(
    TitaniumToolMaterial,
    Item.Settings().attributeModifiers(
        SwordItem.createAttributeModifiers(
            TitaniumToolMaterial,
            3,
            -2.4f
        )
    )
).register("titanium_sword")

val titaniumShovel = ShovelItem(
    TitaniumToolMaterial,
    Item.Settings().attributeModifiers(
        ShovelItem.createAttributeModifiers(
            TitaniumToolMaterial,
            1.5f,
            -3f
        )
    )
).register("titanium_shovel")

val titaniumPickaxe = PickaxeItem(
    TitaniumToolMaterial,
    Item.Settings().attributeModifiers(
        PickaxeItem.createAttributeModifiers(
            TitaniumToolMaterial,
            1f,
            -2.8f
        )
    )
).register("titanium_pickaxe")

val titaniumAxe = AxeItem(
    TitaniumToolMaterial,
    Item.Settings().attributeModifiers(
        AxeItem.createAttributeModifiers(
            TitaniumToolMaterial,
            5f,
            -3f
        )
    )
).register("titanium_axe")

val titaniumHoe = HoeItem(
    TitaniumToolMaterial,
    Item.Settings().attributeModifiers(
        HoeItem.createAttributeModifiers(
            TitaniumToolMaterial,
            -4f,
            0f
        )
    )
).register("titanium_hoe")