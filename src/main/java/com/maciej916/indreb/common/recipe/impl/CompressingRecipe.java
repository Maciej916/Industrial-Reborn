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

public class CompressingRecipe extends BaseChanceRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    public CompressingRecipe(ResourceLocation id, IngredientCount ingredients, ItemStack result, int duration, int tickEnergyCost, float experience, ChanceResult chanceResult) {
        super(id, ingredients, result, duration, tickEnergyCost, experience, chanceResult);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.COMPRESSING.get();
    }

    public static class Serializer implements RecipeSerializer<CompressingRecipe> {

        @Override
        public CompressingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            IngredientCount ingredientCount = IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            int duration = GsonHelper.getAsInt(json, "duration", 180);
            int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 8);
            float experience = GsonHelper.getAsFloat(json, "experience", 0);

            ChanceResult chanceResult = ChanceResult.fromJson(GsonHelper.getAsJsonArray(json, "chance_result"));

            return new CompressingRecipe(recipeId, ingredientCount, result, duration, tickEnergyCost, experience, chanceResult);
        }

        @Nullable
        @Override
        public CompressingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            IngredientCount ingredientCount = IngredientCount.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();
            ChanceResult chanceResult = ChanceResult.fromNetwork(buffer);

            return new CompressingRecipe(recipeId, ingredientCount, result, duration, tickEnergyCost, experience, chanceResult);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CompressingRecipe recipe) {
            recipe.getIngredientCount().toNetwork(buffer);
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
            recipe.getChanceResult().toNetwork(buffer);
        }
    }
}
