package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Transformers extends RecipeProvider {

    public Transformers(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "transformer/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.LV_TRANSFORMER)
                .pattern("pcp")
                .pattern("bob")
                .pattern("pcp")
                .define('p', ItemTags.bind("minecraft:planks"))
                .define('c', ModBlocks.COPPER_CABLE_INSULATED)
                .define('o', ModItems.COIL)
                .define('b', ModItems.BRONZE_PLATE)
                .group(MODID)
                .unlockedBy("oak_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS))
                .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .unlockedBy("coil", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COIL))
                .unlockedBy("bronze_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_PLATE))
                .save(consumer, saveResource("lv_transformer"));

        ShapedRecipeBuilder.shaped(ModBlocks.MV_TRANSFORMER)
                .pattern("g")
                .pattern("b")
                .pattern("g")
                .define('g', ModBlocks.GOLD_CABLE_INSULATED)
                .define('b', ModBlocks.BASIC_MACHINE_CASING)
                .group(MODID)
                .unlockedBy("gold_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CABLE_INSULATED))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .save(consumer, saveResource("mv_transformer"));

        ShapedRecipeBuilder.shaped(ModBlocks.HV_TRANSFORMER)
                .pattern(" g ")
                .pattern("ebn")
                .pattern(" g ")
                .define('g', ModBlocks.HV_CABLE_INSULATED)
                .define('b', ModBlocks.MV_TRANSFORMER)
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('n', ModItems.ENERGY_CRYSTAL)
                .group(MODID)
                .unlockedBy("hv_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HV_CABLE_INSULATED))
                .unlockedBy("mv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MV_TRANSFORMER))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .save(consumer, saveResource("hv_transformer"));

        ShapedRecipeBuilder.shaped(ModBlocks.EV_TRANSFORMER)
                .pattern(" g ")
                .pattern("ebn")
                .pattern(" g ")
                .define('g', ModBlocks.GLASS_FIBRE_CABLE)
                .define('b', ModBlocks.HV_TRANSFORMER)
                .define('e', ModItems.ADVANCED_CIRCUIT)
                .define('n', ModItems.LAPOTRON_CRYSTAL)
                .group(MODID)
                .unlockedBy("glass_fibre_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GLASS_FIBRE_CABLE))
                .unlockedBy("hv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HV_TRANSFORMER))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT))
                .unlockedBy("lapotron_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LAPOTRON_CRYSTAL))
                .save(consumer, saveResource("ev_transformer"));

    }

}