package com.maciej916.indreb.common.api.recipe.lib;

import net.minecraft.world.item.ItemStack;

public record ChanceResultStack(ItemStack stack, float chance) {

    @Override
    public ItemStack stack() {
        return stack.copy();
    }

    public int getCount() {
        return stack.getCount();
    }
}

