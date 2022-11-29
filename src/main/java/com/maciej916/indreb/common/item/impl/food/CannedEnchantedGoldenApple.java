package com.maciej916.indreb.common.item.impl.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class CannedEnchantedGoldenApple extends BaseCanned {

    public static final FoodProperties FOOD = (
            new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.4F)
            .fast()
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 50, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1500, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1500, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 3), 1.0F)
            .alwaysEat()
            .build();

    public CannedEnchantedGoldenApple() {
        super(new Properties().food(FOOD));
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.EPIC;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

}
