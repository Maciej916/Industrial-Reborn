package com.maciej916.indreb.common.energy.provider;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.energy.provider.comparator.EnergyComparator;
import net.minecraft.core.BlockPos;

public class TransferTo extends EnergyComparator<TransferTo> {

    private final IEnergy energy;
    private final BlockPos pos;
    private final int leftReceive;

    public TransferTo(IEnergy energy, BlockPos pos, int leftReceive) {
        super(energy);
        this.energy = energy;
        this.pos = pos;
        this.leftReceive = leftReceive;
    }

    @Override
    public IEnergy getEnergy() {
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
