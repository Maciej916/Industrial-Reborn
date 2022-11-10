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

import javax.annotation.Nullable;
import java.util.List;

public class ScannerRecipe implements IBaseRecipe {

    public static final ScannerRecipe.Serializer SERIALIZER = new ScannerRecipe.Serializer();
    private final ResourceLocation recipeId;

    private Ingredient item;
    protected Cache<NonNullList<Ingredient>> itemList;
    private int matterCost;
    private int energyCost;
    private float experience;
    private int duration;
    private int powerCost;

    public ScannerRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        return item.getItems().length > 0 && item.getItems()[0].getItem() == inv.getItem(0).getItem();
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
        return ModRecipeType.SCANNER.get();
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

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return itemList.get();
    }

    public int getMatterCost() {
        return matterCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public static class Serializer implements RecipeSerializer<ScannerRecipe> {

        @Override
        public ScannerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ScannerRecipe recipe = new ScannerRecipe(recipeId);

            recipe.item = Ingredient.fromJson(json.get("item"));

            JsonObject replication = GsonHelper.getAsJsonObject(json, "replication");
            recipe.matterCost = GsonHelper.getAsInt(replication, "matter_cost", 0);
            recipe.energyCost = GsonHelper.getAsInt(replication, "energy_cost", 0);

            recipe.experience = GsonHelper.getAsFloat(json, "experience", 0);
            recipe.duration = GsonHelper.getAsInt(json, "duration", 500);
            recipe.powerCost = GsonHelper.getAsInt(json, "power_cost", 46);

            recipe.itemList = Cache.create(() -> RecipeUtil.nnListOf(recipe.item));

            return recipe;
        }

        @Nullable
        @Override
        public ScannerRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            ScannerRecipe recipe = new ScannerRecipe(recipeId);

            recipe.experience = buffer.readFloat();
            recipe.duration = buffer.readInt();
            recipe.powerCost = buffer.readInt();
            recipe.matterCost = buffer.readInt();
            recipe.energyCost = buffer.readInt();
            recipe.item = Ingredient.fromNetwork(buffer);

            recipe.itemList = Cache.create(() -> RecipeUtil.nnListOf(recipe.item));

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ScannerRecipe recipe) {

            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.duration);
            buffer.writeInt(recipe.powerCost);
            buffer.writeFloat(recipe.matterCost);
            buffer.writeInt(recipe.energyCost);
            recipe.item.toNetwork(buffer);
        }
    }
}