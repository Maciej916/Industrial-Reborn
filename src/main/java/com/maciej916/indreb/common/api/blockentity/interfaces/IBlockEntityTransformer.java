package com.maciej916.indreb.common.api.blockentity.interfaces;

import com.maciej916.indreb.common.api.enums.EnergyTier;

public interface IBlockEntityTransformer {

    EnergyTier energyExtractTier();
    EnergyTier energyReceiveTier();

}
