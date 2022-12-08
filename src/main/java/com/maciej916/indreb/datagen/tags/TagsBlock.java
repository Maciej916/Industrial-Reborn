package com.maciej916.indreb.datagen.tags;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.tag.ModBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagsBlock extends BlockTagsProvider {

    public TagsBlock(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, IndReb.MODID, helper);
    }

    @Override
    protected void addTags() {

        tag(BlockTags.FENCES)
                .add(ModBlocks.IRON_FENCE.get())
                .add(ModBlocks.RUBBER_FENCE.get())
        ;

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.RUBBER_FENCE.get())
        ;

        tag(BlockTags.LEAVES)
                .add(ModBlocks.RUBBER_LEAVES.get())
        ;

        tag(BlockTags.LOGS)
                .add(ModBlocks.RUBBER_LOG.get())
        ;

        tag(BlockTags.PLANKS)
                .add(ModBlocks.RUBBER_PLANKS.get())
        ;

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.RUBBER_SAPLING.get())
        ;

        tag(BlockTags.WALLS)
                .add(ModBlocks.REINFORCED_STONE_BRICK_WALL.get())
        ;

        tag(BlockTags.DOORS)
                .add(ModBlocks.REINFORCED_DOOR.get())
                .add(ModBlocks.RUBBER_DOOR.get())
        ;

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.RUBBER_DOOR.get())
        ;

        tag(BlockTags.STAIRS)
                .add(ModBlocks.REINFORCED_STONE_STAIRS.get())
                .add(ModBlocks.RUBBER_STAIRS.get())
        ;

        tag(BlockTags.SLABS)
                .add(ModBlocks.REINFORCED_STONE_SLAB.get())
                .add(ModBlocks.RUBBER_SLAB.get())
        ;

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.RUBBER_SLAB.get())
        ;

        tag(BlockTags.TRAPDOORS)
                .add(ModBlocks.RUBBER_TRAP_DOOR.get())
        ;

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.RUBBER_TRAP_DOOR.get())
        ;

        tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.RUBBER_PLATE.get())
        ;

        /* ORES */

        tag(ModBlockTags.FORGE_ORE_TIN)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
        ;

        tag(ModBlockTags.FORGE_ORE_LEAD)
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
        ;

        tag(ModBlockTags.FORGE_ORE_URANIUM)
                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())
        ;

        tag(ModBlockTags.FORGE_ORE_SILVER)
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())
        ;

        /* RAW STORAGE BLOCKS */

        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_RAW_TIN).add(ModBlocks.RAW_TIN_BLOCK.get());
        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_RAW_LEAD).add(ModBlocks.RAW_LEAD_BLOCK.get());
        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_RAW_URANIUM).add(ModBlocks.RAW_URANIUM_BLOCK.get());
        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_RAW_SILVER).add(ModBlocks.RAW_SILVER_BLOCK.get());

        /* STORAGE BLOCKS */

        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_TIN).add(ModBlocks.TIN_BLOCK.get());
        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_SILVER).add(ModBlocks.SILVER_BLOCK.get());
        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_URANIUM).add(ModBlocks.URANIUM_BLOCK.get());
        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_LEAD).add(ModBlocks.LEAD_BLOCK.get());
        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_STEEL).add(ModBlocks.STEEL_BLOCK.get());
        tag(ModBlockTags.FORGE_STORAGE_BLOCKS_BRONZE).add(ModBlocks.BRONZE_BLOCK.get());

        /* REACTOR PARTS */

        tag(ModBlockTags.REACTOR_PART)
                .add(ModBlocks.REACTOR_CHAMBER.get())
                .add(ModBlocks.REACTOR_FRAME.get())
                .add(ModBlocks.REACTOR_CONTROL_ROD.get())
                .add(ModBlocks.NUCLEAR_REACTOR.get())
        ;

        /* MINEABLE WITH HOE */

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.RUBBER_LEAVES.get())
        ;

        /* MINEABLE WITH SHOVEL */

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.RESIN_BLOCK.get())
                .add(ModBlocks.RESIN_SHEET.get())
                .add(ModBlocks.RUBBER_BLOCK.get())
                .add(ModBlocks.RUBBER_SHEET.get())

                .add(ModBlocks.CONSTRUCTION_FOAM.get())
                .add(ModBlocks.REINFORCED_CONSTRUCTION_FOAM.get())
        ;

        /* MINEABLE WITH AXE */

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.RUBBER_LOG.get())
                .add(ModBlocks.RUBBER_WOOD.get())
                .add(ModBlocks.RUBBER_PLANKS.get())
                .add(ModBlocks.RUBBER_STAIRS.get())
                .add(ModBlocks.RUBBER_SLAB.get())
                .add(ModBlocks.RUBBER_PLATE.get())
                .add(ModBlocks.RUBBER_FENCE.get())
                .add(ModBlocks.RUBBER_GATE.get())
                .add(ModBlocks.RUBBER_DOOR.get())
                .add(ModBlocks.RUBBER_TRAP_DOOR.get())

                .add(ModBlocks.BATTERY_BOX.get())
                .add(ModBlocks.CHARGE_PAD_BATTERY_BOX.get())

        ;

        /* NEEDS STONE TOOL */

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.SILVER_ORE.get())

                .add(ModBlocks.RAW_TIN_BLOCK.get())
                .add(ModBlocks.RAW_LEAD_BLOCK.get())
                .add(ModBlocks.RAW_SILVER_BLOCK.get())

                .add(ModBlocks.TIN_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get())
                .add(ModBlocks.SILVER_BLOCK.get())
                .add(ModBlocks.BRONZE_BLOCK.get())

                .add(ModBlocks.BASIC_MACHINE_CASING.get())
                .add(ModBlocks.ADVANCED_MACHINE_CASING.get())

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

                .add(ModBlocks.BATTERY_BOX.get())
                .add(ModBlocks.CESU.get())
                .add(ModBlocks.MFE.get())
                .add(ModBlocks.MFSU.get())

                .add(ModBlocks.CHARGE_PAD_BATTERY_BOX.get())
                .add(ModBlocks.CHARGE_PAD_CESU.get())
                .add(ModBlocks.CHARGE_PAD_MFE.get())
                .add(ModBlocks.CHARGE_PAD_MFSU.get())

                .add(ModBlocks.LV_TRANSFORMER.get())
                .add(ModBlocks.MV_TRANSFORMER.get())
                .add(ModBlocks.HV_TRANSFORMER.get())
                .add(ModBlocks.EV_TRANSFORMER.get())

                .add(ModBlocks.GENERATOR.get())
                .add(ModBlocks.SOLAR_PANEL.get())
                .add(ModBlocks.ADVANCED_SOLAR_PANEL.get())
                .add(ModBlocks.HYBRID_SOLAR_PANEL.get())
                .add(ModBlocks.QUANTUM_SOLAR_PANEL.get())
                .add(ModBlocks.GEO_GENERATOR.get())
                .add(ModBlocks.SEMIFLUID_GENERATOR.get())
                .add(ModBlocks.NUCLEAR_REACTOR.get())
                .add(ModBlocks.REACTOR_CHAMBER.get())
                .add(ModBlocks.REACTOR_CONTROL_ROD.get())
                .add(ModBlocks.REACTOR_FRAME.get())

                .add(ModBlocks.ELECTRIC_FURNACE.get())
                .add(ModBlocks.CRUSHER.get())
                .add(ModBlocks.COMPRESSOR.get())
                .add(ModBlocks.EXTRACTOR.get())
                .add(ModBlocks.SAWMILL.get())
                .add(ModBlocks.EXTRUDER.get())
                .add(ModBlocks.CANNING_MACHINE.get())
                .add(ModBlocks.FLUID_ENRICHER.get())
                .add(ModBlocks.RECYCLER.get())
                .add(ModBlocks.METAL_FORMER.get())

                .add(ModBlocks.ALLOY_SMELTER.get())
                .add(ModBlocks.FERMENTER.get())
                .add(ModBlocks.ORE_WASHING_PLANT.get())
                .add(ModBlocks.THERMAL_CENTRIFUGE.get())

                .add(ModBlocks.MATTER_FABRICATOR.get())

                .add(ModBlocks.SCANNER.get())
                .add(ModBlocks.REPLICATOR.get())

                .add(ModBlocks.PATTERN_STORAGE.get())
                .add(ModBlocks.TELEPORT_ANCHOR.get())
        ;

        /* NEEDS IRON TOOL */

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.URANIUM_ORE.get())

                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())

                .add(ModBlocks.RAW_URANIUM_BLOCK.get())

                .add(ModBlocks.URANIUM_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get())

                .add(ModBlocks.REINFORCED_GLASS.get())
                .add(ModBlocks.REINFORCED_STONE.get())
                .add(ModBlocks.REINFORCED_STONE_SLAB.get())
                .add(ModBlocks.REINFORCED_STONE_STAIRS.get())
                .add(ModBlocks.REINFORCED_STONE_BRICKS.get())
                .add(ModBlocks.SMOOTH_REINFORCED_STONE.get())
                .add(ModBlocks.REINFORCED_STONE_BRICK_WALL.get())
                .add(ModBlocks.REINFORCED_DOOR.get())

        ;

        /* MINEABLE WITH PICKAXE */

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.SILVER_ORE.get())

                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())

                .add(ModBlocks.RAW_TIN_BLOCK.get())
                .add(ModBlocks.RAW_LEAD_BLOCK.get())
                .add(ModBlocks.RAW_URANIUM_BLOCK.get())
                .add(ModBlocks.RAW_SILVER_BLOCK.get())

                .add(ModBlocks.TIN_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get())
                .add(ModBlocks.URANIUM_BLOCK.get())
                .add(ModBlocks.SILVER_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.BRONZE_BLOCK.get())

                .add(ModBlocks.BASIC_MACHINE_CASING.get())
                .add(ModBlocks.ADVANCED_MACHINE_CASING.get())

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

                .add(ModBlocks.REINFORCED_GLASS.get())
                .add(ModBlocks.REINFORCED_STONE.get())
                .add(ModBlocks.REINFORCED_STONE_SLAB.get())
                .add(ModBlocks.REINFORCED_STONE_STAIRS.get())
                .add(ModBlocks.REINFORCED_STONE_BRICKS.get())
                .add(ModBlocks.SMOOTH_REINFORCED_STONE.get())
                .add(ModBlocks.REINFORCED_STONE_BRICK_WALL.get())
                .add(ModBlocks.REINFORCED_DOOR.get())

                .add(ModBlocks.IRON_SCAFFOLDING.get())
                .add(ModBlocks.IRON_FENCE.get())
                .add(ModBlocks.IRON_GATE.get())
                .add(ModBlocks.LUMINATOR.get())

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

                .add(ModBlocks.CESU.get())
                .add(ModBlocks.MFE.get())
                .add(ModBlocks.MFSU.get())

                .add(ModBlocks.CHARGE_PAD_CESU.get())
                .add(ModBlocks.CHARGE_PAD_MFE.get())
                .add(ModBlocks.CHARGE_PAD_MFSU.get())

                .add(ModBlocks.LV_TRANSFORMER.get())
                .add(ModBlocks.MV_TRANSFORMER.get())
                .add(ModBlocks.HV_TRANSFORMER.get())
                .add(ModBlocks.EV_TRANSFORMER.get())

                .add(ModBlocks.SIMPLE_CRUSHER.get())
                .add(ModBlocks.SIMPLE_EXTRACTOR.get())
                .add(ModBlocks.SIMPLE_COMPRESSOR.get())
                .add(ModBlocks.IRON_FURNACE.get())

//                .add(ModBlocks.COPPER_CABLE.get())
//                .add(ModBlocks.COPPER_CABLE_INSULATED.get())
//                .add(ModBlocks.TIN_CABLE.get())
//                .add(ModBlocks.TIN_CABLE_INSULATED.get())
//                .add(ModBlocks.GOLD_CABLE_INSULATED.get())
//                .add(ModBlocks.HV_CABLE.get())
//                .add(ModBlocks.HV_CABLE_INSULATED.get())
//                .add(ModBlocks.GLASS_FIBRE_CABLE.get())

                .add(ModBlocks.GENERATOR.get())
                .add(ModBlocks.SOLAR_PANEL.get())
                .add(ModBlocks.ADVANCED_SOLAR_PANEL.get())
                .add(ModBlocks.HYBRID_SOLAR_PANEL.get())
                .add(ModBlocks.QUANTUM_SOLAR_PANEL.get())
                .add(ModBlocks.GEO_GENERATOR.get())
                .add(ModBlocks.SEMIFLUID_GENERATOR.get())
                .add(ModBlocks.NUCLEAR_REACTOR.get())
                .add(ModBlocks.REACTOR_CHAMBER.get())
                .add(ModBlocks.REACTOR_CONTROL_ROD.get())
                .add(ModBlocks.REACTOR_FRAME.get())

                .add(ModBlocks.ELECTRIC_FURNACE.get())
                .add(ModBlocks.CRUSHER.get())
                .add(ModBlocks.COMPRESSOR.get())
                .add(ModBlocks.EXTRACTOR.get())
                .add(ModBlocks.SAWMILL.get())
                .add(ModBlocks.EXTRUDER.get())
                .add(ModBlocks.CANNING_MACHINE.get())
                .add(ModBlocks.FLUID_ENRICHER.get())
                .add(ModBlocks.RECYCLER.get())
                .add(ModBlocks.METAL_FORMER.get())

                .add(ModBlocks.ALLOY_SMELTER.get())
                .add(ModBlocks.FERMENTER.get())
                .add(ModBlocks.ORE_WASHING_PLANT.get())
                .add(ModBlocks.THERMAL_CENTRIFUGE.get())

                .add(ModBlocks.MATTER_FABRICATOR.get())

                .add(ModBlocks.SCANNER.get())
                .add(ModBlocks.REPLICATOR.get())

                .add(ModBlocks.PATTERN_STORAGE.get())
                .add(ModBlocks.TELEPORT_ANCHOR.get())


        ;




    }

    @Override
    public String getName() {
        return "Industrial Reborn Tags";
    }

}
