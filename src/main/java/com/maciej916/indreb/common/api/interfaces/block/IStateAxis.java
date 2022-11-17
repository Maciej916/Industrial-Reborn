package com.maciej916.indreb.common.api.interfaces.block;

import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public interface IStateAxis {

    default Direction.Axis getAxis(BlockState state) {
        return state.getValue(BlockStateHelper.AXIS_PROPERTY);
    }

}
