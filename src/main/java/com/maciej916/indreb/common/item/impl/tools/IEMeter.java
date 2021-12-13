package com.maciej916.indreb.common.item.impl.tools;

import com.maciej916.indreb.common.item.base.DummyItem;
import com.maciej916.indreb.common.item.base.ToolItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class IEMeter extends DummyItem {
    public IEMeter(CreativeModeTab tab) {
        super(tab);
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_TOOLS) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }
}
