package com.maciej916.indreb.common.item.impl.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class CannedPoison extends BaseCanned {
    public static final FoodProperties FOOD = (
            new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.4F)
            .fast()
            .effect(new MobEffectInstance(MobEffects.POISON, 25, 0), 1.0F)
            .alwaysEat()
            .build();

    public CannedPoison() {
        super(new Properties().food(FOOD));
    }

}
