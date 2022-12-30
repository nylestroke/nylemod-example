package me.nylestroke.nylemod.util

import me.nylestroke.nylemod.item.ModItems
import net.fabricmc.fabric.api.`object`.builder.v1.client.model.FabricModelPredicateProviderRegistry
import net.minecraft.client.item.UnclampedModelPredicateProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object ModModelPredicateProvider {
    fun registerModModels() {
        registerBow(ModItems.VLADIKBOW)
    }

    private fun registerBow(bow: Item?) {
        FabricModelPredicateProviderRegistry.register(bow, Identifier("pull"),
                UnclampedModelPredicateProvider { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int ->
                    if (entity == null) {
                        return@UnclampedModelPredicateProvider 0.0f
                    }
                    if (entity.activeItem != stack) {
                        return@UnclampedModelPredicateProvider 0.0f
                    }
                    (stack.maxUseTime - entity.itemUseTimeLeft).toFloat() / 20.0f
                })
        FabricModelPredicateProviderRegistry.register(bow, Identifier("pulling"),
                UnclampedModelPredicateProvider { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int ->
                    if (entity != null && entity.isUsingItem && entity.activeItem == stack) 1.0f else 0.0f })
    }
}