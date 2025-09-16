package wdfeer.adamantite

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models

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
    }
}