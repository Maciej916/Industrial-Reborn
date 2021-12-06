package com.maciej916.indreb.common.interfaces.block;

import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public interface IStateRubberLog {

    default boolean isWet(BlockState state) {
        return state.getValue(BlockStateHelper.wetProperty);
    }

    default boolean isDry(BlockState state) {
        return state.getValue(BlockStateHelper.dryProperty);
    }

    default BlockState setWet(@Nonnull BlockState state, boolean active) {
        return state.setValue(BlockStateHelper.wetProperty, active);
    }

    default BlockState setDry(@Nonnull BlockState state, boolean active) {
        return state.setValue(BlockStateHelper.dryProperty, active);
    }

}
