package com.maciej916.indreb.datagen.client;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, IndReb.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        singleTexture(ModItems.STONE_DUST.getRegistryName().getPath(),
                new ResourceLocation("item/generated"),"layer0",
                new ResourceLocation(IndReb.MODID, "item/dust/stone"));

        singleTexture(ModItems.DEEPSLATE_DUST.getRegistryName().getPath(),
                new ResourceLocation("item/generated"),"layer0",
                new ResourceLocation(IndReb.MODID, "item/dust/deepslate"));




        // Example generation for a simple textured item:
        //        singleTexture(
        //                Registration.TESTITEM.get().getRegistryName().getPath(),
        //                new ResourceLocation("item/handheld"),
        //                "layer0",
        //                new ResourceLocation(Tutorial.MODID, "item/firstitem"));

//        getBuilder(Registration.TESTITEM.get().getRegistryName().getPath())
//                .parent(getExistingFile(mcLoc("item/handheld")))
//                .texture("layer0", "item/firstitem0")
//                .override().predicate(DISTANCE_PROPERTY, 0).model(createTestModel(0)).end()
//                .override().predicate(DISTANCE_PROPERTY, 1).model(createTestModel(1)).end()
//                .override().predicate(DISTANCE_PROPERTY, 2).model(createTestModel(2)).end()
//                .override().predicate(DISTANCE_PROPERTY, 3).model(createTestModel(3)).end();

//        withExistingParent(ModBlocks.RUBBER_SHEET.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/rubber_sheet"));


        registerPainter();
        registerConstructionFoam();


        withExistingParent(ModBlocks.REINFORCED_GLASS.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/reinforced_glass"));
        withExistingParent(ModBlocks.REINFORCED_STONE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/reinforced_stone"));
        withExistingParent(ModBlocks.REINFORCED_STONE_SLAB.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/reinforced_stone_slab"));
        withExistingParent(ModBlocks.REINFORCED_STONE_STAIRS.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/reinforced_stone_stairs"));

        withExistingParent(ModBlocks.IRON_SCAFFOLDING.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/iron_scaffolding"));
        withExistingParent(ModBlocks.IRON_FENCE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/iron_fence"));

        withExistingParent(ModBlocks.LUMINATOR.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/luminator"));




    }

    protected void registerPainter() {
        singleTexture(ModItems.PAINTER.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter"));
        singleTexture(ModItems.PAINTER_WHITE.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_white"));
        singleTexture(ModItems.PAINTER_RED.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_red"));
        singleTexture(ModItems.PAINTER_ORANGE.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_orange"));
        singleTexture(ModItems.PAINTER_PINK.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_pink"));
        singleTexture(ModItems.PAINTER_YELLOW.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_yellow"));
        singleTexture(ModItems.PAINTER_LIME.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_lime"));
        singleTexture(ModItems.PAINTER_GREEN.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_green"));
        singleTexture(ModItems.PAINTER_LIGHT_BLUE.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_light_blue"));
        singleTexture(ModItems.PAINTER_CYAN.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_cyan"));
        singleTexture(ModItems.PAINTER_BLUE.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_blue"));
        singleTexture(ModItems.PAINTER_MAGENTA.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_magenta"));
        singleTexture(ModItems.PAINTER_PURPLE.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_purple"));
        singleTexture(ModItems.PAINTER_BROWN.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_brown"));
        singleTexture(ModItems.PAINTER_GRAY.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_gray"));
        singleTexture(ModItems.PAINTER_LIGHT_GRAY.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_light_gray"));
        singleTexture(ModItems.PAINTER_BLACK.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(IndReb.MODID, "item/tool/painter/painter_black"));
    }

    protected void registerConstructionFoam() {
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam"));
        withExistingParent(ModBlocks.REINFORCED_CONSTRUCTION_FOAM.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/reinforced_construction_foam"));

        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_white"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_RED.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_red"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_orange"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_pink"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_yellow"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_lime"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_green"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_light_blue"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_cyan"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_blue"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_magenta"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_purple"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_brown"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_gray"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_light_gray"));
        withExistingParent(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/construction_foam_wall_black"));
    }

    private ItemModelBuilder createTestModel(int suffix) {
        return getBuilder("testitem" + suffix).parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/firstitem" + suffix);
    }

}
