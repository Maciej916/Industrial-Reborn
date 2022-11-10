package com.maciej916.indreb.datagen.recipes.builder;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class RecipeBuilderScanner {

    private final Ingredient item;
    private int matterCost;
    private int energyCost;
    private int duration;
    private int powerCost;
    private float experience;

    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    private String group = "scanner";

    public RecipeBuilderScanner(Ingredient item, int matterCost, int energyCost, int duration, int powerCost, float experience) {
        this.item = item;
        this.matterCost = matterCost;
        this.energyCost = energyCost;
        this.duration = duration;
        this.powerCost = powerCost;
        this.experience = experience;
    }

    public static RecipeBuilderScanner builder(ItemLike item) {
        return new RecipeBuilderScanner(Ingredient.of(item), 1, 16,500, 256, 0);
    }

    public static RecipeBuilderScanner builder(TagKey<Item> item) {
        return new RecipeBuilderScanner(Ingredient.of(item), 1, 16,500, 256, 0);
    }

    public RecipeBuilderScanner setMatterCost(int matterCost) {
        this.matterCost = matterCost;
        return this;
    }

    public RecipeBuilderScanner setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
        return this;
    }

    public RecipeBuilderScanner setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public RecipeBuilderScanner setPowerCost(int powerCost) {
        this.powerCost = powerCost;
        return this;
    }

    public RecipeBuilderScanner setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public RecipeBuilderScanner addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public RecipeBuilderScanner setGroup(String groupIn) {
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
        private final RecipeBuilderScanner builder;

        public Result(ResourceLocation id, RecipeBuilderScanner builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {

            json.add("item", builder.item.toJson());

            JsonObject replication = new JsonObject();
            replication.addProperty("matter_cost", builder.matterCost);
            replication.addProperty("energy_cost", builder.energyCost);
            json.add("replication", replication);

            json.addProperty("experience", builder.experience);
            json.addProperty("duration", builder.duration);
            json.addProperty("power_cost", builder.powerCost);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializer.SCANNER.get();
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
