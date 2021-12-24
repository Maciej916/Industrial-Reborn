package com.maciej916.indreb.common.util;

import com.maciej916.indreb.common.entity.block.FluidStorage;
import com.maciej916.indreb.common.item.FluidCell;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemStackHandler;

public final class BlockEntityUtil {

    public static boolean fillTank(ItemStack fillBucketUp, ItemStack fillBucketDown, FluidStorage fluidStorage, ItemStackHandler itemStackHandler, int slotDown) {
        if (fillBucketUp.equals(ItemStack.EMPTY)) return false;
        if (!fillBucketUp.isEmpty() && fillBucketDown.getCount() + 1 <= fillBucketDown.getMaxStackSize()) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(fillBucketUp, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                FluidStack fluid = cap.getFluidInTank(1);
                if (fluidStorage.fillFluid(fluid, true) == fluid.getAmount()) {
                    fluidStorage.fillFluid(fluid, false);
                    if (fillBucketDown.isEmpty()) {
                        fluid.shrink(fluid.getAmount());
                        if (fillBucketUp.getItem() instanceof BucketItem) {
                            itemStackHandler.setStackInSlot(slotDown, new ItemStack(Items.BUCKET));
                        } else {
                            itemStackHandler.setStackInSlot(slotDown, new ItemStack(fillBucketUp.getItem()));
                        }
                    } else {
                        fillBucketDown.grow(1);
                    }
                    fillBucketUp.shrink(1);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean drainTank(ItemStack drainBucketUp, ItemStack drainBucketDown, FluidStorage fluidStorage,  ItemStackHandler itemStackHandler, int slotDown) {
        if (drainBucketUp.equals(ItemStack.EMPTY)) return false;
        if (!drainBucketUp.isEmpty() && drainBucketDown.getCount() + 1 <= drainBucketDown.getMaxStackSize()) {
            if (fluidStorage.getFluidAmount() >= 1000) {
                ItemStack stack = new ItemStack(drainBucketUp.getItem());
                if (stack.getItem() instanceof FluidCell) {
                    if (drainBucketDown.isEmpty()) {
                        itemStackHandler.setStackInSlot(slotDown, FluidCell.setFluid(stack, fluidStorage.getFluid().getFluid()));
                    } else {
                        drainBucketDown.grow(1);
                    }
                    fluidStorage.drain(1000, IFluidHandler.FluidAction.EXECUTE);
                    drainBucketUp.shrink(1);
                    return true;
                }
            }
        }
        return false;
    }


}
