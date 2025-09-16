package wdfeer.adamantite.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import wdfeer.adamantite.Adamantite
import wdfeer.adamantite.adamantiteAxe
import wdfeer.adamantite.adamantiteBoots
import wdfeer.adamantite.adamantiteChestplate
import wdfeer.adamantite.adamantiteHelmet
import wdfeer.adamantite.adamantiteHoe
import wdfeer.adamantite.adamantiteIngot
import wdfeer.adamantite.adamantiteLeggings
import wdfeer.adamantite.adamantitePickaxe
import wdfeer.adamantite.adamantiteShovel
import wdfeer.adamantite.adamantiteSword
import wdfeer.adamantite.deepslateAdamantiteOre
import java.util.function.Consumer

class RecipeProvider(dataOutput: FabricDataOutput) : FabricRecipeProvider(dataOutput) {
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