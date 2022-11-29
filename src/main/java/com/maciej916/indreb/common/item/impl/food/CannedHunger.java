package com.maciej916.indreb.common.item.impl.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class CannedHunger extends BaseCanned {

    public static final FoodProperties FOOD = (
            new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.1F)
            .fast()
            .effect(new MobEffectInstance(MobEffects.HUNGER, 150, 0), 0.5F)
            .build();

    public CannedHunger() {
        super(new Properties().food(FOOD));
    }

}
