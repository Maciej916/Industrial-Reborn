package com.maciej916.indreb.common.interfaces.item;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.LazyOptionalHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public interface IElectricItem {

    EnergyTier getEnergyTier();
    EnumEnergyType getEnergyType();



}
