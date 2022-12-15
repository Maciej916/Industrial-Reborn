package com.maciej916.indreb.common.capability.radiation;

import com.maciej916.indreb.common.capability.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RadiationCapability implements IRadiationCapability, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<IRadiationCapability> radiationCap = LazyOptional.of(() -> this);
    private final double addRads;
    private final double removeRads;

    public RadiationCapability(IHasRadiation hasRadiation) {
        this.addRads = hasRadiation.getAddRads();
        this.removeRads = hasRadiation.getRemoveRads();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.RADIATION) {
            return radiationCap.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public double getAddRads() {
        return addRads;
    }

    @Override
    public double getRemoveRads() {
        return removeRads;
    }

    @Override
    public void clone(IRadiationCapability capability) {

    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();


        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }
}
