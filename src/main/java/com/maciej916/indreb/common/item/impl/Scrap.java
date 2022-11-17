package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.api.item.base.BaseItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class Scrap extends BaseItem {

    public Scrap() {
        super(CreativeModeTab.TAB_MATERIALS, new Properties());
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 200;
    }

}
