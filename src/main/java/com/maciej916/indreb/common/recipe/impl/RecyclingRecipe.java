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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class RecyclingRecipe extends BaseRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    IngredientCount ingredientsExcluded;
    float chance;

    public RecyclingRecipe(ResourceLocation id, IngredientCount ingredients, IngredientCount ingredientsExcluded, float chance, ItemStack result, int duration, int tickEnergyCost, float experience) {
        super(id, ingredients, result, duration, tickEnergyCost, experience);
        this.ingredientsExcluded = ingredientsExcluded;
        this.chance = chance;
    }

    public IngredientCount getIngredientsExcluded() {
        return ingredientsExcluded;
    }

    public float getChance() {
        return Math.min(chance, 100);
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        IngredientCount ingredientCount = getIngredientCount();
        if (ingredientCount.getIngredientStack(0).ingredient() == Ingredient.EMPTY) {
            IngredientCount ingredientCountExcluded = getIngredientsExcluded();
            for (int i = 0; i < ingredientCountExcluded.getSize(); i++) {
                IngredientCountStack countStack1 = ingredientCountExcluded.getIngredientStack(i);
                List<ItemStack> icStack = Stream.of(countStack1.ingredient().getItems()).toList();
                for (ItemStack stack : icStack) {
                    if (stack.getItem() == container.getItem(0).getItem()) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            for (int i = 0; i < ingredientCount.getSize(); i++) {
                IngredientCountStack countStack1 = ingredientCount.getIngredientStack(i);
                List<ItemStack> icStack = Stream.of(countStack1.ingredient().getItems()).peek(itemStack -> itemStack.setCount(countStack1.getCount())).toList();
                for (ItemStack stack : icStack) {
                    if (stack.getItem() == container.getItem(0).getItem()) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.RECYCLING.get();
    }

    public static class Serializer implements RecipeSerializer<RecyclingRecipe> {

        @Override
        public RecyclingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
           boolean hasIncluded = GsonHelper.isValidNode(json, "ingredients_included");
           boolean hasExcluded = GsonHelper.isValidNode(json, "ingredients_excluded");

           if ((hasIncluded && hasExcluded) || (!hasIncluded && !hasExcluded)) {
               throw new RuntimeException("Recipe should contains included or excluded ingredients never both, never empty! Recipeid: " + recipeId);
           }

           IngredientCount ingredientCountIncluded = hasIncluded ? IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients_included")) : new IngredientCount(1);
           IngredientCount ingredientCountExcluded = hasExcluded ? IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients_excluded")) : new IngredientCount(1);
           ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

           float chance = GsonHelper.getAsFloat(json, "chance", 15);
           int duration = GsonHelper.getAsInt(json, "duration", 45);
           int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 1);
           float experience = GsonHelper.getAsFloat(json, "experience", 0);

           return new RecyclingRecipe(recipeId, ingredientCountIncluded, ingredientCountExcluded, chance, result, duration, tickEnergyCost, experience);
        }

        @Nullable
        @Override
        public RecyclingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            IngredientCount ingredientCountIncluded = IngredientCount.fromNetwork(buffer);
            IngredientCount ingredientCountExcluded = IngredientCount.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            float chance = buffer.readFloat();
            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();

            return new RecyclingRecipe(recipeId, ingredientCountIncluded, ingredientCountExcluded, chance, result, duration, tickEnergyCost, experience);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, RecyclingRecipe recipe) {
            recipe.getIngredientCount().toNetwork(buffer);
            recipe.getIngredientsExcluded().toNetwork(buffer);
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeFloat(recipe.getChance());
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
        }
    }
}
