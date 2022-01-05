package com.maciej916.indreb.common.energy.provider;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EnergyNetwork implements IEnergy, INBTSerializable<CompoundTag> {

    private int energy = 0;
    private int energyMax = 0;

    private HashSet<BlockPos> connections = new HashSet<>();
    private HashSet<BlockPos> electrics = new HashSet<>();
    private HashSet<BlockPos> transmitters = new HashSet<>();

    private EnergyTier energyTier;

    public float r,g,b;

    public EnergyNetwork() {
        initColor();
    }

    public EnergyNetwork(BlockPos pos, EnergyTier energyTier) {
        this.connections.add(pos);
        this.energyMax = energyTier.getBasicTransfer();
        this.energyTier = energyTier;
        initColor();
    }

    public EnergyNetwork(int energy, EnergyTier energyTier) {
        this.energy = energy;
        this.energyMax = energyTier.getBasicTransfer();
        this.energyTier = energyTier;
        initColor();
    }

    void initColor() {
        this.r = (float) Math.random();
        this.g = (float) Math.random();
        this.b = (float) Math.random();
    }

    public EnergyTier getEnergyTier() {
        return energyTier;
    }

    public int getEnergy() {
        return energy;
    }

    public HashSet<BlockPos> getConnections() {
        return connections;
    }

    public HashSet<BlockPos> getElectrics() {
        return electrics;
    }

    public HashSet<BlockPos> getTransmitters() {
        return transmitters;
    }

    public void add(HashSet<BlockPos> set, BlockPos pos) {
        set.add(pos);
    }

    public void add(HashSet<BlockPos> set, HashSet<BlockPos> pos) {
        set.addAll(pos);
    }

    public void remove(HashSet<BlockPos> set, BlockPos pos) {
        set.remove(pos);
    }

    public void remove(HashSet<BlockPos> set, HashSet<BlockPos> pos) {
        set.removeAll(pos);
    }

    public boolean hasConnection(BlockPos pos) {
        return connections.contains(pos);
    }

    public boolean hasElectric(BlockPos pos) {
        return electrics.contains(pos);
    }

    public boolean hasTransmitter(BlockPos pos) {
        return transmitters.contains(pos);
    }

    @Override
    public int energyStored() {
        return energy;
    }

    @Override
    public int maxEnergy() {
        return energyTier.getBasicTransfer();
    }

    @Override
    public int setEnergy(int amount) {
        energy = Math.min(amount, energyMax);
        return energy;
    }

    @Override
    public void setMaxEnergy(int amount) {
        this.energyMax = amount;
        if (energy > energyMax) this.energy = amount;
    }

    @Override
    public boolean canReceiveEnergy(Direction side) {
        return true;
    }

    @Override
    public int maxReceiveTick() {
        return energyMax;
    }

    @Override
    public boolean canExtractEnergy(Direction side) {
        return true;
    }

    @Override
    public int maxExtractTick() {
        return energyMax;
    }

    @Override
    public EnergyType energyType() {
        return EnergyType.CABLE;
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
        CompoundTag nbt = new CompoundTag();

        nbt.putInt("energy", energy);
        nbt.putInt("energyMax", energyMax);
        nbt.putInt("energyTier", energyTier.getLvl());

        List<Long> connections = new ArrayList<>();
        for (final BlockPos pos : getConnections()) {
            connections.add(pos.asLong());
        }
        nbt.putLongArray("connections", connections);

        List<Long> electrics = new ArrayList<>();
        for (final BlockPos pos : getElectrics()) {
            electrics.add(pos.asLong());
        }
        nbt.putLongArray("electrics", electrics);

        List<Long> transmitters = new ArrayList<>();
        for (final BlockPos pos : getTransmitters()) {
            transmitters.add(pos.asLong());
        }
        nbt.putLongArray("transmitters", transmitters);


        nbt.putFloat("r", r);
        nbt.putFloat("g", g);
        nbt.putFloat("b", b);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        energy = nbt.getInt("energy");
        energyMax = nbt.getInt("energyMax");
        energyTier = EnergyTier.getTierFromLvl(nbt.getInt("energyTier"));

        HashSet<BlockPos> nbtConnections = new HashSet<>();
        for (long longPos : nbt.getLongArray("connections")) {
            BlockPos pos = BlockPos.of(longPos);
            nbtConnections.add(pos);
        }
        connections = nbtConnections;

        HashSet<BlockPos> nbtElectrics = new HashSet<>();
        for (long longPos : nbt.getLongArray("electrics")) {
            BlockPos pos = BlockPos.of(longPos);
            nbtElectrics.add(pos);
        }
        electrics = nbtElectrics;

        HashSet<BlockPos> nbtTransmitters = new HashSet<>();
        for (long longPos : nbt.getLongArray("transmitters")) {
            BlockPos pos = BlockPos.of(longPos);
            nbtTransmitters.add(pos);
        }
        transmitters = nbtTransmitters;

        r = nbt.getFloat("r");
        g = nbt.getFloat("g");
        b = nbt.getFloat("b");
    }

    @Override
    public String toString() {
        return "EnergyNetwork{" +
                ", energy=" + energy +
                ", energyMax=" + energyMax +
                ", connections=" + connections +
                ", electrics=" + electrics +
                ", transmitters=" + transmitters +
                '}';
    }
}
