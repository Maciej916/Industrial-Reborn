package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Item extends RecipeProvider {

    public Item(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        ShapedRecipeBuilder.shaped(ModItems.ELECTRONIC_CIRCUIT)
                .pattern("ccc")
                .pattern("rir")
                .pattern("ccc")
                .define('c', ModBlocks.COPPER_CABLE_INSULATED)
                .define('i', Items.IRON_INGOT)
                .define('r', Items.REDSTONE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .save(consumer, saveResource("electronic_circuit"));


        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_CIRCUIT)
                .pattern("rgr")
                .pattern("lel")
                .pattern("rgr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('l', Items.LAPIS_LAZULI)
                .define('g', Items.GLOWSTONE_DUST)
                .define('r', Items.REDSTONE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .save(consumer, saveResource("advanced_circuit"));


        ShapedRecipeBuilder.shaped(ModItems.FLUID_CELL, 4)
                .pattern(" t ")
                .pattern("tgt")
                .pattern(" t ")
                .define('t', ItemTags.bind("forge:plates/tin"))
                .define('g', Items.GLASS_PANE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT))
                .save(consumer, saveResource("fluid_cell"));


        ShapedRecipeBuilder.shaped(Items.PAPER, 6)
                .pattern("SSS")
                .define('S', ModItems.SAWDUST)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SAWDUST))
                .save(consumer, saveResource("sawdust_paper"));


        ShapedRecipeBuilder.shaped(ModItems.IRON_ROD, 4)
                .pattern("i  ")
                .pattern("i  ")
                .define('i', Items.IRON_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, saveResource("iron_rod"));


        ShapedRecipeBuilder.shaped(ModItems.CARBON_FIBERS)
                .pattern("cc ")
                .pattern("cc ")
                .define('c', ModItems.COAL_DUST)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST))
                .save(consumer, saveResource("carbon_fibers"));


        ShapedRecipeBuilder.shaped(ModItems.COMBINED_CARBON_FIBERS)
                .pattern("cc ")
                .define('c', ModItems.CARBON_FIBERS)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_FIBERS))
                .save(consumer, saveResource("combined_carbon_fibers"));


        ShapedRecipeBuilder.shaped(ModItems.ENERGIUM_DUST, 9)
                .pattern("rdr")
                .pattern("drd")
                .pattern("rdr")
                .define('r', Items.REDSTONE)
                .define('d', ModItems.DIAMOND_DUST)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_DUST))
                .save(consumer, saveResource("energium_dust"));


    }

}