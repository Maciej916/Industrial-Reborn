package com.maciej916.indreb.common.energy.provider.comparator;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;

public class EnergyExtractComparator extends EnergyComparator<EnergyExtractComparator> {

    public EnergyExtractComparator(IEnergy energy) {
        super(energy);
    }

    @Override
    public int compareTo(EnergyExtractComparator o1) {
        return getEnergy().maxExtract() > o1.getEnergy().maxExtract() ? 1 : 0;
    }
}
