package com.maciej916.indreb.common.energy;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.energy.comparator.EnergyComparator;
import net.minecraft.core.BlockPos;

public class TransferTo extends EnergyComparator<TransferTo> {

    private final IEnergyStorage energy;
    private final BlockPos pos;
    private final int leftReceive;

    public TransferTo(IEnergyStorage energy, BlockPos pos, int leftReceive) {
        super(energy);
        this.energy = energy;
        this.pos = pos;
        this.leftReceive = leftReceive;
    }

    @Override
    public IEnergyStorage getEnergy() {
        return energy;
    }

    public BlockPos getPos() {
        return pos;
    }

    public int getLeftReceive() {
        return leftReceive;
    }

    @Override
    public int compareTo(TransferTo o) {
        return getLeftReceive() > o.getLeftReceive() ? 1 : 0;
    }
}
