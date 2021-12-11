package com.maciej916.indreb.datagen.tags;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModBlocks;
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
                .add(ModBlocks.BATTERY_BOX)

                .add(ModBlocks.RUBBER_LOG)
                .add(ModBlocks.RUBBER_WOOD)
                .add(ModBlocks.RUBBER_SAPLING)
                .add(ModBlocks.RUBBER_PLANKS)
                .add(ModBlocks.RUBBER_STAIRS)
                .add(ModBlocks.RUBBER_STAIRS)
                .add(ModBlocks.RUBBER_SLAB)
        ;

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GENERATOR)
                .add(ModBlocks.GEO_GENERATOR)
                .add(ModBlocks.SOLAR_GENERATOR)
                .add(ModBlocks.ADVANCED_SOLAR_GENERATOR)
                .add(ModBlocks.HYBRID_SOLAR_GENERATOR)
                .add(ModBlocks.QUANTUM_SOLAR_GENERATOR)

                .add(ModBlocks.CESU)
                .add(ModBlocks.MFE)
                .add(ModBlocks.MFSU)

                .add(ModBlocks.IRON_FURNACE)
                .add(ModBlocks.ELECTRIC_FURNACE)

                .add(ModBlocks.CRUSHER)
                .add(ModBlocks.COMPRESSOR)
                .add(ModBlocks.EXTRACTOR)
                .add(ModBlocks.SAWMILL)
                .add(ModBlocks.EXTRUDER)
                .add(ModBlocks.ALLOY_SMELTER)

                .add(ModBlocks.COPPER_CABLE)
                .add(ModBlocks.COPPER_CABLE_INSULATED)
                .add(ModBlocks.TIN_CABLE)
                .add(ModBlocks.TIN_CABLE_INSULATED)
                .add(ModBlocks.GOLD_CABLE_INSULATED)
                .add(ModBlocks.HV_CABLE)
                .add(ModBlocks.HV_CABLE_INSULATED)
                .add(ModBlocks.GLASS_FIBRE_CABLE)

                .add(ModBlocks.TIN_ORE)
                .add(ModBlocks.DEEPSLATE_TIN_ORE)

                .add(ModBlocks.TIN_BLOCK)
                .add(ModBlocks.SILVER_BLOCK)
                .add(ModBlocks.BRONZE_BLOCK)

                .add(ModBlocks.BASIC_MACHINE_CASING)
                .add(ModBlocks.ADVANCED_MACHINE_CASING)

                .add(ModBlocks.REINFORCED_GLASS)
                .add(ModBlocks.REINFORCED_STONE)
                .add(ModBlocks.REINFORCED_STONE_SLAB)
                .add(ModBlocks.REINFORCED_STONE_STAIRS)

                .add(ModBlocks.IRON_SCAFFOLDING)
                .add(ModBlocks.IRON_FENCE)
                .add(ModBlocks.LUMINATOR)
        ;

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.GENERATOR)
                .add(ModBlocks.SOLAR_GENERATOR)
                .add(ModBlocks.GEO_GENERATOR)

                .add(ModBlocks.CESU)
                .add(ModBlocks.MFE)
                .add(ModBlocks.MFSU)

                .add(ModBlocks.IRON_FURNACE)
                .add(ModBlocks.ELECTRIC_FURNACE)

                .add(ModBlocks.CRUSHER)
                .add(ModBlocks.COMPRESSOR)
                .add(ModBlocks.EXTRACTOR)
                .add(ModBlocks.SAWMILL)
                .add(ModBlocks.EXTRUDER)
                .add(ModBlocks.ALLOY_SMELTER)

                .add(ModBlocks.COPPER_CABLE)
                .add(ModBlocks.COPPER_CABLE_INSULATED)
                .add(ModBlocks.TIN_CABLE)
                .add(ModBlocks.TIN_CABLE_INSULATED)
                .add(ModBlocks.GOLD_CABLE_INSULATED)
                .add(ModBlocks.HV_CABLE)
                .add(ModBlocks.HV_CABLE_INSULATED)
                .add(ModBlocks.GLASS_FIBRE_CABLE)

                .add(ModBlocks.TIN_ORE)

                .add(ModBlocks.TIN_BLOCK)
                .add(ModBlocks.SILVER_BLOCK)
                .add(ModBlocks.BRONZE_BLOCK)

                .add(ModBlocks.BASIC_MACHINE_CASING)
                .add(ModBlocks.ADVANCED_MACHINE_CASING)

                .add(ModBlocks.RESIN_SHEET)
                .add(ModBlocks.RUBBER_SHEET)
        ;
    }

    @Override
    public String getName() {
        return "Industrial Reborn Tags";
    }

}
