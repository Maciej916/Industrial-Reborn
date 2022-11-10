package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModTags;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderAdvancedShaped;
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


        ShapedRecipeBuilder.shaped(ModItems.ELECTRONIC_CIRCUIT.get())
                .pattern("ccc")
                .pattern("rir")
                .pattern("ccc")
                .define('c', ModBlocks.COPPER_CABLE_INSULATED.get())
                .define('i', ItemTags.create(new ResourceLocation("forge", "ingots/iron")))
                .define('r', Items.REDSTONE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
                .save(consumer, saveResource("electronic_circuit"));


        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_CIRCUIT.get())
                .pattern("rgr")
                .pattern("lel")
                .pattern("rgr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('l', ModTags.FORGE_PLATES_LAPIS)
                .define('g', Items.GLOWSTONE_DUST)
                .define('r', Items.REDSTONE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
                .save(consumer, saveResource("advanced_circuit"));


        ShapedRecipeBuilder.shaped(ModItems.FLUID_CELL.get(), 4)
                .pattern(" t ")
                .pattern("tgt")
                .pattern(" t ")
                .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .define('g', Items.GLASS_PANE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .save(consumer, saveResource("fluid_cell"));


        ShapedRecipeBuilder.shaped(Items.PAPER, 6)
                .pattern("SSS")
                .define('S', ModItems.SAWDUST.get())
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SAWDUST.get()))
                .save(consumer, saveResource("sawdust_paper"));


        ShapedRecipeBuilder.shaped(ModItems.IRON_ROD.get(), 4)
                .pattern("i  ")
                .pattern("i  ")
                .define('i', ItemTags.create(new ResourceLocation("forge", "ingots/iron")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, saveResource("iron_rod"));


        ShapedRecipeBuilder.shaped(ModItems.CARBON_FIBERS.get())
                .pattern("cc ")
                .pattern("cc ")
                .define('c', ItemTags.create(new ResourceLocation("forge", "dusts/coal")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST.get()))
                .save(consumer, saveResource("carbon_fibers"));


        ShapedRecipeBuilder.shaped(ModItems.COMBINED_CARBON_FIBERS.get())
                .pattern("cc ")
                .define('c', ModItems.CARBON_FIBERS.get())
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_FIBERS.get()))
                .save(consumer, saveResource("combined_carbon_fibers"));


        ShapedRecipeBuilder.shaped(ModItems.ENERGIUM_DUST.get(), 9)
                .pattern("rdr")
                .pattern("drd")
                .pattern("rdr")
                .define('r', Items.REDSTONE)
                .define('d', ItemTags.create(new ResourceLocation("forge", "dusts/diamond")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_DUST.get()))
                .save(consumer, saveResource("energium_dust"));

        ShapedRecipeBuilder.shaped(ModItems.IRIDIUM_PLATE.get(), 1)
                .pattern("iai")
                .pattern("ada")
                .pattern("iai")
                .define('d', ItemTags.create(new ResourceLocation("forge", "gems/diamond")))
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('i', ModItems.IRIDIUM.get())
                .group(MODID)
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("iridium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM.get()))
                .save(consumer, saveResource("iridium_plate"));

        ShapedRecipeBuilder.shaped(ModItems.SMALL_POWER_UNIT.get(), 1)
                .pattern(" ci")
                .pattern("rem")
                .pattern(" ci")
                .define('r', ModItems.BATTERY.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('m', ModItems.ELECTRIC_MOTOR.get())
                .define('i', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .define('c', ModBlocks.COPPER_CABLE.get())
                .group(MODID)
                .unlockedBy("battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE.get()))
                .save(consumer, saveResource("small_power_unit"));

        ShapedRecipeBuilder.shaped(ModItems.POWER_UNIT.get(), 1)
                .pattern("rci")
                .pattern("rem")
                .pattern("rci")
                .define('r', ModItems.BATTERY.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('m', ModItems.ELECTRIC_MOTOR.get())
                .define('i', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .define('c', ModBlocks.COPPER_CABLE.get())
                .group(MODID)
                .unlockedBy("battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE.get()))
                .save(consumer, saveResource("power_unit"));

        ShapedRecipeBuilder.shaped(ModItems.COIL.get(), 1)
                .pattern("ccc")
                .pattern("cic")
                .pattern("ccc")
                .define('i', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/iron"))))
                .define('c', ModBlocks.COPPER_CABLE.get())
                .group(MODID)
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE.get()))
                .save(consumer, saveResource("coil"));

        ShapedRecipeBuilder.shaped(ModItems.ELECTRIC_MOTOR.get(), 1)
                .pattern(" t ")
                .pattern("cic")
                .pattern(" t ")
                .define('i', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/iron"))))
                .define('c', ModItems.COIL.get())
                .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .group(MODID)
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .unlockedBy("coil", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COIL.get()))
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, saveResource("electric_motor"));

        ShapedRecipeBuilder.shaped(ModItems.SCRAP_BOX.get())
                .pattern("sss")
                .pattern("sss")
                .pattern("sss")
                .define('s', ModItems.SCRAP.get())
                .group(MODID)
                .unlockedBy("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get()))
                .save(consumer, saveResource("scrap_box"));


        ShapedRecipeBuilder.shaped(ModItems.FOAM_POWDER.get(), 1)
                .pattern("dsd")
                .pattern("dcd")
                .pattern("dsd")
                .define('d', ModItems.STONE_DUST.get())
                .define('s', Items.SAND)
                .define('c', Items.CLAY_BALL)
                .group(MODID)
                .unlockedBy("stone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STONE_DUST.get()))
                .unlockedBy("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .unlockedBy("clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY))
                .save(consumer, saveResource("foam_powder"));

        ShapedRecipeBuilder.shaped(ModItems.REINFORCED_FOAM_POWDER.get(), 1)
                .pattern("dsd")
                .pattern("dcd")
                .pattern("dsd")
                .define('d', ModItems.DEEPSLATE_DUST.get())
                .define('s', Items.SAND)
                .define('c', Items.CLAY_BALL)
                .group(MODID)
                .unlockedBy("deepslate_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_DUST.get()))
                .unlockedBy("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .unlockedBy("clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY))
                .save(consumer, saveResource("reinforced_foam_powder"));

        ShapedRecipeBuilder.shaped(ModItems.TIN_CAN.get(), 4)
                .pattern("t t")
                .pattern("ttt")
                .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, saveResource("tin_can"));


        ShapedRecipeBuilder.shaped(ModItems.HEAT_CONDUCTOR.get(), 1)
                .pattern("rcr")
                .pattern("rcr")
                .pattern("rcr")
                .define('r', ModItems.RUBBER.get())
                .define('c', ItemTags.create(new ResourceLocation("forge", "plates/copper")))
                .group(MODID)
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .save(consumer, saveResource("heat_conductor"));

//        ShapelessRecipeBuilder.shapeless(ModItems.FERTILIZER.get(), 1)
//                .requires(ModItems.SCRAP.get())
//                .requires(Items.BONE_MEAL)
//                .group(MODID)
//                .unlockedBy("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get()))
//                .unlockedBy("bone_meal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE_MEAL))
//                .save(consumer, saveResource("fertilizer"));

        ShapedRecipeBuilder.shaped(Items.MUD, 1)
                .pattern("mm ")
                .pattern("mm ")
                .define('m', ModItems.MUD_PILE.get())
                .group(MODID)
                .unlockedBy("mud_pile", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MUD_PILE.get()))
                .save(consumer, saveResource("mud"));

        ShapedRecipeBuilder.shaped(ModItems.MEMORY_CARD.get(), 1)
                .pattern("aia")
                .pattern("ici")
                .pattern("pip")
                .define('i', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('a', Items.AMETHYST_SHARD)
                .define('p', ModItems.CARBON_PLATE.get())
                .group(MODID)
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("amethyst_shard", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AMETHYST_SHARD))
                .save(consumer, saveResource("memory_card"));


        ShapedRecipeBuilder.shaped(ModItems.FOAM_SPRAYER.get(), 1)
                .pattern("i  ")
                .pattern(" i ")
                .pattern(" ci")
                .define('i', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .define('c', ModItems.FLUID_CELL.get())
                .group(MODID)
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .save(consumer, saveResource("foam_sprayer"));



    }

}