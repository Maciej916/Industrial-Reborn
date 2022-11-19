package com.maciej916.indreb.common.api.interfaces.fluid;

import net.minecraftforge.fluids.FluidStack;

public interface IFluidStorage {

    int fillFluid(FluidStack resource, boolean simulate);
    int takeFluid(final int amount, boolean simulate);
    void updated();
}
