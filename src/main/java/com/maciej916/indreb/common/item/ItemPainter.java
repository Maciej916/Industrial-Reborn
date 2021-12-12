package com.maciej916.indreb.common.item;

import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

public class ItemPainter extends ItemTool {

    private final MaterialColor color;

    public ItemPainter(MaterialColor color) {
        super(32);
        this.color = color;
    }

    public MaterialColor getColor() {
        return color;
    }

    public BlockState getState() {
        if (color.equals(MaterialColor.WOOL)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_RED)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_RED.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_ORANGE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_PINK)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_YELLOW)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_LIGHT_GREEN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_GREEN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_LIGHT_BLUE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_CYAN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_BLUE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_MAGENTA)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_PURPLE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_BROWN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_GRAY)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_LIGHT_GRAY)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_BLACK)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.defaultBlockState();
        }

        return ModBlocks.CONSTRUCTION_FOAM_WALL_RED.defaultBlockState();
    }

}
