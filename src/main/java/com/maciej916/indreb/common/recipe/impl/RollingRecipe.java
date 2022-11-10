package com.maciej916.indreb.common.recipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.interfaces.receipe.IBaseRecipe;
import com.maciej916.indreb.common.interfaces.receipe.IRecipeSingleIngredient;
import com.maciej916.indreb.common.recipe.RecipeChanceResult;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.util.Cache;
import com.maciej916.indreb.common.util.RecipeUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class RollingRecipe implements IRecipeSingleIngredient {

    public static final RollingRecipe.Serializer SERIALIZER = new RollingRecipe.Serializer();
    private final ResourceLocation recipeId;
    protected Ingredient ingredient;
    protected Cache<NonNullList<Ingredient>> ingredientList;
    protected int ingredientCount;
    protected ItemStack result;
    private float experience;
    private int duration;
    private int powerCost;

    public RollingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
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
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.ROLLING.get();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return getResultItem().copy();
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

    public int getIngredientCount() {
        return ingredientCount;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredientList.get();
    }

    public static class Serializer implements RecipeSerializer<RollingRecipe> {

        @Override
        public RollingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            RollingRecipe recipe = new RollingRecipe(recipeId);

            recipe.ingredient = Ingredient.fromJson(json.get("ingredient"));

            JsonObject ingredient = GsonHelper.getAsJsonObject(json, "ingredient");
            recipe.ingredientCount = GsonHelper.getAsInt(ingredient, "count", 1);

            recipe.result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            recipe.experience = GsonHelper.getAsFloat(json, "experience", 0);
            recipe.duration = GsonHelper.getAsInt(json, "duration", 180);
            recipe.powerCost = GsonHelper.getAsInt(json, "power_cost", 8);

            recipe.ingredientList = Cache.create(() -> RecipeUtil.nnListOf(recipe.ingredient));

            return recipe;
        }

        @Nullable
        @Override
        public RollingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            RollingRecipe recipe = new RollingRecipe(recipeId);

            recipe.result = buffer.readItem();
            recipe.experience = buffer.readFloat();
            recipe.duration = buffer.readInt();
            recipe.powerCost = buffer.readInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);
            recipe.ingredientCount = buffer.readInt();
            recipe.ingredientList = Cache.create(() -> RecipeUtil.nnListOf(recipe.ingredient));

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, RollingRecipe recipe) {
            buffer.writeItemStack(recipe.result, false);
            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.duration);
            buffer.writeInt(recipe.powerCost);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeInt(recipe.ingredientCount);
        }
    }
}