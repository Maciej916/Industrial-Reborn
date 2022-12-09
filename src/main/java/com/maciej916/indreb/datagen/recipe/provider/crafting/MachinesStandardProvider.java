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

public class MachinesStandardProvider extends RecipeProvider {

    public MachinesStandardProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/machine/standard/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.ALLOY_SMELTER.get())
                .pattern(" e ")
                .pattern("fbf")
                .pattern(" h ")
                .define('f', ModBlocks.ELECTRIC_FURNACE.get())
                .define('e', ModItems.ADVANCED_CIRCUIT.get())
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('h', ModItems.HEAT_CONDUCTOR.get())
                .group(MODID + "/machines/standard")
                .unlockedBy("electric_furnace", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ELECTRIC_FURNACE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("heat_conductor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_CONDUCTOR.get()))
                .save(consumer, saveResource("alloy_smelter"));

        ShapedRecipeBuilder.shaped(ModBlocks.FERMENTER.get())
                .pattern("pcp")
                .pattern("cbc")
                .pattern("php")
                .define('p', ModItemTags.FORGE_PLATES_TIN)
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('c', ModItems.FLUID_CELL.get())
                .define('h', ModItems.HEAT_CONDUCTOR.get())
                .group(MODID + "/machines/standard")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .unlockedBy("heat_conductor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_CONDUCTOR.get()))
                .save(consumer, saveResource("fermenter"));

        ShapedRecipeBuilder.shaped(ModBlocks.ORE_WASHING_PLANT.get())
                .pattern("ppp")
                .pattern("cbc")
                .pattern("mem")
                .define('p', ModItemTags.FORGE_PLATES_IRON)
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('c', Items.BUCKET)
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('m', ModItems.ELECTRIC_MOTOR.get())
                .group(MODID + "/machines/standard")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("bucket", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BUCKET))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .save(consumer, saveResource("ore_washing_plant"));

        ShapedRecipeBuilder.shaped(ModBlocks.THERMAL_CENTRIFUGE.get())
                .pattern("chc")
                .pattern("pap")
                .pattern("pmp")
                .define('c', ModItems.COIL.get())
                .define('h', ModItems.HEAT_CONDUCTOR.get())
                .define('p', ModItemTags.FORGE_PLATES_IRON)
                .define('a', ModItems.ADVANCED_MACHINE_CASING.get())
                .define('m', ModItems.ELECTRIC_MOTOR.get())
                .group(MODID + "/machines/standard")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_MACHINE_CASING.get()))
                .unlockedBy("heat_conductor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_CONDUCTOR.get()))
                .unlockedBy("coil", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COIL.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .save(consumer, saveResource("thermal_centrifuge"));


    }
}