package com.maciej916.indreb.datagen.recipe.builder;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.lib.ResultItem;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class FermentingRecipeBuilder {

    private FluidStack fluidInput;
    private FluidStack fluidOutput;

    private final int wasteDuration;
    private final float tickWasteIncrease;
    private final ItemStack wasteOutput;

    private int duration;
    private int tickEnergyCost;
    private float experience;

    private String group = "";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private FermentingRecipeBuilder(FluidStack fluidOutput, int duration, int tickEnergyCost, float experience, int wasteDuration, float tickWasteIncrease, ItemStack wasteOutput) {
        this.fluidOutput = fluidOutput;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
        this.wasteDuration = wasteDuration;
        this.tickWasteIncrease = tickWasteIncrease;
        this.wasteOutput = wasteOutput;
    }

    public static FermentingRecipeBuilder builder(Fluid fluid, int resultCount, int wasteDuration, float tickWasteIncrease, ItemLike wasteOutput) {
        return new FermentingRecipeBuilder(new FluidStack(fluid, resultCount), 600, 16, 0, wasteDuration, tickWasteIncrease, new ItemStack(wasteOutput, 1));
    }

    public FermentingRecipeBuilder setFluidInput(Fluid fluid, int count) {
        this.fluidInput = new FluidStack(fluid, count);
        return this;
    }

    public FermentingRecipeBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public FermentingRecipeBuilder setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public FermentingRecipeBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public FermentingRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public FermentingRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer, String name) {
        ResourceLocation id = new ResourceLocation(MODID, group + "/" + name);
        this.validate(id);
        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id, this));
    }

    private void validate(ResourceLocation id) {

        if (this.fluidInput == null) {
            throw new IllegalStateException("Ingredient can not be empty " + id);
        }

        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final FermentingRecipeBuilder builder;

        public Result(ResourceLocation id, FermentingRecipeBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("fluid_input", ResultItem.fluidToJson(builder.fluidInput));
            json.add("fluid_output", ResultItem.fluidToJson(builder.fluidOutput));

            json.addProperty("waste_duration", builder.wasteDuration);
            json.addProperty("tick_waste_increase", builder.tickWasteIncrease);
            json.add("waste_output", ResultItem.stackToJson(builder.wasteOutput));

            json.addProperty("tick_energy_cost", builder.tickEnergyCost);
            json.addProperty("duration", builder.duration);
            json.addProperty("experience", builder.experience);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializer.FERMENTING.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return builder.advancementBuilder.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return new ResourceLocation(id.getNamespace(), "recipes/" + id.getPath());
        }
    }
}
