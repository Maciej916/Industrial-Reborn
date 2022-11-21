package com.maciej916.indreb.common.capability.energy;

import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.capability.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BasicEnergyStorageProvider extends BasicEnergyStorage implements ICapabilityProvider {

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> this);

    public BasicEnergyStorageProvider(int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
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


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.ENERGY) {
            return energy.cast();
        }
        return LazyOptional.empty();
    }
}
