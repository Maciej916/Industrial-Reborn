package com.maciej916.indreb.common.api.recipe;

import com.maciej916.indreb.common.api.recipe.interfaces.IBaseRecipe;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

public abstract class BaseRecipe implements Recipe<SimpleContainer>, IBaseRecipe {

    private final ResourceLocation id;

    private final IngredientCount ingredients;
    private final ItemStack result;

    private final int duration;
    private final int tickEnergyCost;
    private final float experience;

    public BaseRecipe(ResourceLocation id, IngredientCount ingredients, ItemStack result, int duration, int tickEnergyCost, float experience) {
        this.id = id;
        this.ingredients = ingredients;
        this.result = result;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        // Simple match first ingredient == first container item
        return ingredients.getIngredients().get(0).test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer container) {
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients.getIngredients();
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getTickEnergyCost() {
        return tickEnergyCost;
    }

    @Override
    public float getExperience() {
        return experience;
    }

    @Override
    public IngredientCount getIngredientCount() {
        return ingredients;
    }
}
