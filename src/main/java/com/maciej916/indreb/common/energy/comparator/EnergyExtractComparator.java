package com.maciej916.indreb.common.energy.comparator;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import net.minecraft.world.item.ItemStack;

public class EnergyExtractComparator extends EnergyComparator<EnergyExtractComparator> {

    private final ItemStack stack;

    public EnergyExtractComparator(IEnergyStorage energy, ItemStack stack) {
        super(energy);
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int compareTo(EnergyExtractComparator o1) {
        return getEnergy().maxExtract() > o1.getEnergy().maxExtract() ? 1 : 0;
    }
}
