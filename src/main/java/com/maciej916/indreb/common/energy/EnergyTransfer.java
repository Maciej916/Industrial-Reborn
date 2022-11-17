package com.maciej916.indreb.common.energy;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.HashSet;
import java.util.List;

public class EnergyTransfer {

    private final BlockEntity be;
    private final IEnergyStorage energy;
    private final BlockPos pos;
    private final HashSet<EnergyNetwork> networksTo;


    public EnergyTransfer(BlockEntity be, IEnergyStorage energy, BlockPos pos, EnergyNetwork transmitter) {
        this.be = be;
        this.energy = energy;
        this.pos = pos;
        this.networksTo = new HashSet<>(List.of(transmitter));
    }

    public IEnergyStorage getEnergy() {
        return energy;
    }

    public HashSet<EnergyNetwork> getNetworksTo() {
        return networksTo;
    }

    public int getMaxTransferToTransmitter(EnergyNetwork trans) {
        if (networksTo.size() == 1) {
            return energy.maxExtract();
        } else {
            int otherMaxReceive = 0;
            for (EnergyNetwork eTrans : networksTo) {
                if (eTrans != trans) {
                    otherMaxReceive += eTrans.maxReceive();
                }
            }

            int transferBase = energy.maxExtract() / networksTo.size();
            int powerLeft = Math.max(energy.maxExtract() - otherMaxReceive, 0);
            return (transferBase + powerLeft);
        }
    }

    @Override
    public String toString() {
        return "EnergyTransfer{" +
                "energy=" + energy +
                ", pos=" + pos +
                ", networksTo=" + networksTo +
                '}';
    }
}
