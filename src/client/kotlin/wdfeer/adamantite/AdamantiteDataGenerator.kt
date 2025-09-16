package wdfeer.adamantite

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
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
                }
            }
        }
    }
}