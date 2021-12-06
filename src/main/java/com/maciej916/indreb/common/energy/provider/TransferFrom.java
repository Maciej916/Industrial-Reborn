package com.maciej916.indreb.common.energy.provider;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.energy.provider.comparator.EnergyComparator;
import net.minecraft.core.BlockPos;

public class TransferFrom extends EnergyComparator<TransferFrom> {

    private final IEnergy energy;
    private final BlockPos pos;
    private final int maxExtract;

    public TransferFrom(IEnergy energy, BlockPos pos, int maxExtract) {
        super(energy);
        this.energy = energy;
        this.pos = pos;
        this.maxExtract = maxExtract;
    }

    public IEnergy getEnergy() {
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
