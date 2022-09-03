package com.maciej916.indreb.common.recipe;

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

    protected final ResourceLocation recipeId;
    protected Ingredient ingredient;
    protected Cache<NonNullList<Ingredient>> ingredientList;
    protected int ingredientCount;

    protected ItemStack result;
    protected RecipeChanceResult bonusResult;

    protected float experience;
    protected int duration;
    protected int powerCost;

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

    @Override
    public float getExperience() {
        return experience;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
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