package com.maciej916.indreb.common.item.base;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;

public class EnergyStorageItem extends ElectricItem {

    public EnergyStorageItem(int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        super(new Properties(), energyStored, maxEnergy, energyType, energyTier);
    }
}
