package com.maciej916.indreb.common.capability.item;

import com.maciej916.indreb.common.api.slot.UpgradeSlotItemHandler;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class UpgradesItemStackHandler extends ItemStackHandler implements IUpgradesItemStackHandler {

    private ArrayList<UpgradeSlotItemHandler> slots;

    public UpgradesItemStackHandler(int size) {
        this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);

        ArrayList<UpgradeSlotItemHandler> slots = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            slots.add(new UpgradeSlotItemHandler(this, i, 178, 9 + (i * 18)));
        }
        this.slots = slots;
    }


}
