package com.maciej916.indreb.common.item.base;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DummyItem extends BaseItem {
    CreativeModeTab tab;

    public DummyItem(CreativeModeTab tab) {
        super(new Properties());
        this.tab = tab;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == tab) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

}
