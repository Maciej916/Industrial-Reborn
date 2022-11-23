package com.maciej916.indreb.common.multiblock.reactor;

import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public interface IReactorPart {

    default BlockState setState(@Nonnull BlockState state, ReactorPartIndex index) {
        return state.setValue(BlockStateHelper.REACTOR_PART, index);
    }

}
