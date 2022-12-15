package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.api.interfaces.item.IPainter;
import com.maciej916.indreb.common.api.item.base.ToolItem;
import com.maciej916.indreb.common.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class Painter extends ToolItem implements IPainter {

    private final MaterialColor color;
    private static final HashMap<MaterialColor, RegistryObject<Block>> COLOR_TO_BLOCK = new HashMap<>() {{
        put(MaterialColor.WOOL, ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE);
        put(MaterialColor.COLOR_RED, ModBlocks.CONSTRUCTION_FOAM_WALL_RED);
        put(MaterialColor.COLOR_ORANGE, ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE);
        put(MaterialColor.COLOR_PINK, ModBlocks.CONSTRUCTION_FOAM_WALL_PINK);
        put(MaterialColor.COLOR_YELLOW, ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW);
        put(MaterialColor.COLOR_LIGHT_GREEN, ModBlocks.CONSTRUCTION_FOAM_WALL_LIME);
        put(MaterialColor.COLOR_GREEN, ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN);
        put(MaterialColor.COLOR_LIGHT_BLUE, ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE);
        put(MaterialColor.COLOR_CYAN, ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN);
        put(MaterialColor.COLOR_BLUE, ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE);
        put(MaterialColor.COLOR_MAGENTA, ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA);
        put(MaterialColor.COLOR_PURPLE, ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE);
        put(MaterialColor.COLOR_BROWN, ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN);
        put(MaterialColor.COLOR_GRAY, ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY);
        put(MaterialColor.COLOR_LIGHT_GRAY, ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY);
        put(MaterialColor.COLOR_BLACK, ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK);
    }};

    public Painter(MaterialColor color) {
        super(32);
        this.color = color;
    }

    public MaterialColor getColor() {
        return color;
    }

    public BlockState getState() {
        return COLOR_TO_BLOCK.get(color).get().defaultBlockState();
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return false;
    }
}
