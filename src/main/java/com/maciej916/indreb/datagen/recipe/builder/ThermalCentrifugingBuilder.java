package com.maciej916.indreb.datagen.recipe.builder;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.lib.ChanceResult;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ThermalCentrifugingBuilder {

    private final IngredientCount ingredients = new IngredientCount(1);
    private final ItemStack result;

    private float temperature = -1;
    private int duration;
    private int tickEnergyCost;
    private float experience;

    private ChanceResult chanceResults = new ChanceResult();

    private String group = "";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private ThermalCentrifugingBuilder(ItemStack result, int duration, int tickEnergyCost, float experience) {
        this.result = result;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
    }

    public static ThermalCentrifugingBuilder builder(RegistryObject<Item> item, int resultCount) {
        return builder(item.get(), resultCount);
    }

    public static ThermalCentrifugingBuilder builder(ItemLike item, int resultCount) {
        return new ThermalCentrifugingBuilder(new ItemStack(item, resultCount), 500, 46, 0);
    }

    private ThermalCentrifugingBuilder setIngredient(Ingredient ingredient, int count) {
        this.ingredients.setIngredient(0, ingredient, count);
        return this;
    }

    public ThermalCentrifugingBuilder setIngredient(RegistryObject<Item> item, int count) {
        return setIngredient(Ingredient.of(item.get()), count);
    }

    public ThermalCentrifugingBuilder setIngredient(ItemLike item, int count) {
        return setIngredient(Ingredient.of(item), count);
    }

    public ThermalCentrifugingBuilder setIngredient(TagKey<Item> tag, int count) {
        return setIngredient(Ingredient.of(tag), count);
    }

    public ThermalCentrifugingBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public ThermalCentrifugingBuilder setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public ThermalCentrifugingBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public ThermalCentrifugingBuilder setTemperature(int temperature) {
        this.temperature = temperature;
        return this;
    }

    public ThermalCentrifugingBuilder addChanceResult(ItemLike item, int count, int chance) {
        this.chanceResults = this.chanceResults.addChanceResult(new ItemStack(item, count), chance);
        return this;
    }

    public ThermalCentrifugingBuilder addChanceResult(RegistryObject<Item> item, int count, int chance) {
        this.chanceResults = this.chanceResults.addChanceResult(new ItemStack(item.get(), count), chance);
        return this;
    }

    public ThermalCentrifugingBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public ThermalCentrifugingBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
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

        if (this.ingredients.getIngredients().get(0) == Ingredient.EMPTY) {
            throw new IllegalStateException("Ingredient can not be empty " + id);
        }

        if (this.temperature == -1) {
            throw new IllegalStateException("Temperature can not be negative " + id);
        }

        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final ThermalCentrifugingBuilder builder;

        public Result(ResourceLocation id, ThermalCentrifugingBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredients", builder.ingredients.toJson());
            json.add("result", ResultItem.stackToJson(builder.result));
            json.add("chance_result", builder.chanceResults.toJson());
            json.addProperty("temperature", builder.temperature);
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
            return ModRecipeSerializer.THERMAL_CENTRIFUGING.get();
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
