package com.maciej916.indreb.common.world.feature.tree;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

public class RubberTreeBlockStateProvider extends SimpleStateProvider {

    public RubberTreeBlockStateProvider(BlockState state) {
        super(state);
    }

    @Override
    public BlockState getState(RandomSource randomSource, BlockPos blockPos) {

        if (randomSource != null) {
            boolean wet = false;
            boolean dry = false;
            if (randomSource.nextInt(5) == 0) {
                if (randomSource.nextInt(2) == 0) {
                    wet = true;
                } else {
                    dry = true;
                }
                return ModBlocks.RUBBER_LOG.get().defaultBlockState().setValue(BlockStateHelper.wetProperty, wet).setValue(BlockStateHelper.dryProperty, dry);
            }
        }

        return super.getState(randomSource, blockPos);
    }

}
