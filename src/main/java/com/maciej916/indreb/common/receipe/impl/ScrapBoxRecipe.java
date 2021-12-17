package com.maciej916.indreb.common.receipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.interfaces.receipe.IBaseRecipe;
import com.maciej916.indreb.common.registries.ModItems;
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
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.*;

public class ScrapBoxRecipe implements IBaseRecipe {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static final ScrapBoxRecipe.Serializer SERIALIZER = new ScrapBoxRecipe.Serializer();

    public Cache<Ingredient> ingredient;
    public ResourceLocation recipeId;
    public ItemStack result;
    public float weight;

    private static HashMap<ItemStack, Float> resultMap = new HashMap<>();;
    private static float totalWeight;

    public ScrapBoxRecipe(ResourceLocation recipeId) {
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
        return inv.getItem(0).getItem() == ingredient.get().getItems()[0].getItem();
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
        return ModRecipeType.SCRAP_BOX;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return getResultItem().copy();
    }

    @Override
    public float getExperience() {
        return 0;
    }

    @Override
    public int getDuration() {
        return 40;
    }

    @Override
    public int getPowerCost() {
        return 1;
    }

    public String getDropChance() {
        float chance = (weight / totalWeight) * 100;
        return chance < 0.01 ? "<0.01" : df.format(chance);
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
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(ingredient.get());
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ScrapBoxRecipe> {

        @Override
        public ScrapBoxRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ScrapBoxRecipe recipe = new ScrapBoxRecipe(recipeId);

            recipe.weight = GsonHelper.getAsFloat(json, "weight", 1);
            recipe.result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            recipe.ingredient = Cache.create(() -> Ingredient.of(new ItemStack(ModItems.SCRAP_BOX).getItem()));

            resultMap.put(recipe.result, recipe.weight);
            totalWeight = totalWeight + recipe.weight;

            return recipe;
        }

        @Nullable
        @Override
        public ScrapBoxRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            ScrapBoxRecipe recipe = new ScrapBoxRecipe(recipeId);

            recipe.weight = buffer.readFloat();
            recipe.result = buffer.readItem();
            recipe.ingredient = Cache.create(() -> Ingredient.of(new ItemStack(ModItems.SCRAP_BOX).getItem()));

            resultMap.put(recipe.result, recipe.weight);
            totalWeight = totalWeight + recipe.weight;

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ScrapBoxRecipe recipe) {
            buffer.writeFloat(recipe.weight);
            buffer.writeItemStack(recipe.result, false);
        }
    }
}