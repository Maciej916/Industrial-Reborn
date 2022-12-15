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
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ItemsToolProvider extends RecipeProvider {

    public ItemsToolProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/items/tool/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.WRENCH.get())
                .pattern("C C")
                .pattern("CCC")
                .pattern(" C ")
                .define('C', ModItemTags.FORGE_INGOTS_COPPER)
                .group(MODID + "/items/tool")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer, saveResource("wrench"));

        ShapedRecipeBuilder.shaped(ModItems.TREETAP.get())
                .pattern(" p ")
                .pattern("ppp")
                .pattern("p  ")
                .define('p', ItemTags.create(new ResourceLocation("planks")))
                .group(MODID + "/items/tool")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS))
                .save(consumer, saveResource("treetap"));

        ShapedRecipeBuilder.shaped(ModItems.HAMMER.get())
                .pattern(" ii")
                .pattern("SSi")
                .pattern(" ii")
                .define('i', ModItemTags.FORGE_INGOTS_IRON)
                .define('S', ModItemTags.FORGE_RODS_WOODEN)
                .group(MODID + "/items/tool")
                .unlockedBy("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, saveResource("hammer"));

        ShapedRecipeBuilder.shaped(ModItems.CUTTER.get())
                .pattern("p p")
                .pattern(" p ")
                .pattern("i i")
                .define('i', ModItemTags.FORGE_RODS_WOODEN)
                .define('p', ModItemTags.FORGE_PLATES_IRON)
                .group(MODID + "/items/tool")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .save(consumer, saveResource("cutter"));

        ShapedRecipeBuilder.shaped(ModItems.BRONZE_AXE.get())
                .pattern("bb ")
                .pattern("bs ")
                .pattern(" s ")
                .define('b', ModItemTags.FORGE_INGOTS_BRONZE)
                .define('s', ModItemTags.FORGE_RODS_WOODEN)
                .group(MODID + "/items/bronze")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_axe"));

        ShapedRecipeBuilder.shaped(ModItems.BRONZE_PICKAXE.get())
                .pattern("bbb")
                .pattern(" s ")
                .pattern(" s ")
                .define('b', ModItemTags.FORGE_INGOTS_BRONZE)
                .define('s', ModItemTags.FORGE_RODS_WOODEN)
                .group(MODID + "/items/bronze")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_pickaxe"));

        ShapedRecipeBuilder.shaped(ModItems.BRONZE_SHOVEL.get())
                .pattern(" b ")
                .pattern(" s ")
                .pattern(" s ")
                .define('b', ModItemTags.FORGE_INGOTS_BRONZE)
                .define('s', ModItemTags.FORGE_RODS_WOODEN)
                .group(MODID + "/items/bronze")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_shovel"));

        ShapedRecipeBuilder.shaped(ModItems.BRONZE_HOE.get())
                .pattern("bb ")
                .pattern(" s ")
                .pattern(" s ")
                .define('b', ModItemTags.FORGE_INGOTS_BRONZE)
                .define('s', ModItemTags.FORGE_RODS_WOODEN)
                .group(MODID + "/items/bronze")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_hoe"));

        ShapedRecipeBuilder.shaped(ModItems.BRONZE_SWORD.get())
                .pattern("b")
                .pattern("b")
                .pattern("s")
                .define('b', ModItemTags.FORGE_INGOTS_BRONZE)
                .define('s', ModItemTags.FORGE_RODS_WOODEN)
                .group(MODID + "/items/bronze")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_sword"));

        ShapedRecipeBuilder.shaped(ModItems.IE_METER.get())
                .pattern(" g ")
                .pattern("cec")
                .pattern("c c")
                .define('g', Items.GLOWSTONE_DUST)
                .define('c', ModBlocks.COPPER_CABLE_INSULATED.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "/items/tool")
                .unlockedBy("glowstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .save(consumer, saveResource("ie_meter"));

        ShapedRecipeBuilder.shaped(ModItems.TOOL_BOX.get())
                .pattern("bcb")
                .pattern("bbb")
                .pattern("   ")
                .define('c', Items.CHEST)
                .define('b', ModItemTags.FORGE_PLATES_BRONZE)
                .group(MODID + "/items/tool")
                .unlockedBy("chest", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHEST))
                .unlockedBy("bronze_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_PLATE.get()))
                .save(consumer, saveResource("tool_box"));

        ShapedRecipeBuilder.shaped(ModItems.GEIGER_COUNTER.get())
                .pattern("bos")
                .pattern("crc")
                .pattern("crc")
                .define('r', Items.REDSTONE)
                .define('s', ModItemTags.FORGE_PLATES_STEEL)
                .define('o', ModItemTags.FORGE_PLATES_COPPER)
                .define('b', ModItems.BIOPLASTIC.get())
                .define('c', ModItems.CARBON_PLATE.get())
                .group(MODID + "/items/tool")
                .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE))
                .unlockedBy("steel_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_PLATE.get()))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .unlockedBy("bioplastic", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIOPLASTIC.get()))
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .save(consumer, saveResource("geiger_counter"));


    }
}