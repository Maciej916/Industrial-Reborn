package com.maciej916.indreb.common.item.base;

import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import javax.annotation.Nullable;
import java.util.List;

public class FluidItem extends BaseItem {
    public FluidItem(Properties properties) {
        super(properties.setNoRepair());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
        if (cap != null) {
            FluidStack fluidStack = cap.getFluidInTank(1);
            if (fluidStack.getFluid() != Fluids.EMPTY) {
                list.add(new TextComponent("< " + fluidStack.getAmount() + " mB, ").append(new TranslatableComponent(fluidStack.getFluid().getAttributes().getTranslationKey())).append(" >").withStyle(ChatFormatting.GRAY));
            } else {
                list.add(new TranslatableComponent("item.indreb.empty_fluid").withStyle(ChatFormatting.GRAY));
            }
        }
        super.appendHoverText(stack, level, list, tooltipFlag);
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }


    @Override
    public boolean hasContainerItem(ItemStack stack) {
        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
        if (cap != null) {
            return cap.getFluidInTank(0).getFluid() != Fluids.EMPTY;
        }
        return false;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
        if (cap != null) {
            if (cap.getFluidInTank(0).getFluid() != Fluids.EMPTY) {
                return new ItemStack(this);
            }
        }
        return ItemStack.EMPTY;
    }


    public FluidStack getFluidStack(ItemStack stack) {
        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
        if (cap != null) {
            return cap.getFluidInTank(0);
        }
        return FluidStack.EMPTY;
    }
}
