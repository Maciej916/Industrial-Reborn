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
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ItemElectric extends RecipeProvider {

    public ItemElectric(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/electric/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.BATTERY)
                .pattern(" C ")
                .pattern("TrT")
                .pattern("TrT")
                .define('C', ModBlocks.COPPER_CABLE_INSULATED)
                .define('r', Items.REDSTONE)
                .define('T', ItemTags.bind("forge:plates/tin"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .save(consumer, saveResource("battery"));

        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_BATTERY)
                .pattern("iri")
                .pattern("pbp")
                .pattern("iri")
                .define('i', ModBlocks.COPPER_CABLE_INSULATED)
                .define('r', Items.REDSTONE)
                .define('b', ModItems.BATTERY)
                .define('p', ItemTags.bind("forge:plates/copper"))
                .group(MODID)
                .unlockedBy("battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .save(consumer, saveResource("advanced_battery"));

        ShapedRecipeBuilder.shaped(ModItems.LAPOTRON_CRYSTAL)
                .pattern("lal")
                .pattern("lel")
                .pattern("lal")
                .define('e', ModItems.ENERGY_CRYSTAL)
                .define('l', Items.LAPIS_LAZULI)
                .define('a', ModItems.ADVANCED_CIRCUIT)
                .group(MODID)
                .unlockedBy("energy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .unlockedBy("circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT))
                .save(consumer, saveResource("lapotron_crystal"));

        ShapedRecipeBuilder.shaped(ModItems.NANO_SABER)
                .pattern("ga ")
                .pattern("ga ")
                .pattern("cec")
                .define('c', ModItems.CARBON_PLATE)
                .define('e', ModItems.ENERGY_CRYSTAL)
                .define('g', Items.GLOWSTONE_DUST)
                .define('a', ModItems.ADVANCED_ALLOY)
                .group(MODID)
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .unlockedBy("glowstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY))
                .save(consumer, saveResource("nano_saber"));



    }

}