package com.maciej916.indreb.common.api.interfaces.item;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import net.minecraft.world.item.ItemStack;

public interface IElectricItem {

    EnergyTier getEnergyTier();
    EnergyType getEnergyType();
    IEnergyStorage getEnergyStorage(ItemStack stack);
    default void tickElectric(ItemStack stack) {}

}
