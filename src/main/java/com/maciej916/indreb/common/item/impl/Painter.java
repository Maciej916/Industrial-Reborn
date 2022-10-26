package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.item.base.ToolItem;
import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

public class Painter extends ToolItem {

    private final MaterialColor color;

    public Painter(MaterialColor color) {
        super(32);
        this.color = color;
    }

    public MaterialColor getColor() {
        return color;
    }

    public BlockState getState() {

        // TODO
        // Redo this ugly mess

        if (color.equals(MaterialColor.WOOL)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_RED)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_RED.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_ORANGE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_PINK)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_YELLOW)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_LIGHT_GREEN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_GREEN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_LIGHT_BLUE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_CYAN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_BLUE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_MAGENTA)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_PURPLE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_BROWN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_GRAY)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_LIGHT_GRAY)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.get().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_BLACK)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.get().defaultBlockState();
        }

        return ModBlocks.CONSTRUCTION_FOAM_WALL_RED.get().defaultBlockState();
    }

}
