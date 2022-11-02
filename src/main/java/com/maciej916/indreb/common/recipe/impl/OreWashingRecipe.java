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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class OreWashingRecipe implements IBaseRecipe {

    public static final OreWashingRecipe.Serializer SERIALIZER = new OreWashingRecipe.Serializer();
    private final ResourceLocation recipeId;

    private Ingredient ingredient;
    protected Cache<NonNullList<Ingredient>> ingredientList;
    private int ingredientCount;
    private FluidStack fluidInput;

    private ItemStack result1;
    private ItemStack result2;

    private float experience;
    private int duration;
    private int powerCost;

    public OreWashingRecipe(ResourceLocation recipeId) {
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
        return ingredient.getItems().length > 0 && ingredient.getItems()[0].getItem() == inv.getItem(0).getItem();
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
        return ModRecipeType.ORE_WASHING.get();
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
        return ingredientList.get();
    }

    public List<ItemStack> getResults() {
        return List.of(result1, result2);
    }

    public FluidStack getFluidInput() {
        return fluidInput;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }

    public static class Serializer implements RecipeSerializer<OreWashingRecipe> {

        @Override
        public OreWashingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            OreWashingRecipe recipe = new OreWashingRecipe(recipeId);

            recipe.ingredient = Ingredient.fromJson(json.get("ingredient"));
            JsonObject firstIngredient = GsonHelper.getAsJsonObject(json, "ingredient");
            recipe.ingredientCount = GsonHelper.getAsInt(firstIngredient, "count", 1);

            JsonObject fluidIngredient = GsonHelper.getAsJsonObject(json, "fluid_ingredient");
            ResourceLocation fluidResourceLocation = new ResourceLocation(GsonHelper.getAsString(fluidIngredient, "fluid", "minecraft:empty"));
            recipe.fluidInput = new FluidStack(Objects.requireNonNull(ForgeRegistries.FLUIDS.getValue(fluidResourceLocation)), GsonHelper.getAsInt(fluidIngredient, "amount", 1));

            if (GsonHelper.isValidNode(json, "result_1")) {
                recipe.result1 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result_1"));
            } else {
                recipe.result1 = ItemStack.EMPTY;
            }

            if (GsonHelper.isValidNode(json, "result_2")) {
                recipe.result2 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result_2"));
            } else {
                recipe.result2 = ItemStack.EMPTY;
            }

            recipe.experience = GsonHelper.getAsFloat(json, "experience", 0);
            recipe.duration = GsonHelper.getAsInt(json, "duration", 500);
            recipe.powerCost = GsonHelper.getAsInt(json, "power_cost", 16);

            recipe.ingredientList = Cache.create(() -> RecipeUtil.nnListOf(recipe.ingredient));

            return recipe;
        }

        @Nullable
        @Override
        public OreWashingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            OreWashingRecipe recipe = new OreWashingRecipe(recipeId);

            recipe.result2 = buffer.readItem();
            recipe.result1 = buffer.readItem();
            recipe.experience = buffer.readFloat();
            recipe.duration = buffer.readInt();
            recipe.powerCost = buffer.readInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);
            recipe.ingredientCount = buffer.readInt();
            recipe.fluidInput = buffer.readFluidStack();

            recipe.ingredientList = Cache.create(() -> RecipeUtil.nnListOf(recipe.ingredient));

            return recipe;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, OreWashingRecipe recipe) {
            buffer.writeItem(recipe.result1);
            buffer.writeItem(recipe.result2);
            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.duration);
            buffer.writeInt(recipe.powerCost);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeInt(recipe.ingredientCount);
            buffer.writeFluidStack(recipe.fluidInput);
        }
    }
}