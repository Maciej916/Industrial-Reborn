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

public class FluidExtrudingRecipe extends BaseRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    private final FluidStack firstFluid;
    private final FluidStack secondFluid;
    private final boolean consumeFist;
    private final boolean consumeSecond;

    public FluidExtrudingRecipe(ResourceLocation id, FluidStack firstFluid, FluidStack secondFluid, boolean consumeFist, boolean consumeSecond, ItemStack result, int duration, int tickEnergyCost, float experience) {
        super(id, new IngredientCount(1), result, duration, tickEnergyCost, experience);
        this.firstFluid = firstFluid;
        this.secondFluid = secondFluid;
        this.consumeFist = consumeFist;
        this.consumeSecond = consumeSecond;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.FLUID_EXTRUDING.get();
    }

    public FluidStack getFirstFluid() {
        return firstFluid;
    }

    public FluidStack getSecondFluid() {
        return secondFluid;
    }

    public boolean isConsumeFist() {
        return consumeFist;
    }

    public boolean isConsumeSecond() {
        return consumeSecond;
    }

    public static class Serializer implements RecipeSerializer<FluidExtrudingRecipe> {

        @Override
        public FluidExtrudingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            FluidStack firstFluid = ResultItem.fluidFromJson(GsonHelper.getAsJsonObject(json, "first_fluid"));
            FluidStack secondFluid = ResultItem.fluidFromJson(GsonHelper.getAsJsonObject(json, "second_fluid"));

            boolean consumeFist = GsonHelper.getAsBoolean(json, "consume_fist", true);
            boolean consumeSecond = GsonHelper.getAsBoolean(json, "consume_second", true);

            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            int duration = GsonHelper.getAsInt(json, "duration", 180);
            int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 8);
            float experience = GsonHelper.getAsFloat(json, "experience", 0);

            return new FluidExtrudingRecipe(recipeId, firstFluid, secondFluid, consumeFist, consumeSecond, result, duration, tickEnergyCost, experience);
        }

        @Nullable
        @Override
        public FluidExtrudingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            FluidStack firstFluid = buffer.readFluidStack();
            FluidStack secondFluid = buffer.readFluidStack();
            boolean consumeFist = buffer.readBoolean();
            boolean consumeSecond = buffer.readBoolean();
            ItemStack result = buffer.readItem();
            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();

            return new FluidExtrudingRecipe(recipeId, firstFluid, secondFluid, consumeFist, consumeSecond, result, duration, tickEnergyCost, experience);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FluidExtrudingRecipe recipe) {

            buffer.writeFluidStack(recipe.firstFluid);
            buffer.writeFluidStack(recipe.secondFluid);
            buffer.writeBoolean(recipe.consumeFist);
            buffer.writeBoolean(recipe.consumeSecond);
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
        }
    }
}
