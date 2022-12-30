package me.nylestroke.nylemod.enchantment

import me.nylestroke.nylemod.NylemodExample
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModEnchantments {
    var LIGHTING_STRIKER: Enchantment = register("lighting_striker",
            LightingStrikerEnchantment(Enchantment.Rarity.UNCOMMON,
                    EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND))

    private fun register(name: String, enchantment: Enchantment): Enchantment {
        return Registry.register<Enchantment, Enchantment>(Registry.ENCHANTMENT, Identifier(NylemodExample.MOD_ID, name),
                enchantment)
    }

    fun registerModEnchantments() {
        println("Registering Enchantments for " + NylemodExample.MOD_ID)
    }
}