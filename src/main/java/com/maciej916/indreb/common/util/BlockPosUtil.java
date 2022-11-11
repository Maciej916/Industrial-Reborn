package com.maciej916.indreb.common.util;

import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.function.Predicate;

public class BlockPosUtil {

    public static ArrayList<BlockPos> findAllMatch(BlockPos blockPos, int width, int height, Predicate<BlockPos> predicate) {
        ArrayList<BlockPos> found = new ArrayList<>();

        for (BlockPos blockpos : BlockPos.withinManhattan(blockPos, width, height, width)) {
            if (predicate.test(blockpos)) {
                found.add(blockpos);
            }
        }

        return found;
    }

}
