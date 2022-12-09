package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class CableProvider extends RecipeProvider {

    public CableProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/cable/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(ModBlocks.COPPER_CABLE.get(), 2)
                .requires(ModItems.CUTTER.get())
                .requires(ModItemTags.FORGE_PLATES_COPPER)
                .group(MODID + "/cable")
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .save(consumer, saveResource("copper_cable"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.TIN_CABLE.get(), 2)
                .requires(ModItems.CUTTER.get())
                .requires(ModItemTags.FORGE_PLATES_TIN)
                .group(MODID + "/cable")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, saveResource("tin_cable"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.GOLD_CABLE.get(), 2)
                .requires(ModItems.CUTTER.get())
                .requires(ModItemTags.FORGE_PLATES_GOLD)
                .group(MODID + "/cable")
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE.get()))
                .save(consumer, saveResource("gold_cable"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.HV_CABLE.get(), 2)
                .requires(ModItems.CUTTER.get())
                .requires(ModItemTags.FORGE_PLATES_IRON)
                .group(MODID + "/cable")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .save(consumer, saveResource("hv_cable"));


        ShapelessRecipeBuilder.shapeless(ModBlocks.COPPER_CABLE_INSULATED.get())
                .requires(ModItems.RUBBER.get())
                .requires(ModBlocks.COPPER_CABLE.get())
                .group(MODID + "/cable")
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE.get()))
                .save(consumer, saveResource("copper_cable_insulated"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.TIN_CABLE_INSULATED.get())
                .requires(ModItems.RUBBER.get())
                .requires(ModBlocks.TIN_CABLE.get())
                .group(MODID + "/cable")
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("tin_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE.get()))
                .save(consumer, saveResource("tin_cable_insulated"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.GOLD_CABLE_INSULATED.get())
                .requires(ModItems.RUBBER.get())
                .requires(ModBlocks.GOLD_CABLE.get())
                .group(MODID + "/cable")
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("gold_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CABLE.get()))
                .save(consumer, saveResource("gold_cable_insulated"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.HV_CABLE_INSULATED.get())
                .requires(ModItems.RUBBER.get())
                .requires(ModBlocks.HV_CABLE.get())
                .group(MODID + "/cable")
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("hv_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HV_CABLE.get()))
                .save(consumer, saveResource("hv_cable_insulated"));

        ShapedRecipeBuilder.shaped(ModBlocks.GLASS_FIBRE_CABLE.get())
                .pattern("ggg")
                .pattern("ese")
                .pattern("ggg")
                .define('g', Items.GLASS)
                .define('e', ModItems.ENERGIUM_DUST.get())
                .define('s', ModItemTags.FORGE_DUSTS_SILVER)
                .group(MODID + "/cable")
                .unlockedBy("energium_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGIUM_DUST.get()))
                .unlockedBy("silver_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_DUST.get()))
                .save(consumer, saveResource("glass_fibre_cable"));



    }
}