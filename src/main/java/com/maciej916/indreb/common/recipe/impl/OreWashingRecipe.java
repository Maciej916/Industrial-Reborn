package com.maciej916.indreb.common.recipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.BaseChanceRecipe;
import com.maciej916.indreb.common.api.recipe.lib.ChanceResult;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.ResultItem;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class OreWashingRecipe extends BaseChanceRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    private final FluidStack fluidInput;

    public OreWashingRecipe(ResourceLocation id, IngredientCount ingredients, ItemStack result, FluidStack fluidInput, int duration, int tickEnergyCost, float experience, ChanceResult chanceResult) {
        super(id, ingredients, result, duration, tickEnergyCost, experience, chanceResult);
        this.fluidInput = fluidInput;
    }

    public FluidStack getFluidInput() {
        return fluidInput;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.ORE_WASHING.get();
    }

    public static class Serializer implements RecipeSerializer<OreWashingRecipe> {

        @Override
        public OreWashingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            IngredientCount ingredientCount = IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            FluidStack fluidInput = ResultItem.fluidFromJson(GsonHelper.getAsJsonObject(json, "fluid_input"));

            int duration = GsonHelper.getAsInt(json, "duration", 500);
            int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 16);
            float experience = GsonHelper.getAsFloat(json, "experience", 0);

            ChanceResult chanceResult = ChanceResult.fromJson(GsonHelper.getAsJsonArray(json, "chance_result"));

            return new OreWashingRecipe(recipeId, ingredientCount, result, fluidInput, duration, tickEnergyCost, experience, chanceResult);
        }

        @Nullable
        @Override
        public OreWashingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            IngredientCount ingredientCount = IngredientCount.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            FluidStack fluidInput = buffer.readFluidStack();
            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();
            ChanceResult chanceResult = ChanceResult.fromNetwork(buffer);

            return new OreWashingRecipe(recipeId, ingredientCount, result, fluidInput, duration, tickEnergyCost, experience, chanceResult);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, OreWashingRecipe recipe) {
            recipe.getIngredientCount().toNetwork(buffer);
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeFluidStack(recipe.getFluidInput());
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
            recipe.getChanceResult().toNetwork(buffer);
        }
    }
}
