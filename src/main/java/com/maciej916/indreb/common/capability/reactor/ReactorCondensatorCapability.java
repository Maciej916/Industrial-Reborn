package com.maciej916.indreb.common.capability.reactor;

import net.minecraft.world.item.ItemStack;

public class ReactorCondensatorCapability extends ReactorComponentCapability {

    public ReactorCondensatorCapability(ItemStack stack, int maxDamage, int maxHeat) {
        super(stack, maxDamage, maxHeat);
    }

    @Override
    public int adjustCurrentHeat(final int heat) {
        if (heat < 0) {
            return heat;
        }
        currentCondensatorCooling += heat;
        bestCondensatorCooling = Math.max(currentCondensatorCooling, bestCondensatorCooling);
        int acceptedHeat = Math.min(heat, getMaxHeat() - heat);
        int result = heat - acceptedHeat;
        currentHeat += acceptedHeat;
        maxReachedHeat = Math.max(maxReachedHeat, currentHeat);
        return result;
    }

    @Override
    public boolean needsCoolantInjected() {
        return currentHeat > 0.85 * getMaxHeat();
    }

    @Override
    public void injectCoolant() {
        currentHeat = 0;
    }

}
