package com.maciej916.indreb.datagen.texture;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

import static com.maciej916.indreb.common.util.BlockStateHelper.HORIZONTAL_FACING_PROPERTY;

public class BlockTextures extends BlockStateProvider {

    public BlockTextures(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, IndReb.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerOres();
        registerRawBlock();
        registerMaterial();
        registerCasing();
        registerSheets();
        registerConstructionFoam();
        registerRubberWood();
        registerIron();
        registerDecoration();
        registerGenerators();
        registerMisc();
        registerExplosives();
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

    private void registerRawBlock() {
        createCubeAll(ModBlocks.RAW_TIN_BLOCK, "raw/raw_tin_block");
        createCubeAll(ModBlocks.RAW_LEAD_BLOCK, "raw/raw_lead_block");
        createCubeAll(ModBlocks.RAW_URANIUM_BLOCK, "raw/raw_uranium_block");
        createCubeAll(ModBlocks.RAW_SILVER_BLOCK, "raw/raw_silver_block");
    }

    private void registerMaterial() {
        createCubeAll(ModBlocks.TIN_BLOCK, "material/tin_block");
        createCubeAll(ModBlocks.LEAD_BLOCK, "material/lead_block");
        createCubeAll(ModBlocks.URANIUM_BLOCK, "material/uranium_block");
        createCubeAll(ModBlocks.SILVER_BLOCK, "material/silver_block");
        createCubeAll(ModBlocks.STEEL_BLOCK, "material/steel_block");
        createCubeAll(ModBlocks.BRONZE_BLOCK, "material/bronze_block");
    }

    private void registerCasing() {
        createCubeAll(ModBlocks.BASIC_MACHINE_CASING, "casing/basic_machine_casing");
        createCubeAll(ModBlocks.ADVANCED_MACHINE_CASING, "casing/advanced_machine_casing");
    }

    private void registerSheets() {
        createCubeAll(ModBlocks.RUBBER_BLOCK, "sheet/rubber_block");
        createCubeAll(ModBlocks.RESIN_BLOCK, "sheet/resin_block");
    }

    private void registerConstructionFoam() {
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE, "construction_foam/wall/wall_white");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_RED, "construction_foam/wall/wall_red");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE, "construction_foam/wall/wall_orange");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK, "construction_foam/wall/wall_pink");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW, "construction_foam/wall/wall_yellow");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME, "construction_foam/wall/wall_lime");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN, "construction_foam/wall/wall_green");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE, "construction_foam/wall/wall_light_blue");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN, "construction_foam/wall/wall_cyan");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE, "construction_foam/wall/wall_blue");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA, "construction_foam/wall/wall_magenta");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE, "construction_foam/wall/wall_purple");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN, "construction_foam/wall/wall_brown");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY, "construction_foam/wall/wall_gray");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY, "construction_foam/wall/wall_light_gray");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK, "construction_foam/wall/wall_black");

        createCubeAllRenderType(ModBlocks.REINFORCED_GLASS, "construction_foam/reinforced/reinforced_glass", "translucent");
        createCubeAll(ModBlocks.REINFORCED_STONE, "construction_foam/reinforced/reinforced_stone");
        createSlabBlock(ModBlocks.REINFORCED_STONE_SLAB, "construction_foam/reinforced/reinforced_stone");
        createStairsBlock(ModBlocks.REINFORCED_STONE_STAIRS, "construction_foam/reinforced/reinforced_stone");
        createCubeAll(ModBlocks.REINFORCED_STONE_BRICKS, "construction_foam/reinforced/reinforced_stone_bricks");
        createCubeAll(ModBlocks.SMOOTH_REINFORCED_STONE, "construction_foam/reinforced/smooth_reinforced_stone");
        createWallBlock(ModBlocks.REINFORCED_STONE_BRICK_WALL, "construction_foam/reinforced/reinforced_stone_bricks");
        createDoorBlock(ModBlocks.REINFORCED_DOOR, "construction_foam/reinforced/reinforced_door");
    }

    private void registerRubberWood() {
        createCubeAll(ModBlocks.RUBBER_WOOD, "rubber_wood/rubber_log_side");
        createLeavesBlock(ModBlocks.RUBBER_LEAVES, "rubber_wood/rubber_leaves");
        createCubeAll(ModBlocks.RUBBER_PLANKS, "rubber_wood/rubber_planks");
        createSaplingBlock(ModBlocks.RUBBER_SAPLING, "rubber_wood/rubber_sapling");
        createStairsBlock(ModBlocks.RUBBER_STAIRS, "rubber_wood/rubber_planks");
        createSlabBlock(ModBlocks.RUBBER_SLAB, "rubber_wood/rubber_planks");
        createPressurePlateBlock(ModBlocks.RUBBER_PLATE, "rubber_wood/rubber_planks");
        createFenceGateBlock(ModBlocks.RUBBER_GATE, "rubber_wood/rubber_planks");
        createFenceBlock(ModBlocks.RUBBER_FENCE, "rubber_wood/rubber_planks");
        createDoorBlock(ModBlocks.RUBBER_DOOR, "rubber_wood/rubber_door");
        createTrapDoorBlock(ModBlocks.RUBBER_TRAP_DOOR, "rubber_wood/rubber_trap_door", true);
    }

    private void registerIron() {
        createFenceBlock(ModBlocks.IRON_FENCE, "iron/iron_fence");
        createFenceGateBlock(ModBlocks.IRON_GATE, "iron/iron_fence");
    }

    private void registerDecoration() {
        createCubeAll(ModBlocks.YELLOW_STRIPES_BLOCK_LEFT, "decoration/yellow_stripes_block_left");
        createCubeAll(ModBlocks.YELLOW_STRIPES_BLOCK_RIGHT, "decoration/yellow_stripes_block_right");
        createSimpleSideTopBottom(ModBlocks.RADIOACTIVE_HAZARD_SIGN_BLOCK, "decoration/radioactive_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.BIO_HAZARD_SIGN_BLOCK, "decoration/bio_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.EXPLOSION_HAZARD_SIGN_BLOCK, "decoration/explosion_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.FIRE_HAZARD_SIGN_BLOCK, "decoration/fire_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.ACID_HAZARD_SIGN_BLOCK, "decoration/acid_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.MAGIC_HAZARD_SIGN_BLOCK, "decoration/magic_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.FROST_HAZARD_SIGN_BLOCK, "decoration/frost_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.NOISE_HAZARD_SIGN_BLOCK, "decoration/noise_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
    }

    private void registerGenerators() {
        createFrontActive(ModBlocks.GENERATOR, "generator/generator/generator");
        createSolarPanel(ModBlocks.SOLAR_PANEL, "generator/solar_panel/solar_panel");
        createSolarPanel(ModBlocks.ADVANCED_SOLAR_PANEL, "generator/advanced_solar_panel/advanced_solar_panel");
        createSolarPanel(ModBlocks.HYBRID_SOLAR_PANEL, "generator/hybrid_solar_panel/hybrid_solar_panel");
        createSolarPanel(ModBlocks.QUANTUM_SOLAR_PANEL, "generator/quantum_solar_panel/quantum_solar_panel");
        createFrontActive(ModBlocks.GEO_GENERATOR, "generator/geo_generator/geo_generator");
        createFrontLeftRightActive(ModBlocks.SEMIFLUID_GENERATOR, "generator/semifluid_generator/semifluid_generator");

    }

    private void registerMisc() {
        createLuminator(ModBlocks.LUMINATOR, "misc/luminator/luminator");

    }

    private void registerExplosives() {
        createSimpleSideTopBottom(ModBlocks.INDUSTRIAL_TNT, "explosive/industrial_tnt/industrial_tnt_side", "explosive/industrial_tnt/industrial_tnt_top", "explosive/industrial_tnt/industrial_tnt_bottom");
        createSimpleSideTopBottom(ModBlocks.NUKE, "explosive/nuke/nuke_side", "explosive/nuke/nuke_top", "explosive/nuke/nuke_bottom");
    }














    private void createCubeAll(RegistryObject<Block> block, String path) {
        simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path)));
    }

    private void createCubeAllRenderType(RegistryObject<Block> block, String path, String renderTyoe) {
        simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path)).renderType(renderTyoe));
    }

    public void createLeavesBlock(RegistryObject<Block> block, String path) {
        simpleBlock(block.get(), models().withExistingParent(block.getId().getPath(), "leaves").texture("all", new ResourceLocation(IndReb.MODID, "block/" + path)).renderType("translucent"));
    }

    public void createStairsBlock(RegistryObject<Block> block, String path) {
        stairsBlock((StairBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createSlabBlock(RegistryObject<Block> block, String path) {
        ResourceLocation side_rl = new ResourceLocation(IndReb.MODID, "block/" + path);
        ResourceLocation bottom_rl = new ResourceLocation(IndReb.MODID, "block/" + path);
        ResourceLocation top_rl = new ResourceLocation(IndReb.MODID, "block/" + path);

        ModelFile bottom = models().slab(block.getId().getPath(), side_rl, bottom_rl, top_rl);
        ModelFile top = models().slabTop(block.getId().getPath() + "_top", side_rl, bottom_rl, top_rl);
        ModelFile doubleslab = models().cubeAll(block.getId().getPath() + "_double", side_rl);

        getVariantBuilder(block.get())
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottom))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(top))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubleslab));
    }

    public void createWallBlock(RegistryObject<Block> block, String path) {
        wallBlockWithRenderType((WallBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path), "translucent");
        models().wallInventory(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createDoorBlock(RegistryObject<Block> block, String path) {
        doorBlockWithRenderType((DoorBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path+ "_top"), "translucent");
    }

    public void createSaplingBlock(RegistryObject<Block> block, String path) {
        simpleBlock(block.get(), models().cross(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path)).renderType("cutout"));
    }

    public void createPressurePlateBlock(RegistryObject<Block> block, String path) {
        pressurePlateBlock((PressurePlateBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createFenceGateBlock(RegistryObject<Block> block, String path) {
        fenceGateBlock((FenceGateBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createFenceBlock(RegistryObject<Block> block, String path) {
        fenceBlock((FenceBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path));
        models().fenceInventory(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createTrapDoorBlock(RegistryObject<Block> block, String path, boolean orientable) {
        trapdoorBlockWithRenderType((TrapDoorBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path), orientable, "translucent");
    }

    private void createFrontActive(RegistryObject<Block> block, String path) {
        BlockModelBuilder notActive = models().orientableWithBottom(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path + "_side"), new ResourceLocation(IndReb.MODID, "block/" + path + "_front"), new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path + "_top"));
        BlockModelBuilder active = models().orientableWithBottom(block.getId().getPath() + "_active", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"), new ResourceLocation(IndReb.MODID, "block/" + path + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path + "_top"));

        orientedBlock(block.get(), state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createSolarPanel(RegistryObject<Block> block, String path) {
        ModelFile notActive = models().withExistingParent(block.getId().getPath(), "slab").texture("top", new ResourceLocation(IndReb.MODID, "block/" + path + "_top")).texture("bottom", new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom")).texture("side", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"));
        ModelFile active = models().withExistingParent(block.getId().getPath() + "_active", "slab").texture("top", new ResourceLocation(IndReb.MODID, "block/" + path + "_top_active")).texture("bottom", new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom")).texture("side", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"));

        Function<BlockState, ModelFile> modelFunc  = state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        };
        getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).build());
    }

    private void createFrontLeftRightActive(RegistryObject<Block> block, String path) {
        BlockModelBuilder notActive = cubeWithParticle(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" +path + "_top"), new ResourceLocation(IndReb.MODID, "block/" + path + "_front"), new ResourceLocation(IndReb.MODID, "block/" +path + "_back"), new ResourceLocation(IndReb.MODID, "block/" + path + "_leftright"), new ResourceLocation(IndReb.MODID, "block/" + path + "_leftright"));
        BlockModelBuilder active = cubeWithParticle(block.getId().getPath() + "_active", new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path + "_top"), new ResourceLocation(IndReb.MODID, "block/" + path + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + path + "_back"), new ResourceLocation(IndReb.MODID, "block/" + path + "_leftright_active"), new ResourceLocation(IndReb.MODID, "block/" + path + "_leftright_active"));
        orientedBlock(block.get(), state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createSimpleSideTopBottom(RegistryObject<Block> block, String side, String top, String bottom) {
        simpleBlock(block.get(), models().cubeBottomTop(block.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/" + side),
                new ResourceLocation(IndReb.MODID, "block/" + bottom),
                new ResourceLocation(IndReb.MODID, "block/" + top)
        ));
    }

    private void createLuminator(RegistryObject<Block> block, String path) {
        ModelFile luminatorModel = models().getExistingFile(modLoc("block/luminator"));
        ModelFile luminatorModelActive = models().getExistingFile(modLoc("block/luminator_active"));

        Function<BlockState, ModelFile> modelFunc  = state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return luminatorModelActive;
            } else {
                return luminatorModel;
            }
        };

        getVariantBuilder(block.get())
            .forAllStates(state -> {
                Direction dir = state.getValue(BlockStateHelper.FACING_PROPERTY);
                return ConfiguredModel.builder()
                        .modelFile(modelFunc.apply(state))
                        .rotationX(dir == Direction.DOWN ? 180 : dir == Direction.UP ? 0 : 90)
                        .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                        .build();
            });
    }














    private void createRubberLog(RegistryObject<Block> block, String path, String name) {
//        BlockModelBuilder



            ResourceLocation location = new ResourceLocation(IndReb.MODID, "block/" + path);
            ResourceLocation side = new ResourceLocation(location.getNamespace(), location.getPath() + "_side");
            ResourceLocation end = new ResourceLocation(location.getNamespace(), location.getPath() + "_end");



        ModelFile vertical = models().cubeColumn(block.getId().getPath(), side, end);
        ModelFile horizontal = models().cubeColumnHorizontal(block.getId().getPath() + "_horizontal", side, end);

//        axisBlock((RotatedPillarBlock) block.get(),  new ResourceLocation(IndReb.MODID, "block/" + path));
//
        BlockModelBuilder notActive = models().orientableWithBottom(name,
                new ResourceLocation(IndReb.MODID, "block/" + path),
                new ResourceLocation(IndReb.MODID, "block/" + path),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_top"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_top"));


//        getVariantBuilder(block)
//                .forAllStates(state -> {
//                    Direction dir = state.getValue(horizontalFacingProperty);
//                    return ConfiguredModel.builder()
//                            .modelFile(modelFunc.apply(state))
//                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
//                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
//                            .build();
//                });


        Function<BlockState, ModelFile> modelFunc  = state -> {
            if (state.getValue(BlockStateHelper.WET_PROPERTY)) {
                return notActive;
            } else if (state.getValue(BlockStateHelper.DRY_PROPERTY)) {
                return notActive;
            } else {
                return notActive;
            }
        };


//        getVariantBuilder(block.get())
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
//                .modelForState().modelFile(vertical).addModel()
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
//                .modelForState().modelFile(horizontal).rotationX(90).addModel()
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
//                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel()
//                .forAllStates(state -> {
////                    Direction dir = state.getValue(horizontalFacingProperty);
//                    return ConfiguredModel.builder()
//                            .modelFile(modelFunc.apply(state))
////                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
////                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
//                            .build();
//                })


        ;


        //        BlockModelBuilder active = models().orientableWithBottom(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
//        orientedBlock(block.get(), state -> {
//            if (state.getValue(BlockStateHelper.wetProperty)) {
//                return notActive;
//            } else if (state.getValue(BlockStateHelper.dryProperty)) {
//                return notActive;
//            } else {
//                return notActive;
//            }
//        }, BlockStateHelper.axisProperty);
    }





    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc, EnumProperty<Direction.Axis> axisEnumProperty) {

//        getVariantBuilder(block)
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
//                .modelForState().modelFile(modelFunc).addModel()
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
//                .modelForState().modelFile(horizontal).rotationX(90).addModel()
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
//                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();


        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(HORIZONTAL_FACING_PROPERTY);
                    return ConfiguredModel.builder()

                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();



//                    return ConfiguredModel.builder()
//
//                            .modelFile(modelFunc.apply(state))
//                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
//                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
//                            .build();
                });
    }



    // add predefined models
    // clear this junk
    // rename machines to machine


    private void createSimple(Block block, String category, String name) {
        BlockModelBuilder notActive = models().orientableWithBottom(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
        BlockModelBuilder active = models().orientableWithBottom(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));

        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
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
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
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
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
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
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
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
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
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
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
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
        orientedBlock(block, state -> cubeWithParticle(name, new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side")), BlockStateHelper.FACING_PROPERTY);
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
                    Direction dir = state.getValue(HORIZONTAL_FACING_PROPERTY);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();
                });
    }
}