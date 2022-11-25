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
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class RecipeBuilderCrushing {

    private final IngredientCount ingredients = new IngredientCount(1);
    private final ItemStack result;

    private int duration;
    private int tickEnergyCost;
    private float experience;

    private ChanceResult chanceResults = new ChanceResult();

    private String group = "";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private RecipeBuilderCrushing(ItemStack result, int duration, int tickEnergyCost, float experience) {
        this.result = result;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
    }

    public static RecipeBuilderCrushing builder(ItemLike item, int resultCount) {
        return new RecipeBuilderCrushing(new ItemStack(item, resultCount), 180, 8, 0);
    }

    public RecipeBuilderCrushing setIngredient(Ingredient ingredient, int count) {
        this.ingredients.setIngredient(0, ingredient, count);
        return this;
    }

    public RecipeBuilderCrushing setIngredient(ItemLike item, int count) {
        return setIngredient(Ingredient.of(item), count);
    }

    public RecipeBuilderCrushing setIngredient(TagKey<Item> tag, int count) {
        return setIngredient(Ingredient.of(tag), count);
    }

    public RecipeBuilderCrushing setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public RecipeBuilderCrushing setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public RecipeBuilderCrushing setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public RecipeBuilderCrushing addChanceResult(ItemLike itemIn, int count, int chance) {
        this.chanceResults = this.chanceResults.addChanceResult(new ItemStack(itemIn, count), chance);
        return this;
    }






    public RecipeBuilderCrushing setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public RecipeBuilderCrushing addCriterion(String name, CriterionTriggerInstance criterionIn) {
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

        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final RecipeBuilderCrushing builder;

        public Result(ResourceLocation id, RecipeBuilderCrushing builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredients", builder.ingredients.toJson());
            json.add("result", ResultItem.stackToJson(builder.result));
            json.add("chance_result", builder.chanceResults.toJson());
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
            return ModRecipeSerializer.CRUSHING.get();
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
