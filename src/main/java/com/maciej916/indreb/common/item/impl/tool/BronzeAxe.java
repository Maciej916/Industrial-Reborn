package com.maciej916.indreb.common.item.impl.tool;

import com.maciej916.indreb.common.api.tier.CustomTiers;
import com.maciej916.indreb.common.item.ModItemGroups;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class BronzeAxe extends AxeItem {

    public BronzeAxe() {
        super(CustomTiers.BRONZE, 6.0F, -3.1F, new Properties().tab(ModItemGroups.MAIN));
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_TOOLS) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

}
