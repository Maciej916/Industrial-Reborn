package com.maciej916.indreb.common.energy.provider.comparator;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;

public class EnergyReceiveComparator extends EnergyComparator<EnergyReceiveComparator> {

    public EnergyReceiveComparator(IEnergy energy) {
        super(energy);
    }

    @Override
    public int compareTo(EnergyReceiveComparator o1) {
        return getEnergy().maxReceive() > o1.getEnergy().maxReceive() ? 1 : 0;
    }
}
