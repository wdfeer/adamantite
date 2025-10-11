package wdfeer.adamantite.client

import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.CrossbowItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Identifier
import wdfeer.adamantite.adamantiteCrossbow
import wdfeer.adamantite.titaniumCrossbow

fun registerModelPredicateProviders() {
    registerCrossbowModelPredicateProviders(adamantiteCrossbow)
    registerCrossbowModelPredicateProviders(titaniumCrossbow)
}

private fun registerCrossbowModelPredicateProviders(crossbow: Item) {
    ModelPredicateProviderRegistry.register(
        crossbow,
        Identifier.of("minecraft", "pull")
    ) { stack: ItemStack?, _: ClientWorld?, user: LivingEntity?, _: Int ->
        if (user == null) return@register 0f
        if (user.activeItem != stack || !user.isUsingItem) return@register 0f
        user.itemUseTime.toFloat() / CrossbowItem.getPullTime(stack)
    }
    ModelPredicateProviderRegistry.register(
        crossbow,
        Identifier.of("minecraft", "pulling")
    ) { stack: ItemStack?, _: ClientWorld?, user: LivingEntity?, _: Int ->
        if (user == null) return@register 0f
        if (user.activeItem == stack && user.isUsingItem) 1f else 0f
    }
    ModelPredicateProviderRegistry.register(
        crossbow,
        Identifier.of("minecraft", "charged")
    ) { stack: ItemStack?, _: ClientWorld?, user: LivingEntity?, _: Int ->
        if (user == null) return@register 0f
        if (CrossbowItem.isCharged(stack)) 1f else 0f
    }
    ModelPredicateProviderRegistry.register(
        crossbow,
        Identifier.of("minecraft", "firework")
    ) { stack: ItemStack?, _: ClientWorld?, user: LivingEntity?, _: Int ->
        if (user == null) return@register 0f
        if (CrossbowItem.isCharged(stack) && CrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET)) 1f else 0f
    }
}