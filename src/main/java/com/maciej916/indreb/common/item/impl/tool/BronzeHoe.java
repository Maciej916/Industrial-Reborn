package com.maciej916.indreb.common.item.impl.tool;

import com.maciej916.indreb.common.api.tier.CustomTiers;
import com.maciej916.indreb.common.item.ModItemGroups;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;

public class BronzeHoe extends HoeItem {

    public BronzeHoe() {
        super(CustomTiers.BRONZE, -2, -1.0F, new Properties().tab(ModItemGroups.MAIN));
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_TOOLS) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

}
