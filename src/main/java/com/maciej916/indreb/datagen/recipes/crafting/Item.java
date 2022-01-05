package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Item extends RecipeProvider {

    public Item(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        ShapedRecipeBuilder.shaped(ModItems.ELECTRONIC_CIRCUIT)
                .pattern("ccc")
                .pattern("rir")
                .pattern("ccc")
                .define('c', ModBlocks.COPPER_CABLE_INSULATED)
                .define('i', Items.IRON_INGOT)
                .define('r', Items.REDSTONE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .save(consumer, saveResource("electronic_circuit"));


        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_CIRCUIT)
                .pattern("rgr")
                .pattern("lel")
                .pattern("rgr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('l', Items.LAPIS_LAZULI)
                .define('g', Items.GLOWSTONE_DUST)
                .define('r', Items.REDSTONE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .save(consumer, saveResource("advanced_circuit"));


        ShapedRecipeBuilder.shaped(ModItems.FLUID_CELL, 4)
                .pattern(" t ")
                .pattern("tgt")
                .pattern(" t ")
                .define('t', ItemTags.bind("forge:plates/tin"))
                .define('g', Items.GLASS_PANE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT))
                .save(consumer, saveResource("fluid_cell"));


        ShapedRecipeBuilder.shaped(Items.PAPER, 6)
                .pattern("SSS")
                .define('S', ModItems.SAWDUST)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SAWDUST))
                .save(consumer, saveResource("sawdust_paper"));


        ShapedRecipeBuilder.shaped(ModItems.IRON_ROD, 4)
                .pattern("i  ")
                .pattern("i  ")
                .define('i', Items.IRON_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, saveResource("iron_rod"));


        ShapedRecipeBuilder.shaped(ModItems.CARBON_FIBERS)
                .pattern("cc ")
                .pattern("cc ")
                .define('c', ModItems.COAL_DUST)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST))
                .save(consumer, saveResource("carbon_fibers"));


        ShapedRecipeBuilder.shaped(ModItems.COMBINED_CARBON_FIBERS)
                .pattern("cc ")
                .define('c', ModItems.CARBON_FIBERS)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_FIBERS))
                .save(consumer, saveResource("combined_carbon_fibers"));


        ShapedRecipeBuilder.shaped(ModItems.ENERGIUM_DUST, 9)
                .pattern("rdr")
                .pattern("drd")
                .pattern("rdr")
                .define('r', Items.REDSTONE)
                .define('d', ModItems.DIAMOND_DUST)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_DUST))
                .save(consumer, saveResource("energium_dust"));

        ShapedRecipeBuilder.shaped(ModItems.IRIDIUM_PLATE, 1)
                .pattern("iai")
                .pattern("ada")
                .pattern("iai")
                .define('d', ItemTags.bind("forge:gems/diamond"))
                .define('a', ModItems.ADVANCED_ALLOY)
                .define('i', ModItems.IRIDIUM)
                .group(MODID)
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY))
                .unlockedBy("iridium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM))
                .save(consumer, saveResource("iridium_plate"));

        ShapedRecipeBuilder.shaped(ModItems.SMALL_POWER_UNIT, 1)
                .pattern(" ci")
                .pattern("rem")
                .pattern(" ci")
                .define('r', ModItems.BATTERY)
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('m', ModItems.ELECTRIC_MOTOR)
                .define('i', ModItems.IRON_PLATE)
                .define('c', ModBlocks.COPPER_CABLE)
                .group(MODID)
                .unlockedBy("battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE))
                .save(consumer, saveResource("small_power_unit"));

        ShapedRecipeBuilder.shaped(ModItems.POWER_UNIT, 1)
                .pattern("rci")
                .pattern("rem")
                .pattern("rci")
                .define('r', ModItems.BATTERY)
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('m', ModItems.ELECTRIC_MOTOR)
                .define('i', ModItems.IRON_PLATE)
                .define('c', ModBlocks.COPPER_CABLE)
                .group(MODID)
                .unlockedBy("battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE))
                .save(consumer, saveResource("power_unit"));

        ShapedRecipeBuilder.shaped(ModItems.COIL, 1)
                .pattern("ccc")
                .pattern("cic")
                .pattern("ccc")
                .define('i', Ingredient.of(ItemTags.bind("forge:ingots/iron")))
                .define('c', ModBlocks.COPPER_CABLE)
                .group(MODID)
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE))
                .save(consumer, saveResource("coil"));

        ShapedRecipeBuilder.shaped(ModItems.ELECTRIC_MOTOR, 1)
                .pattern(" t ")
                .pattern("cic")
                .pattern(" t ")
                .define('i', Ingredient.of(ItemTags.bind("forge:ingots/iron")))
                .define('c', ModItems.COIL)
                .define('t', ItemTags.bind("forge:plates/tin"))
                .group(MODID)
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .unlockedBy("coil", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COIL))
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .save(consumer, saveResource("electric_motor"));

        ShapedRecipeBuilder.shaped(ModItems.SCRAP_BOX)
                .pattern("sss")
                .pattern("sss")
                .pattern("sss")
                .define('s', ModItems.SCRAP)
                .group(MODID)
                .unlockedBy("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP))
                .save(consumer, saveResource("scrap_box"));




        ShapedRecipeBuilder.shaped(ModItems.FOAM_POWDER, 1)
                .pattern("dsd")
                .pattern("dcd")
                .pattern("dsd")
                .define('d', ModItems.STONE_DUST)
                .define('s', Items.SAND)
                .define('c', Items.CLAY_BALL)
                .group(MODID)
                .unlockedBy("stone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STONE_DUST))
                .unlockedBy("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .unlockedBy("clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY))
                .save(consumer, saveResource("foam_powder"));

        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_FOAM_POWDER, 1)
                .pattern("dsd")
                .pattern("dcd")
                .pattern("dsd")
                .define('d', ModItems.DEEPSLATE_DUST)
                .define('s', Items.SAND)
                .define('c', Items.CLAY_BALL)
                .group(MODID)
                .unlockedBy("deepslate_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_DUST))
                .unlockedBy("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .unlockedBy("clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY))
                .save(consumer, saveResource("reinforced_foam_powder"));

        ShapedRecipeBuilder.shaped(ModItems.TIN_CAN, 4)
                .pattern("t t")
                .pattern("ttt")
                .define('t', ItemTags.bind("forge:plates/tin"))
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .save(consumer, saveResource("tin_can"));


        ShapedRecipeBuilder.shaped(ModItems.HEAT_CONDUCTOR, 1)
                .pattern("rcr")
                .pattern("rcr")
                .pattern("rcr")
                .define('r', ModItems.RUBBER)
                .define('c', ModItems.COPPER_PLATE)
                .group(MODID)
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE))
                .save(consumer, saveResource("heat_conductor"));

        ShapelessRecipeBuilder.shapeless(ModItems.FERTILIZER, 1)
                .requires(ModItems.SCRAP)
                .requires(Items.BONE_MEAL)
                .group(MODID)
                .unlockedBy("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP))
                .unlockedBy("bone_meal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE_MEAL))
                .save(consumer, saveResource("fertilizer"));








    }

}