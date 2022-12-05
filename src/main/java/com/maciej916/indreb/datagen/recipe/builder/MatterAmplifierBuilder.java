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

public class MatterAmplifierBuilder {

    private final IngredientCount ingredients = new IngredientCount(1);
    private int amplifiedAmount;

    private String group = "matter_amplifier";
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    private MatterAmplifierBuilder(int amplifiedAmount) {
        this.amplifiedAmount = amplifiedAmount;
    }

    public static MatterAmplifierBuilder builder(int amplifiedAmount) {
        return new MatterAmplifierBuilder(amplifiedAmount);
    }

    private MatterAmplifierBuilder setIngredient(Ingredient ingredient) {
        this.ingredients.setIngredient(0, ingredient, 1);
        return this;
    }

    public MatterAmplifierBuilder setIngredient(RegistryObject<Item> item) {
        return setIngredient(Ingredient.of(item.get()));
    }

    public MatterAmplifierBuilder setIngredient(ItemLike item) {
        return setIngredient(Ingredient.of(item));
    }

    public MatterAmplifierBuilder setIngredient(TagKey<Item> tag) {
        return setIngredient(Ingredient.of(tag));
    }

    public MatterAmplifierBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public MatterAmplifierBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
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
        private final MatterAmplifierBuilder builder;

        public Result(ResourceLocation id, MatterAmplifierBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredients", builder.ingredients.toJson());
            json.addProperty("amplified_amount", builder.amplifiedAmount);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializer.MATTER_AMPLIFIER.get();
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
