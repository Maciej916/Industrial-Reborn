package com.maciej916.indreb.common.recipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.BaseRecipe;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCountStack;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class AlloySmeltingRecipe extends BaseRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    public AlloySmeltingRecipe(ResourceLocation id, IngredientCount ingredients, ItemStack result, int duration, int tickEnergyCost, float experience) {
        super(id, ingredients, result, duration, tickEnergyCost, experience);
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        int containerSize = container.getContainerSize();
        if (containerSize == 1) {
            for (int i = 0; i < getIngredientCount().getSize(); i++) {
                IngredientCountStack countStack = getIngredientCount().getIngredientStack(i);
                if (countStack.ingredient().test(container.getItem(0))) {
                    return true;
                }
            }
        } else {
            int matches = 0;
            for (int j = 0; j < getIngredientCount().getSize(); j++) {
                if (getIngredientCount().getSize() == container.getContainerSize()) {
                    IngredientCountStack countStack = getIngredientCount().getIngredientStack(j);
                    for (int i = 0; i < container.getContainerSize(); i++) {
                        if (countStack.ingredient().test(container.getItem(i))) {
                            matches++;
                        }
                    }
                }
            }

            return matches == container.getContainerSize();
        }

        return false;
    }

    public int getIngredientCost(ItemStack stack) {
        IngredientCount ingredientCount = getIngredientCount();
        for (int i = 0; i < ingredientCount.getSize(); i++) {
            IngredientCountStack countStack = ingredientCount.getIngredientStack(i);
            if (countStack.ingredient().test(stack)) {
                return countStack.getCount();
            }
        }
        return 0;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.ALLOY_SMELTING.get();
    }

    public static class Serializer implements RecipeSerializer<AlloySmeltingRecipe> {

        @Override
        public AlloySmeltingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            IngredientCount ingredientCount = IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            int duration = GsonHelper.getAsInt(json, "duration", 560);
            int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 16);
            float experience = GsonHelper.getAsFloat(json, "experience", 0);

            return new AlloySmeltingRecipe(recipeId, ingredientCount, result, duration, tickEnergyCost, experience);
        }

        @Nullable
        @Override
        public AlloySmeltingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            IngredientCount ingredientCount = IngredientCount.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();

            return new AlloySmeltingRecipe(recipeId, ingredientCount, result, duration, tickEnergyCost, experience);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, AlloySmeltingRecipe recipe) {
            recipe.getIngredientCount().toNetwork(buffer);
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
        }
    }
}
