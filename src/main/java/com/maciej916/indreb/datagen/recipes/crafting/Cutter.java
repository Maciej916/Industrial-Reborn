package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Cutter extends RecipeProvider {

    public Cutter(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "cutter/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(ModBlocks.COPPER_CABLE, 2)
                .requires(ModItems.CUTTER)
                .requires(ItemTags.create(new ResourceLocation("forge", "plates/copper")))
                .group(MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE))
                .save(consumer, saveResource("copper_cable"));


        ShapelessRecipeBuilder.shapeless(ModBlocks.TIN_CABLE, 3)
                .requires(ModItems.CUTTER)
                .requires(ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .group(MODID)
                .unlockedBy("tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .save(consumer, saveResource("tin_cable"));


        ShapelessRecipeBuilder.shapeless(ModBlocks.GOLD_CABLE, 3)
                .requires(ModItems.CUTTER)
                .requires(ItemTags.create(new ResourceLocation("forge", "plates/gold")))
                .group(MODID)
                .unlockedBy("gold", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE))
                .save(consumer, saveResource("gold_cable"));


        ShapelessRecipeBuilder.shapeless(ModBlocks.HV_CABLE, 3)
                .requires(ModItems.CUTTER)
                .requires(ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .group(MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .save(consumer, saveResource("hv_cable"));

    }

}