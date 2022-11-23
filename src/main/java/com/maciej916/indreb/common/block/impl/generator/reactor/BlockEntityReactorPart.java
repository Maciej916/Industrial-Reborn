package com.maciej916.indreb.common.block.impl.generator.reactor;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityReactorPart extends IndRebBlockEntity {

    public BlockEntityReactorPart(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.REACTOR_PART.get(), pos, blockState);
    }
}
