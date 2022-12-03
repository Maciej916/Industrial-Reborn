package com.maciej916.indreb.common.recipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.BaseRecipe;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.util.GuiUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ScrapBoxRecipe extends BaseRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    private static final HashMap<ItemStack, Float> resultMap = new HashMap<>();
    private static float totalWeight;

    private final float weight;

    public ScrapBoxRecipe(ResourceLocation id, IngredientCount ingredients, ItemStack result, float weight, int duration, int tickEnergyCost, float experience) {
        super(id, ingredients, result, duration, tickEnergyCost, experience);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public String getDropChance() {
        float chance = (weight / totalWeight) * 100;
        return chance < 0.001 ? "<0.001" : GuiUtil.DECIMAL_FORMAT_2.format(chance);
    }

    public ItemStack getDrop() {
        float random = new Random().nextFloat() * (totalWeight);

        ArrayList<ItemStack> items = new ArrayList<>(resultMap.keySet());
        ArrayList<Float> total = new ArrayList<>();
        for (float number : resultMap.values()) {
            total.add(number + (total.size() > 0 ? total.get(total.size() - 1) : 0));
        }

        for (int j = 0; j < total.size(); j++) {
            if (total.get(j) >= random) {
                return items.get(j);
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.SCRAP_BOX.get();
    }

    public static class Serializer implements RecipeSerializer<ScrapBoxRecipe> {

        @Override
        public ScrapBoxRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            IngredientCount ingredientCount = IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            float weight = GsonHelper.getAsFloat(json, "weight", 1);
            int duration = GsonHelper.getAsInt(json, "duration", 10);
            int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 1);
            float experience = GsonHelper.getAsFloat(json, "experience", 0);

            resultMap.put(result, weight);
            totalWeight = totalWeight + weight;

            return new ScrapBoxRecipe(recipeId, ingredientCount, result, weight, duration, tickEnergyCost, experience);
        }

        @Nullable
        @Override
        public ScrapBoxRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            IngredientCount ingredientCount = IngredientCount.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            float weight = buffer.readFloat();
            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();

            resultMap.put(result, weight);
            totalWeight = totalWeight + weight;

            return new ScrapBoxRecipe(recipeId, ingredientCount, result, weight, duration, tickEnergyCost, experience);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ScrapBoxRecipe recipe) {
            recipe.getIngredientCount().toNetwork(buffer);
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeFloat(recipe.getWeight());
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
        }
    }
}
