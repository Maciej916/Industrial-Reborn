package com.maciej916.indreb.common.energy.comparator;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;

public class EnergyComparator<T> implements Comparable<T> {

    protected final IEnergyStorage energy;

    public EnergyComparator(IEnergyStorage energy) {
        this.energy = energy;
    }

    public IEnergyStorage getEnergy() {
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
