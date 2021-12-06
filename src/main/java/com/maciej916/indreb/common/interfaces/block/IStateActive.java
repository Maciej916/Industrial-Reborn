package com.maciej916.indreb.common.interfaces.block;

import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public interface IStateActive {

    default boolean isActive(BlockState state) {
        return state.getValue(BlockStateHelper.activeProperty);
    }

    default BlockState setActive(@Nonnull BlockState state, boolean active) {
        return state.setValue(BlockStateHelper.activeProperty, active);
    }

}
