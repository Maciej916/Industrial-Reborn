package com.maciej916.indreb.common.energy;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.energy.comparator.EnergyComparator;
import net.minecraft.core.BlockPos;

public class TransferFrom extends EnergyComparator<TransferFrom> {

    private final IEnergyStorage energy;
    private final BlockPos pos;
    private final int maxExtract;

    public TransferFrom(IEnergyStorage energy, BlockPos pos, int maxExtract) {
        super(energy);
        this.energy = energy;
        this.pos = pos;
        this.maxExtract = maxExtract;
    }

    @Override
    public IEnergyStorage getEnergy() {
        return energy;
    }

    public BlockPos getPos() {
        return pos;
    }

    public int getMaxExtract() {
        return maxExtract;
    }

    @Override
    public int compareTo(TransferFrom o) {
        return getMaxExtract() > o.getMaxExtract() ? 1 : 0;
    }
}
