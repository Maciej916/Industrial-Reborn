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
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.client.model.generators.BlockStateProvider;

import java.util.function.Function;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, IndReb.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerOres();
        registerMachines();
        registerConstructionFoam();
        registerRubberWood();



        createCubeAll(ModBlocks.REINFORCED_GLASS.getBlock(), "reinforced_glass");
        createCubeAll(ModBlocks.REINFORCED_STONE.getBlock(), "reinforced_stone");
        createCubeAll(ModBlocks.LUMINATOR.getBlock(), "machines/luminator");

        stairsBlock((StairBlock) ModBlocks.REINFORCED_STONE_STAIRS.getBlock(), new ResourceLocation(IndReb.MODID, "block/reinforced_stone"));

        slabBlock((SlabBlock) ModBlocks.REINFORCED_STONE_SLAB.getBlock(),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone"),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone")
        );

        simpleBlock(ModBlocks.IRON_SCAFFOLDING.getBlock(), models().cubeBottomTop(ModBlocks.IRON_SCAFFOLDING.getBlock().getRegistryName().getPath(),
                new ResourceLocation(IndReb.MODID, "block/iron_scaffolding_sides"),
                new ResourceLocation(IndReb.MODID, "block/iron_scaffolding_bottomtop"),
                new ResourceLocation(IndReb.MODID, "block/iron_scaffolding_bottomtop")
        ));

        models().fenceInventory(ModBlocks.IRON_FENCE.getBlock().getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/iron_fence"));
        fenceBlock((FenceBlock) ModBlocks.IRON_FENCE.getBlock(), new ResourceLocation(IndReb.MODID, "block/iron_fence"));

    }

    private void registerOres() {
        createCubeAll(ModBlocks.TIN_ORE.getBlock(), "ore/tin");
        createCubeAll(ModBlocks.DEEPSLATE_TIN_ORE.getBlock(), "ore/deepslate_tin");
    }

    private void registerConstructionFoam() {
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM.getBlock(), "cf/foam");
        createCubeAll(ModBlocks.REINFORCED_CONSTRUCTION_FOAM.getBlock(), "cf/reinforced_foam");

        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.getBlock(), "cf/wall_white");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_RED.getBlock(), "cf/wall_red");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.getBlock(), "cf/wall_orange");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.getBlock(), "cf/wall_pink");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.getBlock(), "cf/wall_yellow");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.getBlock(), "cf/wall_lime");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.getBlock(), "cf/wall_green");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.getBlock(), "cf/wall_light_blue");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.getBlock(), "cf/wall_cyan");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.getBlock(), "cf/wall_blue");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.getBlock(), "cf/wall_magenta");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.getBlock(), "cf/wall_purple");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.getBlock(), "cf/wall_brown");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.getBlock(), "cf/wall_gray");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.getBlock(), "cf/wall_light_gray");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.getBlock(), "cf/wall_black");
    }

    private void registerRubberWood() {
        simpleBlock(ModBlocks.RUBBER_SAPLING.getBlock(), models().cross(ModBlocks.RUBBER_SAPLING.getBlock().getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/rubber_wood/rubber_sapling")));


    }


    private void registerMachines() {
        ResourceLocation txt = new ResourceLocation(IndReb.MODID, "block/generator/generator_side");


        BlockModelBuilder noActive = models().orientableWithBottom("canning_machine", txt, new ResourceLocation(IndReb.MODID, "block/generator/generator_front"), new ResourceLocation(IndReb.MODID, "block/generator/generator_bottom"), new ResourceLocation(IndReb.MODID, "block/generator/generator_top"));

        BlockModelBuilder active = models().orientableWithBottom("canning_machine_active", txt, new ResourceLocation(IndReb.MODID, "block/generator/generator_front_active"), new ResourceLocation(IndReb.MODID, "block/generator/generator_bottom"), new ResourceLocation(IndReb.MODID, "block/generator/generator_top"));

        orientedBlock(ModBlocks.CANNING_MACHINE.getBlock(), state -> {
            if (state.getValue(BlockStateHelper.activeProperty)) {
                return active;
            } else {
                return noActive;
            }
        });

    }

    private void createCubeAll(Block block, String path) {
        simpleBlock(block, models().cubeAll(block.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path)));
    }

//
//    private void registerGeneratorBlock() {
//        ResourceLocation txt = new ResourceLocation(IndReb.MODID, "block/generator");
//        BlockModelBuilder modelFirstblock = models().cube("generator",
//                txt, txt, new ResourceLocation(IndReb.MODID, "block/generator_front"), txt, txt, txt);
//        BlockModelBuilder modelFirstblockPowered = models().cube("generator_powered",
//                txt, txt, new ResourceLocation(IndReb.MODID, "block/generator_powered"), txt, txt, txt);
//        orientedBlock(Registration.GENERATOR.get(), state -> {
//            if (state.getValue(BlockStateProperties.POWERED)) {
//                return modelFirstblockPowered;
//            } else {
//                return modelFirstblock;
//            }
//        });
//    }
//



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