package wdfeer.adamantite.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.book.RecipeCategory
import wdfeer.adamantite.*
import java.util.function.Consumer

class RecipeProvider(dataOutput: FabricDataOutput) : FabricRecipeProvider(dataOutput) {
    private fun offerAdamantiteUpgradeRecipe(
        exporter: Consumer<RecipeJsonProvider>,
        input: Item,
        category: RecipeCategory,
        result: Item
    ) { // idk just copied from VanillaRecipeProvider
        SmithingTransformRecipeJsonBuilder.create(
            Ingredient.ofItems(*arrayOf<ItemConvertible>(adamantiteUpgradeTemplate)),
            Ingredient.ofItems(*arrayOf<ItemConvertible>(input)),
            Ingredient.ofItems(*arrayOf<ItemConvertible>(adamantiteIngot)),
            category,
            result
        ).criterion("has_adamantite_ingot", conditionsFromItem(Items.NETHERITE_INGOT))
            .offerTo(exporter, getItemPath(result) + "_smithing")
    }

    private fun offerTitaniumUpgradeRecipe(
        exporter: Consumer<RecipeJsonProvider>,
        input: Item,
        category: RecipeCategory,
        result: Item
    ) { // idk just copied from the function above lol
        SmithingTransformRecipeJsonBuilder.create(
            Ingredient.ofItems(*arrayOf<ItemConvertible>(titaniumUpgradeTemplate)),
            Ingredient.ofItems(*arrayOf<ItemConvertible>(input)),
            Ingredient.ofItems(*arrayOf<ItemConvertible>(titaniumIngot)),
            category,
            result
        ).criterion("has_titanium_ingot", conditionsFromItem(Items.NETHERITE_INGOT))
            .offerTo(exporter, getItemPath(result) + "_smithing")
    }

    private fun offerSmeltingBlasting(exporter: Consumer<RecipeJsonProvider>, input: ItemConvertible, output: Item) {
        offerSmelting(
            exporter,
            listOf(input),
            RecipeCategory.MISC,
            output,
            1f,
            300,
            ""
        )
        offerBlasting(
            exporter,
            listOf(input),
            RecipeCategory.MISC,
            output,
            1f,
            300,
            ""
        )
    }

    private fun offerChorusConversionRecipe(exporter: Consumer<RecipeJsonProvider>, input: Item, output: Item) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, 1)
            .input(input)
            .input(Items.CHORUS_FRUIT)
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter, convertBetween(output, input))
    }

    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        offerSmeltingBlasting(exporter, deepslateAdamantiteOre, adamantiteIngot)
        offerSmeltingBlasting(exporter, deepslateTitaniumOre, titaniumIngot)

        offerSmithingTemplateCopyingRecipe(exporter, adamantiteUpgradeTemplate, Items.END_STONE)
        offerSmithingTemplateCopyingRecipe(exporter, titaniumUpgradeTemplate, Items.COBBLED_DEEPSLATE)

        offerChorusConversionRecipe(exporter, adamantiteUpgradeTemplate, titaniumUpgradeTemplate)
        offerChorusConversionRecipe(exporter, adamantiteIngot, titaniumIngot)

        offerChorusConversionRecipe(exporter, titaniumUpgradeTemplate, adamantiteUpgradeTemplate)
        offerChorusConversionRecipe(exporter, titaniumIngot, adamantiteIngot)

        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, adamantiteHelmet)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, adamantiteChestplate)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, adamantiteLeggings)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, adamantiteBoots)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, adamantiteShovel)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, adamantitePickaxe)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_AXE, RecipeCategory.TOOLS, adamantiteAxe)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_HOE, RecipeCategory.TOOLS, adamantiteHoe)
        offerAdamantiteUpgradeRecipe(exporter, Items.CROSSBOW, RecipeCategory.TOOLS, adamantiteCrossbow)

        offerTitaniumUpgradeRecipe(exporter, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, titaniumHelmet)
        offerTitaniumUpgradeRecipe(exporter, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, titaniumChestplate)
        offerTitaniumUpgradeRecipe(exporter, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, titaniumLeggings)
        offerTitaniumUpgradeRecipe(exporter, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, titaniumBoots)
        offerTitaniumUpgradeRecipe(exporter, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, titaniumShovel)
        offerTitaniumUpgradeRecipe(exporter, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, titaniumPickaxe)
        offerTitaniumUpgradeRecipe(exporter, Items.NETHERITE_AXE, RecipeCategory.TOOLS, titaniumAxe)
        offerTitaniumUpgradeRecipe(exporter, Items.NETHERITE_HOE, RecipeCategory.TOOLS, titaniumHoe)
        offerTitaniumUpgradeRecipe(exporter, Items.CROSSBOW, RecipeCategory.TOOLS, titaniumCrossbow)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, adamantiteNugget, 9)
            .input(adamantiteIngot)
            .criterion("has_adamantite_ingot", conditionsFromItem(adamantiteIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, adamantiteIngot)
            .input('X', adamantiteNugget)
            .criterion("has_adamantite_ingot", conditionsFromItem(adamantiteIngot))
            .pattern("XXX")
            .pattern("XXX")
            .pattern("XXX")
            .offerTo(exporter)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, adamantiteIngot, 9)
            .input(adamantiteBlock)
            .criterion("has_adamantite_ingot", conditionsFromItem(adamantiteIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, adamantiteBlock)
            .input('X', adamantiteIngot)
            .criterion("has_adamantite_ingot", conditionsFromItem(adamantiteIngot))
            .pattern("XXX")
            .pattern("XXX")
            .pattern("XXX")
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, titaniumNugget, 9)
            .input(titaniumIngot)
            .criterion("has_titanium_ingot", conditionsFromItem(titaniumIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, titaniumIngot)
            .input('X', titaniumNugget)
            .criterion("has_titanium_ingot", conditionsFromItem(titaniumIngot))
            .pattern("XXX")
            .pattern("XXX")
            .pattern("XXX")
            .offerTo(exporter)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, titaniumIngot, 9)
            .input(titaniumBlock)
            .criterion("has_titanium_ingot", conditionsFromItem(titaniumIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, titaniumBlock)
            .input('X', titaniumIngot)
            .criterion("has_titanium_ingot", conditionsFromItem(titaniumIngot))
            .pattern("XXX")
            .pattern("XXX")
            .pattern("XXX")
            .offerTo(exporter)
    }
}