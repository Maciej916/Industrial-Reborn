package com.maciej916.indreb.common.api.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MultiBlockTools {

    public static boolean breakMultiblock(IMultiBlockType type, Level level, BlockPos blockPos, BlockState oldState, BlockState newState) {
        BlockPos bottomLeft = type.getBottomLowerLeft(level, blockPos, oldState);
        if (bottomLeft != null) {
            if (type.isValidFormedMultiBlock(level, bottomLeft, blockPos)) {
                for (int dx = 0; dx < type.getWidth(); dx++) {
                    for (int dy = 0; dy < type.getHeight(); dy++) {
                        for (int dz = 0; dz < type.getDepth(); dz++) {
                            BlockPos p = bottomLeft.offset(dx, dy, dz);
                            type.unformBlock(level, p);
                        }
                    }
                }
                return true;
            }
        }

        return false;
    }

    public static boolean formMultiblock(IMultiBlockType type, Level level, BlockPos pos) {
        for (int dx = -type.getWidth()+1 ; dx <= 0 ; dx++) {
            for (int dy = -type.getHeight()+1 ; dy <= 0 ; dy++) {
                for (int dz = -type.getDepth()+1 ; dz <= 0 ; dz++) {
                    BlockPos p = pos.offset(dx, dy, dz);
                    if (type.isValidUnformedMultiBlock(level, p)) {
                        createMultiblock(type, level, p);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void createMultiblock(IMultiBlockType type, Level level, BlockPos posBottomLeft) {
        for (int dx = 0 ; dx < type.getWidth() ; dx++) {
            for (int dy = 0 ; dy < type.getHeight() ; dy++) {
                for (int dz = 0 ; dz < type.getDepth() ; dz++) {
                    type.formBlock(level, posBottomLeft.offset(dx, dy, dz), dx, dy, dz);
                }
            }
        }
    }

}
