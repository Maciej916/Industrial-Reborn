package com.maciej916.indreb.datagen.client;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, IndReb.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerOres();
        registerGenerators();
        registerMachines();
        registerMisc();
        registerDecoration();
        registerTransformers();
        registerChargePad();
        registerConstructionFoam();
        registerRubberWood();
        registerSimple();
        registerExplosives();



        createCubeAllWithType(ModBlocks.REINFORCED_GLASS, "reinforced_glass", "translucent");
        createCubeAll(ModBlocks.REINFORCED_STONE, "reinforced_stone");
        createCubeAll(ModBlocks.LUMINATOR, "machines/luminator");

        stairsBlock((StairBlock) ModBlocks.REINFORCED_STONE_STAIRS.get(), new ResourceLocation(IndReb.MODID, "block/reinforced_stone"));

        slabBlock((SlabBlock) ModBlocks.REINFORCED_STONE_SLAB.get(),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone"),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone")
        );

        simpleBlock(ModBlocks.IRON_SCAFFOLDING.get(), models().cubeBottomTop(ModBlocks.IRON_SCAFFOLDING.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/iron_scaffolding_sides"),
                new ResourceLocation(IndReb.MODID, "block/iron_scaffolding_bottomtop"),
                new ResourceLocation(IndReb.MODID, "block/iron_scaffolding_bottomtop")
        ).renderType("cutout"));

        models().fenceInventory(ModBlocks.IRON_FENCE.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/iron_fence"));
        fenceBlock((FenceBlock) ModBlocks.IRON_FENCE.get(), new ResourceLocation(IndReb.MODID, "block/iron_fence"));

    }

    private void registerOres() {
        createCubeAll(ModBlocks.TIN_ORE, "ore/tin");
        createCubeAll(ModBlocks.DEEPSLATE_TIN_ORE, "ore/deepslate_tin");
        createCubeAll(ModBlocks.LEAD_ORE, "ore/lead");
        createCubeAll(ModBlocks.DEEPSLATE_LEAD_ORE, "ore/deepslate_lead");
        createCubeAll(ModBlocks.URANIUM_ORE, "ore/uranium");
        createCubeAll(ModBlocks.DEEPSLATE_URANIUM_ORE, "ore/deepslate_uranium");
        createCubeAll(ModBlocks.SILVER_ORE, "ore/silver");
        createCubeAll(ModBlocks.DEEPSLATE_SILVER_ORE, "ore/deepslate_silver");
    }

    private void registerTransformers() {
        createTransformer(ModBlocks.LV_TRANSFORMER.get(), "lv_transformer");
        createTransformer(ModBlocks.MV_TRANSFORMER.get(), "mv_transformer");
        createTransformer(ModBlocks.HV_TRANSFORMER.get(), "hv_transformer");
        createTransformer(ModBlocks.EV_TRANSFORMER.get(), "ev_transformer");
    }

    private void registerChargePad() {
        createOnlyTopActive(ModBlocks.CHARGE_PAD_BATTERY_BOX.get(), "charge_pad", "battery_box");
        createOnlyTopActive(ModBlocks.CHARGE_PAD_CESU.get(), "charge_pad", "cesu");
        createOnlyTopActive(ModBlocks.CHARGE_PAD_MFE.get(), "charge_pad", "mfe");
        createOnlyTopActive(ModBlocks.CHARGE_PAD_MFSU.get(), "charge_pad", "mfsu");
    }

    private void registerConstructionFoam() {
        createCubeAllWithType(ModBlocks.CONSTRUCTION_FOAM, "cf/foam", "cutout");
        createCubeAllWithType(ModBlocks.REINFORCED_CONSTRUCTION_FOAM, "cf/reinforced_foam", "cutout");

        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE, "cf/wall_white");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_RED, "cf/wall_red");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE, "cf/wall_orange");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK, "cf/wall_pink");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW, "cf/wall_yellow");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME, "cf/wall_lime");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN, "cf/wall_green");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE, "cf/wall_light_blue");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN, "cf/wall_cyan");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE, "cf/wall_blue");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA, "cf/wall_magenta");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE, "cf/wall_purple");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN, "cf/wall_brown");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY, "cf/wall_gray");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY, "cf/wall_light_gray");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK, "cf/wall_black");
    }

    private void registerRubberWood() {
        simpleBlock(ModBlocks.RUBBER_SAPLING.get(), models().cross(ModBlocks.RUBBER_SAPLING.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/rubber_wood/rubber_sapling")).renderType("cutout"));


    }

    private void registerSimple() {
        createCubeAll(ModBlocks.BASIC_MACHINE_CASING, "basic_machine_casing");
        createCubeAll(ModBlocks.ADVANCED_MACHINE_CASING, "advanced_machine_casing");

    }

    private void registerGenerators() {


        createWithSidesActive(ModBlocks.SEMIFLUID_GENERATOR.get(), "generator", "semifluid_generator");


        createSidesActive(ModBlocks.NUCLEAR_REACTOR.get(), "generator/reactor/nuclear_reactor", "nuclear_reactor");
        createBottomTop(ModBlocks.REACTOR_CHAMBER.get(), "generator/reactor/reactor_chamber", "reactor_chamber");


    }

    private void registerMachines() {
        createWithSidesActive(ModBlocks.FLUID_ENRICHER.get(), "machines/basic", "fluid_enricher");
        createSimple(ModBlocks.CANNING_MACHINE.get(), "machines/basic", "canning_machine");
        createWithSidesActive(ModBlocks.FERMENTER.get(), "machines/basic", "fermenter");
        createWithTopActive(ModBlocks.RECYCLER.get(), "machines/basic", "recycler");
        createWithSidesActive(ModBlocks.ORE_WASHING_PLANT.get(), "machines/basic", "ore_washing_plant");
        createSimple(ModBlocks.ALLOY_SMELTER.get(), "machines/basic", "alloy_smelter");
        createWithTopActive(ModBlocks.METAL_FORMER.get(), "machines/basic", "metal_former");

        createSimple(ModBlocks.MATTER_FABRICATOR.get(), "machines/advanced", "matter_fabricator");
        createWithSidesActive(ModBlocks.THERMAL_CENTRIFUGE.get(), "machines/advanced", "thermal_centrifuge");
        createAllActive(ModBlocks.REPLICATOR.get(), "machines/advanced", "replicator");
        createSimple(ModBlocks.SCANNER.get(), "machines/advanced", "scanner");
    }

    private void registerMisc() {
        simpleBlock(ModBlocks.TELEPORT_ANCHOR.get(), models().cubeBottomTop(ModBlocks.TELEPORT_ANCHOR.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/misc/teleport_anchor_side"),
                new ResourceLocation(IndReb.MODID, "block/misc/teleport_anchor_bottom"),
                new ResourceLocation(IndReb.MODID, "block/misc/teleport_anchor_top")
        ));

        simpleBlock(ModBlocks.PATTERN_STORAGE.get(), models().orientableWithBottom(ModBlocks.PATTERN_STORAGE.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/misc/pattern_storage_side"),
                new ResourceLocation(IndReb.MODID, "block/misc/pattern_storage_front"),
                new ResourceLocation(IndReb.MODID, "block/misc/pattern_storage_bottom"),
                new ResourceLocation(IndReb.MODID, "block/misc/pattern_storage_top")
        ));
    }

    private void registerDecoration() {
        createCubeAll(ModBlocks.YELLOW_STRIPES_BLOCK_LEFT, "decoration/yellow_stripes_block_left");
        createCubeAll(ModBlocks.YELLOW_STRIPES_BLOCK_RIGHT, "decoration/yellow_stripes_block_right");
        createBottomTopSides(ModBlocks.RADIOACTIVE_HAZARD_SIGN_BLOCK, "decoration/radioactive_hazard_sign_block", "basic_machine_casing", "basic_machine_casing");
        createBottomTopSides(ModBlocks.BIO_HAZARD_SIGN_BLOCK, "decoration/bio_hazard_sign_block", "basic_machine_casing", "basic_machine_casing");
        createBottomTopSides(ModBlocks.EXPLOSION_HAZARD_SIGN_BLOCK, "decoration/explosion_hazard_sign_block", "basic_machine_casing", "basic_machine_casing");
        createBottomTopSides(ModBlocks.FIRE_HAZARD_SIGN_BLOCK, "decoration/fire_hazard_sign_block", "basic_machine_casing", "basic_machine_casing");
        createBottomTopSides(ModBlocks.ACID_HAZARD_SIGN_BLOCK, "decoration/acid_hazard_sign_block", "basic_machine_casing", "basic_machine_casing");
        createBottomTopSides(ModBlocks.MAGIC_HAZARD_SIGN_BLOCK, "decoration/magic_hazard_sign_block", "basic_machine_casing", "basic_machine_casing");
        createBottomTopSides(ModBlocks.FROST_HAZARD_SIGN_BLOCK, "decoration/frost_hazard_sign_block", "basic_machine_casing", "basic_machine_casing");
        createBottomTopSides(ModBlocks.NOISE_HAZARD_SIGN_BLOCK, "decoration/noise_hazard_sign_block", "basic_machine_casing", "basic_machine_casing");

    }

    private void registerExplosives() {
        createBottomTop(ModBlocks.INDUSTRIAL_TNT.get(), "explosive/industrial_tnt", "industrial_tnt");
        createBottomTop(ModBlocks.NUKE.get(), "explosive/nuke", "nuke");
    }








    // add predefined models
    // clear this junk
    // rename machines to machine





    private void createSimple(Block block, String category, String name) {
        BlockModelBuilder notActive = models().orientableWithBottom(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
        BlockModelBuilder active = models().orientableWithBottom(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.activeProperty)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createWithTopActive(Block block, String category, String name) {
        BlockModelBuilder notActive = models().orientableWithBottom(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
        BlockModelBuilder active = models().orientableWithBottom(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.activeProperty)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createOnlyTopActive(Block block, String category, String name) {
        BlockModelBuilder notActive = models().orientableWithBottom(category + "_" + name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
        BlockModelBuilder active = models().orientableWithBottom(category + "_" + name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.activeProperty)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createWithSidesActive(Block block, String category, String name) {
        BlockModelBuilder notActive = cubeWithParticle(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_back"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_leftright"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_leftright"));
        BlockModelBuilder active = cubeWithParticle(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_back"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_leftright_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_leftright_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.activeProperty)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createSidesActive(Block block, String category, String name) {
        BlockModelBuilder notActive = cubeWithParticle(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"));
        BlockModelBuilder active = cubeWithParticle(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.activeProperty)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createAllActive(Block block, String category, String name) {
        BlockModelBuilder notActive = cubeWithParticle(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_back"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_left"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_right"));
        BlockModelBuilder active = cubeWithParticle(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_back_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_left_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_right_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.activeProperty)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createBottomTop(Block block, String category, String name) {
        simpleBlock(block, models().cubeBottomTop(name,
                new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"),
                new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"),
                new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top")
        ));
    }

    private void createTransformer(Block block, String name) {
        orientedBlock(block, state -> cubeWithParticle(name, new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side")), BlockStateHelper.facingProperty);
    }

    private BlockModelBuilder cubeWithParticle(String name, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        return models().withExistingParent(name, "cube")
                .texture("down", down)
                .texture("up", up)
                .texture("north", north)
                .texture("south", south)
                .texture("east", east)
                .texture("west", west)
                .texture("particle", north);
    }

    private void createCubeAll(RegistryObject<Block> block, String path) {
        simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path)));
    }

    private void createCubeAllWithType(RegistryObject<Block> block, String path, String renderTyoe) {
        simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path)).renderType(renderTyoe));
    }

    private void createBottomTopSides(RegistryObject<Block> block, String side, String top, String bottom) {
        simpleBlock(block.get(), models().cubeBottomTop(block.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/" + side),
                new ResourceLocation(IndReb.MODID, "block/" + bottom),
                new ResourceLocation(IndReb.MODID, "block/" + top)
        ));
    }

    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc, DirectionProperty directionProperty) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(directionProperty);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();
                });
    }

    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockStateHelper.horizontalFacingProperty);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();
                });
    }
}