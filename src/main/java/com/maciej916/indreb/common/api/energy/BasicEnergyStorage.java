package com.maciej916.indreb.common.api.energy;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class BasicEnergyStorage implements IEnergyStorage, IBaseProgress, INBTSerializable<CompoundTag> {

    public int energyStored;
    public int maxEnergy;

    public EnergyType energyType;
    public EnergyTier energyTier;

    public final int origEnergy;
    public final EnergyTier origTier;

    public int lastGenerated;
    public int totalGenerated;

    public int lastConsumed;
    public int totalConsumed;

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

    public void setTotalGenerated(int totalGenerated) {
        this.totalGenerated = totalGenerated;
    }

    public int lastGenerated() {
        return lastGenerated;
    }

    public void setLastGenerated(int lastGenerated) {
        this.lastGenerated = lastGenerated;
    }

    public int totalConsumed() {
        return totalConsumed;
    }

    public void setTotalConsumed(int totalConsumed) {
        this.totalConsumed = totalConsumed;
    }

    public int lastConsumed() {
        return lastConsumed;
    }

    public void setLastConsumed(int lastConsumed) {
        this.lastConsumed = lastConsumed;
    }

    @Override
    public EnergyType energyType() {
        return energyType;
    }

    @Override
    public void setEnergyType(EnergyType type) {
        this.energyType = type;

        System.out.println("setEnergyType");
        updated();
    }

    @Override
    public EnergyTier energyTier() {
        return energyTier;
    }

    @Override
    public void setEnergyTier(EnergyTier tier) {
        this.energyTier = tier;
        updated();
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
    public float currentProgress() {
        return energyStored;
    }

    @Override
    public float getProgressMax() {
        return maxEnergy;
    }
}
