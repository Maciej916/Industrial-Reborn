package com.maciej916.indreb.datagen.recipes.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class RecipeBuilderRecycling {

    private final ItemStack result;
    private float chance;
    private final HashSet<Ingredient> excluded = new HashSet<>();

    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    private String group = "";

    public RecipeBuilderRecycling(ItemStack result, float chance) {
        this.result = result;
        this.chance = chance;
    }

    public static RecipeBuilderRecycling builder(ItemLike item) {
        return new RecipeBuilderRecycling(new ItemStack(item, 1), 0.1F);
    }

    public RecipeBuilderRecycling setChance(float chance) {
        this.chance = chance;
        return this;
    }

    public RecipeBuilderRecycling addExcluded(Ingredient ingredient) {
        this.excluded.add(ingredient);
        return this;
    }

    public RecipeBuilderRecycling addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public RecipeBuilderRecycling setGroup(String groupIn) {
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
        private final RecipeBuilderRecycling builder;

        public Result(ResourceLocation id, RecipeBuilderRecycling builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonObject result = new JsonObject();
            result.addProperty("item", ForgeRegistries.ITEMS.getKey(builder.result.getItem()).toString());
            if (builder.result.getCount() > 1) {
                result.addProperty("count", builder.result.getCount());
            }
            json.add("result", result);
            json.addProperty("chance", builder.chance);

            JsonArray excluded = new JsonArray();
            builder.excluded.forEach(ingredient -> {
                excluded.add(ingredient.toJson());
            });
            json.add("excluded", excluded);
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
