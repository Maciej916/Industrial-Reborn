package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.item.base.BaseItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FilledTinCan extends BaseItem {
    public static final FoodProperties TIN_CAN = (new FoodProperties.Builder()).nutrition(1).saturationMod(3.2F).fast().build();

    public FilledTinCan() {
        super(new Properties().food(TIN_CAN));
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
        if (tab == CreativeModeTab.TAB_FOOD) {
            list.add(new ItemStack(this));
        }
    }

}
