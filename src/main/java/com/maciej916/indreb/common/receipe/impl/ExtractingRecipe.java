package com.maciej916.indreb.common.receipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.receipe.BasicChanceRecipe;
import com.maciej916.indreb.common.receipe.RecipeChanceResult;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.util.Cache;
import com.maciej916.indreb.common.util.RecipeUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class ExtractingRecipe extends BasicChanceRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    public ExtractingRecipe(ResourceLocation recipeId) {
        super(recipeId);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.EXTRACTING;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ExtractingRecipe> {

        @Override
        public ExtractingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ExtractingRecipe recipe = new ExtractingRecipe(recipeId);

            recipe.ingredient = Ingredient.fromJson(json.get("ingredient"));

            JsonObject ingredient = GsonHelper.getAsJsonObject(json, "ingredient");
            recipe.ingredientCount = GsonHelper.getAsInt(ingredient, "count", 1);

            recipe.result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            recipe.experience = GsonHelper.getAsFloat(json, "experience", 0);
            recipe.duration = GsonHelper.getAsInt(json, "duration", 180);
            recipe.powerCost = GsonHelper.getAsInt(json, "power_cost", 8);

            if (GsonHelper.isValidNode(json, "bonus_result")) {
                JsonObject ele = GsonHelper.getAsJsonObject(json, "bonus_result");
                ItemStack itemStack = ShapedRecipe.itemStackFromJson(ele);
                itemStack.setCount(GsonHelper.getAsInt(ele, "count", 1));
                float chance = GsonHelper.getAsFloat(ele, "chance", 100);
                recipe.bonusResult.addChanceResult(itemStack, chance);
            }

            recipe.ingredientList = Cache.create(() -> RecipeUtil.nnListOf(recipe.ingredient));

            return recipe;
        }

        @Nullable
        @Override
        public ExtractingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            ExtractingRecipe recipe = new ExtractingRecipe(recipeId);

            recipe.result = buffer.readItem();
            recipe.bonusResult = RecipeChanceResult.read(buffer);
            recipe.experience = buffer.readFloat();
            recipe.duration = buffer.readInt();
            recipe.powerCost = buffer.readInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);
            recipe.ingredientCount = buffer.readInt();
            recipe.ingredientList = Cache.create(() -> RecipeUtil.nnListOf(recipe.ingredient));

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ExtractingRecipe recipe) {
            buffer.writeItemStack(recipe.result, false);
            RecipeChanceResult.write(buffer, recipe.bonusResult);
            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.duration);
            buffer.writeInt(recipe.powerCost);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeInt(recipe.ingredientCount);
        }
    }
}