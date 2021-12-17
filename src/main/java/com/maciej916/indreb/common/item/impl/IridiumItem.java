package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.item.base.BaseItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class IridiumItem extends BaseItem {

    public IridiumItem() {
        super(new Properties());
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
