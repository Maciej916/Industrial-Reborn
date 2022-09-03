package com.maciej916.indreb.datagen.recipes.builder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
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
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class RecipeBuilderCanning {

    private Ingredient firstIngredient;
    private int firstIngredientCount;

    private Ingredient secondIngredient;
    private int secondIngredientCount;

    private final ItemStack result;
    private int duration;
    private int powerCost;
    private float experience;
    
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    private String group = "";

    public RecipeBuilderCanning(ItemStack result, int duration, int powerCost, float experience) {
        this.result = result;
        this.duration = duration;
        this.powerCost = powerCost;
        this.experience = experience;
    }

    public static RecipeBuilderCanning builder(ItemLike item, int resultCount) {
        return new RecipeBuilderCanning(new ItemStack(item, resultCount), 180, 4, 0);
    }

    public RecipeBuilderCanning setFirstIngredient(Ingredient ingredient, int count) {
        this.firstIngredient = ingredient;
        this.firstIngredientCount = count;
        return this;
    }

    public RecipeBuilderCanning setFirstIngredient(ItemLike item, int count) {
        return setFirstIngredient(Ingredient.of(item), count);
    }

    public RecipeBuilderCanning setFirstIngredient(TagKey<Item> tag, int count) {
        return setFirstIngredient(Ingredient.of(tag), count);
    }

    public RecipeBuilderCanning setSecondIngredient(Ingredient ingredient, int count) {
        this.secondIngredient = ingredient;
        this.secondIngredientCount = count;
        return this;
    }

    public RecipeBuilderCanning setSecondIngredient(ItemLike item, int count) {
        return setSecondIngredient(Ingredient.of(item), count);
    }

    public RecipeBuilderCanning setSecondIngredient(TagKey<Item> tag, int count) {
        return setSecondIngredient(Ingredient.of(tag), count);
    }

    public RecipeBuilderCanning setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public RecipeBuilderCanning setPowerCost(int powerCost) {
        this.powerCost = powerCost;
        return this;
    }

    public RecipeBuilderCanning setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public RecipeBuilderCanning addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public RecipeBuilderCanning setGroup(String groupIn) {
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
        private final RecipeBuilderCanning builder;

        public Result(ResourceLocation id, RecipeBuilderCanning builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {

            json.add("ingredient_from", serializeIngredient(builder.firstIngredient, builder.firstIngredientCount));
            json.add("ingredient_to", serializeIngredient(builder.secondIngredient, builder.secondIngredientCount));

            JsonObject result = new JsonObject();
            result.addProperty("item", ForgeRegistries.ITEMS.getKey(builder.result.getItem()).toString());
            if (builder.result.getCount() > 1) {
                result.addProperty("count", builder.result.getCount());
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
            return ModRecipeSerializer.CANNING;
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
