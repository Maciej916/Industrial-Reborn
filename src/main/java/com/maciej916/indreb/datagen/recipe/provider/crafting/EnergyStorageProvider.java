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

public class EnergyStorageProvider extends RecipeProvider {

    public EnergyStorageProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/energy_storage/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.BATTERY_BOX.get())
                .pattern("pCp")
                .pattern("BBB")
                .pattern("ppp")
                .define('p', ItemTags.PLANKS)
                .define('C', ModBlocks.COPPER_CABLE_INSULATED.get())
                .define('B', ModItems.BATTERY.get())
                .group(MODID + "/energy_storage")
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY.get()))
                .save(consumer, saveResource("battery_box"));

        ShapedRecipeBuilder.shaped(ModBlocks.CESU.get())
                .pattern("pcp")
                .pattern("bbb")
                .pattern("ppp")
                .define('p', ModItemTags.FORGE_PLATES_BRONZE)
                .define('c', ModBlocks.COPPER_CABLE.get())
                .define('b', ModItems.ADVANCED_BATTERY.get())
                .group(MODID + "/energy_storage")
                .unlockedBy("advanced_battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_BATTERY.get()))
                .save(consumer, saveResource("cesu"));

        ShapedRecipeBuilder.shaped(ModBlocks.MFE.get())
                .pattern("geg")
                .pattern("ece")
                .pattern("geg")
                .define('g', ModBlocks.GOLD_CABLE_INSULATED.get())
                .define('e', ModItems.ENERGY_CRYSTAL.get())
                .define('c', ModBlocks.BASIC_MACHINE_CASING.get())
                .group(MODID + "/energy_storage")
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
                .group(MODID + "/energy_storage")
                .unlockedBy("lapotron_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LAPOTRON_CRYSTAL.get()))
                .save(consumer, saveResource("mfsu"));


        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_BATTERY_BOX.get())
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER.get())
                .define('b', ModBlocks.BATTERY_BOX.get())
                .group(MODID + "/charge_pad")
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("battery_box", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BATTERY_BOX.get()))
                .save(consumer, saveResource("charge_pad_battery_box"));

        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_CESU.get())
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER.get())
                .define('b', ModBlocks.CESU.get())
                .group(MODID + "/charge_pad")
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("cesu", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.CESU.get()))
                .save(consumer, saveResource("charge_pad_cesu"));

        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_MFE.get())
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER.get())
                .define('b', ModBlocks.MFE.get())
                .group(MODID + "/charge_pad")
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("mfe", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MFE.get()))
                .save(consumer, saveResource("charge_pad_mfe"));

        ShapedRecipeBuilder.shaped(ModBlocks.CHARGE_PAD_MFSU.get())
                .pattern("epe")
                .pattern("rbr")
                .define('e', ModItems.ADVANCED_CIRCUIT.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', ModItems.RUBBER.get())
                .define('b', ModBlocks.MFSU.get())
                .group(MODID + "/charge_pad")
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("stone_pressure_plate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PRESSURE_PLATE))
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("mfsu", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MFSU.get()))
                .save(consumer, saveResource("charge_pad_mfsu"));


        ShapedRecipeBuilder.shaped(ModBlocks.LV_TRANSFORMER.get())
                .pattern("pcp")
                .pattern("bob")
                .pattern("pcp")
                .define('p', ItemTags.PLANKS)
                .define('c', ModBlocks.COPPER_CABLE_INSULATED.get())
                .define('o', ModItems.COIL.get())
                .define('b', ModItemTags.FORGE_PLATES_BRONZE)
                .group(MODID + "/transformer")
                .unlockedBy("oak_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS))
                .unlockedBy("copper_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED.get()))
                .unlockedBy("coil", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COIL.get()))
                .unlockedBy("bronze_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_PLATE.get()))
                .save(consumer, saveResource("lv_transformer"));

        ShapedRecipeBuilder.shaped(ModBlocks.MV_TRANSFORMER.get())
                .pattern(" g ")
                .pattern("eba")
                .pattern(" g ")
                .define('g', ModBlocks.GOLD_CABLE_INSULATED.get())
                .define('b', ModBlocks.LV_TRANSFORMER.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('a', ModItems.ADVANCED_BATTERY.get())
                .group(MODID + "/transformer")
                .unlockedBy("gold_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CABLE_INSULATED.get()))
                .unlockedBy("lv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LV_TRANSFORMER.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("advanced_battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_BATTERY.get()))
                .save(consumer, saveResource("mv_transformer"));

        ShapedRecipeBuilder.shaped(ModBlocks.HV_TRANSFORMER.get())
                .pattern(" g ")
                .pattern("ebn")
                .pattern(" g ")
                .define('g', ModBlocks.HV_CABLE_INSULATED.get())
                .define('b', ModBlocks.MV_TRANSFORMER.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('n', ModItems.ENERGY_CRYSTAL.get())
                .group(MODID + "/transformer")
                .unlockedBy("hv_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HV_CABLE_INSULATED.get()))
                .unlockedBy("mv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MV_TRANSFORMER.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL.get()))
                .save(consumer, saveResource("hv_transformer"));

        ShapedRecipeBuilder.shaped(ModBlocks.EV_TRANSFORMER.get())
                .pattern(" g ")
                .pattern("ebn")
                .pattern(" g ")
                .define('g', ModBlocks.GLASS_FIBRE_CABLE.get())
                .define('b', ModBlocks.HV_TRANSFORMER.get())
                .define('e', ModItems.ADVANCED_CIRCUIT.get())
                .define('n', ModItems.LAPOTRON_CRYSTAL.get())
                .group(MODID + "/transformer")
                .unlockedBy("glass_fibre_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GLASS_FIBRE_CABLE.get()))
                .unlockedBy("hv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HV_TRANSFORMER.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("lapotron_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LAPOTRON_CRYSTAL.get()))
                .save(consumer, saveResource("ev_transformer"));


    }
}