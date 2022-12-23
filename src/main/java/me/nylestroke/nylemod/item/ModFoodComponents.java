package me.nylestroke.nylemod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

    public static final FoodComponent GRAPE = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 150, 0), 0.25f).meat().build();

}
