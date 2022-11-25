package com.maciej916.indreb.common.api.recipe;

import com.maciej916.indreb.common.api.recipe.interfaces.IChanceRecipe;
import com.maciej916.indreb.common.api.recipe.lib.ChanceResult;
import com.maciej916.indreb.common.api.recipe.lib.ChanceResultStack;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public abstract class BaseChanceRecipe extends BaseRecipe implements IChanceRecipe {

    private final ChanceResult chanceResult;

    public BaseChanceRecipe(ResourceLocation id, IngredientCount ingredients, ItemStack result, int duration, int tickEnergyCost, float experience, ChanceResult chanceResult) {
        super(id, ingredients, result, duration, tickEnergyCost, experience);
        this.chanceResult = chanceResult;
    }

    public ChanceResult getChanceResult() {
        return chanceResult;
    }

    @Override
    public List<ChanceResultStack> getChanceResultStacks() {
        return chanceResult.getResults();
    }

    @Override
    public ItemStack rollChanceResult() {
        return chanceResult.rollResult();
    }
}
