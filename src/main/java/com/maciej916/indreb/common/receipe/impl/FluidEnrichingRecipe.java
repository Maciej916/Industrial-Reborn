package com.maciej916.indreb.common.receipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.interfaces.receipe.IBaseRecipe;
import com.maciej916.indreb.common.receipe.RecipeChanceResult;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.util.Cache;
import com.maciej916.indreb.common.util.RecipeUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;

public class FluidEnrichingRecipe implements IBaseRecipe {

    public static final FluidEnrichingRecipe.Serializer SERIALIZER = new FluidEnrichingRecipe.Serializer();
    private ResourceLocation recipeId;

    public Ingredient ingredient;
    private int ingredientCount;

    private FluidStack fluidInput;
    private FluidStack result;

    private float experience;
    private int duration;
    private int powerCost;

    public FluidEnrichingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return null;
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        return ingredient.getItems().length > 0 && ingredient.getItems()[0].getItem() == inv.getItem(0).getItem();
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
        return ModRecipeType.FLUID_ENRICHING;
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

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return null;
    }

    public FluidStack getFluidInput() {
        return fluidInput;
    }

    public FluidStack getResult() {
        return result;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FluidEnrichingRecipe> {

        @Override
        public FluidEnrichingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            FluidEnrichingRecipe recipe = new FluidEnrichingRecipe(recipeId);

            recipe.ingredient = Ingredient.fromJson(json.get("ingredient"));
            JsonObject firstIngredient = GsonHelper.getAsJsonObject(json, "ingredient");
            recipe.ingredientCount = GsonHelper.getAsInt(firstIngredient, "count", 1);

            JsonObject fluidIngredient = GsonHelper.getAsJsonObject(json, "fluid_ingredient");
            ResourceLocation fluidResourceLocation = new ResourceLocation(GsonHelper.getAsString(fluidIngredient, "fluid", "minecraft:empty"));
            recipe.fluidInput = new FluidStack(Objects.requireNonNull(ForgeRegistries.FLUIDS.getValue(fluidResourceLocation)), GsonHelper.getAsInt(fluidIngredient, "amount", 1));

            JsonObject resultIngredient = GsonHelper.getAsJsonObject(json, "result");
            ResourceLocation resultResourceLocation = new ResourceLocation(GsonHelper.getAsString(resultIngredient, "fluid", "minecraft:empty"));
            recipe.result = new FluidStack(Objects.requireNonNull(ForgeRegistries.FLUIDS.getValue(resultResourceLocation)), GsonHelper.getAsInt(fluidIngredient, "amount", 1));

            recipe.experience = GsonHelper.getAsFloat(json, "experience", 0);
            recipe.duration = GsonHelper.getAsInt(json, "duration", 180);
            recipe.powerCost = GsonHelper.getAsInt(json, "power_cost", 8);

            return recipe;
        }

        @Nullable
        @Override
        public FluidEnrichingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            FluidEnrichingRecipe recipe = new FluidEnrichingRecipe(recipeId);

            recipe.result = buffer.readFluidStack();
            recipe.experience = buffer.readFloat();
            recipe.duration = buffer.readInt();
            recipe.powerCost = buffer.readInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);
            recipe.ingredientCount = buffer.readInt();
            recipe.fluidInput = buffer.readFluidStack();

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FluidEnrichingRecipe recipe) {
            buffer.writeFluidStack(recipe.result);
            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.duration);
            buffer.writeInt(recipe.powerCost);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeInt(recipe.ingredientCount);
            buffer.writeFluidStack(recipe.fluidInput);
        }
    }
}