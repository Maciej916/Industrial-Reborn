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

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class MachinesSuperProvider extends RecipeProvider {

    public MachinesSuperProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/machine/super/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.SCANNER.get())
                .pattern("igi")
                .pattern("mlm")
                .pattern("cac")
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('g', ModItems.REINFORCED_GLASS.get())
                .define('l', ModItems.LUMINATOR.get())
                .define('m', ModItems.ELECTRIC_MOTOR.get())
                .define('a', ModItems.ADVANCED_CIRCUIT.get())
                .define('c', ModItems.ADVANCED_MACHINE_CASING.get())
                .group(MODID + "/super")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("reinforced_glass", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_GLASS.get()))
                .unlockedBy("luminator", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LUMINATOR.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_MACHINE_CASING.get()))
                .save(consumer, saveResource("scanner"));

        ShapedRecipeBuilder.shaped(ModBlocks.REPLICATOR.get())
                .pattern("gsg")
                .pattern("ttt")
                .pattern("hmh")
                .define('g', ModItems.REINFORCED_GLASS.get())
                .define('s', ModItems.REINFORCED_STONE.get())
                .define('t', ModItems.TELEPORT_ANCHOR.get())
                .define('h', ModItems.HV_TRANSFORMER.get())
                .define('m', ModItems.MFE.get())
                .group(MODID + "/super")
                .unlockedBy("reinforced_glass", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_GLASS.get()))
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE.get()))
                .unlockedBy("teleport_anchor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TELEPORT_ANCHOR.get()))
                .unlockedBy("hv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HV_TRANSFORMER.get()))
                .unlockedBy("mfe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MFE.get()))
                .save(consumer, saveResource("replicator"));




    }
}