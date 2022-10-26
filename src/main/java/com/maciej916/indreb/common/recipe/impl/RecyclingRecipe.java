package com.maciej916.indreb.common.recipe.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.maciej916.indreb.common.interfaces.receipe.IBaseRecipe;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.util.Cache;
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
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class RecyclingRecipe implements IBaseRecipe {

    public static final RecyclingRecipe.Serializer SERIALIZER = new RecyclingRecipe.Serializer();

    private final ResourceLocation recipeId;
    private Cache<NonNullList<Ingredient>> ingredientList;
    private final HashSet<Ingredient> excluded = new HashSet<>();
    private ItemStack result;
    private float chance;

    public RecyclingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        for (Ingredient ingredient : ingredientList.get()) {
            if (ingredient.getItems().length > 0 && ingredient.getItems()[0].getItem() == inv.getItem(0).getItem()) {
                return true;
            }
        }
        return false;
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
        return ModRecipeType.RECYCLING.get();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return getResultItem().copy();
    }

    public float getChance() {
        return chance;
    }

    @Override
    public float getExperience() {
        return 0;
    }

    @Override
    public int getDuration() {
        return 45;
    }

    @Override
    public int getPowerCost() {
        return 1;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredientList.get();
    }

    private static NonNullList<Ingredient> getIngredientList(HashSet<Ingredient> excluded) {
        NonNullList<Ingredient> items = NonNullList.create();
        List<ItemStack> exStack = new ArrayList<>();
        excluded.forEach(ingredient -> exStack.addAll(Arrays.asList(ingredient.getItems())));
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            boolean contains = false;
            for (ItemStack ingStack : exStack) {
                if (ingStack.getItem() == item) {
                    contains = true;
                }
            }
            if (!contains) {
                items.add(Ingredient.of(item));
            }
        }
        return items;
    }

    public static class Serializer implements RecipeSerializer<RecyclingRecipe> {

        @Override
        public RecyclingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            RecyclingRecipe recipe = new RecyclingRecipe(recipeId);

            recipe.chance = GsonHelper.getAsFloat(json, "chance", 1);
            recipe.result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            JsonArray JSONExcluded = json.getAsJsonArray("excluded");
            if (JSONExcluded != null) {
                for (int i = 0; i < JSONExcluded.size(); i++) {
                    JsonObject object = (JsonObject) JSONExcluded.get(i);
                    recipe.excluded.add(Ingredient.fromJson(object));
                }
            }

            recipe.ingredientList = Cache.create(() -> getIngredientList(recipe.excluded));

            return recipe;
        }

        @Nullable
        @Override
        public RecyclingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            RecyclingRecipe recipe = new RecyclingRecipe(recipeId);

            recipe.chance = buffer.readFloat();
            recipe.result = buffer.readItem();

            int excludedCount = buffer.readByte();
            for (int i = 0; i < excludedCount; ++i) {
                Ingredient ingredient = Ingredient.fromNetwork(buffer);
                recipe.excluded.add(ingredient);
            }
            recipe.ingredientList = Cache.create(() -> getIngredientList(recipe.excluded));

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, RecyclingRecipe recipe) {
            buffer.writeFloat(recipe.chance);
            buffer.writeItemStack(recipe.result, false);
            buffer.writeByte(recipe.excluded.size());
            recipe.excluded.forEach((ingredient) -> ingredient.toNetwork(buffer));
        }
    }
}