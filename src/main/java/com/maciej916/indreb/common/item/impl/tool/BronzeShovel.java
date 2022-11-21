package com.maciej916.indreb.common.item.impl.tool;

import com.maciej916.indreb.common.api.tier.CustomTiers;
import com.maciej916.indreb.common.item.ModItemGroups;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;

public class BronzeShovel extends ShovelItem {

    public BronzeShovel() {
        super(CustomTiers.BRONZE, 1.5F, -3.0F, new Properties().tab(ModItemGroups.MAIN));
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_TOOLS) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

}
