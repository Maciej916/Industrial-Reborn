package com.maciej916.indreb.common.interfaces.item;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import net.minecraft.world.item.ItemStack;

public interface IElectricItem {

    EnergyTier getEnergyTier();
    EnergyType getEnergyType();
    IEnergy getEnergy(ItemStack stack);


}
