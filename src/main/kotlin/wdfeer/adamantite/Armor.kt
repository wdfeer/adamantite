package wdfeer.adamantite

import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterial.*
import net.minecraft.item.Item
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.world.event.listener.GameEventListener.Holder
import java.util.List
import java.util.function.Supplier


// Adamantite Armor
private val adamantiteArmorMaterial = ArmorMaterial(
    TODO()
)
//private val adamantiteArmorMaterial = object : ArmorMaterial {
//    override fun getDurability(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getDurability(type)
//    override fun getProtection(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getProtection(type)
//    override fun getEnchantability(): Int = ArmorMaterials.NETHERITE.enchantability + 1
//    override fun getEquipSound(): SoundEvent = ArmorMaterials.IRON.equipSound
//    override fun getName(): String = "adamantite"
//    override fun getToughness(): Float = ArmorMaterials.NETHERITE.toughness + 1
//    override fun getKnockbackResistance(): Float = ArmorMaterials.NETHERITE.knockbackResistance
//    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(adamantiteIngot)
//}
val adamantiteHelmet =
    ArmorItem(adamantiteArmorMaterial, ArmorItem.Type.HELMET, Item.Settings()).register("adamantite_helmet")
val adamantiteChestplate = ArmorItem(
    adamantiteArmorMaterial, ArmorItem.Type.CHESTPLATE, Item.Settings()
).register("adamantite_chestplate")
val adamantiteLeggings =
    ArmorItem(adamantiteArmorMaterial, ArmorItem.Type.LEGGINGS, Item.Settings()).register("adamantite_leggings")
val adamantiteBoots =
    ArmorItem(adamantiteArmorMaterial, ArmorItem.Type.BOOTS, Item.Settings()).register("adamantite_boots")

// Titanium Armor
private val titaniumArmorMaterial = ArmorMaterial(
    TODO()
)
//private val titaniumArmorMaterial = object : ArmorMaterial {
//    override fun getDurability(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getDurability(type)
//    override fun getProtection(type: ArmorItem.Type): Int = ArmorMaterials.NETHERITE.getProtection(type)
//    override fun getEnchantability(): Int = ArmorMaterials.NETHERITE.enchantability + 1
//    override fun getEquipSound(): SoundEvent = ArmorMaterials.IRON.equipSound
//    override fun getName(): String = "titanium"
//    override fun getToughness(): Float = ArmorMaterials.NETHERITE.toughness + 1
//    override fun getKnockbackResistance(): Float = ArmorMaterials.NETHERITE.knockbackResistance
//    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(titaniumIngot)
//}
val titaniumHelmet =
    ArmorItem(titaniumArmorMaterial, ArmorItem.Type.HELMET, Item.Settings()).register("titanium_helmet")
val titaniumChestplate = ArmorItem(
    titaniumArmorMaterial, ArmorItem.Type.CHESTPLATE, Item.Settings()
).register("titanium_chestplate")
val titaniumLeggings =
    ArmorItem(titaniumArmorMaterial, ArmorItem.Type.LEGGINGS, Item.Settings()).register("titanium_leggings")
val titaniumBoots = ArmorItem(titaniumArmorMaterial, ArmorItem.Type.BOOTS, Item.Settings()).register("titanium_boots")

// copied from the wiki, TODO: adapt to our armors
fun registerMaterial(
    id: String?,
    defensePoints: MutableMap<ArmorItem.Type?, Int?>?,
    enchantability: Int,
    equipSound: Holder<SoundEvent>,
    repairIngredientSupplier: Supplier<Ingredient?>?,
    toughness: Float,
    knockbackResistance: Float,
    dyeable: Boolean
): Holder<ArmorMaterial> {
    // Get the supported layers for the armor material
    val layers =
        List.of<Layer?>( // The ID of the texture layer, the suffix, and whether the layer is dyeable.
            // We can just pass the armor material ID as the texture layer ID.
            // We have no need for a suffix, so we'll pass an empty string.
            // We'll pass the dyeable boolean we received as the dyeable parameter.
            Layer(ResourceLocation.fromNamespaceAndPath(ExampleMod.MOD_ID, id), "", dyeable)
        )

    var material: ArmorMaterial? = ArmorMaterial(
        defensePoints,
        enchantability,
        equipSound,
        repairIngredientSupplier,
        layers,
        toughness,
        knockbackResistance
    )
    // Register the material within the ArmorMaterials registry.
    material = Registry.register(
        BuiltInRegistries.ARMOR_MATERIAL,
        ResourceLocation.fromNamespaceAndPath(ExampleMod.MOD_ID, id),
        material
    )

    // The majority of the time, you'll want the RegistryEntry of the material - especially for the ArmorItem constructor.
    return Holder.direct(material)
}

fun initArmorMaterials() {

}