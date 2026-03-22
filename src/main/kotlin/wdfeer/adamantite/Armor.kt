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

fun initArmor() = Unit // inits this file

private const val maxDamageMultiplier = 37 // same as netherite
private fun armorItem(material: RegistryEntry<ArmorMaterial>, type: ArmorItem.Type): ArmorItem =
    ArmorItem(
        material, type, Item.Settings().maxDamage(
            type.getMaxDamage(maxDamageMultiplier)
        )
    )

// === Adamantite Armor ===

private val adamantite = registerMaterial(
    "adamantite",
    ArmorMaterials.NETHERITE.value().defense,
    ArmorMaterials.NETHERITE.value().enchantability + 1,
    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
    { Ingredient.ofItems(adamantiteIngot) },
    ArmorMaterials.NETHERITE.value().toughness + 1,
    ArmorMaterials.NETHERITE.value().knockbackResistance
)

val adamantiteHelmet = armorItem(adamantite, ArmorItem.Type.HELMET).register("adamantite_helmet")
val adamantiteChestplate = armorItem(adamantite, ArmorItem.Type.CHESTPLATE).register("adamantite_chestplate")
val adamantiteLeggings = armorItem(adamantite, ArmorItem.Type.LEGGINGS).register("adamantite_leggings")
val adamantiteBoots = armorItem(adamantite, ArmorItem.Type.BOOTS).register("adamantite_boots")

// === Titanium Armor ===
private val titanium = registerMaterial(
    "titanium",
    ArmorMaterials.NETHERITE.value().defense,
    ArmorMaterials.NETHERITE.value().enchantability + 1,
    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
    { Ingredient.ofItems(titaniumIngot) },
    ArmorMaterials.NETHERITE.value().toughness + 1,
    ArmorMaterials.NETHERITE.value().knockbackResistance
)

val titaniumHelmet =
    armorItem(titanium, ArmorItem.Type.HELMET).register("titanium_helmet")
val titaniumChestplate = armorItem(
    titanium, ArmorItem.Type.CHESTPLATE
).register("titanium_chestplate")
val titaniumLeggings =
    armorItem(titanium, ArmorItem.Type.LEGGINGS).register("titanium_leggings")
val titaniumBoots = armorItem(titanium, ArmorItem.Type.BOOTS).register("titanium_boots")

private fun registerMaterial(
    id: String,
    defensePoints: MutableMap<ArmorItem.Type, Int>,
    enchantability: Int,
    equipSound: RegistryEntry<SoundEvent>,
    repairIngredientSupplier: Supplier<Ingredient>,
    toughness: Float,
    knockbackResistance: Float,
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