package com.maciej916.indreb.common.energy.interfaces;

import com.maciej916.indreb.common.energy.provider.EnergyNetworks;
import net.minecraft.core.BlockPos;

public interface IEnergyCore {

    void addEnergyBlock(BlockPos pos);
    void removeEnergyBlock(BlockPos pos);

    EnergyNetworks getNetworks();

    void tick();

}
