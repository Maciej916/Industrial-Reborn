package com.maciej916.indreb.common.recipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.BaseChanceRecipe;
import com.maciej916.indreb.common.api.recipe.lib.ChanceResult;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;

import javax.annotation.Nullable;

public class ThermalCentrifugingRecipe extends BaseChanceRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    private final float temperature;

    public ThermalCentrifugingRecipe(ResourceLocation id, IngredientCount ingredients, ItemStack result, float temperature, int duration, int tickEnergyCost, float experience, ChanceResult chanceResult) {
        super(id, ingredients, result, duration, tickEnergyCost, experience, chanceResult);
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.THERMAL_CENTRIFUGING.get();
    }

    public static class Serializer implements RecipeSerializer<ThermalCentrifugingRecipe> {

        @Override
        public ThermalCentrifugingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            IngredientCount ingredientCount = IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            float temperature = GsonHelper.getAsFloat(json, "temperature", 0);
            int duration = GsonHelper.getAsInt(json, "duration", 500);
            int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 46);
            float experience = GsonHelper.getAsFloat(json, "experience", 0);

            ChanceResult chanceResult = ChanceResult.fromJson(GsonHelper.getAsJsonArray(json, "chance_result"));

            return new ThermalCentrifugingRecipe(recipeId, ingredientCount, result, temperature, duration, tickEnergyCost, experience, chanceResult);
        }

        @Nullable
        @Override
        public ThermalCentrifugingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            IngredientCount ingredientCount = IngredientCount.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            float temperature = buffer.readFloat();
            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();
            ChanceResult chanceResult = ChanceResult.fromNetwork(buffer);

            return new ThermalCentrifugingRecipe(recipeId, ingredientCount, result, temperature, duration, tickEnergyCost, experience, chanceResult);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ThermalCentrifugingRecipe recipe) {
            recipe.getIngredientCount().toNetwork(buffer);
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeFloat(recipe.getTemperature());
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
            recipe.getChanceResult().toNetwork(buffer);
        }
    }
}
