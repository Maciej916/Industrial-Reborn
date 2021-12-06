package com.maciej916.indreb.common.block;

import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class IndRebEntityBlock extends IndRebBlock implements EntityBlock {
    public IndRebEntityBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (level1, pos, state1, tile) -> {
            if (tile instanceof IndRebBlockEntity indRebTile) {
                if (level.isClientSide()) {
                    indRebTile.tickClient(state1);
                } else {
                    indRebTile.tickServer(state1);
                }
            }
        };
    }

}

