package com.maciej916.indreb.common.api.interfaces.block;

import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public interface IStateRubberLog {

    default boolean isWet(BlockState state) {
        return state.getValue(BlockStateHelper.WET_PROPERTY);
    }

    default boolean isDry(BlockState state) {
        return state.getValue(BlockStateHelper.DRY_PROPERTY);
    }

    default BlockState setWet(@Nonnull BlockState state, boolean active) {
        return state.setValue(BlockStateHelper.WET_PROPERTY, active);
    }

    default BlockState setDry(@Nonnull BlockState state, boolean active) {
        return state.setValue(BlockStateHelper.DRY_PROPERTY, active);
    }

}
