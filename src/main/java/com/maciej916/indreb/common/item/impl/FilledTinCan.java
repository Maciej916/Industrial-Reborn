package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.api.item.base.BaseItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class FilledTinCan extends BaseItem {
    public static final FoodProperties TIN_CAN = (new FoodProperties.Builder()).nutrition(1).saturationMod(3.2F).fast().build();

    public FilledTinCan() {
        super(CreativeModeTab.TAB_FOOD, new Item.Properties().food(TIN_CAN));
    }

}
