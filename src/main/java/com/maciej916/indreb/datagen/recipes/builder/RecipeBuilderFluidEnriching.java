package com.maciej916.indreb.datagen.recipes.builder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class RecipeBuilderFluidEnriching {

    public Ingredient ingredient;
    private int ingredientCount;

    private FluidStack fluidInput;
    private FluidStack fluidResult;

    private int duration;
    private int powerCost;
    private float experience;

    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    private String group = "";

    public RecipeBuilderFluidEnriching(FluidStack result, int duration, int powerCost, float experience) {
        this.fluidResult = result;
        this.duration = duration;
        this.powerCost = powerCost;
        this.experience = experience;
    }

    public static RecipeBuilderFluidEnriching builder(Fluid fluid, int resultCount) {
        return new RecipeBuilderFluidEnriching(new FluidStack(fluid, resultCount), 180, 8, 0);
    }

    public RecipeBuilderFluidEnriching setIngredient(Ingredient ingredient, int count) {
        this.ingredient = ingredient;
        this.ingredientCount = count;
        return this;
    }

    public RecipeBuilderFluidEnriching setIngredient(ItemLike item, int count) {
        return setIngredient(Ingredient.of(item), count);
    }

    public RecipeBuilderFluidEnriching setIngredient(Tag<Item> tag, int count) {
        return setIngredient(Ingredient.of(tag), count);
    }

    public RecipeBuilderFluidEnriching setFluidIngredient(Fluid fluid, int count) {
        this.fluidInput = new FluidStack(fluid, count);
        return this;
    }

    public RecipeBuilderFluidEnriching setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public RecipeBuilderFluidEnriching setPowerCost(int powerCost) {
        this.powerCost = powerCost;
        return this;
    }

    public RecipeBuilderFluidEnriching setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public RecipeBuilderFluidEnriching addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public RecipeBuilderFluidEnriching setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer, String name) {
        ResourceLocation id = new ResourceLocation(MODID, group + "/" + name);
        this.validate(id);
        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id, this));
    }

    private void validate(ResourceLocation id) {
        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final RecipeBuilderFluidEnriching builder;

        public Result(ResourceLocation id, RecipeBuilderFluidEnriching builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {

            json.add("ingredient", serializeIngredient(builder.ingredient, builder.ingredientCount));

            JsonObject fluid = new JsonObject();
            fluid.addProperty("fluid", Registry.FLUID.getKey(builder.fluidInput.getFluid()).toString());
            if (builder.fluidInput.getAmount() > 1) {
                fluid.addProperty("amount", builder.fluidInput.getAmount());
            }
            json.add("fluid_ingredient", fluid);

            JsonObject result = new JsonObject();
            result.addProperty("fluid", Registry.FLUID.getKey(builder.fluidResult.getFluid()).toString());
            if (builder.fluidInput.getAmount() > 1) {
                result.addProperty("amount", builder.fluidResult.getAmount());
            }
            json.add("result", result);

            json.addProperty("experience", builder.experience);
            json.addProperty("duration", builder.duration);
            json.addProperty("power_cost", builder.powerCost);
        }

        private JsonElement serializeIngredient(Ingredient ingredient, int count) {
            JsonElement json = ingredient.toJson();
            json.getAsJsonObject().addProperty("count", count);
            return json;
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializer.FLUID_ENRICHING;
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
