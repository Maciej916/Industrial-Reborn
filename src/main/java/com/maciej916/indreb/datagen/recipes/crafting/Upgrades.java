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

//        // BASE
//
//        ShapedRecipeBuilder.shaped(ModItems.UPGRADE_BASE.get(), 3)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
//                .define('a', ModBlocks.TIN_CABLE_INSULATED.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
//                .unlockedBy("tin_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE_INSULATED.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("upgrade_base_tici"));
//
//        ShapedRecipeBuilder.shaped(ModItems.UPGRADE_BASE.get(), 6)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
//                .define('a', ModBlocks.COPPER_CABLE_INSULATED.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
//                .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("upgrade_base_coci"));
//
//        ShapedRecipeBuilder.shaped(ModItems.UPGRADE_BASE.get(), 8)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
//                .define('a', ModBlocks.GOLD_CABLE_INSULATED.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
//                .unlockedBy("gold_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CABLE_INSULATED.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("upgrade_base_goci"));
//
//        ShapedRecipeBuilder.shaped(ModItems.UPGRADE_BASE.get(), 12)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
//                .define('a', ModBlocks.HV_CABLE_INSULATED.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
//                .unlockedBy("hv_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HV_CABLE_INSULATED.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("upgrade_base_hvci"));
//
//        ShapedRecipeBuilder.shaped(ModItems.UPGRADE_BASE.get(), 16)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
//                .define('a', ModBlocks.GLASS_FIBRE_CABLE.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
//                .unlockedBy("glass_fibre_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GLASS_FIBRE_CABLE.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("upgrade_base_gfci"));
//
//        // Advanced base
//
//        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_UPGRADE_BASE.get(), 3)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ADVANCED_CIRCUIT.get())
//                .define('a', ModBlocks.TIN_CABLE_INSULATED.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
//                .unlockedBy("tin_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE_INSULATED.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("advanced_upgrade_base_tici"));
//
//        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_UPGRADE_BASE.get(), 6)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ADVANCED_CIRCUIT.get())
//                .define('a', ModBlocks.COPPER_CABLE_INSULATED.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
//                .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("advanced_upgrade_base_coci"));
//
//        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_UPGRADE_BASE.get(), 8)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ADVANCED_CIRCUIT.get())
//                .define('a', ModBlocks.GOLD_CABLE_INSULATED.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
//                .unlockedBy("gold_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CABLE_INSULATED.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("advanced_upgrade_base_goci"));
//
//        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_UPGRADE_BASE.get(), 12)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ADVANCED_CIRCUIT.get())
//                .define('a', ModBlocks.HV_CABLE_INSULATED.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
//                .unlockedBy("hv_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HV_CABLE_INSULATED.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("advanced_upgrade_base_hvci"));
//
//        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_UPGRADE_BASE.get(), 16)
//                .pattern("sas")
//                .pattern("aca")
//                .pattern("sas")
//                .define('c', ModItems.ADVANCED_CIRCUIT.get())
//                .define('a', ModBlocks.GLASS_FIBRE_CABLE.get())
//                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
//                .group(MODID)
//                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
//                .unlockedBy("glass_fibre_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GLASS_FIBRE_CABLE.get()))
//                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
//                .save(consumer, saveResource("advanced_upgrade_base_gfci"));



        // Upgrades

        ShapedRecipeBuilder.shaped(ModItems.OVERCLOCKER_UPGRADE.get(), 2)
                    .pattern("ccc")
                    .pattern("aea")
                    .define('c', ModItems.SMALL_COOLANT_CELL.get())
                    .define('a', ModBlocks.COPPER_CABLE_INSULATED.get())
                    .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                    .group(MODID)
                    .unlockedBy("small_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMALL_COOLANT_CELL.get()))
                    .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                    .save(consumer, saveResource("overclocker_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.OVERCLOCKER_UPGRADE.get(), 6)
                    .pattern("ccc")
                    .pattern("aea")
                    .define('c', ModItems.MEDIUM_COOLANT_CELL.get())
                    .define('a', ModBlocks.COPPER_CABLE_INSULATED.get())
                    .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                    .group(MODID)
                    .unlockedBy("medium_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MEDIUM_COOLANT_CELL.get()))
                    .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                    .save(consumer, saveResource("overclocker_upgrade_1"));


        ShapedRecipeBuilder.shaped(ModItems.OVERCLOCKER_UPGRADE.get(), 12)
                    .pattern("ccc")
                    .pattern("aea")
                    .define('c', ModItems.LARGE_COOLANT_CELL.get())
                    .define('a', ModBlocks.COPPER_CABLE_INSULATED.get())
                    .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                    .group(MODID)
                    .unlockedBy("large_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LARGE_COOLANT_CELL.get()))
                    .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                    .save(consumer, saveResource("overclocker_upgrade_2"));

        ShapedRecipeBuilder.shaped(ModItems.TRANSFORMER_UPGRADE.get())
                    .pattern("ggg")
                    .pattern("ctc")
                    .pattern("geg")
                    .define('g', Items.GLASS)
                    .define('c', ModBlocks.GOLD_CABLE_INSULATED.get())
                    .define('t', ModBlocks.MV_TRANSFORMER.get())
                    .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                    .group(MODID)
                    .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                    .unlockedBy("gold_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CABLE_INSULATED.get()))
                    .unlockedBy("mv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MV_TRANSFORMER.get()))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                    .save(consumer, saveResource("transformer_upgrade"));


        ShapedRecipeBuilder.shaped(ModItems.ENERGY_STORAGE_UPGRADE.get())
                    .pattern("ppp")
                    .pattern("cbc")
                    .pattern("pep")
                    .define('p', ItemTags.create(new ResourceLocation("planks")))
                    .define('b', ModItems.BATTERY.get())
                    .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                    .define('c', ModBlocks.COPPER_CABLE_INSULATED.get())
                    .group(MODID)
                    .unlockedBy("planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS))
                    .unlockedBy("battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY.get()))
                    .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                    .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
                    .save(consumer, saveResource("energy_storage_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.REDSTONE_SIGNAL_INVERTER_UPGRADE.get())
                    .pattern("t t")
                    .pattern(" l ")
                    .pattern("t t")
                    .define('l', Items.LEVER)
                    .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                    .group(MODID)
                    .unlockedBy("lever", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEVER))
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                    .save(consumer, saveResource("redstone_signal_inverter_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.PULLING_UPGRADE.get())
                    .pattern("t t")
                    .pattern(" p ")
                    .pattern("t t")
                    .define('p', Items.STICKY_PISTON)
                    .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                    .group(MODID)
                    .unlockedBy("sticky_piston", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICKY_PISTON))
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                    .save(consumer, saveResource("pulling_upgrade"));

         ShapedRecipeBuilder.shaped(ModItems.EJECTOR_UPGRADE.get())
                    .pattern("t t")
                    .pattern(" p ")
                    .pattern("t t")
                    .define('p', Items.PISTON)
                    .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                    .group(MODID)
                    .unlockedBy("piston", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PISTON))
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                    .save(consumer, saveResource("ejector_upgrade"));

         ShapedRecipeBuilder.shaped(ModItems.FLUID_EJECTOR_UPGRADE.get())
                    .pattern("t t")
                    .pattern(" e ")
                    .pattern("t t")
                    .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                    .define('e', ModItems.ELECTRIC_MOTOR.get())
                    .group(MODID)
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                    .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                    .save(consumer, saveResource("fluid_ejector_upgrade"));

         ShapedRecipeBuilder.shaped(ModItems.FLUID_PULLING_UPGRADE.get())
                    .pattern("trt")
                    .pattern(" e ")
                    .pattern("t t")
                    .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                    .define('e', ModItems.ELECTRIC_MOTOR.get())
                    .define('r', ModItems.TREETAP.get())
                    .group(MODID)
                    .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                    .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                    .unlockedBy("treetap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TREETAP.get()))
                    .save(consumer, saveResource("fluid_pulling_upgrade"));


    }

}