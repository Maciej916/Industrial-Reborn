package com.maciej916.indreb.common.api.fluid;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import com.maciej916.indreb.common.api.interfaces.fluid.IFluidStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class FluidStorage extends FluidTank implements IFluidStorage, IBaseProgress {
    public FluidStorage(int capacity) {
        super(capacity);
    }

    public FluidStorage(int capacity, Predicate<FluidStack> validator) {
        super(capacity, validator);
    }

    public int fillFluid(FluidStack resource, boolean simulate) {
        int fluidFilled = Math.min(capacity - fluid.getAmount(), Math.min(this.capacity, resource.getAmount()));
        if (!simulate) {
            fill(resource, FluidAction.EXECUTE);
            updated();
        }
        return fluidFilled;
    }

    public int takeFluid(final int amount, boolean simulate) {
        int fluidExtracted = Math.min(fluid.getAmount(), amount);
        if (!simulate) {
            drain(amount, FluidAction.EXECUTE);
            updated();
        }

        return fluidExtracted;
    }


    /* USE fillFluid */
    @Deprecated
    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return super.fill(resource, action);
    }

    /* USE takeFluid */
    @Deprecated
    @Override
    public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
        return super.drain(maxDrain, action);
    }

    @Override
    public void updated() {

    }

    @Override
    public float getCurrentProgress() {
        return fluid.getAmount();
    }

    @Override
    public float getProgressMax() {
        return getCapacity();
    }
}
