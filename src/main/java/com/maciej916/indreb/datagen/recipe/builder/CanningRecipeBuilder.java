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

public class CanningRecipeBuilder {

    private final IngredientCount ingredients = new IngredientCount(2);
    private final ItemStack result;

    private int duration;
    private int tickEnergyCost;
    private float experience;

    private String group = "";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private CanningRecipeBuilder(ItemStack result, int duration, int tickEnergyCost, float experience) {
        this.result = result;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
    }

    public static CanningRecipeBuilder builder(RegistryObject<Item> item, int resultCount) {
        return builder(item.get(), resultCount);
    }

    public static CanningRecipeBuilder builder(ItemLike item, int resultCount) {
        return new CanningRecipeBuilder(new ItemStack(item, resultCount), 180, 8, 0);
    }

    private CanningRecipeBuilder setIngredient(int index, Ingredient ingredient, int count) {
        this.ingredients.setIngredient(index, ingredient, count);
        return this;
    }

    public CanningRecipeBuilder setFirstIngredient(RegistryObject<Item> item, int count) {
        return setIngredient(0, Ingredient.of(item.get()), count);
    }

    public CanningRecipeBuilder setFirstIngredient(ItemLike item, int count) {
        return setIngredient(0, Ingredient.of(item), count);
    }

    public CanningRecipeBuilder setFirstIngredient(TagKey<Item> tag, int count) {
        return setIngredient(0, Ingredient.of(tag), count);
    }

    public CanningRecipeBuilder setSecondIngredient(RegistryObject<Item> item, int count) {
        return setIngredient(1, Ingredient.of(item.get()), count);
    }

    public CanningRecipeBuilder setSecondIngredient(ItemLike item, int count) {
        return setIngredient(1, Ingredient.of(item), count);
    }

    public CanningRecipeBuilder setSecondIngredient(TagKey<Item> tag, int count) {
        return setIngredient(1, Ingredient.of(tag), count);
    }

    public CanningRecipeBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public CanningRecipeBuilder setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public CanningRecipeBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public CanningRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public CanningRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
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

        if (this.ingredients.getIngredients().get(0) == Ingredient.EMPTY || this.ingredients.getIngredients().get(1) == Ingredient.EMPTY) {
            throw new IllegalStateException("Ingredient can not be empty " + id);
        }

        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final CanningRecipeBuilder builder;

        public Result(ResourceLocation id, CanningRecipeBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredients", builder.ingredients.toJson());
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
            return ModRecipeSerializer.CANNING.get();
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
