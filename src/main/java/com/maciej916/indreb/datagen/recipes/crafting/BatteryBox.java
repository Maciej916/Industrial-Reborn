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

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class BatteryBox extends RecipeProvider {

    public BatteryBox(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "battery_box/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        ShapedRecipeBuilder.shaped(ModBlocks.BATTERY_BOX)
                .pattern("pCp")
                .pattern("BBB")
                .pattern("ppp")
                .define('p', ItemTags.bind("minecraft:planks"))
                .define('C', ModBlocks.COPPER_CABLE_INSULATED)
                .define('B', ModItems.BATTERY)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY))
                .save(consumer, saveResource("wooden_battery_box"));


        ShapedRecipeBuilder.shaped(ModBlocks.CESU)
                .pattern("pcp")
                .pattern("bbb")
                .pattern("ppp")
                .define('p', ItemTags.bind("forge:plates/bronze"))
                .define('c', ModBlocks.COPPER_CABLE)
                .define('b', ModItems.ADVANCED_BATTERY)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_BATTERY))
                .save(consumer, saveResource("cesu"));


        ShapedRecipeBuilder.shaped(ModBlocks.MFE)
                .pattern("geg")
                .pattern("ece")
                .pattern("geg")
                .define('g', ModBlocks.GOLD_CABLE_INSULATED)
                .define('e', ModItems.ENERGY_CRYSTAL)
                .define('c', ModBlocks.BASIC_MACHINE_CASING)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .save(consumer, saveResource("mfe"));


        ShapedRecipeBuilder.shaped(ModBlocks.MFSU)
                .pattern("lal")
                .pattern("lml")
                .pattern("lcl")
                .define('l', ModItems.LAPOTRON_CRYSTAL)
                .define('a', ModItems.ADVANCED_CIRCUIT)
                .define('m', ModBlocks.MFE)
                .define('c', ModBlocks.ADVANCED_MACHINE_CASING)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LAPOTRON_CRYSTAL))
                .save(consumer, saveResource("mfsu"));




    }

}