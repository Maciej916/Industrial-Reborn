package com.maciej916.indreb.datagen.client;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.client.model.generators.BlockStateProvider;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, IndReb.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {




//        models().cubeAll(ModBlocks.RUBBER_SHEET.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/rubber_sheet"));


        registerConstructionFoam();

        simpleBlock(ModBlocks.REINFORCED_GLASS, models().cubeAll(ModBlocks.REINFORCED_GLASS.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/reinforced_glass")));
        simpleBlock(ModBlocks.REINFORCED_STONE, models().cubeAll(ModBlocks.REINFORCED_STONE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/reinforced_stone")));
        simpleBlock(ModBlocks.REINFORCED_STONE_SLAB, models().slab(ModBlocks.REINFORCED_STONE_SLAB.getRegistryName().getPath(),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone"),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone"),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone")
        ));
        simpleBlock(ModBlocks.REINFORCED_STONE_STAIRS, models().stairs(ModBlocks.REINFORCED_STONE_STAIRS.getRegistryName().getPath(),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone"),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone"),
                new ResourceLocation(IndReb.MODID, "block/reinforced_stone")
        ));

        simpleBlock(ModBlocks.IRON_SCAFFOLDING, models().cubeBottomTop(ModBlocks.IRON_SCAFFOLDING.getRegistryName().getPath(),
                new ResourceLocation(IndReb.MODID, "block/iron_scaffolding_sides"),
                new ResourceLocation(IndReb.MODID, "block/iron_scaffolding_bottomtop"),
                new ResourceLocation(IndReb.MODID, "block/iron_scaffolding_bottomtop")
        ));

        models().fenceSide(ModBlocks.IRON_FENCE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/iron_fence"));
        models().fencePost(ModBlocks.IRON_FENCE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/iron_fence"));
        simpleBlock(ModBlocks.IRON_FENCE, models().fenceInventory(ModBlocks.IRON_FENCE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/iron_fence")));

        simpleBlock(ModBlocks.LUMINATOR, models().cubeAll(ModBlocks.LUMINATOR.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/machines/luminator")));



    }

    private void registerConstructionFoam() {
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/foam")));
        simpleBlock(ModBlocks.REINFORCED_CONSTRUCTION_FOAM, models().cubeAll(ModBlocks.REINFORCED_CONSTRUCTION_FOAM.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/reinforced_foam")));

        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_white")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_RED, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_RED.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_red")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_orange")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_pink")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_yellow")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_lime")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_green")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_light_blue")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_cyan")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_blue")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_magenta")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_purple")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_brown")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_gray")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_light_gray")));
        simpleBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK, models().cubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/cf/wall_black")));
        
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
//    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
//        getVariantBuilder(block)
//                .forAllStates(state -> {
//                    Direction dir = state.getValue(BlockStateProperties.FACING);
//                    return ConfiguredModel.builder()
//                            .modelFile(modelFunc.apply(state))
//                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
//                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
//                            .build();
//                });
//    }
}