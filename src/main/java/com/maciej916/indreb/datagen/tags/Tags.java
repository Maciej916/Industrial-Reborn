package com.maciej916.indreb.datagen.tags;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Tags extends BlockTagsProvider {

    public Tags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, IndReb.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BATTERY_BOX.get())

                .add(ModBlocks.RUBBER_LOG.get())
                .add(ModBlocks.RUBBER_WOOD.get())
                .add(ModBlocks.RUBBER_SAPLING.get())
                .add(ModBlocks.RUBBER_PLANKS.get())
                .add(ModBlocks.RUBBER_STAIRS.get())
                .add(ModBlocks.RUBBER_STAIRS.get())
                .add(ModBlocks.RUBBER_SLAB.get())
        ;

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GENERATOR.get())
                .add(ModBlocks.GEO_GENERATOR.get())
                .add(ModBlocks.SOLAR_GENERATOR.get())
                .add(ModBlocks.ADVANCED_SOLAR_GENERATOR.get())
                .add(ModBlocks.HYBRID_SOLAR_GENERATOR.get())
                .add(ModBlocks.QUANTUM_SOLAR_GENERATOR.get())
//                .add(ModBlocks.CRYSTALLINE_GENERATOR.get())
                .add(ModBlocks.SEMIFLUID_GENERATOR.get())

                .add(ModBlocks.CESU.get())
                .add(ModBlocks.MFE.get())
                .add(ModBlocks.MFSU.get())

                .add(ModBlocks.IRON_FURNACE.get())
                .add(ModBlocks.ELECTRIC_FURNACE.get())

                .add(ModBlocks.CRUSHER.get())
                .add(ModBlocks.COMPRESSOR.get())
                .add(ModBlocks.EXTRACTOR.get())
                .add(ModBlocks.SAWMILL.get())
                .add(ModBlocks.EXTRUDER.get())
                .add(ModBlocks.CANNING_MACHINE.get())
                .add(ModBlocks.FLUID_ENRICHER.get())
                .add(ModBlocks.RECYCLER.get())
                .add(ModBlocks.ALLOY_SMELTER.get())
                .add(ModBlocks.FERMENTER.get())
                .add(ModBlocks.ORE_WASHING_PLANT.get())
                .add(ModBlocks.THERMAL_CENTRIFUGE.get())
                .add(ModBlocks.MATTER_FABRICATOR.get())
                .add(ModBlocks.PATTERN_STORAGE.get())
                .add(ModBlocks.SCANNER.get())
                .add(ModBlocks.REPLICATOR.get())
                .add(ModBlocks.TELEPORT_ANCHOR.get())
                .add(ModBlocks.METAL_FORMER.get())


                .add(ModBlocks.COPPER_CABLE.get())
                .add(ModBlocks.COPPER_CABLE_INSULATED.get())
                .add(ModBlocks.TIN_CABLE.get())
                .add(ModBlocks.TIN_CABLE_INSULATED.get())
                .add(ModBlocks.GOLD_CABLE_INSULATED.get())
                .add(ModBlocks.HV_CABLE.get())
                .add(ModBlocks.HV_CABLE_INSULATED.get())
                .add(ModBlocks.GLASS_FIBRE_CABLE.get())

                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())

                .add(ModBlocks.TIN_BLOCK.get())
                .add(ModBlocks.SILVER_BLOCK.get())
                .add(ModBlocks.BRONZE_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get())

                .add(ModBlocks.BASIC_MACHINE_CASING.get())
                .add(ModBlocks.ADVANCED_MACHINE_CASING.get())

                .add(ModBlocks.REINFORCED_GLASS.get())
                .add(ModBlocks.REINFORCED_STONE.get())
                .add(ModBlocks.REINFORCED_STONE_SLAB.get())
                .add(ModBlocks.REINFORCED_STONE_STAIRS.get())

                .add(ModBlocks.IRON_SCAFFOLDING.get())
                .add(ModBlocks.IRON_FENCE.get())
                .add(ModBlocks.LUMINATOR.get())

                .add(ModBlocks.CHARGE_PAD_BATTERY_BOX.get())
                .add(ModBlocks.CHARGE_PAD_CESU.get())
                .add(ModBlocks.CHARGE_PAD_MFE.get())
                .add(ModBlocks.CHARGE_PAD_MFSU.get())

                .add(ModBlocks.LV_TRANSFORMER.get())
                .add(ModBlocks.MV_TRANSFORMER.get())
                .add(ModBlocks.HV_TRANSFORMER.get())
                .add(ModBlocks.EV_TRANSFORMER.get())

                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_RED.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.get())
                .add(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.get())

                .add(ModBlocks.YELLOW_STRIPES_BLOCK_LEFT.get())
                .add(ModBlocks.YELLOW_STRIPES_BLOCK_RIGHT.get())
                .add(ModBlocks.RADIOACTIVE_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.BIO_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.EXPLOSION_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.FIRE_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.ACID_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.MAGIC_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.FROST_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.NOISE_HAZARD_SIGN_BLOCK.get())
        ;

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.GENERATOR.get())
                .add(ModBlocks.SOLAR_GENERATOR.get())
                .add(ModBlocks.GEO_GENERATOR.get())
                .add(ModBlocks.ADVANCED_SOLAR_GENERATOR.get())
                .add(ModBlocks.HYBRID_SOLAR_GENERATOR.get())
                .add(ModBlocks.QUANTUM_SOLAR_GENERATOR.get())
//                .add(ModBlocks.CRYSTALLINE_GENERATOR.get())
                .add(ModBlocks.SEMIFLUID_GENERATOR.get())

                .add(ModBlocks.CESU.get())
                .add(ModBlocks.MFE.get())
                .add(ModBlocks.MFSU.get())

                .add(ModBlocks.IRON_FURNACE.get())
                .add(ModBlocks.ELECTRIC_FURNACE.get())

                .add(ModBlocks.CRUSHER.get())
                .add(ModBlocks.COMPRESSOR.get())
                .add(ModBlocks.EXTRACTOR.get())
                .add(ModBlocks.SAWMILL.get())
                .add(ModBlocks.EXTRUDER.get())
                .add(ModBlocks.CANNING_MACHINE.get())
                .add(ModBlocks.FLUID_ENRICHER.get())
                .add(ModBlocks.RECYCLER.get())
                .add(ModBlocks.ALLOY_SMELTER.get())
                .add(ModBlocks.FERMENTER.get())
                .add(ModBlocks.ORE_WASHING_PLANT.get())
                .add(ModBlocks.THERMAL_CENTRIFUGE.get())
                .add(ModBlocks.MATTER_FABRICATOR.get())
                .add(ModBlocks.PATTERN_STORAGE.get())
                .add(ModBlocks.SCANNER.get())
                .add(ModBlocks.REPLICATOR.get())
                .add(ModBlocks.TELEPORT_ANCHOR.get())
                .add(ModBlocks.METAL_FORMER.get())

                .add(ModBlocks.COPPER_CABLE.get())
                .add(ModBlocks.COPPER_CABLE_INSULATED.get())
                .add(ModBlocks.TIN_CABLE.get())
                .add(ModBlocks.TIN_CABLE_INSULATED.get())
                .add(ModBlocks.GOLD_CABLE_INSULATED.get())
                .add(ModBlocks.HV_CABLE.get())
                .add(ModBlocks.HV_CABLE_INSULATED.get())
                .add(ModBlocks.GLASS_FIBRE_CABLE.get())

                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())
                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())

                .add(ModBlocks.TIN_BLOCK.get())
                .add(ModBlocks.SILVER_BLOCK.get())
                .add(ModBlocks.BRONZE_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get())

                .add(ModBlocks.BASIC_MACHINE_CASING.get())
                .add(ModBlocks.ADVANCED_MACHINE_CASING.get())

                .add(ModBlocks.RESIN_SHEET.get())
                .add(ModBlocks.RUBBER_SHEET.get())
                .add(ModBlocks.LUMINATOR.get())

                .add(ModBlocks.CHARGE_PAD_BATTERY_BOX.get())
                .add(ModBlocks.CHARGE_PAD_CESU.get())
                .add(ModBlocks.CHARGE_PAD_MFE.get())
                .add(ModBlocks.CHARGE_PAD_MFSU.get())

                .add(ModBlocks.LV_TRANSFORMER.get())
                .add(ModBlocks.MV_TRANSFORMER.get())
                .add(ModBlocks.HV_TRANSFORMER.get())
                .add(ModBlocks.EV_TRANSFORMER.get())

                .add(ModBlocks.YELLOW_STRIPES_BLOCK_LEFT.get())
                .add(ModBlocks.YELLOW_STRIPES_BLOCK_RIGHT.get())
                .add(ModBlocks.RADIOACTIVE_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.BIO_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.EXPLOSION_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.FIRE_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.ACID_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.MAGIC_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.FROST_HAZARD_SIGN_BLOCK.get())
                .add(ModBlocks.NOISE_HAZARD_SIGN_BLOCK.get())
        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())
        ;


    }

    @Override
    public String getName() {
        return "Industrial Reborn Tags";
    }

}
