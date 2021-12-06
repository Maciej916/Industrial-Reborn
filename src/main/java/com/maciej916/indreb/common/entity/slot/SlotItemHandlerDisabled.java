package com.maciej916.indreb.common.entity.slot;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotItemHandlerDisabled extends SlotItemHandler {

    public SlotItemHandlerDisabled(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return false;
    }

}
