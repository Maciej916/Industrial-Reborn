package com.maciej916.indreb.common.energy.comparator;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;

public class EnergyExtractComparatorNetwork extends EnergyComparator<EnergyExtractComparatorNetwork> {

    private final int maxExtract;

    public EnergyExtractComparatorNetwork(IEnergyStorage energy, int maxExtract) {
        super(energy);
        this.maxExtract = maxExtract;
    }

    public int getMaxExtract() {
        return maxExtract;
    }

    @Override
    public int compareTo(EnergyExtractComparatorNetwork o1) {
        return getMaxExtract() > o1.getMaxExtract() ? 1 : 0;
    }
}
