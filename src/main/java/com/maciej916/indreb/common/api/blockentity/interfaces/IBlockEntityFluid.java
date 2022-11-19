package com.maciej916.indreb.common.api.blockentity.interfaces;

import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public interface IBlockEntityFluid {

    List<FluidStack> getStoredFluids();
    void setStoredFluids(List<FluidStack> fluids);

}
