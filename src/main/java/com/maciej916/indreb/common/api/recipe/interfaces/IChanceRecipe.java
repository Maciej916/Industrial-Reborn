package com.maciej916.indreb.common.api.recipe.interfaces;

import com.maciej916.indreb.common.api.recipe.lib.ChanceResult;
import com.maciej916.indreb.common.api.recipe.lib.ChanceResultStack;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IChanceRecipe {

    default boolean hasBonus() {
        return getFirstBonusItem() != null;
    }

    @Deprecated
    default ItemStack getFirstBonusItem() {
        if (getChanceResultStacks().size() == 0) return null;
        return getChanceResultStacks().get(0).stack();
    }

    @Deprecated
    default ChanceResultStack getFirstBonusStack() {
        return getChanceResultStacks().get(0);
    }

    ChanceResult getChanceResult();
    List<ChanceResultStack> getChanceResultStacks();
    List<ItemStack> getChanceResultItemStacks();
    float getChanceForStack(ItemStack stack);
    ItemStack rollChanceResult();

}
