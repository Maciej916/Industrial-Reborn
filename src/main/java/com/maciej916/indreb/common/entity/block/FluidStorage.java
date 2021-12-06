package com.maciej916.indreb.common.entity.block;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.function.Predicate;

public class FluidStorage extends FluidTank {
    public FluidStorage(int capacity) {
        super(capacity);
    }

    public FluidStorage(int capacity, Predicate<FluidStack> validator) {
        super(capacity, validator);
    }

    public int fillFluid(FluidStack resource, boolean simulate) {
        int fluidFilled = Math.min(capacity - fluid.getAmount(), Math.min(this.capacity, resource.getAmount()));
        if (!simulate) fill(resource, FluidAction.EXECUTE);
        return fluidFilled;
    }

    public int takeFluid(final int amount, boolean simulate) {
        int fluidExtracted = Math.min(fluid.getAmount(), amount);
        if (!simulate) drain(amount, FluidAction.EXECUTE);
        return fluidExtracted;
    }
}
