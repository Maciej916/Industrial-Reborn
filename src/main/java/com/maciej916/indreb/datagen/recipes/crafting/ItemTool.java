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

public class ItemTool extends RecipeProvider {

    public ItemTool(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/tool/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.WRENCH)
                .pattern("C C")
                .pattern("CCC")
                .pattern(" C ")
                .define('C', ItemTags.bind("forge:ingots/copper"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer, saveResource("wrench"));


        ShapedRecipeBuilder.shaped(ModItems.TREETAP)
                .pattern(" p ")
                .pattern("ppp")
                .pattern("p  ")
                .define('p', ItemTags.bind("minecraft:planks"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS))
                .save(consumer, saveResource("treetap"));


        ShapedRecipeBuilder.shaped(ModItems.HAMMER)
                .pattern(" ii")
                .pattern("SSi")
                .pattern(" ii")
                .define('i', ItemTags.bind("forge:ingots/iron"))
                .define('S', ItemTags.bind("forge:rods/wooden"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, saveResource("hammer"));

        ShapedRecipeBuilder.shaped(ModItems.CUTTER)
                .pattern("p p")
                .pattern(" p ")
                .pattern("i i")
                .define('i', ModItems.IRON_ROD)
                .define('p', ItemTags.bind("forge:plates/iron"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD))
                .save(consumer, saveResource("cutter"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_AXE)
                .pattern("bb ")
                .pattern("bs ")
                .pattern(" s ")
                .define('b', ModItems.BRONZE_INGOT)
                .define('s', ItemTags.bind("forge:rods/wooden"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_axe"));

        ShapedRecipeBuilder.shaped(ModItems.BRONZE_PICKAXE)
                .pattern("bbb")
                .pattern(" s ")
                .pattern(" s ")
                .define('b', ModItems.BRONZE_INGOT)
                .define('s', ItemTags.bind("forge:rods/wooden"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_pickaxe"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_SHOVEL)
                .pattern(" b ")
                .pattern(" s ")
                .pattern(" s ")
                .define('b', ModItems.BRONZE_INGOT)
                .define('s', ItemTags.bind("forge:rods/wooden"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_shovel"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_HOE)
                .pattern("bb ")
                .pattern(" s ")
                .pattern(" s ")
                .define('b', ModItems.BRONZE_INGOT)
                .define('s', ItemTags.bind("forge:rods/wooden"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_hoe"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_SWORD)
                .pattern("b")
                .pattern("b")
                .pattern("s")
                .define('b', ModItems.BRONZE_INGOT)
                .define('s', ItemTags.bind("forge:rods/wooden"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_sword"));


        ShapedRecipeBuilder.shaped(ModItems.IE_METER)
                .pattern(" g ")
                .pattern("cec")
                .pattern("c c")
                .define('g', Items.GLOWSTONE_DUST)
                .define('c', ModBlocks.COPPER_CABLE_INSULATED)
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .group(MODID)
                .unlockedBy("glowstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .save(consumer, saveResource("ie_meter"));


    }

}