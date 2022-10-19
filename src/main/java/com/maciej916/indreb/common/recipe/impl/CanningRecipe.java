package com.maciej916.indreb.common.recipe.impl;

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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class CanningRecipe implements IBaseRecipe {

    public static final CanningRecipe.Serializer SERIALIZER = new CanningRecipe.Serializer();

    private final ResourceLocation recipeId;
    private Cache<NonNullList<Ingredient>> ingredientList;

    private Ingredient firstIngredient;
    private int firstIngredientCount;

    private Ingredient secondIngredient;
    private int secondIngredientCount;

    private ItemStack result;

    private float experience;
    private int duration;
    private int powerCost;

    public CanningRecipe(ResourceLocation recipeId) {
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
        if (inv.getContainerSize() == 1) {
            return firstIngredient.test(inv.getItem(0)) || secondIngredient.test(inv.getItem(0));
        } else {
            return firstIngredient.test(inv.getItem(0)) &&
                    inv.getItem(0).getCount() >= firstIngredientCount &&
                    secondIngredient.test(inv.getItem(1)) &&
                    inv.getItem(1).getCount() >= secondIngredientCount;
        }
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
        return ModRecipeType.CANNING.get();
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
        return experience;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPowerCost() {
        return powerCost;
    }

    public Ingredient getFirstIngredient() {
        return firstIngredient;
    }

    public int getFirstIngredientCount() {
        return firstIngredientCount;
    }

    public Ingredient getSecondIngredient() {
        return secondIngredient;
    }

    public int getSecondIngredientCount() {
        return secondIngredientCount;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredientList.get();
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CanningRecipe> {

        @Override
        public CanningRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            CanningRecipe recipe = new CanningRecipe(recipeId);

            recipe.firstIngredient = Ingredient.fromJson(json.get("ingredient_from"));
            JsonObject firstIngredient = GsonHelper.getAsJsonObject(json, "ingredient_from");
            recipe.firstIngredientCount = GsonHelper.getAsInt(firstIngredient, "count", 1);

            recipe.secondIngredient = Ingredient.fromJson(json.get("ingredient_to"));
            JsonObject secondIngredient = GsonHelper.getAsJsonObject(json, "ingredient_to");
            recipe.secondIngredientCount = GsonHelper.getAsInt(secondIngredient, "count", 1);

            recipe.result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            recipe.experience = GsonHelper.getAsFloat(json, "experience", 0);
            recipe.duration = GsonHelper.getAsInt(json, "duration", 180);
            recipe.powerCost = GsonHelper.getAsInt(json, "power_cost", 8);

            recipe.ingredientList = Cache.create(() -> RecipeUtil.nnListOf(recipe.firstIngredient, recipe.secondIngredient));

            return recipe;
        }

        @Nullable
        @Override
        public CanningRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            CanningRecipe recipe = new CanningRecipe(recipeId);

            recipe.firstIngredient = Ingredient.fromNetwork(buffer);
            recipe.firstIngredientCount = buffer.readInt();
            recipe.secondIngredient = Ingredient.fromNetwork(buffer);
            recipe.secondIngredientCount = buffer.readInt();
            recipe.result = buffer.readItem();
            recipe.experience = buffer.readFloat();
            recipe.duration = buffer.readInt();
            recipe.powerCost = buffer.readInt();

            recipe.ingredientList = Cache.create(() -> RecipeUtil.nnListOf(recipe.firstIngredient, recipe.secondIngredient));

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CanningRecipe recipe) {
            recipe.firstIngredient.toNetwork(buffer);
            buffer.writeInt(recipe.firstIngredientCount);
            recipe.secondIngredient.toNetwork(buffer);
            buffer.writeInt(recipe.secondIngredientCount);
            buffer.writeItemStack(recipe.result, false);
            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.duration);
            buffer.writeInt(recipe.powerCost);
        }
    }
}