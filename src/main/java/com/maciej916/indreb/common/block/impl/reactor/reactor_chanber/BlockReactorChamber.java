package com.maciej916.indreb.common.block.impl.reactor.reactor_chanber;

import com.maciej916.indreb.common.block.IndRebEntityBlock;
import com.maciej916.indreb.common.block.impl.reactor.nuclear_reactor.BlockNuclearReactor;
import com.maciej916.indreb.common.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class BlockReactorChamber extends IndRebEntityBlock {

    public BlockReactorChamber() {
        super(BlockUtil.BLOCK_MACHINE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityReactorChamber(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        BlockEntityReactorChamber chamberEntity = (BlockEntityReactorChamber) level.getBlockEntity(pos);
        if (chamberEntity != null && chamberEntity.getEntityNuclearReactor() != null) {
            BlockNuclearReactor blockNuclearReactor = (BlockNuclearReactor) chamberEntity.getEntityNuclearReactor().getBlock();
            return blockNuclearReactor.use(state, level, chamberEntity.getEntityNuclearReactor().getBlockPos(), player, hand, trace);
        }

        return super.use(state, level, pos, player, hand, trace);
    }
}
