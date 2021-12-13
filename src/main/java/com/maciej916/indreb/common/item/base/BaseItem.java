package com.maciej916.indreb.common.item.base;

import com.maciej916.indreb.common.registries.ModItemGroups;
import net.minecraft.world.item.Item;

public class BaseItem extends Item {

    public BaseItem(Properties properties) {
        super(properties.tab(ModItemGroups.MAIN_ITEM_GROUP));
    }
}
