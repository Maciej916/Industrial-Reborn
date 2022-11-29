package com.maciej916.indreb.common.item.impl.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class CannedGoldenApple extends BaseCanned {

    public static final FoodProperties FOOD = (
            new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.1F)
            .fast()
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 25, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 1.0F)
            .build();

    public CannedGoldenApple() {
        super(new Properties().food(FOOD));
    }

}
