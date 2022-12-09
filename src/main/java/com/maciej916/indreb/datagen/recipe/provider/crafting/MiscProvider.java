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

public class MiscProvider extends RecipeProvider {

    public MiscProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/misc/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.LUMINATOR.get(), 8)
                .pattern("pcp")
                .pattern("gug")
                .pattern("ggg")
                .define('p', ModItemTags.FORGE_PLATES_TIN)
                .define('c', ModBlocks.TIN_CABLE_INSULATED.get())
                .define('u', ModBlocks.TIN_CABLE.get())
                .define('g', Items.GLASS)
                .group(MODID + "/misc")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("tin_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE_INSULATED.get()))
                .unlockedBy("tin_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE.get()))
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .save(consumer, saveResource("luminator"));

        ShapedRecipeBuilder.shaped(ModBlocks.PATTERN_STORAGE.get())
                .pattern("rrr")
                .pattern("mam")
                .pattern("scs")
                .define('r', ModItems.REINFORCED_STONE.get())
                .define('m', ModItems.MEMORY_CARD.get())
                .define('a', ModItems.BASIC_MACHINE_CASING.get())
                .define('c', ModItems.ADVANCED_CIRCUIT.get())
                .define('s', ModItemTags.FORGE_CHESTS_WOODEN)
                .group(MODID + "/misc")
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE.get()))
                .unlockedBy("basic_transporter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_TRANSPORTER.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("chest", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHEST))
                .save(consumer, saveResource("pattern_storage"));

        ShapedRecipeBuilder.shaped(ModBlocks.TELEPORT_ANCHOR.get())
                .pattern("ctc")
                .pattern("gag")
                .pattern("cdc")
                .define('c', ModItems.ADVANCED_CIRCUIT.get())
                .define('t', ModItems.BASIC_TRANSPORTER.get())
                .define('g', ModItems.GLASS_FIBRE_CABLE.get())
                .define('d', ModItemTags.FORGE_GEMS_DIAMOND)
                .define('a', ModItems.ADVANCED_MACHINE_CASING.get())
                .group(MODID + "/misc")
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("basic_transporter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_TRANSPORTER.get()))
                .unlockedBy("glass_fibre_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GLASS_FIBRE_CABLE.get()))
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_MACHINE_CASING.get()))
                .save(consumer, saveResource("teleport_anchor"));
    }
}