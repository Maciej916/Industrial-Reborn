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

public class AlloySmeltingRecipeBuilder {

    private final IngredientCount ingredients = new IngredientCount();
    private final ItemStack result;

    private int duration;
    private int tickEnergyCost;
    private float experience;

    private String group = "";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private AlloySmeltingRecipeBuilder(ItemStack result, int duration, int tickEnergyCost, float experience) {
        this.result = result;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
    }

    public static AlloySmeltingRecipeBuilder builder(RegistryObject<Item> item, int resultCount) {
        return builder(item.get(), resultCount);
    }

    public static AlloySmeltingRecipeBuilder builder(ItemLike item, int resultCount) {
        return new AlloySmeltingRecipeBuilder(new ItemStack(item, resultCount), 560, 16, 0);
    }

    private AlloySmeltingRecipeBuilder addIngredient(Ingredient ingredient, int count) {
        this.ingredients.addIngredient(ingredient, count);
        return this;
    }

    public AlloySmeltingRecipeBuilder addIngredient(RegistryObject<Item> item, int count) {
        return addIngredient(Ingredient.of(item.get()), count);
    }

    public AlloySmeltingRecipeBuilder addIngredient(ItemLike item, int count) {
        return addIngredient(Ingredient.of(item), count);
    }

    public AlloySmeltingRecipeBuilder addIngredient(TagKey<Item> tag, int count) {
        return addIngredient(Ingredient.of(tag), count);
    }

    public AlloySmeltingRecipeBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public AlloySmeltingRecipeBuilder setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public AlloySmeltingRecipeBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public AlloySmeltingRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public AlloySmeltingRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
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

        if (this.ingredients.getIngredients().size() > 3) {
            throw new IllegalStateException("Recipe can not have more than 3 ingredients " + id);
        }

        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final AlloySmeltingRecipeBuilder builder;

        public Result(ResourceLocation id, AlloySmeltingRecipeBuilder builder) {
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
            return ModRecipeSerializer.ALLOY_SMELTING.get();
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
