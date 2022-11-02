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
import java.util.ArrayList;
import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class RecipeBuilderThermalCentrifuging {

    private final Ingredient ingredient;
    private final int ingredientCount;

    private final ArrayList<ItemStack> result = new ArrayList<>();

    private int temperature;
    private int duration;
    private int powerCost;
    private float experience;

    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    private String group = "";

    public RecipeBuilderThermalCentrifuging(Ingredient ingredient, int ingredientCount, int duration, int powerCost, float experience) {
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
        this.duration = duration;
        this.powerCost = powerCost;
        this.experience = experience;
    }

    public static RecipeBuilderThermalCentrifuging builder(ItemLike item, int ingredientCount) {
        return new RecipeBuilderThermalCentrifuging(Ingredient.of(item), ingredientCount, 500, 48, 0);
    }

    public static RecipeBuilderThermalCentrifuging builder(TagKey<Item> item, int ingredientCount) {
        return new RecipeBuilderThermalCentrifuging(Ingredient.of(item), ingredientCount, 500, 48, 0);
    }

    public RecipeBuilderThermalCentrifuging addResult(ItemStack item) {
        result.add(item);
        return this;
    }

    public RecipeBuilderThermalCentrifuging addResult(ItemLike item, int count) {
        return addResult(new ItemStack(item, count));
    }

    public RecipeBuilderThermalCentrifuging setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public RecipeBuilderThermalCentrifuging setTemperature(int temperature) {
        this.temperature = temperature;
        return this;
    }

    public RecipeBuilderThermalCentrifuging setPowerCost(int powerCost) {
        this.powerCost = powerCost;
        return this;
    }

    public RecipeBuilderThermalCentrifuging setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public RecipeBuilderThermalCentrifuging addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public RecipeBuilderThermalCentrifuging setGroup(String groupIn) {
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
        private final RecipeBuilderThermalCentrifuging builder;

        public Result(ResourceLocation id, RecipeBuilderThermalCentrifuging builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {

            json.add("ingredient", serializeIngredient(builder.ingredient, builder.ingredientCount));

            int i = 0;
            for (ItemStack stack: builder.result) {
                JsonObject result = new JsonObject();
                result.addProperty("item", ForgeRegistries.ITEMS.getKey(stack.getItem()).toString());
                if (stack.getCount() > 1) {
                    result.addProperty("count", stack.getCount());
                }
                i++;
                json.add("result_"+i, result);
            }

            json.addProperty("temperature", builder.temperature);
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
