package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.item.base.MaterialItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class Iridium extends MaterialItem {

    public Iridium() {
        super();
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
