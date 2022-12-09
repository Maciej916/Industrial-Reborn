package com.maciej916.indreb.datagen.recipe.provider.crafting;

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

public class ItemsStorageProvider extends RecipeProvider {

    public ItemsStorageProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/items/storage/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.FLUID_CELL.get(), 4)
                .pattern(" t ")
                .pattern("tgt")
                .pattern(" t ")
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .define('g', Items.GLASS_PANE)
                .group(MODID + "/items/storage")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .save(consumer, saveResource("fluid_cell"));
        
        ShapedRecipeBuilder.shaped(ModItems.MEMORY_CARD.get(), 1)
                .pattern("aia")
                .pattern("ici")
                .pattern("pip")
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('a', Items.AMETHYST_SHARD)
                .define('p', ModItems.CARBON_PLATE.get())
                .group(MODID + "/items/storage")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("amethyst_shard", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AMETHYST_SHARD))
                .save(consumer, saveResource("memory_card"));

        ShapedRecipeBuilder.shaped(ModItems.FOAM_SPRAYER.get(), 1)
                .pattern("i  ")
                .pattern(" i ")
                .pattern(" ci")
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('c', ModItems.FLUID_CELL.get())
                .group(MODID + "/items/storage")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .save(consumer, saveResource("foam_sprayer"));

        ShapedRecipeBuilder.shaped(ModItems.EMPTY_CAN.get(), 4)
                .pattern("t t")
                .pattern("ttt")
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .group(MODID + "/items/storage")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, saveResource("tin_can"));

    }
}