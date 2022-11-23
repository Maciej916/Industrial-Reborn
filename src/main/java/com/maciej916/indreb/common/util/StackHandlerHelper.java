package com.maciej916.indreb.common.util;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class StackHandlerHelper {

    public static ItemStack shrinkStack(ItemStackHandler stackHandler, int slotId, int shrinkCount) {
        final ItemStack inputStack = stackHandler.getStackInSlot(slotId);
        inputStack.shrink(shrinkCount);
        stackHandler.setStackInSlot(slotId, inputStack.copy());

        return inputStack;
    }

    public static void addOutputStack(ItemStackHandler stackHandler, int slotId, ItemStack resultStack) {
        final ItemStack outputStack = stackHandler.getStackInSlot(slotId);
        if (outputStack.isEmpty()) {
            stackHandler.setStackInSlot(slotId, resultStack.copy());
        } else {
            ItemStack newStack = outputStack.copy();
            newStack.grow(resultStack.getCount());
            stackHandler.setStackInSlot(slotId, newStack);
        }
    }

}
