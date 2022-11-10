package com.maciej916.indreb.common.recipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.interfaces.receipe.IBaseRecipe;
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
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class FluidExtrudingRecipe implements IBaseRecipe {

    public static final FluidExtrudingRecipe.Serializer SERIALIZER = new FluidExtrudingRecipe.Serializer();

    private final ResourceLocation recipeId;
    private Ingredient result;
    private int resultCount;

    private int waterCost;
    private int lavaCost;

    private float experience;
    private int duration;
    private int powerCost;

    public FluidExtrudingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public Cache<NonNullList<Ingredient>> getIngredientList() {
        return Cache.create(() -> RecipeUtil.nnListOf(result));
    }

    @Override
    public ItemStack getResultItem() {
        return result.getItems()[0];
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        return result.test(inv.getItem(0));
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
        return ModRecipeType.FLUID_EXTRUDING.get();
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

    public int getWaterCost() {
        return waterCost;
    }

    public int getLavaCost() {
        return lavaCost;
    }

    public static class Serializer implements RecipeSerializer<FluidExtrudingRecipe> {

        @Override
        public FluidExtrudingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            FluidExtrudingRecipe recipe = new FluidExtrudingRecipe(recipeId);

            recipe.result = Ingredient.fromJson(json.get("result"));

            JsonObject ingredient = GsonHelper.getAsJsonObject(json, "result");
            recipe.resultCount = GsonHelper.getAsInt(ingredient, "count", 1);

            recipe.experience = GsonHelper.getAsFloat(json, "experience", 0);
            recipe.duration = GsonHelper.getAsInt(json, "duration", 180);
            recipe.powerCost = GsonHelper.getAsInt(json, "power_cost", 8);
            recipe.waterCost = GsonHelper.getAsInt(json, "water_cost", 0);
            recipe.lavaCost = GsonHelper.getAsInt(json, "lava_cost", 0);

            return recipe;
        }

        @Nullable
        @Override
        public FluidExtrudingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            FluidExtrudingRecipe recipe = new FluidExtrudingRecipe(recipeId);

            recipe.waterCost = buffer.readInt();
            recipe.lavaCost = buffer.readInt();
            recipe.experience = buffer.readFloat();
            recipe.duration = buffer.readInt();
            recipe.powerCost = buffer.readInt();
            recipe.result = Ingredient.fromNetwork(buffer);
            recipe.resultCount = buffer.readInt();

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FluidExtrudingRecipe recipe) {
            buffer.writeInt(recipe.waterCost);
            buffer.writeInt(recipe.lavaCost);
            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.duration);
            buffer.writeInt(recipe.powerCost);
            recipe.result.toNetwork(buffer);
            buffer.writeInt(recipe.resultCount);
        }
    }
}