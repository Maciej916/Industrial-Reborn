package com.maciej916.indreb.common.energy.interfaces;

import com.maciej916.indreb.common.energy.provider.EnergyNetworks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;

public interface IEnergyCore {

    void addEnergyBlock(BlockPos pos);
    void removeEnergyBlock(BlockPos pos);

    EnergyNetworks getNetworks();

    void tick();

    CompoundTag getNetworkTag(@Nullable BlockPos pos);
    void setNetworkTag(CompoundTag tag);
}
