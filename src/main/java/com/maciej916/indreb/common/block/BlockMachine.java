package com.maciej916.indreb.common.block;

import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.BlockUtil;

public class BlockMachine extends IndRebEntityBlock implements IStateFacing, IStateActive {



    public BlockMachine(int lightOn, int lightOff) {
        super(BlockUtil.BLOCK_MACHINE.lightLevel(state -> state.getValue(BlockStateHelper.activeProperty) ? lightOn : lightOff));
    }

}
