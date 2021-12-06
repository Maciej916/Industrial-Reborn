package com.maciej916.indreb.common.receipe.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.maciej916.indreb.common.interfaces.receipe.IBaseRecipe;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.util.Cache;
import com.maciej916.indreb.common.util.RecipeUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class AlloySmeltingRecipe implements IBaseRecipe {

    public static final AlloySmeltingRecipe.Serializer SERIALIZER = new AlloySmeltingRecipe.Serializer();

    public ResourceLocation recipeId;
    private final HashMap<Ingredient, Integer> ingredients = new LinkedHashMap<>();
    private ItemStack result;

    public float experience;
    public int duration;
    public int powerCost;

    public AlloySmeltingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public Map<Ingredient, Integer> getIngredientMap() {
        return ImmutableMap.copyOf(ingredients);
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }

    public List<ItemStack> getIngredientCost() {
        List<ItemStack> cost = new ArrayList<>();
        for (Map.Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
            cost.add(new ItemStack(entry.getKey().getItems()[0].getItem(), entry.getValue()));
        }
        return cost;
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        ArrayList<Item> allItems = new ArrayList<>();
        for (Map.Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            allItems.add(ingredient.getItems()[0].getItem());
        }

        int notAirItems = 0;
        for (int slot = 0; slot < inv.getContainerSize(); ++slot) {
            Item item = inv.getItem(slot).getItem();
            if (item != Items.AIR) {
                notAirItems++;
            }
        }

        boolean matches = true;
        if (notAirItems == 0 || notAirItems > allItems.size()) {
            matches = false;
        } else {
            if (inv.getContainerSize() == allItems.size() || inv.getContainerSize() == 1) {
                for (int slot = 0; slot < inv.getContainerSize(); ++slot) {
                    Item item = inv.getItem(slot).getItem();
                    if (!allItems.contains(item)) {
                        matches = false;
                        break;
                    }
                }
            }
        }

        return matches;
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
        return ModRecipeType.ALLOY_SMELTING;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return getResultItem().copy();
    }

    public float getExperience() {
        return experience;
    }

    public int getDuration() {
        return duration;
    }

    public int getPowerCost() {
        return powerCost;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<AlloySmeltingRecipe> {

        @Override
        public AlloySmeltingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            AlloySmeltingRecipe recipe = new AlloySmeltingRecipe(recipeId);

            JsonArray ingredients = json.getAsJsonArray("ingredients");
            for (int i = 0; i < Math.min(ingredients.size(), 3); i++) {
                JsonObject object = (JsonObject) ingredients.get(i);
                Ingredient item = Ingredient.fromJson(object);
                int count = GsonHelper.getAsInt(object, "count", 1);
                recipe.ingredients.put(item, count);
            }

            recipe.result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            recipe.experience = GsonHelper.getAsFloat(json, "experience", 0);
            recipe.duration = GsonHelper.getAsInt(json, "duration", 180);
            recipe.powerCost = GsonHelper.getAsInt(json, "power_cost", 8);

            return recipe;
        }

        @Nullable
        @Override
        public AlloySmeltingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            AlloySmeltingRecipe recipe = new AlloySmeltingRecipe(recipeId);

            int ingredientCount = buffer.readByte();
            for (int i = 0; i < ingredientCount; ++i) {
                Ingredient ingredient = Ingredient.fromNetwork(buffer);
                int count = buffer.readByte();
                recipe.ingredients.put(ingredient, count);
            }

            recipe.result = buffer.readItem();
            recipe.experience = buffer.readFloat();
            recipe.duration = buffer.readInt();
            recipe.powerCost = buffer.readInt();

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, AlloySmeltingRecipe recipe) {

            buffer.writeByte(recipe.ingredients.size());
            recipe.ingredients.forEach((ingredient, count) -> {
                ingredient.toNetwork(buffer);
                buffer.writeByte(count);
            });

            buffer.writeItemStack(recipe.result, false);
            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.duration);
            buffer.writeInt(recipe.powerCost);

        }
    }
}