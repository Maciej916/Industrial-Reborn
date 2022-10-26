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
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.client.model.generators.BlockStateProvider;
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
        registerTransformers();
        registerChargePad();
        registerConstructionFoam();
        registerRubberWood();
        registerSimple();
        registerExplosives();



        createCubeAll(ModBlocks.REINFORCED_GLASS, "reinforced_glass");
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
        ));

        models().fenceInventory(ModBlocks.IRON_FENCE.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/iron_fence"));
        fenceBlock((FenceBlock) ModBlocks.IRON_FENCE.get(), new ResourceLocation(IndReb.MODID, "block/iron_fence"));

    }

    private void registerOres() {
        createCubeAll(ModBlocks.TIN_ORE, "ore/tin");
        createCubeAll(ModBlocks.DEEPSLATE_TIN_ORE, "ore/deepslate_tin");
        createCubeAll(ModBlocks.LEAD_ORE, "ore/lead");
        createCubeAll(ModBlocks.DEEPSLATE_LEAD_ORE, "ore/deepslate_lead");
        createCubeAll(ModBlocks.DEEPSLATE_URANIUM_ORE, "ore/deepslate_uranium");
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
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM, "cf/foam");
        createCubeAll(ModBlocks.REINFORCED_CONSTRUCTION_FOAM, "cf/reinforced_foam");

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
        simpleBlock(ModBlocks.RUBBER_SAPLING.get(), models().cross(ModBlocks.RUBBER_SAPLING.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/rubber_wood/rubber_sapling")));


    }

    private void registerSimple() {
        createCubeAll(ModBlocks.BASIC_MACHINE_CASING, "basic_machine_casing");
        createCubeAll(ModBlocks.ADVANCED_MACHINE_CASING, "advanced_machine_casing");

    }

    private void registerGenerators() {


        createWithSidesActive(ModBlocks.SEMIFLUID_GENERATOR.get(), "generator", "semifluid_generator");
    }

    private void registerMachines() {




        createSimple(ModBlocks.CANNING_MACHINE.get(), "machines", "canning_machine");
        createWithTopActive(ModBlocks.RECYCLER.get(), "machines", "recycler");
        createWithSidesActive(ModBlocks.FLUID_ENRICHER.get(), "machines", "fluid_enricher");
        createWithSidesActive(ModBlocks.FERMENTER.get(), "machines", "fermenter");
    }

    private void registerExplosives() {
        simpleBlock(ModBlocks.INDUSTRIAL_TNT.get(), models().cubeBottomTop(ModBlocks.INDUSTRIAL_TNT.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/explosive/industrial_tnt_side"),
                new ResourceLocation(IndReb.MODID, "block/explosive/industrial_tnt_bottom"),
                new ResourceLocation(IndReb.MODID, "block/explosive/industrial_tnt_top")
        ));
        simpleBlock(ModBlocks.NUKE.get(), models().cubeBottomTop(ModBlocks.NUKE.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/explosive/nuke_side"),
                new ResourceLocation(IndReb.MODID, "block/explosive/nuke_bottom"),
                new ResourceLocation(IndReb.MODID, "block/explosive/nuke_top")
        ));


    }













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