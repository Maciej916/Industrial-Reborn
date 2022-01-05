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
                .add(ModBlocks.BATTERY_BOX.getBlock())

                .add(ModBlocks.RUBBER_LOG.getBlock())
                .add(ModBlocks.RUBBER_WOOD.getBlock())
                .add(ModBlocks.RUBBER_SAPLING.getBlock())
                .add(ModBlocks.RUBBER_PLANKS.getBlock())
                .add(ModBlocks.RUBBER_STAIRS.getBlock())
                .add(ModBlocks.RUBBER_STAIRS.getBlock())
                .add(ModBlocks.RUBBER_SLAB.getBlock())
        ;

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GENERATOR.getBlock())
                .add(ModBlocks.GEO_GENERATOR.getBlock())
                .add(ModBlocks.SOLAR_GENERATOR.getBlock())
                .add(ModBlocks.ADVANCED_SOLAR_GENERATOR.getBlock())
                .add(ModBlocks.HYBRID_SOLAR_GENERATOR.getBlock())
                .add(ModBlocks.QUANTUM_SOLAR_GENERATOR.getBlock())
                .add(ModBlocks.CRYSTALLINE_GENERATOR.getBlock())
                .add(ModBlocks.SEMIFLUID_GENERATOR.getBlock())

                .add(ModBlocks.CESU.getBlock())
                .add(ModBlocks.MFE.getBlock())
                .add(ModBlocks.MFSU.getBlock())

                .add(ModBlocks.IRON_FURNACE.getBlock())
                .add(ModBlocks.ELECTRIC_FURNACE.getBlock())

                .add(ModBlocks.CRUSHER.getBlock())
                .add(ModBlocks.COMPRESSOR.getBlock())
                .add(ModBlocks.EXTRACTOR.getBlock())
                .add(ModBlocks.SAWMILL.getBlock())
                .add(ModBlocks.EXTRUDER.getBlock())
                .add(ModBlocks.CANNING_MACHINE.getBlock())
                .add(ModBlocks.FLUID_ENRICHER.getBlock())
                .add(ModBlocks.RECYCLER.getBlock())
                .add(ModBlocks.ALLOY_SMELTER.getBlock())
                .add(ModBlocks.FERMENTER.getBlock())

                .add(ModBlocks.COPPER_CABLE.getBlock())
                .add(ModBlocks.COPPER_CABLE_INSULATED.getBlock())
                .add(ModBlocks.TIN_CABLE.getBlock())
                .add(ModBlocks.TIN_CABLE_INSULATED.getBlock())
                .add(ModBlocks.GOLD_CABLE_INSULATED.getBlock())
                .add(ModBlocks.HV_CABLE.getBlock())
                .add(ModBlocks.HV_CABLE_INSULATED.getBlock())
                .add(ModBlocks.GLASS_FIBRE_CABLE.getBlock())

                .add(ModBlocks.TIN_ORE.getBlock())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.getBlock())
                .add(ModBlocks.LEAD_ORE.getBlock())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.getBlock())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.getBlock())

                .add(ModBlocks.TIN_BLOCK.getBlock())
                .add(ModBlocks.SILVER_BLOCK.getBlock())
                .add(ModBlocks.BRONZE_BLOCK.getBlock())

                .add(ModBlocks.BASIC_MACHINE_CASING.getBlock())
                .add(ModBlocks.ADVANCED_MACHINE_CASING.getBlock())

                .add(ModBlocks.REINFORCED_GLASS.getBlock())
                .add(ModBlocks.REINFORCED_STONE.getBlock())
                .add(ModBlocks.REINFORCED_STONE_SLAB.getBlock())
                .add(ModBlocks.REINFORCED_STONE_STAIRS.getBlock())

                .add(ModBlocks.IRON_SCAFFOLDING.getBlock())
                .add(ModBlocks.IRON_FENCE.getBlock())
                .add(ModBlocks.LUMINATOR.getBlock())

                .add(ModBlocks.CHARGE_PAD_BATTERY_BOX.getBlock())
                .add(ModBlocks.CHARGE_PAD_CESU.getBlock())
                .add(ModBlocks.CHARGE_PAD_MFE.getBlock())
                .add(ModBlocks.CHARGE_PAD_MFSU.getBlock())

                .add(ModBlocks.LV_TRANSFORMER.getBlock())
                .add(ModBlocks.MV_TRANSFORMER.getBlock())
                .add(ModBlocks.HV_TRANSFORMER.getBlock())
                .add(ModBlocks.EV_TRANSFORMER.getBlock())
        ;

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.GENERATOR.getBlock())
                .add(ModBlocks.SOLAR_GENERATOR.getBlock())
                .add(ModBlocks.GEO_GENERATOR.getBlock())
                .add(ModBlocks.ADVANCED_SOLAR_GENERATOR.getBlock())
                .add(ModBlocks.HYBRID_SOLAR_GENERATOR.getBlock())
                .add(ModBlocks.QUANTUM_SOLAR_GENERATOR.getBlock())
                .add(ModBlocks.CRYSTALLINE_GENERATOR.getBlock())
                .add(ModBlocks.SEMIFLUID_GENERATOR.getBlock())

                .add(ModBlocks.CESU.getBlock())
                .add(ModBlocks.MFE.getBlock())
                .add(ModBlocks.MFSU.getBlock())

                .add(ModBlocks.IRON_FURNACE.getBlock())
                .add(ModBlocks.ELECTRIC_FURNACE.getBlock())

                .add(ModBlocks.CRUSHER.getBlock())
                .add(ModBlocks.COMPRESSOR.getBlock())
                .add(ModBlocks.EXTRACTOR.getBlock())
                .add(ModBlocks.SAWMILL.getBlock())
                .add(ModBlocks.EXTRUDER.getBlock())
                .add(ModBlocks.CANNING_MACHINE.getBlock())
                .add(ModBlocks.FLUID_ENRICHER.getBlock())
                .add(ModBlocks.RECYCLER.getBlock())
                .add(ModBlocks.ALLOY_SMELTER.getBlock())
                .add(ModBlocks.FERMENTER.getBlock())

                .add(ModBlocks.COPPER_CABLE.getBlock())
                .add(ModBlocks.COPPER_CABLE_INSULATED.getBlock())
                .add(ModBlocks.TIN_CABLE.getBlock())
                .add(ModBlocks.TIN_CABLE_INSULATED.getBlock())
                .add(ModBlocks.GOLD_CABLE_INSULATED.getBlock())
                .add(ModBlocks.HV_CABLE.getBlock())
                .add(ModBlocks.HV_CABLE_INSULATED.getBlock())
                .add(ModBlocks.GLASS_FIBRE_CABLE.getBlock())

                .add(ModBlocks.TIN_ORE.getBlock())
                .add(ModBlocks.LEAD_ORE.getBlock())

                .add(ModBlocks.TIN_BLOCK.getBlock())
                .add(ModBlocks.SILVER_BLOCK.getBlock())
                .add(ModBlocks.BRONZE_BLOCK.getBlock())

                .add(ModBlocks.BASIC_MACHINE_CASING.getBlock())
                .add(ModBlocks.ADVANCED_MACHINE_CASING.getBlock())

                .add(ModBlocks.RESIN_SHEET.getBlock())
                .add(ModBlocks.RUBBER_SHEET.getBlock())
                .add(ModBlocks.LUMINATOR.getBlock())

                .add(ModBlocks.CHARGE_PAD_BATTERY_BOX.getBlock())
                .add(ModBlocks.CHARGE_PAD_CESU.getBlock())
                .add(ModBlocks.CHARGE_PAD_MFE.getBlock())
                .add(ModBlocks.CHARGE_PAD_MFSU.getBlock())

                .add(ModBlocks.LV_TRANSFORMER.getBlock())
                .add(ModBlocks.MV_TRANSFORMER.getBlock())
                .add(ModBlocks.HV_TRANSFORMER.getBlock())
                .add(ModBlocks.EV_TRANSFORMER.getBlock())
        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DEEPSLATE_TIN_ORE.getBlock())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.getBlock())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.getBlock())
        ;
    }

    @Override
    public String getName() {
        return "Industrial Reborn Tags";
    }

}
