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
import net.minecraft.world.level.block.Blocks;

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

        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_BATTERY_BOX)
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER)
                .define('b', ModBlocks.BATTERY_BOX)
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER))
                .unlockedBy("battery_box", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BATTERY_BOX))
                .save(consumer, saveResource("charge_pad_battery_box"));

        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_CESU)
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER)
                .define('b', ModBlocks.CESU)
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER))
                .unlockedBy("cesu", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.CESU))
                .save(consumer, saveResource("charge_pad_cesu"));

        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_MFE)
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER)
                .define('b', ModBlocks.MFE)
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER))
                .unlockedBy("mfe", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MFE))
                .save(consumer, saveResource("charge_pad_mfe"));

    ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_MFSU)
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER)
                .define('b', ModBlocks.MFSU)
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER))
                .unlockedBy("mfsu", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MFSU))
                .save(consumer, saveResource("charge_pad_mfsu"));

    }

}