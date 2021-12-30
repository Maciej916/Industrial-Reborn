package com.maciej916.indreb.common.energy.impl;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.interfaces.entity.IProgress;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class BasicEnergyStorage implements IEnergy, IProgress, INBTSerializable<CompoundTag> {

    private int energyStored;
    private int maxEnergy;

    public final int origEnergy;
    private final int maxReceiveTick;
    private final int maxExtractTick;
    private final EnumEnergyType energyType;

    public BasicEnergyStorage(int energyStored, int maxEnergy, int maxReceiveTick, int maxExtractTick, EnumEnergyType energyType) {
        this.energyStored = energyStored;
        this.origEnergy = maxEnergy;
        this.maxEnergy = maxEnergy;
        this.maxReceiveTick = maxReceiveTick;
        this.maxExtractTick = maxExtractTick;
        this.energyType = energyType;
    }

    @Override
    public int energyStored() {
        return energyStored;
    }

    @Override
    public int maxEnergy() {
        return maxEnergy;
    }

    @Override
    public int setEnergy(int amount) {
        energyStored = Math.min(maxEnergy, amount);
        return energyStored;
    }

    @Override
    public int setMaxEnergy(int amount) {
        this.maxEnergy = amount;
        if (energyStored > maxEnergy) this.energyStored = amount;
        return amount;
    }

    @Override
    public boolean canReceiveEnergy(Direction side) {
        return false;
    }

    @Override
    public int maxReceiveTick() {
        return maxReceiveTick;
    }

    @Override
    public boolean canExtractEnergy(Direction side) {
        return false;
    }

    @Override
    public int maxExtractTick() {
        return maxExtractTick;
    }

    @Override
    public EnumEnergyType energyType() {
        return energyType;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("energyStored", energyStored);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.energyStored = nbt.getInt("energyStored");
    }

    @Override
    public float getProgress() {
        return energyStored;
    }

    @Override
    public float getProgressMax() {
        return maxEnergy;
    }
}
