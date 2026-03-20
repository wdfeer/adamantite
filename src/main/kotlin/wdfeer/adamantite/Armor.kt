package wdfeer.adamantite

import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterial.*
import net.minecraft.item.ArmorMaterials
import net.minecraft.item.Item
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import java.util.function.Supplier


// === Adamantite Armor ===

private val adamantite = registerMaterial(
    "adamantite",
    ArmorMaterials.NETHERITE.value().defense,
    ArmorMaterials.NETHERITE.value().enchantability + 1,
    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
    Supplier { Ingredient.ofItems(adamantiteIngot) },
    ArmorMaterials.NETHERITE.value().toughness + 1,
    ArmorMaterials.NETHERITE.value().knockbackResistance,
    false
)
val adamantiteHelmet = ArmorItem(adamantite, ArmorItem.Type.HELMET, Item.Settings()).register("adamantite_helmet")
val adamantiteChestplate = ArmorItem(
    adamantite, ArmorItem.Type.CHESTPLATE, Item.Settings()
).register("adamantite_chestplate")
val adamantiteLeggings = ArmorItem(adamantite, ArmorItem.Type.LEGGINGS, Item.Settings()).register("adamantite_leggings")
val adamantiteBoots = ArmorItem(adamantite, ArmorItem.Type.BOOTS, Item.Settings()).register("adamantite_boots")

// === Titanium Armor ===
private val titanium = registerMaterial(
    "titanium",
    ArmorMaterials.NETHERITE.value().defense,
    ArmorMaterials.NETHERITE.value().enchantability + 1,
    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
    Supplier { Ingredient.ofItems(titaniumIngot) },
    ArmorMaterials.NETHERITE.value().toughness + 1,
    ArmorMaterials.NETHERITE.value().knockbackResistance,
    false
)
val titaniumHelmet =
    ArmorItem(titanium, ArmorItem.Type.HELMET, Item.Settings()).register("titanium_helmet")
val titaniumChestplate = ArmorItem(
    titanium, ArmorItem.Type.CHESTPLATE, Item.Settings()
).register("titanium_chestplate")
val titaniumLeggings =
    ArmorItem(titanium, ArmorItem.Type.LEGGINGS, Item.Settings()).register("titanium_leggings")
val titaniumBoots = ArmorItem(titanium, ArmorItem.Type.BOOTS, Item.Settings()).register("titanium_boots")

fun registerMaterial(
    id: String,
    defensePoints: MutableMap<ArmorItem.Type, Int>,
    enchantability: Int,
    equipSound: RegistryEntry<SoundEvent>,
    repairIngredientSupplier: Supplier<Ingredient>,
    toughness: Float,
    knockbackResistance: Float,
    dyeable: Boolean
): RegistryEntry<ArmorMaterial> {
    val layers = listOf<Layer?>(
        // The ID of the texture layer, the suffix, and whether the layer is dyeable.
        // We can just pass the armor material ID as the texture layer ID.
        // We have no need for a suffix, so we'll pass an empty string.
        // We'll pass the dyeable boolean we received as the dyeable parameter.
        Layer(Adamantite.id(id))
    )

    var material: ArmorMaterial? = ArmorMaterial(
        defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance
    )
    // Register the material within the ArmorMaterials registry.
    material = Registry.register(
        Registries.ARMOR_MATERIAL, Adamantite.id(id), material
    )

    // The majority of the time, you'll want the RegistryEntry of the material - especially for the ArmorItem constructor.
    return RegistryEntry.Direct(material)
}