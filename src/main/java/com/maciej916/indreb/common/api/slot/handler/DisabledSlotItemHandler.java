package com.maciej916.indreb.common.api.slot.handler;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class DisabledSlotItemHandler extends BaseSlotItemHandler {

    public DisabledSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, boolean isActive) {
        super(itemHandler, index, xPosition, yPosition, isActive);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return false;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
