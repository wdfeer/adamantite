package wdfeer.adamantite

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
                    generator.register(adamantiteSword, Models.HANDHELD)
                    generator.register(adamantitePickaxe, Models.HANDHELD)
                    generator.register(adamantiteShovel, Models.HANDHELD)
                    generator.register(adamantiteAxe, Models.HANDHELD)
                    generator.register(adamantiteHoe, Models.HANDHELD)
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
                    getTagBuilder(ItemTags.PICKAXES).add(Adamantite.id("adamantite_pickaxe"))
                    getTagBuilder(ItemTags.SHOVELS).add(Adamantite.id("adamantite_shovel"))
                    getTagBuilder(ItemTags.AXES).add(Adamantite.id("adamantite_axe"))
                    getTagBuilder(ItemTags.HOES).add(Adamantite.id("adamantite_hoe"))
                }
            }
        }

        pack.addProvider { dataOutput, _ ->
            object : FabricLanguageProvider(dataOutput, "en_us") {
                override fun generateTranslations(buffer: TranslationBuilder) {
                    buffer.add(deepslateAdamantiteOre, "Deepslate Adamantite Ore")
                    buffer.add(adamantiteSword, "Adamantite Sword")
                    buffer.add(adamantitePickaxe, "Adamantite Pickaxe")
                    buffer.add(adamantiteShovel, "Adamantite Shovel")
                    buffer.add(adamantiteAxe, "Adamantite Axe")
                    buffer.add(adamantiteHoe, "Adamantite Hoe")
                }
            }
        }
    }
}