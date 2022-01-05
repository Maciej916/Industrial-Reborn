package com.maciej916.indreb.common.energy.impl;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.interfaces.entity.IProgress;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class BasicEnergyStorage implements IEnergy, IProgress, INBTSerializable<CompoundTag> {

    private int energyStored;
    private int maxEnergy;

    private final EnergyType energyType;
    private EnergyTier energyTier;

    public final int origEnergy;
    public final EnergyTier origTier;

    private int lastGenerated;
    private int totalGenerated;

    private int lastConsumed;
    private int totalConsumed;

    public BasicEnergyStorage(int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        this.energyStored = energyStored;
        this.maxEnergy = maxEnergy;
        this.energyType = energyType;
        this.energyTier = energyTier;

        this.origEnergy = maxEnergy;
        this.origTier = energyTier;

        this.lastGenerated = 0;
        this.totalGenerated = 0;
        this.lastConsumed = 0;
        this.totalConsumed = 0;
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
        updated();
        return energyStored;
    }

    @Override
    public void setMaxEnergy(int amount) {
        this.maxEnergy = amount;
        if (energyStored > maxEnergy) this.energyStored = amount;
        updated();
    }

    @Override
    public boolean canReceiveEnergy(Direction side) {
        return false;
    }

    @Override
    public int maxReceiveTick() {
        return energyType == EnergyType.RECEIVE || energyType == EnergyType.BOTH ? energyTier.getBasicTransfer() : 0;
    }

    @Override
    public boolean canExtractEnergy(Direction side) {
        return false;
    }

    @Override
    public int maxExtractTick() {
        return energyType == EnergyType.EXTRACT || energyType == EnergyType.BOTH ? energyTier.getBasicTransfer() : 0;
    }

    public void updateGenerated(int amount) {
        this.lastGenerated = amount;
        if (amount > 0) {
            this.totalGenerated = this.totalGenerated + amount;
        }
    }

    public void updateConsumed(int amount) {
        this.lastConsumed = amount;
        if (amount > 0) {
            this.totalConsumed = this.totalConsumed + amount;
        }
    }

    public int totalGenerated() {
        return totalGenerated;
    }

    public int lastGenerated() {
        return lastGenerated;
    }

    public int totalConsumed() {
        return totalConsumed;
    }

    public int lastConsumed() {
        return lastConsumed;
    }

    @Override
    public EnergyType energyType() {
        return energyType;
    }

    @Override
    public EnergyTier energyTier() {
        return energyTier;
    }

    @Override
    public void setEnergyTier(EnergyTier tier) {
        this.energyTier = tier;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("energyStored", energyStored);
        tag.putInt("maxEnergy", maxEnergy);

        tag.putInt("lastGenerated", lastGenerated);
        tag.putInt("totalGenerated", totalGenerated);
        tag.putInt("lastConsumed", lastConsumed);
        tag.putInt("totalConsumed", totalConsumed);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.energyStored = nbt.getInt("energyStored");
        this.maxEnergy = nbt.getInt("maxEnergy");

        this.lastGenerated = nbt.getInt("lastGenerated");
        this.totalGenerated = nbt.getInt("totalGenerated");
        this.lastConsumed = nbt.getInt("lastConsumed");
        this.totalConsumed = nbt.getInt("totalConsumed");
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
