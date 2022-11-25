package com.maciej916.indreb.common.api.recipe.interfaces;

import com.maciej916.indreb.common.api.recipe.lib.ChanceResult;
import com.maciej916.indreb.common.api.recipe.lib.ChanceResultStack;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IChanceRecipe {

    default boolean hasBonus() {
        return getBonusItem() != null;
    }

    default ItemStack getBonusItem() {
        if (getChanceResultStacks().size() == 0) return null;
        return getChanceResultStacks().get(0).stack();
    }

    default ChanceResultStack getBonus() {
        return getChanceResultStacks().get(0);
    }

    ChanceResult getChanceResult();
    List<ChanceResultStack> getChanceResultStacks();
    ItemStack rollChanceResult();

}
