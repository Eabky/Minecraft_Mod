package net.eabky_dev.codexa.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties
{
    public static final FoodProperties WISDOM_FRUIT = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(2f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 400), 100f) //will change to custom effect when I make it.
            .build();
}
