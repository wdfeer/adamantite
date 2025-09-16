package wdfeer.adamantite

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Block
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.ItemTags
import java.util.function.Consumer

object AdamantiteDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider { dataOutput, _ ->
            object : FabricBlockLootTableProvider(dataOutput) {
                override fun generate() {
                    addDrop(deepslateAdamantiteOre, deepslateAdamantiteOre)
                }
            }
        }

        pack.addProvider { dataOutput, _ ->
            object : FabricModelProvider(dataOutput) {
                override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
                    generator.registerSimpleCubeAll(deepslateAdamantiteOre)
                }

                override fun generateItemModels(generator: ItemModelGenerator) {
                    generator.register(adamantiteIngot, Models.GENERATED)
                    generator.register(adamantiteSword, Models.HANDHELD)
                    generator.register(adamantiteShovel, Models.HANDHELD)
                    generator.register(adamantitePickaxe, Models.HANDHELD)
                    generator.register(adamantiteAxe, Models.HANDHELD)
                    generator.register(adamantiteHoe, Models.HANDHELD)
                    generator.register(adamantiteHelmet, Models.GENERATED)
                    generator.register(adamantiteChestplate, Models.GENERATED)
                    generator.register(adamantiteLeggings, Models.GENERATED)
                    generator.register(adamantiteBoots, Models.GENERATED)
                }
            }
        }

        pack.addProvider { dataOutput, _ ->
            object : FabricRecipeProvider(dataOutput) {
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
        }

        pack.addProvider { dataOutput, registriesFuture ->
            object : FabricTagProvider<Block>(dataOutput, RegistryKeys.BLOCK, registriesFuture) {
                override fun configure(lookup: RegistryWrapper.WrapperLookup?) {
                    // TODO: extract the ids into constants or sth
                    getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(Adamantite.id("deepslate_adamantite_ore"))
                    getTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(Adamantite.id("deepslate_adamantite_ore"))
                }
            }
        }

        pack.addProvider { dataOutput, registriesFuture ->
            object : FabricTagProvider<Item>(dataOutput, RegistryKeys.ITEM, registriesFuture) {
                override fun configure(lookup: RegistryWrapper.WrapperLookup?) {
                    // TODO: extract the ids into constants or sth
                    getTagBuilder(ItemTags.SWORDS).add(Adamantite.id("adamantite_sword"))
                    getTagBuilder(ItemTags.SHOVELS).add(Adamantite.id("adamantite_shovel"))
                    getTagBuilder(ItemTags.PICKAXES).add(Adamantite.id("adamantite_pickaxe"))
                    getTagBuilder(ItemTags.AXES).add(Adamantite.id("adamantite_axe"))
                    getTagBuilder(ItemTags.HOES).add(Adamantite.id("adamantite_hoe"))
                }
            }
        }

        pack.addProvider { dataOutput, _ ->
            object : FabricLanguageProvider(dataOutput, "en_us") {
                override fun generateTranslations(buffer: TranslationBuilder) {
                    buffer.add(deepslateAdamantiteOre, "Deepslate Adamantite Ore")
                    buffer.add(adamantiteIngot, "Adamantite Ingot")
                    buffer.add(adamantiteSword, "Adamantite Sword")
                    buffer.add(adamantiteShovel, "Adamantite Shovel")
                    buffer.add(adamantitePickaxe, "Adamantite Pickaxe")
                    buffer.add(adamantiteAxe, "Adamantite Axe")
                    buffer.add(adamantiteHoe, "Adamantite Hoe")
                    buffer.add(adamantiteHelmet, "Adamantite Helmet")
                    buffer.add(adamantiteChestplate, "Adamantite Chestplate")
                    buffer.add(adamantiteLeggings, "Adamantite Leggings")
                    buffer.add(adamantiteBoots, "Adamantite Boots")
                }
            }
        }
    }
}