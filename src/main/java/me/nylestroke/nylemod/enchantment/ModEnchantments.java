package me.nylestroke.nylemod.enchantment;

import me.nylestroke.nylemod.NylemodExample;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

    public static Enchantment LIGHTING_STRIKER = register("lighting_striker",
            new LightingStrikerEnchantment(Enchantment.Rarity.UNCOMMON,
                    EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(NylemodExample.MOD_ID, name),
                enchantment);
    }

    public static void registerModEnchantments() {
        System.out.println("Registering Enchantments for " + NylemodExample.MOD_ID);
    }

}
