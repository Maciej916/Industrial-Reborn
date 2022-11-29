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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class RecyclingRecipeBuilder {

    private final IngredientCount ingredientIncluded = new IngredientCount();
    private final IngredientCount ingredientsExcluded = new IngredientCount();
    private final ItemStack result;

    private float chance;
    private int duration;
    private int tickEnergyCost;
    private float experience;

    private String group = "";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private RecyclingRecipeBuilder(ItemStack result, int duration, int tickEnergyCost, float experience) {
        this.result = result;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
        this.chance = 15;
    }

    public static RecyclingRecipeBuilder builder(RegistryObject<Item> item, int resultCount) {
        return builder(item.get(), resultCount);
    }

    public static RecyclingRecipeBuilder builder(ItemLike item, int resultCount) {
        return new RecyclingRecipeBuilder(new ItemStack(item, resultCount), 45, 1, 0);
    }

    private RecyclingRecipeBuilder addIngredientIncluded(Ingredient ingredient, int count) {
        this.ingredientIncluded.addIngredient(ingredient, count);
        return this;
    }

    public RecyclingRecipeBuilder addIngredientIncluded(RegistryObject<Item> item, int count) {
        return addIngredientIncluded(Ingredient.of(item.get()), count);
    }

    public RecyclingRecipeBuilder addIngredientIncluded(ItemLike item, int count) {
        return addIngredientIncluded(Ingredient.of(item), count);
    }

    public RecyclingRecipeBuilder addIngredientIncluded(TagKey<Item> tag, int count) {
        return addIngredientIncluded(Ingredient.of(tag), count);
    }

    private RecyclingRecipeBuilder addIngredientExcluded(Ingredient ingredient, int count) {
        this.ingredientsExcluded.addIngredient(ingredient, count);
        return this;
    }

    public RecyclingRecipeBuilder addIngredientExcluded(RegistryObject<Item> item, int count) {
        return addIngredientExcluded(Ingredient.of(item.get()), count);
    }

    public RecyclingRecipeBuilder addIngredientExcluded(ItemLike item, int count) {
        return addIngredientExcluded(Ingredient.of(item), count);
    }

    public RecyclingRecipeBuilder addIngredientExcluded(TagKey<Item> tag, int count) {
        return addIngredientExcluded(Ingredient.of(tag), 1);
    }

    public RecyclingRecipeBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public RecyclingRecipeBuilder setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public RecyclingRecipeBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public RecyclingRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public RecyclingRecipeBuilder setChance(float chance) {
        this.chance = chance;
        return this;
    }

    public RecyclingRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
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

        if (this.ingredientIncluded.getIngredients().size() > 0 && this.ingredientsExcluded.getIngredients().size() > 0) {
            throw new IllegalStateException("Recipe can not have included and excluded ingredients " + id);
        }

        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final RecyclingRecipeBuilder builder;

        public Result(ResourceLocation id, RecyclingRecipeBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (builder.ingredientIncluded.getSize() > 0) json.add("ingredients_included", builder.ingredientIncluded.toJson());
            if (builder.ingredientsExcluded.getSize() > 0) json.add("ingredients_excluded", builder.ingredientsExcluded.toJson());
            json.add("result", ResultItem.stackToJson(builder.result));
            json.addProperty("chance", builder.chance);
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
            return ModRecipeSerializer.RECYCLING.get();
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
