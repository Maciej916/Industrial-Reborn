package com.maciej916.indreb.datagen.recipes.builder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.maciej916.indreb.common.receipe.ChanceResult;
import com.maciej916.indreb.common.receipe.RecipeChanceResult;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class RecipeBuilderSawing {

    private Ingredient ingredient;
    private int ingredientCount;
    private final ItemStack result;
    private RecipeChanceResult bonusResult = new RecipeChanceResult();
    private int duration;
    private int powerCost;
    private float experience;
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    private String group = "";

    public RecipeBuilderSawing(ItemStack result, int duration, int powerCost, float experience) {
        this.result = result;
        this.duration = duration;
        this.powerCost = powerCost;
        this.experience = experience;
    }

    public static RecipeBuilderSawing builder(ItemLike item, int resultCount) {
        return new RecipeBuilderSawing(new ItemStack(item, resultCount), 180, 8, 0);
    }

    public RecipeBuilderSawing setBonus(ItemLike itemIn, int count, int chance) {
        this.bonusResult = this.bonusResult.addChanceResult(new ItemStack(itemIn, count), chance);
        return this;
    }

    public RecipeBuilderSawing setIngredient(Ingredient ingredient, int count) {
        this.ingredient = ingredient;
        this.ingredientCount = count;
        return this;
    }

    public RecipeBuilderSawing setIngredient(ItemLike item, int count) {
        return setIngredient(Ingredient.of(item), count);
    }

    public RecipeBuilderSawing setIngredient(Tag<Item> tag, int count) {
        return setIngredient(Ingredient.of(tag), count);
    }

    public RecipeBuilderSawing setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public RecipeBuilderSawing setPowerCost(int powerCost) {
        this.powerCost = powerCost;
        return this;
    }

    public RecipeBuilderSawing setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public RecipeBuilderSawing addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public RecipeBuilderSawing setGroup(String groupIn) {
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
        private final RecipeBuilderSawing builder;

        public Result(ResourceLocation id, RecipeBuilderSawing builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {

            json.add("ingredient", serializeIngredient(builder.ingredient, builder.ingredientCount));

            JsonObject result = new JsonObject();
            result.addProperty("item", Registry.ITEM.getKey(builder.result.getItem()).toString());
            if (builder.result.getCount() > 1) {
                result.addProperty("count", builder.result.getCount());
            }
            json.add("result", result);

            if (builder.bonusResult.getResults().size() > 0) {
                JsonObject bonusJSON = new JsonObject();
                ChanceResult bonusResult = builder.bonusResult.getResults().get(0);
                bonusJSON.addProperty("item", Registry.ITEM.getKey(bonusResult.getStack().getItem()).toString());
                bonusJSON.addProperty("count", bonusResult.getCount());
                bonusJSON.addProperty("chance", bonusResult.getChance());
                json.add("bonus_result", bonusJSON);
            }

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
            return ModRecipeSerializer.SAWING;
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
