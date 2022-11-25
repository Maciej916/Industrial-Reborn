package com.maciej916.indreb.common.block.impl.machines.simple.simple_compressor;

import com.maciej916.indreb.common.api.block.BaseMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockSimpleCompressor extends BaseMachineBlock {

    public BlockSimpleCompressor() {
        super(12, 0);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntitySimpleCompressor(pos, state);
    }

}
