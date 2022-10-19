package com.maciej916.indreb.common.recipe;

import net.minecraft.world.item.ItemStack;

public record ChanceResult(ItemStack stack, float chance) {

    @Override
    public ItemStack stack() {
        return stack.copy();
    }

    public int getCount() {
        return stack.getCount();
    }
}

