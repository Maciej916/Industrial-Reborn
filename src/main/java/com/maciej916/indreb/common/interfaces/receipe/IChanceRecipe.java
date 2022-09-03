package com.maciej916.indreb.common.interfaces.receipe;

import com.maciej916.indreb.common.recipe.ChanceResult;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IChanceRecipe extends IBaseRecipe {

    default boolean hasBonus() {
        return getBonusItem() != null;
    }

    default ItemStack getBonusItem() {
        if (getChanceResults().size() == 0) return null;
        return getChanceResults().get(0).stack();
    }

    default ChanceResult getBonus() {
        return getChanceResults().get(0);
    }

    int getIngredientCount();
    List<ChanceResult> getChanceResults();
    ItemStack rollChanceResult();
}
