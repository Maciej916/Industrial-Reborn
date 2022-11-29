package com.maciej916.indreb.common.item.impl.food;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class CannedFood extends BaseCanned {
    public static final FoodProperties TIN_CAN = (new FoodProperties.Builder()).nutrition(1).saturationMod(3.2F).fast().build();

    public CannedFood() {
        super(new Item.Properties().food(TIN_CAN));
    }

}
