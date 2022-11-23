package com.maciej916.indreb.common.capability.reactor;

import net.minecraft.world.item.ItemStack;

public class ReactorCoolantCellCapability extends ReactorComponentCapability {

    public ReactorCoolantCellCapability(ItemStack stack, int maxDamage, int maxHeat) {
        super(stack, maxDamage, maxHeat);
    }

    @Override
    public int adjustCurrentHeat(final int heat) {
        currentCellCooling += heat;
        bestCellCooling = Math.max(currentCellCooling, bestCellCooling);
        return super.adjustCurrentHeat(heat);
    }
}
