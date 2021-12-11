package com.maciej916.indreb.common.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ItemPainter extends ItemTool {

    BlockState state;

    public ItemPainter(BlockState state) {
        super(32);
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack result = itemStack.copy();
        result.setDamageValue(itemStack.getDamageValue() + 1);
        return result.getDamageValue() >= result.getMaxDamage() ? ItemStack.EMPTY : result;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

}
