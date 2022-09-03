package com.maciej916.indreb.common.interfaces.receipe;

import com.maciej916.indreb.common.recipe.ChanceResult;
import net.minecraft.world.item.ItemStack;

public interface IBonusRecipe extends IChanceRecipe {

    @Override
    default boolean hasBonus() {
        return getBonusItem() != null;
    }

    @Override
    default ItemStack getBonusItem() {
        if (getChanceResults().size() == 0) return null;
        return getChanceResults().get(0).stack();
    }

    @Override
    default ChanceResult getBonus() {
        return getChanceResults().get(0);
    }
}
