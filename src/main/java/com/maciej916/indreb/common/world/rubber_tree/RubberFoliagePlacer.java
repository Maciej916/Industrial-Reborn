package com.maciej916.indreb.common.world.rubber_tree;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;

import java.util.function.BiConsumer;

public class RubberFoliagePlacer extends BlobFoliagePlacer {
    public RubberFoliagePlacer(IntProvider provider, IntProvider provider1, int i) {
        super(provider, provider1, i);
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, TreeConfiguration config, int pMaxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        for(int i = offset + 2; i >= offset - foliageHeight - 1; --i) {
            int j = Math.max(foliageRadius + attachment.radiusOffset() - 1 - i / 2, 0);
            if (i >= 1) j = 0;
            this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), j, i, attachment.doubleTrunk());
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return !(localY >= 0 && localZ== 0 && localX== 0) && localX == range && localZ == range && (random.nextInt(2) == 0 || localY == 0);
    }
}
