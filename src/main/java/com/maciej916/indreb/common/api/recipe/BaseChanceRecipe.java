package com.maciej916.indreb.common.api.recipe;

import com.maciej916.indreb.common.api.recipe.interfaces.IChanceRecipe;
import com.maciej916.indreb.common.api.recipe.lib.ChanceResult;
import com.maciej916.indreb.common.api.recipe.lib.ChanceResultStack;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
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
    public List<ItemStack> getChanceResultItemStacks() {
        List<ItemStack> stacks = new ArrayList<>();
        for (ChanceResultStack resultStack : chanceResult.getResults()) {
            stacks.add(resultStack.stack());
        }
        return stacks;
    }

    @Override
    public float getChanceForStack(ItemStack stack) {
        for (ChanceResultStack resultStack : chanceResult.getResults()) {
            if (stack.getItem() == resultStack.stack().getItem()) {
                return (resultStack.rawChance() / Math.max(chanceResult.getTotalWeight(), 100) * 100);
            }
        }
        return 0f;
    }

    @Override
    public ItemStack rollChanceResult() {
        return chanceResult.rollResult();
    }
}
