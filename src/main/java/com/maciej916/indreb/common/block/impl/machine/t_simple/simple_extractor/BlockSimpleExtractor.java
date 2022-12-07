package com.maciej916.indreb.common.block.impl.machine.t_simple.simple_extractor;

import com.maciej916.indreb.common.api.block.BaseMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockSimpleExtractor extends BaseMachineBlock {

    public BlockSimpleExtractor() {
        super(12, 0);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntitySimpleExtractor(pos, state);
    }

}
