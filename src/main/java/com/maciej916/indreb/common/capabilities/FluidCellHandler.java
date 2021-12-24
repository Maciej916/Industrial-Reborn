package com.maciej916.indreb.common.capabilities;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.item.FluidCell;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidCellHandler implements IFluidHandlerItem, ICapabilityProvider {
    private final LazyOptional<IFluidHandlerItem> holder = LazyOptional.of(() -> this);

    @Nonnull
    protected ItemStack container;

    public FluidCellHandler(@Nonnull ItemStack c) {
        container = c;
    }

    @Override
    @Nonnull
    public ItemStack getContainer() {
        return container;
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
        return CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY.orEmpty(capability, holder);
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        return true;
    }

    @Override
    public int getTankCapacity(int tank) {
        return ServerConfig.fluid_cell_capacity.get();
    }

    private Fluid getFluid() {
        return FluidCell.getFluid(container);
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        return getFluid() == Fluids.EMPTY ? FluidStack.EMPTY : new FluidStack(getFluid(), ServerConfig.fluid_cell_capacity.get());
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (getFluid() != Fluids.EMPTY || resource.getAmount() < ServerConfig.fluid_cell_capacity.get()) {
            return 0;
        }

        if (action.execute()) {
            FluidCell.setFluid(container, resource.getFluid());
        }

        return ServerConfig.fluid_cell_capacity.get();
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (resource.isEmpty() || resource.getAmount() < ServerConfig.fluid_cell_capacity.get()) {
            return FluidStack.EMPTY;
        }

        Fluid fluid = getFluid();

        if (fluid == Fluids.EMPTY || fluid != resource.getFluid()) {
            return FluidStack.EMPTY;
        }

        FluidStack output = new FluidStack(fluid, ServerConfig.fluid_cell_capacity.get());

        if (action.execute()) {
            FluidCell.setFluid(container, Fluids.EMPTY);
        }

        return output;
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        if (maxDrain < ServerConfig.fluid_cell_capacity.get()) {
            return FluidStack.EMPTY;
        }

        Fluid fluid = getFluid();

        if (fluid == Fluids.EMPTY) {
            return FluidStack.EMPTY;
        }

        FluidStack output = new FluidStack(fluid, ServerConfig.fluid_cell_capacity.get());

        if (action.execute()) {
            FluidCell.setFluid(container, Fluids.EMPTY);
        }

        return output;
    }
}