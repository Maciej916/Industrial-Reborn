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
            return ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_RED)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_RED.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_ORANGE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_PINK)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_YELLOW)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_LIGHT_GREEN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_GREEN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_LIGHT_BLUE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_CYAN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_BLUE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_MAGENTA)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_PURPLE)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_BROWN)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_GRAY)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_LIGHT_GRAY)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.getBlock().defaultBlockState();
        } else if (color.equals(MaterialColor.COLOR_BLACK)) {
            return ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.getBlock().defaultBlockState();
        }

        return ModBlocks.CONSTRUCTION_FOAM_WALL_RED.getBlock().defaultBlockState();
    }

}
