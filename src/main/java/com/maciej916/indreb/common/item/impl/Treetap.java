package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.api.item.base.ToolItem;
import net.minecraft.world.item.ItemStack;

public class Treetap extends ToolItem {

    public Treetap(int maxDamage) {
        super(maxDamage);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return false;
    }
}
