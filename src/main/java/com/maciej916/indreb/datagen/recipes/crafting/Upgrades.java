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

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Upgrades extends RecipeProvider {

    public Upgrades(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/upgrades/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.OVERCLOCKER_UPGRADE, 2)
                    .pattern("ccc")
                    .pattern("aea")
                    .define('c', ModItems.SMALL_COOLANT_CELL)
                    .define('a', ModBlocks.COPPER_CABLE_INSULATED)
                    .define('e', ModItems.ELECTRONIC_CIRCUIT)
                    .group(MODID)
                    .unlockedBy("small_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMALL_COOLANT_CELL))
                    .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                    .save(consumer, saveResource("overclocker_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.OVERCLOCKER_UPGRADE, 6)
                    .pattern("ccc")
                    .pattern("aea")
                    .define('c', ModItems.MEDIUM_COOLANT_CELL)
                    .define('a', ModBlocks.COPPER_CABLE_INSULATED)
                    .define('e', ModItems.ELECTRONIC_CIRCUIT)
                    .group(MODID)
                    .unlockedBy("medium_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MEDIUM_COOLANT_CELL))
                    .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                    .save(consumer, saveResource("overclocker_upgrade_1"));


        ShapedRecipeBuilder.shaped(ModItems.OVERCLOCKER_UPGRADE, 12)
                    .pattern("ccc")
                    .pattern("aea")
                    .define('c', ModItems.LARGE_COOLANT_CELL)
                    .define('a', ModBlocks.COPPER_CABLE_INSULATED)
                    .define('e', ModItems.ELECTRONIC_CIRCUIT)
                    .group(MODID)
                    .unlockedBy("large_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LARGE_COOLANT_CELL))
                    .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                    .save(consumer, saveResource("overclocker_upgrade_2"));

        ShapedRecipeBuilder.shaped(ModItems.TRANSFORMER_UPGRADE)
                    .pattern("ggg")
                    .pattern("ctc")
                    .pattern("geg")
                    .define('g', Items.GLASS)
                    .define('c', ModBlocks.GOLD_CABLE_INSULATED)
                    .define('t', ModBlocks.MV_TRANSFORMER)
                    .define('e', ModItems.ELECTRONIC_CIRCUIT)
                    .group(MODID)
                    .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                    .unlockedBy("gold_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CABLE_INSULATED))
                    .unlockedBy("mv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MV_TRANSFORMER))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                    .save(consumer, saveResource("transformer_upgrade"));


        ShapedRecipeBuilder.shaped(ModItems.ENERGY_STORAGE_UPGRADE)
                    .pattern("ppp")
                    .pattern("cbc")
                    .pattern("pep")
                    .define('p', ItemTags.bind("minecraft:planks"))
                    .define('b', ModItems.BATTERY)
                    .define('e', ModItems.ELECTRONIC_CIRCUIT)
                    .define('c', ModBlocks.COPPER_CABLE_INSULATED)
                    .group(MODID)
                    .unlockedBy("planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS))
                    .unlockedBy("battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                    .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                    .save(consumer, saveResource("energy_storage_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.REDSTONE_SIGNAL_INVERTER_UPGRADE)
                    .pattern("t t")
                    .pattern(" l ")
                    .pattern("t t")
                    .define('l', Items.LEVER)
                    .define('t', ItemTags.bind("forge:plates/tin"))
                    .group(MODID)
                    .unlockedBy("lever", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEVER))
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                    .save(consumer, saveResource("redstone_signal_inverter_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.PULLING_UPGRADE)
                    .pattern("t t")
                    .pattern(" p ")
                    .pattern("t t")
                    .define('p', Items.STICKY_PISTON)
                    .define('t', ItemTags.bind("forge:plates/tin"))
                    .group(MODID)
                    .unlockedBy("sticky_piston", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICKY_PISTON))
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                    .save(consumer, saveResource("pulling_upgrade"));

         ShapedRecipeBuilder.shaped(ModItems.EJECTOR_UPGRADE)
                    .pattern("t t")
                    .pattern(" p ")
                    .pattern("t t")
                    .define('p', Items.PISTON)
                    .define('t', ItemTags.bind("forge:plates/tin"))
                    .group(MODID)
                    .unlockedBy("piston", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PISTON))
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                    .save(consumer, saveResource("ejector_upgrade"));

         ShapedRecipeBuilder.shaped(ModItems.FLUID_EJECTOR_UPGRADE)
                    .pattern("t t")
                    .pattern(" e ")
                    .pattern("t t")
                    .define('t', ItemTags.bind("forge:plates/tin"))
                    .define('e', ModItems.ELECTRIC_MOTOR)
                    .group(MODID)
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                    .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR))
                    .save(consumer, saveResource("fluid_ejector_upgrade"));

         ShapedRecipeBuilder.shaped(ModItems.FLUID_PULLING_UPGRADE)
                    .pattern("trt")
                    .pattern(" e ")
                    .pattern("t t")
                    .define('t', ItemTags.bind("forge:plates/tin"))
                    .define('e', ModItems.ELECTRIC_MOTOR)
                    .define('r', ModItems.TREETAP)
                    .group(MODID)
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                    .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR))
                    .unlockedBy("treetap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TREETAP))
                    .save(consumer, saveResource("fluid_pulling_upgrade"));


    }

}