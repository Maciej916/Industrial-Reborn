package com.maciej916.indreb.common.interfaces.entity;

import com.maciej916.indreb.common.enums.EnergyTier;

import java.util.List;

public interface IElectricSlot extends ISlot {

    boolean isCharging();
    List<EnergyTier> getAllowedTiers();

}
