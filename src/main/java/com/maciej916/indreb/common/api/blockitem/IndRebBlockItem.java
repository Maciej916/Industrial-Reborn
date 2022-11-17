package com.maciej916.indreb.common.api.blockitem;

import com.maciej916.indreb.common.item.ModItemGroups;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

public class IndRebBlockItem extends BlockItem {

    Rarity rarity;

    public IndRebBlockItem(Block block, Rarity rarity) {
        super(block, new Item.Properties().tab(ModItemGroups.MAIN));
        this.rarity = rarity;
    }


    @Override
    public Rarity getRarity(ItemStack pStack) {
        return rarity;
    }
}
