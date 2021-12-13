package com.maciej916.indreb.common.item.impl.treetap;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.item.base.ElectricItem;

public class ElectricTreetap extends ElectricItem {
    public ElectricTreetap(int energyStored, int maxEnergy, EnumEnergyType energyType, EnergyTier energyTier) {
        super(new Properties(), energyStored, maxEnergy, energyType, energyTier);
    }
}
