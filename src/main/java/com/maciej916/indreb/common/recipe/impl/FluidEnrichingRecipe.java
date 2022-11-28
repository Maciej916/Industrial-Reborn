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
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class FluidEnrichingRecipe extends BaseRecipe {

    public static final Serializer SERIALIZER = new Serializer();

    private final FluidStack fluidInput;
    private final FluidStack fluidOutput;
    private final boolean consumeFluid;

    public FluidEnrichingRecipe(ResourceLocation id, IngredientCount ingredientCount, FluidStack fluidInput, boolean consumeFluid, FluidStack fluidOutput, int duration, int tickEnergyCost, float experience) {
        super(id, ingredientCount, ItemStack.EMPTY, duration, tickEnergyCost, experience);
        this.fluidInput = fluidInput;
        this.fluidOutput = fluidOutput;
        this.consumeFluid = consumeFluid;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.FLUID_ENRICHING.get();
    }

    public FluidStack getFluidInput() {
        return fluidInput;
    }

    public boolean isConsumeFluid() {
        return consumeFluid;
    }

    public FluidStack getFluidOutput() {
        return fluidOutput;
    }

    public static class Serializer implements RecipeSerializer<FluidEnrichingRecipe> {

        @Override
        public FluidEnrichingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            IngredientCount ingredientCount = IngredientCount.fromJson(GsonHelper.getAsJsonArray(json, "ingredients"));

            FluidStack fluidInput = ResultItem.fluidFromJson(GsonHelper.getAsJsonObject(json, "fluid_input"));
            FluidStack fluidOutput = ResultItem.fluidFromJson(GsonHelper.getAsJsonObject(json, "fluid_output"));

            boolean consumeFluid = GsonHelper.getAsBoolean(json, "consume_fluid", true);

            int duration = GsonHelper.getAsInt(json, "duration", 180);
            int tickEnergyCost = GsonHelper.getAsInt(json, "tick_energy_cost", 8);
            float experience = GsonHelper.getAsFloat(json, "experience", 0);

            return new FluidEnrichingRecipe(recipeId, ingredientCount, fluidInput, consumeFluid, fluidOutput, duration, tickEnergyCost, experience);
        }

        @Nullable
        @Override
        public FluidEnrichingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            IngredientCount ingredientCount = IngredientCount.fromNetwork(buffer);
            FluidStack fluidInput = buffer.readFluidStack();
            FluidStack fluidOutput = buffer.readFluidStack();
            boolean consumeFluid = buffer.readBoolean();
            int duration = buffer.readInt();
            int tickEnergyCost = buffer.readInt();
            float experience = buffer.readFloat();

            return new FluidEnrichingRecipe(recipeId, ingredientCount, fluidInput, consumeFluid, fluidOutput, duration, tickEnergyCost, experience);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FluidEnrichingRecipe recipe) {
            recipe.getIngredientCount().toNetwork(buffer);
            buffer.writeFluidStack(recipe.fluidInput);
            buffer.writeFluidStack(recipe.fluidOutput);
            buffer.writeBoolean(recipe.consumeFluid);
            buffer.writeInt(recipe.getDuration());
            buffer.writeInt(recipe.getTickEnergyCost());
            buffer.writeFloat(recipe.getExperience());
        }
    }
}
