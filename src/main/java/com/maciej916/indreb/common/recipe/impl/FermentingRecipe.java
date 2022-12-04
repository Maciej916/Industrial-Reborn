package com.maciej916.indreb.common.recipe.impl;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.BaseRecipe;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.ResultItem;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class FermentingRecipe extends BaseRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    private final FluidStack fluidInput;
    private final FluidStack fluidOutput;
    private final int wasteDuration;
    private final float tickWasteIncrease;
    private final ItemStack wasteOutput;

    public FermentingRecipe(ResourceLocation id, FluidStack fluidInput, FluidStack fluidOutput, int wasteDuration, float tickWasteIncrease, ItemStack wasteOutput, int duration, int tickEnergyCost, float experience) {
        super(id, new IngredientCount(1), ItemStack.EMPTY, duration, tickEnergyCost, experience);
        this.fluidInput = fluidInput;
        this.fluidOutput = fluidOutput;
        this.wasteDuration = wasteDuration;
        this.tickWasteIncrease = tickWasteIncrease;
        this.wasteOutput = wasteOutput;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.FERMENTING.get();
    }

    public FluidStack getFluidInput() {
        return fluidInput;
    }

    public FluidStack getFluidOutput() {
        return fluidOutput;
    }

    public float getTickWasteIncrease() {
        return tickWasteIncrease;
    }

    public int getWasteDuration() {
        return wasteDuration;
    }

    public ItemStack getWasteOutput() {
        return wasteOutput;
    }

    public static class Serializer implements RecipeSerializer<FermentingRecipe> {

        @Override
        public FermentingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            FluidStack fluidInput = ResultItem.fluidFromJson(GsonHelper.getAsJsonObject(json, "fluid_input"));
            FluidStack fluidOutput = ResultItem.fluidFromJson(GsonHelper.getAsJsonObject(json, "fluid_output"));

            int wasteDuration = GsonHelper.getAsInt(json, "waste_duration", 1400);
            float tickWasteIncrease = GsonHelper.getAsFloat(json, "tick_waste_increase", 1f);
            ItemStack wasteOutput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "waste_output"));

            int duration = GsonHelper.getAsInt(json, "duration", 600);
            int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 16);
            float experience = GsonHelper.getAsFloat(json, "experience", 0);

            return new FermentingRecipe(recipeId, fluidInput, fluidOutput, wasteDuration, tickWasteIncrease, wasteOutput, duration, tickEnergyCost, experience);
        }

        @Nullable
        @Override
        public FermentingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            FluidStack fluidInput = buffer.readFluidStack();
            FluidStack fluidOutput = buffer.readFluidStack();

            int wasteDuration = buffer.readInt();
            float tickWasteIncrease = buffer.readFloat();
            ItemStack wasteOutput = buffer.readItem();

            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();

            return new FermentingRecipe(recipeId, fluidInput, fluidOutput, wasteDuration, tickWasteIncrease, wasteOutput, duration, tickEnergyCost, experience);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FermentingRecipe recipe) {
            buffer.writeFluidStack(recipe.getFluidInput());
            buffer.writeFluidStack(recipe.getFluidOutput());
            buffer.writeInt(recipe.getWasteDuration());
            buffer.writeFloat(recipe.getTickWasteIncrease());
            buffer.writeItemStack(recipe.getWasteOutput(), false);
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
        }
    }
}
