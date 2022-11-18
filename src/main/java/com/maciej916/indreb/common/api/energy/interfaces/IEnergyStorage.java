package com.maciej916.indreb.common.api.energy.interfaces;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import net.minecraft.core.Direction;

public interface IEnergyStorage {

    int energyStored();
    int maxEnergy();
    int setEnergy(int amount);
    void setMaxEnergy(int amount);

    default boolean canReceiveEnergy() {
        return canReceiveEnergy(null);
    }

    boolean canReceiveEnergy(Direction side);
    int maxReceiveTick();

    default int maxReceive() {
        return Math.min(maxEnergy() - energyStored(), maxReceiveTick());
    }

    default int receiveEnergy(int amount, boolean simulate) {
        return receiveEnergy(null, amount, simulate);
    }

    default int receiveEnergy(Direction side, int amount, boolean simulate) {
        if (!canReceiveEnergy(side) && side != null) return 0;
        int received = Math.min(amount, maxReceive());
        if (!simulate && received > 0) setEnergy(energyStored() + received);
        return received;
    }

    default boolean canExtractEnergy() {
        return canExtractEnergy(null);
    }

    boolean canExtractEnergy(Direction side);
    int maxExtractTick();

    default int maxExtract() {
        return Math.min(energyStored(), maxExtractTick());
    }

    default int extractEnergy(int amount, boolean simulate) {
        return extractEnergy(null, amount, simulate);
    }

    default int extractEnergy(Direction side, int amount, boolean simulate) {
        if (!canExtractEnergy(side) && side != null) return 0;
        int extracted = Math.min(energyStored(), Math.min(maxExtract(), amount));
        if (!simulate && extracted > 0) setEnergy(energyStored() - extracted);
        return extracted;
    }

    default int generateEnergy(final int amount, boolean simulate) {
        if (maxExtractTick() == 0) return 0;
        int energy = Math.min(maxEnergy() - energyStored(), Math.min(maxEnergy(), amount));
        if (!simulate && energy > 0) setEnergy(energyStored() + energy);
        return energy;
    }

    default int consumeEnergy(final int amount, boolean simulate) {
        int energy = Math.min(energyStored(), amount);
        if (!simulate && energy > 0) setEnergy(energyStored() - energy);
        return energy;
    }

    EnergyType energyType();
    void setEnergyType(EnergyType type);

    EnergyTier energyTier();
    void setEnergyTier(EnergyTier tier);

    default void updated() {}
}
