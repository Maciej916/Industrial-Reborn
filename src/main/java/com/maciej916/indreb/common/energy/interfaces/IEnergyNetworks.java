package com.maciej916.indreb.common.energy.interfaces;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.energy.EnergyNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IEnergyNetworks {

    Level getLevel();

    EnergyNetwork getNetwork(BlockPos pos);
    EnergyNetwork getNetworkOther(BlockPos pos);
    EnergyNetwork createNetwork(BlockPos pos, EnergyTier energyTier);

    void removeNetwork(EnergyNetwork network);

    void onPlaced(BlockPos pos, BlockState state, EnergyTier energyTier);
    void onRemove(BlockPos pos);
    void neighborChanged(BlockPos pos, BlockPos neighborPos);
}