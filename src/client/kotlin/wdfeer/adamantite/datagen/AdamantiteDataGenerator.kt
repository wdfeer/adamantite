package wdfeer.adamantite.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Block
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.ItemTags
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
import wdfeer.adamantite.adamantiteUpgradeTemplate
import wdfeer.adamantite.deepslateAdamantiteOre
import wdfeer.adamantite.deepslateTitaniumOre

object AdamantiteDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider { dataOutput, _ ->
            object : FabricBlockLootTableProvider(dataOutput) {
                override fun generate() {
                    addDrop(deepslateAdamantiteOre, deepslateAdamantiteOre)
                    addDrop(deepslateTitaniumOre, deepslateTitaniumOre)
                }
            }
        }

        pack.addProvider { dataOutput, _ ->
            object : FabricModelProvider(dataOutput) {
                override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
                    generator.registerSimpleCubeAll(deepslateAdamantiteOre)
                    generator.registerSimpleCubeAll(deepslateTitaniumOre)
                }

                override fun generateItemModels(generator: ItemModelGenerator) {
                    generator.register(adamantiteIngot, Models.GENERATED)
                    generator.register(adamantiteUpgradeTemplate, Models.GENERATED)
                    generator.register(adamantiteSword, Models.HANDHELD)
                    generator.register(adamantiteShovel, Models.HANDHELD)
                    generator.register(adamantitePickaxe, Models.HANDHELD)
                    generator.register(adamantiteAxe, Models.HANDHELD)
                    generator.register(adamantiteHoe, Models.HANDHELD)
                    generator.registerArmor(adamantiteHelmet)
                    generator.registerArmor(adamantiteChestplate)
                    generator.registerArmor(adamantiteLeggings)
                    generator.registerArmor(adamantiteBoots)
                }
            }
        }

        pack.addProvider { dataOutput, _ -> RecipeProvider(dataOutput) }

        pack.addProvider { dataOutput, registriesFuture ->
            object : FabricTagProvider<Block>(dataOutput, RegistryKeys.BLOCK, registriesFuture) {
                override fun configure(lookup: RegistryWrapper.WrapperLookup?) {
                    getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(Adamantite.id("deepslate_adamantite_ore"))
                    getTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(Adamantite.id("deepslate_adamantite_ore"))
                    getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(Adamantite.id("deepslate_titanium_ore"))
                    getTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(Adamantite.id("deepslate_titanium_ore"))
                }
            }
        }

        pack.addProvider { dataOutput, registriesFuture ->
            object : FabricTagProvider<Item>(dataOutput, RegistryKeys.ITEM, registriesFuture) {
                override fun configure(lookup: RegistryWrapper.WrapperLookup?) {
                    getTagBuilder(ItemTags.SWORDS).add(Adamantite.id("adamantite_sword"))
                    getTagBuilder(ItemTags.SHOVELS).add(Adamantite.id("adamantite_shovel"))
                    getTagBuilder(ItemTags.PICKAXES).add(Adamantite.id("adamantite_pickaxe"))
                    getTagBuilder(ItemTags.AXES).add(Adamantite.id("adamantite_axe"))
                    getTagBuilder(ItemTags.HOES).add(Adamantite.id("adamantite_hoe"))

                    getTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Adamantite.id("adamantite_helmet"))
                    getTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Adamantite.id("adamantite_chestplate"))
                    getTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Adamantite.id("adamantite_leggings"))
                    getTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Adamantite.id("adamantite_boots"))
                }
            }
        }

        pack.addProvider { dataOutput, _ ->
            object : FabricLanguageProvider(dataOutput, "en_us") {
                override fun generateTranslations(buffer: TranslationBuilder) {
                    buffer.add(deepslateAdamantiteOre, "Deepslate Adamantite Ore")
                    buffer.add(deepslateTitaniumOre, "Deepslate Titanium Ore")
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