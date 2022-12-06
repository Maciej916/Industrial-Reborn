package com.maciej916.indreb.datagen.recipe.builder;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ScanningRecipeBuilder {

    private final IngredientCount ingredients = new IngredientCount(1);
    private int matterCost = 0;
    private int energyCost = 0;

    private int duration;
    private int tickEnergyCost;
    private float experience;

    private String group = "scanning";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private ScanningRecipeBuilder(Ingredient ingredient, int matterCost, int energyCost, int duration, int tickEnergyCost, float experience) {
        this.ingredients.setIngredient(0, ingredient, 1);
        this.matterCost = matterCost;
        this.energyCost = energyCost;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
    }

    public static ScanningRecipeBuilder builder(Ingredient ingredient) {
        return new ScanningRecipeBuilder(ingredient, 1, 32,600, 256, 0);
    }

    public static ScanningRecipeBuilder builder(RegistryObject<Item> item) {
        return builder(Ingredient.of(item.get()));
    }

    public static ScanningRecipeBuilder builder(ItemLike item) {
        return builder(Ingredient.of(item));
    }

    public static ScanningRecipeBuilder builder(TagKey<Item> tag) {
        return builder(Ingredient.of(tag));
    }

    public ScanningRecipeBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public ScanningRecipeBuilder setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public ScanningRecipeBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public ScanningRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public ScanningRecipeBuilder setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
        return this;
    }

    public ScanningRecipeBuilder setMatterCost(int matterCost) {
        this.matterCost = matterCost;
        return this;
    }

    public ScanningRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
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

        if (this.ingredients.getIngredients().size() == 0 || (matterCost == 0 && energyCost == 0)) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }

        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final ScanningRecipeBuilder builder;

        public Result(ResourceLocation id, ScanningRecipeBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredients", builder.ingredients.toJson());

            JsonObject replication = new JsonObject();
            replication.addProperty("matter_cost", builder.matterCost);
            replication.addProperty("energy_cost", builder.energyCost);
            json.add("replication", replication);

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
            return ModRecipeSerializer.SCANNING.get();
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
