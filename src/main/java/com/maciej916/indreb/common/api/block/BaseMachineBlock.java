package com.maciej916.indreb.common.api.block;

import com.maciej916.indreb.common.api.interfaces.block.IStateActive;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.BlockUtil;

public abstract class BaseMachineBlock extends IndRebEntityBlock implements IStateFacing, IStateActive {

    public BaseMachineBlock(int lightOn, int lightOff) {
        super(BlockUtil.BLOCK_MACHINE.lightLevel(state -> state.getValue(BlockStateHelper.ACTIVE_PROPERTY) ? lightOn : lightOff));
    }


}
