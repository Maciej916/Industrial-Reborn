package com.maciej916.indreb.common.capability.item;

import com.maciej916.indreb.common.api.slot.BaseSlotItemHandler;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class BaseItemStackHandler extends ItemStackHandler implements IBaseItemStackHandler {

    private final ArrayList<BaseSlotItemHandler> slots;

    public BaseItemStackHandler(ArrayList<BaseSlotItemHandler> slots) {
        this.slots = slots;
        this.stacks = NonNullList.withSize(slots.size(), ItemStack.EMPTY);
    }

}
