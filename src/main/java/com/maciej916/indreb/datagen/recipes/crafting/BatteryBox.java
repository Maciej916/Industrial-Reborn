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


        ShapedRecipeBuilder.shaped(ModBlocks.BATTERY_BOX.get())
                .pattern("pCp")
                .pattern("BBB")
                .pattern("ppp")
                .define('p', ItemTags.create(new ResourceLocation("planks")))
                .define('C', ModBlocks.COPPER_CABLE_INSULATED.get())
                .define('B', ModItems.BATTERY.get())
                .group(MODID)
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY.get()))
                .save(consumer, saveResource("wooden_battery_box"));


        ShapedRecipeBuilder.shaped(ModBlocks.CESU.get())
                .pattern("pcp")
                .pattern("bbb")
                .pattern("ppp")
                .define('p', ItemTags.create(new ResourceLocation("forge", "plates/bronze")))
                .define('c', ModBlocks.COPPER_CABLE.get())
                .define('b', ModItems.ADVANCED_BATTERY.get())
                .group(MODID)
                .unlockedBy("advanced_battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_BATTERY.get()))
                .save(consumer, saveResource("cesu"));


        ShapedRecipeBuilder.shaped(ModBlocks.MFE.get())
                .pattern("geg")
                .pattern("ece")
                .pattern("geg")
                .define('g', ModBlocks.GOLD_CABLE_INSULATED.get())
                .define('e', ModItems.ENERGY_CRYSTAL.get())
                .define('c', ModBlocks.BASIC_MACHINE_CASING.get())
                .group(MODID)
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL.get()))
                .save(consumer, saveResource("mfe"));


        ShapedRecipeBuilder.shaped(ModBlocks.MFSU.get())
                .pattern("lal")
                .pattern("lml")
                .pattern("lcl")
                .define('l', ModItems.LAPOTRON_CRYSTAL.get())
                .define('a', ModItems.ADVANCED_CIRCUIT.get())
                .define('m', ModBlocks.MFE.get())
                .define('c', ModBlocks.ADVANCED_MACHINE_CASING.get())
                .group(MODID)
                .unlockedBy("lapotron_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LAPOTRON_CRYSTAL.get()))
                .save(consumer, saveResource("mfsu"));




    }

}