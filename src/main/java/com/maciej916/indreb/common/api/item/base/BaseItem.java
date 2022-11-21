package com.maciej916.indreb.common.api.item.base;

import com.maciej916.indreb.common.item.ModItemGroups;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class BaseItem extends Item {
    private final CreativeModeTab tab;

    public BaseItem(CreativeModeTab tab, Properties properties) {
        super(properties.tab(ModItemGroups.MAIN));
        this.tab = tab;
    }

    @Override
    public void fillItemCategory(CreativeModeTab creativeModeTab, NonNullList<ItemStack> stacks) {
        if (tab != null && creativeModeTab == tab) stacks.add(new ItemStack(this));
        super.fillItemCategory(creativeModeTab, stacks);
    }

}
