package com.maciej916.indreb.common.util;

import com.maciej916.indreb.common.api.fluid.FluidStorage;
import com.maciej916.indreb.common.api.util.Progress;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class BlockEntityUtil {

    public static void spawnExpOrbs(ServerLevel level, @Nullable Player player, BlockPos pos, int entry, float experience) {
        if (experience == 0.0F) {
            entry = 0;
        } else if (experience < 1.0F) {
            int i = (int) Math.floor((float)entry * experience);
            if (i < Math.ceil((float)entry * experience) && Math.random() < (double)((float)entry * experience - (float)i)) {
                ++i;
            }
            entry = i;
        }

        while(entry > 0) {
            int j = ExperienceOrb.getExperienceValue(entry);
            entry -= j;

            if (player == null) {
                level.addFreshEntity(new ExperienceOrb(level, pos.getX(), pos.getY() + 0.5D, pos.getZ() + 0.5D, j));
            } else {
                level.addFreshEntity(new ExperienceOrb(level, player.getX(), player.getY() + 0.5D, player.getZ() + 0.5D, j));
            }
        }
    }

    public static boolean fillTank(Progress progress, FluidStorage fluidStorage, ItemStackHandler itemStackHandler, int upSlot, int downSlot) {

        final ItemStack fillBucketUp = itemStackHandler.getStackInSlot(upSlot);
        final ItemStack fillBucketDown = itemStackHandler.getStackInSlot(downSlot);

        if (progress.currentProgress() == 0) {
            if (!fillBucketUp.isEmpty() && fillBucketDown.getCount() + 1 <= fillBucketDown.getMaxStackSize()) {

                ItemStack newStack = fillBucketUp.copy();
                newStack.setCount(1);
                IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(newStack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
                if (cap != null) {
                    FluidStack fluid = cap.getFluidInTank(1);
                    if (!fluid.isEmpty()) {
                        if (fluidStorage.fillFluid(fluid, true) == fluid.getAmount()) {
                            fluidStorage.fillFluid(fluid, false);

                            cap.drain(fluid.getAmount(), IFluidHandler.FluidAction.EXECUTE);
                            if (fillBucketDown.isEmpty()) {
                                itemStackHandler.setStackInSlot(downSlot, cap.getContainer());
                            } else {
                                fillBucketDown.grow(1);
                            }

                            fillBucketUp.shrink(1);

                            progress.setCurrentProgress(1);

                            return true;
                        }
                    }
                }
            }
        } else {
            progress.setCurrentProgress(0);
        }

        return false;
    }

    // TODO maybe change?

    public static boolean drainTank(ItemStack drainBucketUp, ItemStack drainBucketDown, FluidStorage fluidStorage, ItemStackHandler itemStackHandler, int slotUp, int slotDown) {
        if (drainBucketUp.equals(ItemStack.EMPTY)) return false;
        if (!drainBucketUp.isEmpty() && drainBucketDown.getCount() + 1 <= drainBucketDown.getMaxStackSize()) {
            ItemStack stack = drainBucketUp.copy();
            stack.setCount(1);
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
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

    public static boolean useFillTank(Player player, InteractionHand hand, FluidStorage fluidStorage) {
        ItemStack stack = player.getItemInHand(hand);

        ItemStack newStack = stack.copy();
        newStack.setCount(1);

        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(newStack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
        if (cap != null) {
            FluidStack fluid = cap.getFluidInTank(1);
            if (fluidStorage.isFluidValid(fluid)) {
                if (fluidStorage.fillFluid(fluid, true) == fluid.getAmount()) {
                    fluidStorage.fillFluid(fluid, false);
                    cap.drain(fluid.getAmount(), IFluidHandler.FluidAction.EXECUTE);
                    player.addItem(cap.getContainer());

                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    return true;
                }
            }
        }

        return false;
    }

}
