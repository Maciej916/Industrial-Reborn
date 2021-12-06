package com.maciej916.indreb.common.energy.provider;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.HashSet;
import java.util.List;

public class EnergyTransfer {

    BlockEntity be;
    IEnergy energy;
    BlockPos pos;
    HashSet<EnergyNetwork> networksTo;


    public EnergyTransfer(BlockEntity be, IEnergy energy, BlockPos pos, EnergyNetwork transmitter) {
        this.be = be;
        this.energy = energy;
        this.pos = pos;
        this.networksTo = new HashSet<>(List.of(transmitter));
    }

    public IEnergy getEnergy() {
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
            int transferThis = transferBase + powerLeft;
            return transferThis;
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
