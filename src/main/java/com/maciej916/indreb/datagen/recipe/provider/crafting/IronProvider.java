package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class IronProvider extends RecipeProvider {

    public IronProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/iron/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.IRON_ROD.get(), 4)
                .pattern("i  ")
                .pattern("i  ")
                .define('i', ModItemTags.FORGE_INGOTS_IRON)
                .group(MODID + "/iron")
                .unlockedBy("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, saveResource("iron_rod"));

        ShapedRecipeBuilder.shaped(ModBlocks.IRON_FENCE.get(), 6)
                .pattern("iri")
                .pattern("iri")
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('r', ModItemTags.FORGE_RODS_IRON)
                .group(MODID + "/iron")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD.get()))
                .save(consumer, saveResource("iron_fence"));

        ShapedRecipeBuilder.shaped(ModBlocks.IRON_GATE.get(), 1)
                .pattern("rir")
                .pattern("rir")
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('r', ModItemTags.FORGE_RODS_IRON)
                .group(MODID + "/iron")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD.get()))
                .save(consumer, saveResource("iron_gate"));

        ShapedRecipeBuilder.shaped(ModBlocks.IRON_SCAFFOLDING.get(), 6)
                .pattern("ppp")
                .pattern("ftf")
                .pattern("ppp")
                .define('p', ModItemTags.FORGE_PLATES_IRON)
                .define('t', ModItemTags.FORGE_RODS_IRON)
                .define('f', ModBlocks.IRON_FENCE.get())
                .group(MODID + "/iron")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD.get()))
                .unlockedBy("iron_fence", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.IRON_FENCE.get()))
                .save(consumer, saveResource("iron_scaffolding"));




    }
}