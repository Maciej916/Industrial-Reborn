package com.maciej916.indreb.common.receipe;

import com.maciej916.indreb.common.interfaces.receipe.IChanceRecipe;
import com.maciej916.indreb.common.util.Cache;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class BasicChanceRecipe implements IChanceRecipe {

    public ResourceLocation recipeId;
    public Ingredient ingredient;
    public Cache<NonNullList<Ingredient>> ingredientList;
    public int ingredientCount;

    public ItemStack result;
    public RecipeChanceResult bonusResult;

    public float experience;
    public int duration;
    public int powerCost;

    public BasicChanceRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
        this.bonusResult = new RecipeChanceResult();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        return ingredient.test(inv.getItem(0));
    }

    @Override
    public ResourceLocation getId() {
        return recipeId;
    }


    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return result.copy();
    }

    @Override
    public List<ChanceResult> getChanceResults() {
        return bonusResult.getResults();
    }

    @Override
    public ItemStack rollChanceResult() {
        return bonusResult.rollResult();
    }

    public float getExperience() {
        return experience;
    }

    public int getDuration() {
        return duration;
    }

    public int getPowerCost() {
        return powerCost;
    }

    @Override
    public int getIngredientCount() {
        return ingredientCount;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredientList.get();
    }
}