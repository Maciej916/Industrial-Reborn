package com.maciej916.indreb.common.item.base;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.enums.EnumLang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class EnergyStorageItem extends ElectricItem {

    public EnergyStorageItem(int energyStored, int maxEnergy, EnumEnergyType energyType, EnergyTier energyTier) {
        super(new Properties(), energyStored, maxEnergy, energyType, energyTier);
    }
}
