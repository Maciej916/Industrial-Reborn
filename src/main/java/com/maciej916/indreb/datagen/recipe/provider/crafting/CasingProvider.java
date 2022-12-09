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

public class CasingProvider extends RecipeProvider {

    public CasingProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/casing/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.BASIC_MACHINE_CASING.get())
                .pattern("iii")
                .pattern("iri")
                .pattern("iii")
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('r', ModItems.RUBBER.get())
                .group(MODID + "/casing")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .save(consumer, saveResource("basic_machine_casing"));

        ShapedRecipeBuilder.shaped(ModBlocks.ADVANCED_MACHINE_CASING.get())
                .pattern("scs")
                .pattern("aba")
                .pattern("scs")
                .define('s', ModItemTags.FORGE_PLATES_STEEL)
                .define('c', ModItems.CARBON_PLATE.get())
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .group(MODID + "/casing")
                .unlockedBy("steel_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_PLATE.get()))
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("basic_machine", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("advanced_machine_casing"));

        ShapedRecipeBuilder.shaped(ModBlocks.ADVANCED_MACHINE_CASING.get())
                .pattern("sas")
                .pattern("cbc")
                .pattern("sas")
                .define('s', ModItemTags.FORGE_PLATES_STEEL)
                .define('c', ModItems.CARBON_PLATE.get())
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .group(MODID + "/casing")
                .unlockedBy("steel_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_PLATE.get()))
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("basic_machine", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("advanced_machine_casing_2"));





    }
}