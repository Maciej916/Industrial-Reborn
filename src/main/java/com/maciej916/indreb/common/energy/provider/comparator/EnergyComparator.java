package com.maciej916.indreb.common.energy.provider.comparator;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;

public class EnergyComparator<T> implements Comparable<T> {

    protected final IEnergy energy;

    public EnergyComparator(IEnergy energy) {
        this.energy = energy;
    }

    public IEnergy getEnergy() {
        return energy;
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }

    @Override
    public String toString() {
        return "EnergyComparator{" +
                "energy=" + energy +
                '}';
    }
}
