package com.maciej916.indreb.datagen.tags;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagsItem extends ItemTagsProvider {

    public TagsItem(DataGenerator generator, BlockTagsProvider blockTagProvider, ExistingFileHelper helper) {
        super(generator, blockTagProvider, IndReb.MODID, helper);
    }

    @Override
    protected void addTags() {

        tag(ItemTags.LEAVES)
                .add(ModItems.RUBBER_LEAVES.get())
                .replace(false)
        ;

        /* ORES */

        tag(ModItemTags.FORGE_ORE_TIN)
                .add(ModItems.TIN_ORE.get())
                .add(ModItems.DEEPSLATE_TIN_ORE.get())
        ;

        tag(ModItemTags.FORGE_ORE_LEAD)
                .add(ModItems.LEAD_ORE.get())
                .add(ModItems.DEEPSLATE_LEAD_ORE.get())
        ;

        tag(ModItemTags.FORGE_ORE_URANIUM)
                .add(ModItems.URANIUM_ORE.get())
                .add(ModItems.DEEPSLATE_URANIUM_ORE.get())
        ;

        tag(ModItemTags.FORGE_ORE_SILVER)
                .add(ModItems.SILVER_ORE.get())
                .add(ModItems.DEEPSLATE_SILVER_ORE.get())
        ;

        /* RAW STORAGE BLOCKS */

        tag(ModItemTags.FORGE_STORAGE_BLOCKS_RAW_TIN).add(ModItems.RAW_TIN_BLOCK.get());
        tag(ModItemTags.FORGE_STORAGE_BLOCKS_RAW_LEAD).add(ModItems.RAW_LEAD_BLOCK.get());
        tag(ModItemTags.FORGE_STORAGE_BLOCKS_RAW_URANIUM).add(ModItems.RAW_URANIUM_BLOCK.get());
        tag(ModItemTags.FORGE_STORAGE_BLOCKS_RAW_SILVER).add(ModItems.RAW_SILVER_BLOCK.get());

        /* STORAGE_BLOCKS */

        tag(ModItemTags.FORGE_STORAGE_BLOCKS_TIN).add(ModItems.TIN_BLOCK.get());
        tag(ModItemTags.FORGE_STORAGE_BLOCKS_SILVER).add(ModItems.SILVER_BLOCK.get());
        tag(ModItemTags.FORGE_STORAGE_BLOCKS_URANIUM).add(ModItems.URANIUM_BLOCK.get());
        tag(ModItemTags.FORGE_STORAGE_BLOCKS_LEAD).add(ModItems.LEAD_BLOCK.get());
        tag(ModItemTags.FORGE_STORAGE_BLOCKS_STEEL).add(ModItems.STEEL_BLOCK.get());
        tag(ModItemTags.FORGE_STORAGE_BLOCKS_BRONZE).add(ModItems.BRONZE_BLOCK.get());
        tag(ModItemTags.FORGE_RAW_MATERIALS_TIN).add(ModItems.RAW_TIN.get());
        tag(ModItemTags.FORGE_RAW_MATERIALS_SILVER).add(ModItems.RAW_SILVER.get());
        tag(ModItemTags.FORGE_RAW_MATERIALS_URANIUM).add(ModItems.RAW_URANIUM.get());
        tag(ModItemTags.FORGE_RAW_MATERIALS_LEAD).add(ModItems.RAW_LEAD.get());

        /* INGOTS */

        tag(ModItemTags.FORGE_INGOTS_TIN).add(ModItems.TIN_INGOT.get());
        tag(ModItemTags.FORGE_INGOTS_BRONZE).add(ModItems.BRONZE_INGOT.get());
        tag(ModItemTags.FORGE_INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());
        tag(ModItemTags.FORGE_INGOTS_SILVER).add(ModItems.SILVER_INGOT.get());
        tag(ModItemTags.FORGE_INGOTS_LEAD).add(ModItems.LEAD_INGOT.get());
        tag(ModItemTags.FORGE_INGOTS_LEAD).add(ModItems.LEAD_INGOT.get());
        tag(ModItemTags.FORGE_INGOTS_URANIUM).add(ModItems.URANIUM_INGOT.get());

        /* PLATES */

        tag(ModItemTags.FORGE_PLATES_COPPER).add(ModItems.COPPER_PLATE.get());
        tag(ModItemTags.FORGE_PLATES_TIN).add(ModItems.TIN_PLATE.get());
        tag(ModItemTags.FORGE_PLATES_IRON).add(ModItems.IRON_PLATE.get());
        tag(ModItemTags.FORGE_PLATES_LEAD).add(ModItems.LEAD_PLATE.get());
        tag(ModItemTags.FORGE_PLATES_GOLD).add(ModItems.GOLD_PLATE.get());
        tag(ModItemTags.FORGE_PLATES_BRONZE).add(ModItems.BRONZE_PLATE.get());
        tag(ModItemTags.FORGE_PLATES_STEEL).add(ModItems.STEEL_PLATE.get());
        tag(ModItemTags.FORGE_PLATES_IRIDIUM).add(ModItems.IRIDIUM_PLATE.get());
        tag(ModItemTags.FORGE_PLATES_LAPIS).add(ModItems.LAPIS_LAZULI_PLATE.get());

        /* DUSTS */

        tag(ModItemTags.FORGE_DUSTS_TIN).add(ModItems.TIN_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_COPPER).add(ModItems.COPPER_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_GOLD).add(ModItems.GOLD_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_LEAD).add(ModItems.LEAD_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_COAL).add(ModItems.COAL_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_LAPIS).add(ModItems.LAPIS_LAZULI_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_DIAMOND).add(ModItems.DIAMOND_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_STONE).add(ModItems.STONE_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_DEEPSLATE).add(ModItems.DEEPSLATE_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_SULFUR).add(ModItems.SULFUR_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_URANIUM).add(ModItems.URANIUM_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_SILVER).add(ModItems.SILVER_DUST.get());
        tag(ModItemTags.FORGE_DUSTS_IRON).add(ModItems.IRON_DUST.get());

        /* RODS */

        tag(ModItemTags.FORGE_RODS_IRON).add(ModItems.IRON_ROD.get());

        /* WRENCHES */

        tag(ModItemTags.WRENCH)
                .add(ModItems.WRENCH.get())
                .add(ModItems.ELECTRIC_WRENCH.get())
                .add(ModItems.MULTI_TOOL.get())
        ;

        /* TREETAPS */

        tag(ModItemTags.TREETAP)
                .add(ModItems.TREETAP.get())
                .add(ModItems.ELECTRIC_TREETAP.get())
        ;

        /* HELMET */

        tag(ModItemTags.HELMET)
                .add(ModItems.NANO_HELMET.get())
                .add(ModItems.BRONZE_HELMET.get())
                .add(ModItems.NIGHTVISION_GOGGLES.get())
        ;

        /* CHESTPLATE */

        tag(ModItemTags.CHESTPLATE)
                .add(ModItems.NANO_CHESTPLATE.get())
                .add(ModItems.BRONZE_CHESTPLATE.get())
        ;

        /* LEGGINGS */

        tag(ModItemTags.LEGGINGS)
                .add(ModItems.NANO_LEGGINGS.get())
                .add(ModItems.BRONZE_LEGGINGS.get())
        ;

        /* BOOTS */

        tag(ModItemTags.BOOTS)
                .add(ModItems.NANO_BOOTS.get())
                .add(ModItems.BRONZE_BOOTS.get())
                .add(ModItems.RUBBER_BOOTS.get())
        ;

        /* REACTOR COMPONENTS */

        tag(ModItemTags.REACTOR_COMPONENT)
                .add(ModItems.SMALL_COOLANT_CELL.get())
                .add(ModItems.MEDIUM_COOLANT_CELL.get())
                .add(ModItems.LARGE_COOLANT_CELL.get())
                .add(ModItems.HEAT_VENT.get())
                .add(ModItems.OVERCLOCKED_HEAT_VENT.get())
                .add(ModItems.ADVANCED_HEAT_VENT.get())
                .add(ModItems.HEAT_EXCHANGER.get())
                .add(ModItems.ADVANCED_HEAT_EXCHANGER.get())
                .add(ModItems.COMPONENT_HEAT_EXCHANGER.get())
                .add(ModItems.COMPONENT_HEAT_VENT.get())
                .add(ModItems.CONTAINMENT_REACTOR_PLATING.get())
                .add(ModItems.HEAT_CAPACITY_REACTOR_PLATING.get())
                .add(ModItems.HEATING_CELL.get())
                .add(ModItems.REACTOR_HEAT_EXCHANGER.get())
                .add(ModItems.REACTOR_HEAT_VENT.get())
                .add(ModItems.REACTOR_PLATING.get())
                .add(ModItems.RSH_CONDENSATOR.get())
                .add(ModItems.ZLH_CONDENSATOR.get())
                .add(ModItems.NEUTRON_REFLECTOR.get())
                .add(ModItems.THICK_NEUTRON_REFLECTOR.get())
                .add(ModItems.IRIDIUM_NEUTRON_REFLECTOR.get())
                .add(ModItems.FUEL_ROD_URANIUM.get())
                .add(ModItems.FUEL_ROD_URANIUM_DEPLETED.get())
                .add(ModItems.FUEL_ROD_URANIUM_DUAL.get())
                .add(ModItems.FUEL_ROD_URANIUM_DUAL_DEPLETED.get())
                .add(ModItems.FUEL_ROD_URANIUM_QUAD.get())
                .add(ModItems.FUEL_ROD_URANIUM_QUAD_DEPLETED.get())
                .add(ModItems.FUEL_ROD_MOX.get())
                .add(ModItems.FUEL_ROD_MOX_DEPLETED.get())
                .add(ModItems.FUEL_ROD_MOX_DUAL.get())
                .add(ModItems.FUEL_ROD_MOX_DUAL_DEPLETED.get())
                .add(ModItems.FUEL_ROD_MOX_QUAD.get())
                .add(ModItems.FUEL_ROD_MOX_QUAD_DEPLETED.get())
        ;

        /* BASE MACHINE SIGN */

        tag(ModItemTags.BASIC_MACHINE_SIGN)
                .add(ModItems.YELLOW_STRIPES_BLOCK_LEFT.get())
                .add(ModItems.YELLOW_STRIPES_BLOCK_RIGHT.get())
                .add(ModItems.RADIOACTIVE_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.BIO_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.EXPLOSION_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.FIRE_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.ACID_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.MAGIC_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.FROST_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.NOISE_HAZARD_SIGN_BLOCK.get())
        ;

        /* FUSION REACTOR COMPONENTS */

        tag(ModItemTags.REACTOR_FUSION)
                .add(ModItems.SMALL_COOLANT_CELL.get())
                .add(ModItems.MEDIUM_COOLANT_CELL.get())
                .add(ModItems.LARGE_COOLANT_CELL.get())
                .add(ModItems.HEAT_VENT.get())
                .add(ModItems.OVERCLOCKED_HEAT_VENT.get())
                .add(ModItems.ADVANCED_HEAT_VENT.get())
                .add(ModItems.HEAT_EXCHANGER.get())
                .add(ModItems.ADVANCED_HEAT_EXCHANGER.get())
                .add(ModItems.COMPONENT_HEAT_EXCHANGER.get())
                .add(ModItems.COMPONENT_HEAT_VENT.get())
                .add(ModItems.CONTAINMENT_REACTOR_PLATING.get())
                .add(ModItems.HEAT_CAPACITY_REACTOR_PLATING.get())
                .add(ModItems.REACTOR_HEAT_EXCHANGER.get())
                .add(ModItems.REACTOR_HEAT_VENT.get())
                .add(ModItems.REACTOR_PLATING.get())
                .add(ModItems.NEUTRON_REFLECTOR.get())
                .add(ModItems.THICK_NEUTRON_REFLECTOR.get())
                .add(ModItems.IRIDIUM_NEUTRON_REFLECTOR.get())
                .add(ModItems.FUEL_ROD_URANIUM.get())
                .add(ModItems.FUEL_ROD_URANIUM_DEPLETED.get())
                .add(ModItems.FUEL_ROD_URANIUM_DUAL.get())
                .add(ModItems.FUEL_ROD_URANIUM_DUAL_DEPLETED.get())
                .add(ModItems.FUEL_ROD_URANIUM_QUAD.get())
                .add(ModItems.FUEL_ROD_URANIUM_QUAD_DEPLETED.get())
        ;

        /* ELECTRIC */

        tag(ModItemTags.ELECTRIC_ITEMS)
                .add(ModItems.NANO_HELMET.get())
                .add(ModItems.NANO_CHESTPLATE.get())
                .add(ModItems.NANO_LEGGINGS.get())
                .add(ModItems.NANO_HELMET.get())
                .add(ModItems.NANO_SABER.get())
                .add(ModItems.ELECTRIC_TREETAP.get())
                .add(ModItems.ELECTRIC_WRENCH.get())
                .add(ModItems.ELECTRIC_HOE.get())
                .add(ModItems.WIND_METER.get())
                .add(ModItems.MULTI_TOOL.get())
                .add(ModItems.CHAINSAW.get())
                .add(ModItems.DIAMOND_CHAINSAW.get())
                .add(ModItems.IRIDIUM_CHAINSAW.get())
                .add(ModItems.MINING_DRILL.get())
                .add(ModItems.DIAMOND_DRILL.get())
                .add(ModItems.IRIDIUM_DRILL.get())
                .add(ModItems.BATTERY.get())
                .add(ModItems.ADVANCED_BATTERY.get())
                .add(ModItems.ENERGY_CRYSTAL.get())
                .add(ModItems.LAPOTRON_CRYSTAL.get())
                .add(ModItems.CHARGING_BATTERY.get())
                .add(ModItems.CHARGING_ADVANCED_BATTERY.get())
                .add(ModItems.CHARGING_ENERGY_CRYSTAL.get())
                .add(ModItems.CHARGING_LAPOTRON_CRYSTAL.get())
        ;

        /* BATTERY */

        tag(ModItemTags.BATTERY)
                .add(ModItems.BATTERY.get())
                .add(ModItems.ADVANCED_BATTERY.get())
                .add(ModItems.ENERGY_CRYSTAL.get())
                .add(ModItems.LAPOTRON_CRYSTAL.get())
                .add(ModItems.CHARGING_BATTERY.get())
                .add(ModItems.CHARGING_ADVANCED_BATTERY.get())
                .add(ModItems.CHARGING_ENERGY_CRYSTAL.get())
                .add(ModItems.CHARGING_LAPOTRON_CRYSTAL.get())
        ;

        /* UPGRADE */

        tag(ModItemTags.UPGRADE)
                .add(ModItems.OVERCLOCKER_UPGRADE.get())
                .add(ModItems.TRANSFORMER_UPGRADE.get())
                .add(ModItems.ENERGY_STORAGE_UPGRADE.get())
                .add(ModItems.REDSTONE_SIGNAL_INVERTER_UPGRADE.get())
                .add(ModItems.EJECTOR_UPGRADE.get())
                .add(ModItems.PULLING_UPGRADE.get())
                .add(ModItems.FLUID_EJECTOR_UPGRADE.get())
                .add(ModItems.FLUID_PULLING_UPGRADE.get())
                .add(ModItems.ADVANCED_EJECTOR_UPGRADE.get())
                .add(ModItems.ADVANCED_PULLING_UPGRADE.get())
                .add(ModItems.ADVANCED_FLUID_EJECTOR_UPGRADE.get())
                .add(ModItems.ADVANCED_FLUID_PULLING_UPGRADE.get())
        ;
        /* GENERATORS */

        tag(ModItemTags.GENERATORS)
                .add(ModItems.GENERATOR.get())
                .add(ModItems.SOLAR_PANEL.get())
                .add(ModItems.ADVANCED_SOLAR_PANEL.get())
                .add(ModItems.HYBRID_SOLAR_PANEL.get())
                .add(ModItems.QUANTUM_SOLAR_PANEL.get())
                .add(ModItems.GEO_GENERATOR.get())
                .add(ModItems.SEMIFLUID_GENERATOR.get())
                .add(ModItems.NUCLEAR_REACTOR.get())
                .add(ModItems.REACTOR_CHAMBER.get())
                .add(ModItems.REACTOR_CONTROL_ROD.get())
                .add(ModItems.REACTOR_FRAME.get())
        ;

        /* MACHINES */

        tag(ModItemTags.MACHINES)
                .add(ModItems.ELECTRIC_FURNACE.get())
                .add(ModItems.CRUSHER.get())
                .add(ModItems.COMPRESSOR.get())
                .add(ModItems.EXTRACTOR.get())
                .add(ModItems.SAWMILL.get())
                .add(ModItems.EXTRUDER.get())
                .add(ModItems.CANNING_MACHINE.get())
                .add(ModItems.FLUID_ENRICHER.get())
                .add(ModItems.RECYCLER.get())
                .add(ModItems.METAL_FORMER.get())

                .add(ModItems.ALLOY_SMELTER.get())
                .add(ModItems.FERMENTER.get())
                .add(ModItems.ORE_WASHING_PLANT.get())
                .add(ModItems.THERMAL_CENTRIFUGE.get())

                .add(ModItems.MATTER_FABRICATOR.get())

                .add(ModItems.SCANNER.get())
                .add(ModItems.REPLICATOR.get())

                .add(ModItems.PATTERN_STORAGE.get())
                .add(ModItems.TELEPORT_ANCHOR.get())

        ;

        /* TRANSFORMERS */

        tag(ModItemTags.TRANSFORMERS)
                .add(ModItems.LV_TRANSFORMER.get())
                .add(ModItems.MV_TRANSFORMER.get())
                .add(ModItems.HV_TRANSFORMER.get())
                .add(ModItems.EV_TRANSFORMER.get())
        ;

        /* BATTERY BOXES */

        tag(ModItemTags.BATTERY_BOXEX)
                .add(ModItems.BATTERY_BOX.get())
                .add(ModItems.CESU.get())
                .add(ModItems.MFE.get())
                .add(ModItems.MFSU.get())
                .add(ModItems.CHARGE_PAD_BATTERY_BOX.get())
                .add(ModItems.CHARGE_PAD_CESU.get())
                .add(ModItems.CHARGE_PAD_MFE.get())
                .add(ModItems.CHARGE_PAD_MFSU.get())
        ;

        /* CANNED FOOD */

        tag(ModItemTags.CANNED_FOOD)
                .add(ModItems.CANNED_FOOD.get())
                .add(ModItems.CANNED_CHORUS_FRUIT.get())
                .add(ModItems.CANNED_GOLDEN_APPLE.get())
                .add(ModItems.CANNED_ENCHANTED_GOLDEN_APPLE.get())
                .add(ModItems.CANNED_POISON.get())
                .add(ModItems.CANNED_HUNGER.get())
        ;



    }

    @Override
    public String getName() {
        return "Industrial Reborn Tags";
    }

}
