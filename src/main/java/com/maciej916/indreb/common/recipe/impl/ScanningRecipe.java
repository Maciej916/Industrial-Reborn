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

public class ScanningRecipe extends BaseRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    private final int matterCost;
    private final int energyCost;

    public ScanningRecipe(ResourceLocation id, IngredientCount ingredients, int matterCost, int energyCost, int duration, int tickEnergyCost, float experience) {
        super(id, ingredients, ItemStack.EMPTY, duration, tickEnergyCost, experience);
        this.matterCost = matterCost;
        this.energyCost = energyCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public int getMatterCost() {
        return matterCost;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.SCANNING.get();
    }

    public static class Serializer implements RecipeSerializer<ScanningRecipe> {

        @Override
        public ScanningRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            IngredientCount ingredientCount = IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients"));

            JsonObject replication = GsonHelper.getAsJsonObject(json, "replication");
            int matterCost = GsonHelper.getAsInt(replication, "matter_cost", 1);
            int energyCost = GsonHelper.getAsInt(replication, "energy_cost", 1);

            int duration = GsonHelper.getAsInt(json, "duration", 600);
            int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 256);
            float experience = GsonHelper.getAsFloat(json, "experience", 0);

            return new ScanningRecipe(recipeId, ingredientCount, matterCost, energyCost, duration, tickEnergyCost, experience);
        }

        @Nullable
        @Override
        public ScanningRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            IngredientCount ingredientCount = IngredientCount.fromNetwork(buffer);
            int matterCost = buffer.readInt();
            int energyCost = buffer.readInt();
            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();

            return new ScanningRecipe(recipeId, ingredientCount, matterCost, energyCost, duration, tickEnergyCost, experience);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ScanningRecipe recipe) {
            recipe.getIngredientCount().toNetwork(buffer);
            buffer.writeFloat(recipe.getMatterCost());
            buffer.writeFloat(recipe.getEnergyCost());
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
        }
    }
}
