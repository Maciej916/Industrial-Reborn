package com.maciej916.indreb.common.item.impl.tool;

import com.maciej916.indreb.common.api.tier.CustomTiers;
import com.maciej916.indreb.common.item.ModItemGroups;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

public class BronzeSword extends SwordItem {

    public BronzeSword() {
        super(CustomTiers.BRONZE, 3, -2.4F, new Properties().tab(ModItemGroups.MAIN));
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_COMBAT) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

}
