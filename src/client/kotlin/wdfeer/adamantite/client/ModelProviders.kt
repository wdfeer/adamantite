package wdfeer.adamantite.client

import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.CrossbowItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import wdfeer.adamantite.Adamantite
import wdfeer.adamantite.adamantiteCrossbow
import wdfeer.adamantite.titaniumCrossbow

fun registerModelPredicateProviders() {
    registerCrossbowModelPredicateProviders(adamantiteCrossbow)
    registerCrossbowModelPredicateProviders(titaniumCrossbow)
}

private fun registerCrossbowModelPredicateProviders(crossbow: Item) {
    registerActiveProvider(crossbow, "pull") { user, stack ->
        (stack.maxUseTime - user.getItemUseTimeLeft()) / 20.0f
    }
    registerActiveProvider(crossbow, "pulling") { user, _ ->
        if (user.isUsingItem) 1f else 0f
    }
    registerActiveProvider(crossbow, "charged") { _, stack ->
        if (CrossbowItem.isCharged(stack)) 1f else 0f
    }
    registerActiveProvider(crossbow, "firework") { _, stack ->
        if (CrossbowItem.isCharged(stack) && CrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET)) 1f else 0f
    }
}

private fun registerActiveProvider(
    item: Item,
    id: String,
    provider: (user: LivingEntity, stack: ItemStack) -> Float
) {
    ModelPredicateProviderRegistry.register(
        item,
        Adamantite.id(id)
    ) { itemStack: ItemStack?, _: ClientWorld?, livingEntity: LivingEntity?, _: Int ->
        if (livingEntity == null) return@register 0f
        if (livingEntity.activeItem != itemStack) return@register 0f
        provider(livingEntity, itemStack)
    }
}