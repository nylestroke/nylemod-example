package me.nylestroke.nylemod.effect

import me.nylestroke.nylemod.NylemodExample
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModEffects {
    var FREEZE: StatusEffect? = null
    private fun registerStatusEffect(name: String?): StatusEffect {
        return Registry.register<StatusEffect, FreezeEffect>(Registry.STATUS_EFFECT, Identifier(NylemodExample.MOD_ID, name),
                FreezeEffect(StatusEffectCategory.HARMFUL, 3124687))
    }

    fun registerEffects() {
        FREEZE = registerStatusEffect("freeze")
    }
}