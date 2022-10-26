package com.maciej916.indreb.common.world.feature.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import java.util.function.BiConsumer;

public class RubberFoliagePlacer extends BlobFoliagePlacer {
    public RubberFoliagePlacer(IntProvider p_161356_, IntProvider p_161357_, int p_161358_) {
        super(p_161356_, p_161357_, p_161358_);
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliagePlacer.FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        for(int i = pOffset + 2; i >= pOffset - pFoliageHeight - 1; --i) {
            int j = Math.max(pFoliageRadius + pAttachment.radiusOffset() - 1 - i / 2, 0);
            if (i >= 1) j = 0;
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos(), j, i, pAttachment.doubleTrunk());
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return !(pLocalY >= 0 && pLocalZ== 0 && pLocalX== 0) && pLocalX == pRange && pLocalZ == pRange && (pRandom.nextInt(2) == 0 || pLocalY == 0);
    }
}
