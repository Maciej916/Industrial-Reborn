package com.maciej916.indreb.common.energy.comparator;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import net.minecraft.world.item.ItemStack;

public class EnergyReceiveComparator extends EnergyComparator<EnergyReceiveComparator> {

    private final ItemStack stack;

    public EnergyReceiveComparator(IEnergyStorage energy, ItemStack stack) {
        super(energy);
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int compareTo(EnergyReceiveComparator o1) {
        return getEnergy().maxReceive() > o1.getEnergy().maxReceive() ? 1 : 0;
    }
}
