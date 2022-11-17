package com.maciej916.indreb.common.api.item.base;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class MaterialItemRare extends BaseItem {

    public MaterialItemRare() {
        super(CreativeModeTab.TAB_MATERIALS, new Properties());
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
