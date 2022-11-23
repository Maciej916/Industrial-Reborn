package com.maciej916.indreb.common.api.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public interface IMultiBlockType {

    /**
     * Get the main controller block of the multiblock
     */
    Block getControllerBlock();

    /**
     * Return the anchor position for the given formed multiblock. This is the corner at the
     * bottom/lower/left position.
     * WARNING! Implementations of this function can not assume that the given block is
     * actually a valid part of the multiblock!
     * @return anchor position or null in case this is not a valid (formed) multiblock
     */
    @Nullable
    BlockPos getBottomLowerLeft(Level level, BlockPos pos, BlockState state);

    /**
     * Return a given block in the world to its unformed state.
     * This function can assume the given position refers to a valid multiblock part
     */
    void unformBlock(Level level, BlockPos pos);

    /**
     * Convert a given block in the world to its formed state for the given
     * relative position in the multiblock.
     * This function can assume the given position refers to a valid multiblock part
     */
    void formBlock(Level level, BlockPos pos, int dx, int dy, int dz);

    /**
     * Return true if the given position is the bottom/lower/left position
     * of an unformed multiblock. i.e. it is possible to form a multiblock here
     */
    boolean isValidUnformedMultiBlock(Level level, BlockPos pos);

    /**
     * Return true if the given position is the bottom/lower/left position
     * of a formed multiblock
     * Skip block pos when breaking block for still valid structure
     */
    boolean isValidFormedMultiBlock(Level level, BlockPos pos, @Nullable BlockPos skipPos);

    /**
     * The dimension of this multiblock type on the Y axis
     */
    int getWidth();

    /**
     * The dimension of this multiblock type on the Y axis
     */
    int getHeight();


    /**
    * The dimension of this mulitblock type on the Z axis
    */
    int getDepth();

}
