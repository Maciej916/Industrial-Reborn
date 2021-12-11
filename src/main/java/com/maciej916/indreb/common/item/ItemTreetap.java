package com.maciej916.indreb.common.item;

import net.minecraft.world.item.ItemStack;

public class ItemTreetap extends ItemTool {

    public ItemTreetap(int durability) {
        super(durability);
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}
