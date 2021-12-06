package com.maciej916.indreb.common.receipe;

import net.minecraft.world.item.ItemStack;

public class ChanceResult {

    private final ItemStack stack;
    private final float chance;

    public ChanceResult(ItemStack stack, float chance) {
        this.stack = stack;
        this.chance = chance;
    }

    public ItemStack getStack() {
        return stack.copy();
    }

    public int getCount() {
        return stack.getCount();
    }

    public float getChance() {
        return chance;
    }
}

