package com.maciej916.indreb.common.recipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.BaseRecipe;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class MatterAmplifierRecipe extends BaseRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    private final int amplifiedAmount;


    public MatterAmplifierRecipe(ResourceLocation id, IngredientCount ingredients, int amplifiedAmount) {
        super(id, ingredients, ItemStack.EMPTY, 0, 0, 0);
        this.amplifiedAmount = amplifiedAmount;
    }

    public int getAmplifiedAmount() {
        return amplifiedAmount;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.MATTER_AMPLIFIER.get();
    }

    public static class Serializer implements RecipeSerializer<MatterAmplifierRecipe> {

        @Override
        public MatterAmplifierRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            IngredientCount ingredientCount = IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            int amplifiedAmount = GsonHelper.getAsInt(json, "amplified_amount", 1);

            return new MatterAmplifierRecipe(recipeId, ingredientCount, amplifiedAmount);
        }

        @Nullable
        @Override
        public MatterAmplifierRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            IngredientCount ingredientCount = IngredientCount.fromNetwork(buffer);
            int amplifiedAmount = buffer.readInt();

            return new MatterAmplifierRecipe(recipeId, ingredientCount, amplifiedAmount);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MatterAmplifierRecipe recipe) {
            recipe.getIngredientCount().toNetwork(buffer);
            buffer.writeInt(recipe.getAmplifiedAmount());
        }
    }
}
