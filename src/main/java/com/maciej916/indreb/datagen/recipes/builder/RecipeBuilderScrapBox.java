package com.maciej916.indreb.datagen.recipes.builder;

import com.google.gson.JsonObject;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class RecipeBuilderScrapBox {

    private final ItemStack result;
    private float weight;

    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    public RecipeBuilderScrapBox(ItemStack result, float weight) {
        this.result = result;
        this.weight = weight;
        advancementBuilder.addCriterion("scrap_box", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP_BOX));
    }

    public static RecipeBuilderScrapBox builder(ItemLike item) {
        return new RecipeBuilderScrapBox(new ItemStack(item, 1), 0.1F);
    }

    public RecipeBuilderScrapBox setWeight(float weight) {
        this.weight = weight;
        return this;
    }

    public RecipeBuilderScrapBox addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }


    public void save(Consumer<FinishedRecipe> consumer, String name) {
        ResourceLocation id = new ResourceLocation(MODID, "scrap_box/" + name);
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
        private final RecipeBuilderScrapBox builder;

        public Result(ResourceLocation id, RecipeBuilderScrapBox builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonObject result = new JsonObject();
            result.addProperty("item", Registry.ITEM.getKey(builder.result.getItem()).toString());
            if (builder.result.getCount() > 1) {
                result.addProperty("count", builder.result.getCount());
            }
            json.add("result", result);
            json.addProperty("weight", builder.weight);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializer.SCRAP_BOX;
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
