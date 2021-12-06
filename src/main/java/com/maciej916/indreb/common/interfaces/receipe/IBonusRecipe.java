package com.maciej916.indreb.common.interfaces.receipe;

import com.maciej916.indreb.common.receipe.ChanceResult;
import net.minecraft.world.item.ItemStack;

public interface IBonusRecipe extends IChanceRecipe {

    default boolean hasBonus() {
        return getBonusItem() != null;
    }

    default ItemStack getBonusItem() {
        if (getChanceResults().size() == 0) return null;
        return getChanceResults().get(0).getStack();
    }

    default ChanceResult getBonus() {
        return getChanceResults().get(0);
    }
}
