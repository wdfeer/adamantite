package wdfeer.adamantite.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
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

    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        offerSmelting(
            exporter,
            listOf(deepslateAdamantiteOre),
            RecipeCategory.MISC,
            adamantiteIngot,
            1f,
            300,
            ""
        )
        offerBlasting(
            exporter,
            listOf(deepslateAdamantiteOre),
            RecipeCategory.MISC,
            adamantiteIngot,
            1f,
            300,
            ""
        )

        offerSmithingTemplateCopyingRecipe(exporter, adamantiteUpgradeTemplate, adamantiteIngot)

        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, adamantiteHelmet)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, adamantiteChestplate)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, adamantiteLeggings)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, adamantiteBoots)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, adamantiteShovel)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, adamantitePickaxe)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_AXE, RecipeCategory.TOOLS, adamantiteAxe)
        offerAdamantiteUpgradeRecipe(exporter, Items.NETHERITE_HOE, RecipeCategory.TOOLS, adamantiteHoe)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, adamantiteShovel)
            .pattern(" A ")
            .pattern(" S ")
            .pattern(" S ")
            .input('A', adamantiteIngot)
            .input('S', Items.STICK)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, adamantitePickaxe)
            .pattern("AAA")
            .pattern(" S ")
            .pattern(" S ")
            .input('A', adamantiteIngot)
            .input('S', Items.STICK)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, adamantiteAxe)
            .pattern("AA ")
            .pattern("AS ")
            .pattern(" S ")
            .input('A', adamantiteIngot)
            .input('S', Items.STICK)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter, Adamantite.id("adamantite_axe_left"))
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, adamantiteAxe)
            .pattern(" AA")
            .pattern(" SA")
            .pattern(" S ")
            .input('A', adamantiteIngot)
            .input('S', Items.STICK)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter, Adamantite.id("adamantite_axe_right"))
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, adamantiteHoe)
            .pattern("AA ")
            .pattern(" S ")
            .pattern(" S ")
            .input('A', adamantiteIngot)
            .input('S', Items.STICK)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter, Adamantite.id("adamantite_hoe_left"))
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, adamantiteHoe)
            .pattern(" AA")
            .pattern(" S ")
            .pattern(" S ")
            .input('A', adamantiteIngot)
            .input('S', Items.STICK)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter, Adamantite.id("adamantite_hoe_right"))
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, adamantiteSword)
            .pattern(" A ")
            .pattern(" A ")
            .pattern(" S ")
            .input('A', adamantiteIngot)
            .input('S', Items.STICK)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, adamantiteHelmet)
            .pattern("AAA")
            .pattern("A A")
            .input('A', adamantiteIngot)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, adamantiteChestplate)
            .pattern("A A")
            .pattern("AAA")
            .pattern("AAA")
            .input('A', adamantiteIngot)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, adamantiteLeggings)
            .pattern("AAA")
            .pattern("A A")
            .pattern("A A")
            .input('A', adamantiteIngot)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter)
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, adamantiteBoots)
            .pattern("A A")
            .pattern("A A")
            .input('A', adamantiteIngot)
            .criterion(hasItem(adamantiteIngot), conditionsFromItem(adamantiteIngot))
            .offerTo(exporter)
    }
}