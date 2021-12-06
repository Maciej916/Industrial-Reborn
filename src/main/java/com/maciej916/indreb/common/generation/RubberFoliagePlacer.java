package com.maciej916.indreb.common.generation;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import java.util.Random;
import java.util.function.BiConsumer;

public class RubberFoliagePlacer extends BlobFoliagePlacer {
    public RubberFoliagePlacer(IntProvider p_161356_, IntProvider p_161357_, int p_161358_) {
        super(p_161356_, p_161357_, p_161358_);
    }

    @Override
    protected void createFoliage(LevelSimulatedReader p_161360_, BiConsumer<BlockPos, BlockState> p_161361_, Random p_161362_, TreeConfiguration p_161363_, int p_161364_, FoliagePlacer.FoliageAttachment p_161365_, int p_161366_, int p_161367_, int p_161368_) {
        for(int i = p_161368_ + 2; i >= p_161368_ - p_161366_ - 1; --i) {
            int j = Math.max(p_161367_ + p_161365_.radiusOffset() - 1 - i / 2, 0);
            if (i  >= 1) j = 0;
            this.placeLeavesRow(p_161360_, p_161361_, p_161362_, p_161363_, p_161365_.pos(), j, i, p_161365_.doubleTrunk());
        }
    }

    @Override
    protected boolean shouldSkipLocation(Random pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return !(pLocalY >= 0 && pLocalZ== 0 && pLocalX== 0) && pLocalX == pRange && pLocalZ == pRange && (pRandom.nextInt(2) == 0 || pLocalY == 0);
    }
}
