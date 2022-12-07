package com.maciej916.indreb.common.block.impl.misc.pattern_storage;

import com.maciej916.indreb.common.api.block.IndRebEntityBlock;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockPatternStorage extends IndRebEntityBlock implements IStateFacing {

    public BlockPatternStorage() {
        super(BlockUtil.BLOCK_MACHINE_NOT_ACTIVE);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityPatternStorage(pos, state);
    }

}
