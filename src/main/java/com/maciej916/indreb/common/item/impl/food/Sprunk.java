package com.maciej916.indreb.common.item.impl.food;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class Sprunk extends BaseCanned {

    public static final FoodProperties FOOD = (new FoodProperties.Builder())
            .saturationMod(0.1F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1), 1F)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 600, 1), 1F)
            .alwaysEat()
            .fast()
            .build();

    public Sprunk() {
        super(new Properties().food(FOOD));
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
