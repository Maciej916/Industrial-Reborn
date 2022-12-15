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

public class ItemsBasicProvider extends RecipeProvider {

    public ItemsBasicProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/items/basic/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        
        ShapedRecipeBuilder.shaped(Items.PAPER, 6)
                .pattern("SSS")
                .define('S', ModItems.SAWDUST.get())
                .group(MODID + "/items/crafting")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SAWDUST.get()))
                .save(consumer, saveResource("sawdust_paper"));
        
        ShapedRecipeBuilder.shaped(ModItems.CARBON_FIBERS.get())
                .pattern("cc ")
                .pattern("cc ")
                .define('c', ModItemTags.FORGE_DUSTS_COAL)
                .group(MODID + "/items/crafting")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST.get()))
                .save(consumer, saveResource("carbon_fibers"));

        ShapedRecipeBuilder.shaped(ModItems.COMBINED_CARBON_FIBERS.get())
                .pattern("cc ")
                .define('c', ModItems.CARBON_FIBERS.get())
                .group(MODID + "/items/crafting")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_FIBERS.get()))
                .save(consumer, saveResource("combined_carbon_fibers"));

        ShapedRecipeBuilder.shaped(ModItems.ENERGIUM_DUST.get(), 9)
                .pattern("rdr")
                .pattern("drd")
                .pattern("rdr")
                .define('r', Items.REDSTONE)
                .define('d', ModItemTags.FORGE_DUSTS_DIAMOND)
                .group(MODID + "/items/crafting")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_DUST.get()))
                .save(consumer, saveResource("energium_dust"));

        ShapedRecipeBuilder.shaped(ModItems.IRIDIUM_PLATE.get(), 1)
                .pattern("iai")
                .pattern("ada")
                .pattern("iai")
                .define('d', ModItemTags.FORGE_GEMS_DIAMOND)
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('i', ModItems.IRIDIUM.get())
                .group(MODID + "/items/crafting")
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
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('c', ModBlocks.COPPER_CABLE.get())
                .group(MODID + "/items/crafting")
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
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('c', ModBlocks.COPPER_CABLE.get())
                .group(MODID + "/items/crafting")
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
                .define('i', ModItemTags.FORGE_RODS_IRON)
                .define('c', ModBlocks.COPPER_CABLE.get())
                .group(MODID + "/items/crafting")
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD.get()))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE.get()))
                .save(consumer, saveResource("coil"));

        ShapedRecipeBuilder.shaped(ModItems.ELECTRIC_MOTOR.get(), 1)
                .pattern(" t ")
                .pattern("cic")
                .pattern(" t ")
                .define('i', ModItemTags.FORGE_RODS_IRON)
                .define('c', ModItems.COIL.get())
                .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .group(MODID + "/items/crafting")
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD.get()))
                .unlockedBy("coil", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COIL.get()))
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, saveResource("electric_motor"));

        ShapedRecipeBuilder.shaped(ModItems.SCRAP_BOX.get())
                .pattern("sss")
                .pattern("sss")
                .pattern("sss")
                .define('s', ModItems.SCRAP.get())
                .group(MODID + "/items/crafting")
                .unlockedBy("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get()))
                .save(consumer, saveResource("scrap_box"));

        ShapedRecipeBuilder.shaped(ModItems.FOAM_POWDER.get(), 1)
                .pattern("dsd")
                .pattern("dcd")
                .pattern("dsd")
                .define('d', ModItems.STONE_DUST.get())
                .define('s', Items.SAND)
                .define('c', Items.CLAY_BALL)
                .group(MODID + "/items/foam")
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
                .group(MODID + "/items/foam")
                .unlockedBy("deepslate_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_DUST.get()))
                .unlockedBy("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .unlockedBy("clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY))
                .save(consumer, saveResource("reinforced_foam_powder"));
        
        ShapedRecipeBuilder.shaped(ModItems.HEAT_CONDUCTOR.get(), 1)
                .pattern("rcr")
                .pattern("rcr")
                .pattern("rcr")
                .define('r', ModItems.RUBBER.get())
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .group(MODID + "/items/crafting")
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .save(consumer, saveResource("heat_conductor"));

        ShapedRecipeBuilder.shaped(Items.MUD, 1)
                .pattern("mm ")
                .pattern("mm ")
                .define('m', ModItems.MUD_PILE.get())
                .group(MODID + "/items/crafting")
                .unlockedBy("mud_pile", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MUD_PILE.get()))
                .save(consumer, saveResource("mud"));

        ShapedRecipeBuilder.shaped(ModItems.RADIATION_SHIELDING.get(), 2)
                .pattern("bbb")
                .pattern("lrl")
                .pattern("ccc")
                .define('b', ModItems.BIOPLASTIC.get())
                .define('r', ModItems.REACTOR_PLATING.get())
                .define('c', ModItems.CARBON_PLATE.get())
                .define('l', ModItemTags.FORGE_PLATES_LEAD)
                .group(MODID + "/items/crafting")
                .unlockedBy("bioplastic", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIOPLASTIC.get()))
                .unlockedBy("reactor_plating", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REACTOR_PLATING.get()))
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("lead_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_PLATE.get()))
                .save(consumer, saveResource("radiation_shielding"));

    }
}