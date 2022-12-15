package com.maciej916.indreb.common.world.rubber_tree;

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
    public BlockState getState(RandomSource random, BlockPos blockPos) {
        BlockState thisState = super.getState(random, blockPos);

        if (random != null) {
            boolean wet = false;
            boolean dry = false;
            if (random.nextInt(5) == 0) {
                if (random.nextInt(2) == 0) {
                    wet = true;
                } else {
                    dry = true;
                }
                return thisState.setValue(BlockStateHelper.WET_PROPERTY, wet).setValue(BlockStateHelper.DRY_PROPERTY, dry);
            }
        }

        return thisState;
    }

}
