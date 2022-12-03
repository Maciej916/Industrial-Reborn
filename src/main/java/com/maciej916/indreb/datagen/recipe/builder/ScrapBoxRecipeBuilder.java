package com.maciej916.indreb.datagen.recipe.builder;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.ResultItem;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ScrapBoxRecipeBuilder {

    private final IngredientCount ingredients = new IngredientCount(1);
    private final ItemStack result;

    private float weight = -1;
    private int duration;
    private int tickEnergyCost;
    private float experience;

    private String group = "scrap_box";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private ScrapBoxRecipeBuilder(ItemStack result, int duration, int tickEnergyCost, float experience) {
        this.result = result;
        this.duration = duration;
        this.tickEnergyCost = tickEnergyCost;
        this.experience = experience;
        this.ingredients.setIngredient(0, Ingredient.of(ModItems.SCRAP_BOX.get()), 1);
        this.advancementBuilder.addCriterion("scrap_box", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP_BOX.get()));
    }

    public static ScrapBoxRecipeBuilder builder(RegistryObject<Item> item) {
        return builder(item.get());
    }

    public static ScrapBoxRecipeBuilder builder(ItemLike item) {
        return new ScrapBoxRecipeBuilder(new ItemStack(item, 1), 10, 1, 0);
    }

    private ScrapBoxRecipeBuilder setIngredient(Ingredient ingredient, int count) {
        this.ingredients.setIngredient(0, ingredient, count);
        return this;
    }

    public ScrapBoxRecipeBuilder setIngredient(RegistryObject<Item> item, int count) {
        return setIngredient(Ingredient.of(item.get()), count);
    }

    public ScrapBoxRecipeBuilder setIngredient(ItemLike item, int count) {
        return setIngredient(Ingredient.of(item), count);
    }

    public ScrapBoxRecipeBuilder setIngredient(TagKey<Item> tag, int count) {
        return setIngredient(Ingredient.of(tag), count);
    }

    public ScrapBoxRecipeBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public ScrapBoxRecipeBuilder setTickEnergyCost(int tickEnergyCost) {
        this.tickEnergyCost = tickEnergyCost;
        return this;
    }

    public ScrapBoxRecipeBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public ScrapBoxRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public ScrapBoxRecipeBuilder setWeight(float weight) {
        this.weight = weight;
        return this;
    }

    public ScrapBoxRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
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

        if (this.ingredients.getIngredients().size() == 0 || weight == -1) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }

        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final ScrapBoxRecipeBuilder builder;

        public Result(ResourceLocation id, ScrapBoxRecipeBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredients", builder.ingredients.toJson());
            json.add("result", ResultItem.stackToJson(builder.result));
            json.addProperty("weight", builder.weight);
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
            return ModRecipeSerializer.SCRAP_BOX.get();
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
