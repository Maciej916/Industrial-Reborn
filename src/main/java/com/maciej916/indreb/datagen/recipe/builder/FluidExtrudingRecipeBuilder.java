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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class FluidExtrudingRecipeBuilder {

    private FluidStack firstFluid;
    private FluidStack secondFluid;
    private boolean consumeFist;
    private boolean consumeSecond;

    private final ItemStack result;

    private int duration;
    private int tickEnergyCost;
    private float experience;

    private String group = "";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private FluidExtrudingRecipeBuilder(ItemStack result, int duration, int tickEnergyCost, float experience) {
        this.result = result;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
    }

    public static FluidExtrudingRecipeBuilder builder(RegistryObject<Item> item, int resultCount) {
        return builder(item.get(), resultCount);
    }

    public static FluidExtrudingRecipeBuilder builder(ItemLike item, int resultCount) {
        return new FluidExtrudingRecipeBuilder(new ItemStack(item, resultCount), 180, 8, 0);
    }

    public FluidExtrudingRecipeBuilder setFirstFluid(Fluid fluid, int count, boolean consume) {
        this.firstFluid = new FluidStack(fluid, count);
        this.consumeFist = consume;
        return this;
    }

    public FluidExtrudingRecipeBuilder setSecondFluid(Fluid fluid, int count, boolean consume) {
        this.secondFluid = new FluidStack(fluid, count);
        this.consumeSecond = consume;
        return this;
    }

    public FluidExtrudingRecipeBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public FluidExtrudingRecipeBuilder setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public FluidExtrudingRecipeBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public FluidExtrudingRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public FluidExtrudingRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
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

        if (this.firstFluid == null || this.secondFluid == null) {
            throw new IllegalStateException("Ingredient can not be empty " + id);
        }

        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final FluidExtrudingRecipeBuilder builder;

        public Result(ResourceLocation id, FluidExtrudingRecipeBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("first_fluid", ResultItem.fluidToJson(builder.firstFluid));
            json.add("second_fluid", ResultItem.fluidToJson(builder.secondFluid));

            json.addProperty("consume_fist", builder.consumeFist);
            json.addProperty("consume_second", builder.consumeSecond);

            json.add("result", ResultItem.stackToJson(builder.result));

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
            return ModRecipeSerializer.FLUID_EXTRUDING.get();
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
