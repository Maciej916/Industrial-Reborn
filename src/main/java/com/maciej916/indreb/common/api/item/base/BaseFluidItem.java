package com.maciej916.indreb.common.api.item.base;

import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class BaseFluidItem extends BaseItem {

    public BaseFluidItem(Properties properties) {
        super(CreativeModeTab.TAB_MATERIALS, properties.setNoRepair());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
        if (cap != null) {
            FluidStack fluidStack = cap.getFluidInTank(1);
            if (fluidStack.getFluid() != Fluids.EMPTY) {
                list.add(Component.literal("< " + fluidStack.getAmount() + " mB, ").append(Component.translatable(fluidStack.getFluid().getFluidType().getDescriptionId())).append(" >").withStyle(ChatFormatting.GRAY));
            } else {
                list.add(Component.translatable("item.indreb.empty_fluid").withStyle(ChatFormatting.GRAY));
            }
        }
        super.appendHoverText(stack, level, list, tooltipFlag);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
        if (cap != null) {
            return cap.getFluidInTank(0).getFluid() != Fluids.EMPTY;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
        if (cap != null) {
            if (cap.getFluidInTank(0).getFluid() != Fluids.EMPTY) {
                return new ItemStack(this);
            }
        }
        return ItemStack.EMPTY;
    }


    public FluidStack getFluidStack(ItemStack stack) {
        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
        if (cap != null) {
            return cap.getFluidInTank(0);
        }
        return FluidStack.EMPTY;
    }

    public static class Colors implements ItemColor {
        @Override
        public int getColor(@NotNull ItemStack stack, int tintIndex) {
            if (tintIndex != 1) return 0xFFFFFFFF;
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                FluidStack fluidStack = cap.getFluidInTank(1);
                if (fluidStack.getFluid() != Fluids.EMPTY) {
                    return IClientFluidTypeExtensions.of(fluidStack.getFluid()).getTintColor(fluidStack);
                }
            }
            return 0xFFFFFFFF;
        }
    }
}
