package com.maciej916.indreb.common.item.impl.tools;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.item.base.ElectricItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ElectricHoe extends ElectricItem {
    public ElectricHoe(int energyStored, int maxEnergy, EnumEnergyType energyType, EnergyTier energyTier) {
        super(new Properties(), energyStored, maxEnergy, energyType, energyTier);
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_TOOLS) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }
}
