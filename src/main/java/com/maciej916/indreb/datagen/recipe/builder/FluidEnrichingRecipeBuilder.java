package com.maciej916.indreb.datagen.recipe.builder;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.ResultItem;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class FluidEnrichingRecipeBuilder {

    private final IngredientCount ingredients = new IngredientCount(1);
    private FluidStack fluidInput;
    private boolean consumeFluid;

    private final FluidStack fluidOutput;

    private int duration;
    private int tickEnergyCost;
    private float experience;

    private String group = "";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private FluidEnrichingRecipeBuilder(FluidStack fluidOutput, int duration, int tickEnergyCost, float experience) {
        this.fluidOutput = fluidOutput;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
    }

    public static FluidEnrichingRecipeBuilder builder(Fluid fluid, int resultCount) {
        return new FluidEnrichingRecipeBuilder(new FluidStack(fluid, resultCount), 180, 8, 0);
    }

    private FluidEnrichingRecipeBuilder setIngredient(Ingredient ingredient, int count) {
        this.ingredients.setIngredient(0, ingredient, count);
        return this;
    }

    public FluidEnrichingRecipeBuilder setIngredient(RegistryObject<Item> item, int count) {
        return setIngredient(Ingredient.of(item.get()), count);
    }

    public FluidEnrichingRecipeBuilder setIngredient(ItemLike item, int count) {
        return setIngredient(Ingredient.of(item), count);
    }

    public FluidEnrichingRecipeBuilder setIngredient(TagKey<Item> tag, int count) {
        return setIngredient(Ingredient.of(tag), count);
    }


    public FluidEnrichingRecipeBuilder setFluidInput(Fluid fluid, int count, boolean consume) {
        this.fluidInput = new FluidStack(fluid, count);
        this.consumeFluid = consume;
        return this;
    }

    public FluidEnrichingRecipeBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public FluidEnrichingRecipeBuilder setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public FluidEnrichingRecipeBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public FluidEnrichingRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public FluidEnrichingRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
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
        private final FluidEnrichingRecipeBuilder builder;

        public Result(ResourceLocation id, FluidEnrichingRecipeBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredients", builder.ingredients.toJson());

            json.add("fluid_input", ResultItem.fluidToJson(builder.fluidInput));
            json.addProperty("consume_fluid", builder.consumeFluid);

            json.add("fluid_output", ResultItem.fluidToJson(builder.fluidOutput));

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
            return ModRecipeSerializer.FLUID_ENRICHING.get();
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
