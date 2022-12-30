package me.nylestroke.nylemod.potion

import me.nylestroke.nylemod.NylemodExample
import me.nylestroke.nylemod.effect.ModEffects
import me.nylestroke.nylemod.item.ModItems
import me.nylestroke.nylemod.mixin.BrewingRecipeRegistryMixin
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModPotions {
    private var FREEZE_POTION: Potion? = null
    private fun registerPotion(name: String?): Potion {
        return Registry.register<Potion, Potion>(Registry.POTION, Identifier(NylemodExample.MOD_ID, name),
                Potion(StatusEffectInstance(ModEffects.FREEZE, 200, 0)))
    }

    fun registerPotions() {
        FREEZE_POTION = registerPotion("freeze_potion")
        registerPotionRecipes()
    }

    private fun registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(
            Potions.AWKWARD, ModItems.MYTHRIL_INGOT,
                FREEZE_POTION)
    }
}