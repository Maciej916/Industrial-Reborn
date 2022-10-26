package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ChargePads extends RecipeProvider {

    public ChargePads(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "charge_pad/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_BATTERY_BOX.get())
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER.get())
                .define('b', ModBlocks.BATTERY_BOX.get())
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("battery_box", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BATTERY_BOX.get()))
                .save(consumer, saveResource("charge_pad_battery_box"));

        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_CESU.get())
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER.get())
                .define('b', ModBlocks.CESU.get())
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("cesu", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.CESU.get()))
                .save(consumer, saveResource("charge_pad_cesu"));

        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_MFE.get())
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER.get())
                .define('b', ModBlocks.MFE.get())
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("mfe", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MFE.get()))
                .save(consumer, saveResource("charge_pad_mfe"));

    ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_MFSU.get())
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER.get())
                .define('b', ModBlocks.MFSU.get())
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("mfsu", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MFSU.get()))
                .save(consumer, saveResource("charge_pad_mfsu"));

    }

}