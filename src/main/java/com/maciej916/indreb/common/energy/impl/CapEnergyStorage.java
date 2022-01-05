package com.maciej916.indreb.common.energy.impl;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.registries.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapEnergyStorage extends BasicEnergyStorage implements ICapabilityProvider {

    private final LazyOptional<IEnergy> energy = LazyOptional.of(() -> this);

    public CapEnergyStorage(int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        super(energyStored, maxEnergy, energyType, energyTier);
    }

    @Override
    public boolean canExtractEnergy(Direction side) {
        return energyType() == EnergyType.EXTRACT || energyType() == EnergyType.BOTH;
    }

    @Override
    public boolean canReceiveEnergy(Direction side) {
        return energyType() == EnergyType.RECEIVE || energyType() == EnergyType.BOTH;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.ENERGY) {
            return energy.cast();
        }
        return LazyOptional.empty();
    }
}
