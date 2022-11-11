package com.maciej916.indreb.common.entity.slot;

import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class SlotItemHandlerDisabled extends IndRebSlotItemHandler {

    public SlotItemHandlerDisabled(IndRebBlockEntity entity, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(entity, itemHandler, index, xPosition, yPosition);
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
