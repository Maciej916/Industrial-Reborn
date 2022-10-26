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
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Armour extends RecipeProvider {

    public Armour(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_HELMET.get())
                .pattern("bbb")
                .pattern("b b")
                .define('b', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/bronze"))))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_helmet"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_CHESTPLATE.get())
                .pattern("b b")
                .pattern("bbb")
                .pattern("bbb")
                .define('b', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/bronze"))))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_chestplate"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_LEGGINGS.get())
                .pattern("bbb")
                .pattern("b b")
                .pattern("b b")
                .define('b', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/bronze"))))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_leggings"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_BOOTS.get())
                .pattern("b b")
                .pattern("b b")
                .define('b', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/bronze"))))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_boots"));


        ShapedRecipeBuilder.shaped(ModItems.RUBBER_BOOTS.get())
                .pattern("r r")
                .pattern("r r")
                .pattern("rwr")
                .define('r', ModItems.RUBBER.get())
                .define('w', Items.WHITE_WOOL)
                .group(MODID)
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("white_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_WOOL))
                .save(consumer, saveResource("rubber_boots"));


        ShapedRecipeBuilder.shaped(ModItems.NIGHTVISION_GOGGLES.get())
                .pattern(" b ")
                .pattern("lgl")
                .pattern("rar")
                .define('b', ModItems.ADVANCED_BATTERY.get())
                .define('l', ModBlocks.LUMINATOR.get())
                .define('g', ModBlocks.REINFORCED_GLASS.get())
                .define('r', ModItems.RUBBER.get())
                .define('a', ModItems.ADVANCED_CIRCUIT.get())
                .group(MODID)
                .unlockedBy("advanced_battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_BATTERY.get()))
                .unlockedBy("luminator", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LUMINATOR.get()))
                .unlockedBy("reinforced_glass", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_GLASS.get()))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .save(consumer, saveResource("nightvision_goggles"));

        ShapedRecipeBuilder.shaped(ModItems.NANO_HELMET.get())
                .pattern("cec")
                .pattern("cnc")
                .define('c', ModItems.CARBON_PLATE.get())
                .define('e', ModItems.ENERGY_CRYSTAL.get())
                .define('n', ModItems.NIGHTVISION_GOGGLES.get())
                .group(MODID)
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL.get()))
                .unlockedBy("nightvision_goggles", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NIGHTVISION_GOGGLES.get()))
                .save(consumer, saveResource("nano_helmet"));


        ShapedRecipeBuilder.shaped(ModItems.NANO_CHESTPLATE.get())
                .pattern("c c")
                .pattern("cec")
                .pattern("ccc")
                .define('c', ModItems.CARBON_PLATE.get())
                .define('e', ModItems.ENERGY_CRYSTAL.get())
                .group(MODID)
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL.get()))
                .save(consumer, saveResource("nano_chestplate"));


        ShapedRecipeBuilder.shaped(ModItems.NANO_LEGGINGS.get())
                .pattern("cec")
                .pattern("c c")
                .pattern("c c")
                .define('c', ModItems.CARBON_PLATE.get())
                .define('e', ModItems.ENERGY_CRYSTAL.get())
                .group(MODID)
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL.get()))
                .save(consumer, saveResource("nano_leggings"));


        ShapedRecipeBuilder.shaped(ModItems.NANO_BOOTS.get())
                .pattern("c c")
                .pattern("cec")
                .define('c', ModItems.CARBON_PLATE.get())
                .define('e', ModItems.ENERGY_CRYSTAL.get())
                .group(MODID)
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL.get()))
                .save(consumer, saveResource("nano_boots"));


    }

}