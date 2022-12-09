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
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ItemsUpgradeProvider extends RecipeProvider {

    public ItemsUpgradeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/items/upgrade/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.OVERCLOCKER_UPGRADE.get(), 2)
                .pattern("ccc")
                .pattern("aea")
                .define('c', ModItems.SMALL_COOLANT_CELL.get())
                .define('a', ModBlocks.COPPER_CABLE_INSULATED.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "/items/upgrade")
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
                .group(MODID + "/items/upgrade")
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
                .group(MODID + "/items/upgrade")
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
                .group(MODID + "/items/upgrade")
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .unlockedBy("gold_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CABLE_INSULATED.get()))
                .unlockedBy("mv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MV_TRANSFORMER.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .save(consumer, saveResource("transformer_upgrade"));


        ShapedRecipeBuilder.shaped(ModItems.ENERGY_STORAGE_UPGRADE.get())
                .pattern("ppp")
                .pattern("cbc")
                .pattern("pep")
                .define('p', ItemTags.PLANKS)
                .define('b', ModItems.BATTERY.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('c', ModBlocks.COPPER_CABLE_INSULATED.get())
                .group(MODID + "/items/upgrade")
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
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .group(MODID + "/items/upgrade")
                .unlockedBy("lever", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEVER))
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, saveResource("redstone_signal_inverter_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.PULLING_UPGRADE.get())
                .pattern("t t")
                .pattern(" p ")
                .pattern("t t")
                .define('p', Items.STICKY_PISTON)
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .group(MODID + "/items/upgrade")
                .unlockedBy("sticky_piston", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICKY_PISTON))
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, saveResource("pulling_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.EJECTOR_UPGRADE.get())
                .pattern("t t")
                .pattern(" p ")
                .pattern("t t")
                .define('p', Items.PISTON)
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .group(MODID + "/items/upgrade")
                .unlockedBy("piston", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PISTON))
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, saveResource("ejector_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.FLUID_EJECTOR_UPGRADE.get())
                .pattern("t t")
                .pattern(" e ")
                .pattern("t t")
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .define('e', ModItems.ELECTRIC_MOTOR.get())
                .group(MODID + "/items/upgrade")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .save(consumer, saveResource("fluid_ejector_upgrade"));

        ShapedRecipeBuilder.shaped(ModItems.FLUID_PULLING_UPGRADE.get())
                .pattern("trt")
                .pattern(" e ")
                .pattern("t t")
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .define('e', ModItems.ELECTRIC_MOTOR.get())
                .define('r', ModItems.TREETAP.get())
                .group(MODID + "/items/upgrade")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .unlockedBy("treetap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TREETAP.get()))
                .save(consumer, saveResource("fluid_pulling_upgrade"));


    }
}