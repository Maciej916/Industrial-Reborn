package com.maciej916.indreb.common.energy.interfaces;

import com.maciej916.indreb.common.enums.EnumEnergyType;
import net.minecraft.core.Direction;

public interface IEnergy {

    int energyStored();
    int maxEnergy();
    int setEnergy(int amount);
    int setMaxEnergy(int amount);

    default boolean canReceiveEnergy() {
        return canReceiveEnergy(null);
    };

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
        if (!simulate) {
            setEnergy(energyStored() + received);
            updated();
        }
        return received;
    }

    default boolean canExtractEnergy() {
        return canExtractEnergy(null);
    };

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
        if (!simulate) {
            setEnergy(energyStored() - extracted);
            updated();
        }
        return extracted;
    }

    default int generateEnergy(final int amount, boolean simulate) {
        if (maxExtractTick() == 0) return 0;
        int energy = Math.min(maxEnergy() - energyStored(), Math.min(maxEnergy(), amount));
        if (!simulate) setEnergy(energyStored() + energy);
        return energy;
    }

    default int consumeEnergy(final int amount, boolean simulate) {
        int energy = Math.min(energyStored(), amount);
        if (!simulate) setEnergy(energyStored() - energy);
        return energy;
    }

    EnumEnergyType energyType();
    default void updated() {};

}
