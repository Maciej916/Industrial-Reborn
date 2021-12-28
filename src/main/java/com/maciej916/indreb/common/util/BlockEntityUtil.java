package com.maciej916.indreb.common.util;

import com.maciej916.indreb.common.entity.block.FluidStorage;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemStackHandler;

public final class BlockEntityUtil {

    public static boolean fillTank(ItemStack fillBucketUp, ItemStack fillBucketDown, FluidStorage fluidStorage, ItemStackHandler itemStackHandler, int slotDown) {
        if (fillBucketUp.equals(ItemStack.EMPTY)) return false;
        if (!fillBucketUp.isEmpty() && fillBucketDown.getCount() + 1 <= fillBucketDown.getMaxStackSize()) {

            ItemStack newStack = fillBucketUp.copy();
            newStack.setCount(1);
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(newStack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                FluidStack fluid = cap.getFluidInTank(1);
                if (!fluid.isEmpty()) {
                    if (fluidStorage.fillFluid(fluid, true) == fluid.getAmount()) {
                        fluidStorage.fillFluid(fluid, false);

                        cap.drain(fluid.getAmount(), IFluidHandler.FluidAction.EXECUTE);
                        if (fillBucketDown.isEmpty()) {
                            itemStackHandler.setStackInSlot(slotDown, cap.getContainer());
                        } else {
                            fillBucketDown.grow(1);
                        }

                        fillBucketUp.shrink(1);

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean drainTank(ItemStack drainBucketUp, ItemStack drainBucketDown, FluidStorage fluidStorage,  ItemStackHandler itemStackHandler, int slotUp, int slotDown) {
        if (drainBucketUp.equals(ItemStack.EMPTY)) return false;
        if (!drainBucketUp.isEmpty() && drainBucketDown.getCount() + 1 <= drainBucketDown.getMaxStackSize()) {
            ItemStack stack = drainBucketUp.copy();
            stack.setCount(1);
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                int amountLeft = cap.getTankCapacity(1) - cap.getFluidInTank(1).getAmount();
                int amount = Math.min(1000, amountLeft);
                if (fluidStorage.getFluidAmount() >= amount) {
                    cap.fill(new FluidStack(fluidStorage.getFluid(), amount), IFluidHandler.FluidAction.EXECUTE);
                    fluidStorage.drain(amount, IFluidHandler.FluidAction.EXECUTE);

                    if (cap.getFluidInTank(1).getAmount() == cap.getTankCapacity(1)) {
                        drainBucketUp.shrink(1);
                        if (drainBucketDown.isEmpty()) {
                            itemStackHandler.setStackInSlot(slotDown, cap.getContainer());
                        } else {
                            drainBucketDown.grow(1);
                        }
                    } else {
                        itemStackHandler.setStackInSlot(slotUp, cap.getContainer());
                    }
                }
            }
        }
        return false;
    }


}
