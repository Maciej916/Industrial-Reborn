package com.maciej916.indreb.common.api.item.base;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;

public class EnergyStorageItem extends BaseElectricItem {

    public EnergyStorageItem(int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        super(new Properties(), energyStored, maxEnergy, energyType, energyTier);
    }
}
