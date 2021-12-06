package com.maciej916.indreb.common.energy.provider.comparator;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;

public class EnergyExtractComparatorNetwork extends EnergyComparator<EnergyExtractComparatorNetwork> {

    private final int maxExtract;

    public EnergyExtractComparatorNetwork(IEnergy energy, int maxExtract) {
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
