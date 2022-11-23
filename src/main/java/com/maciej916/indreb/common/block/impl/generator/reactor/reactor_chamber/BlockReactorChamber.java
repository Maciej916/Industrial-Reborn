package com.maciej916.indreb.common.block.impl.generator.reactor.reactor_chamber;

import com.maciej916.indreb.common.api.block.IndRebEntityBlock;
import com.maciej916.indreb.common.api.multiblock.MultiBlockTools;
import com.maciej916.indreb.common.block.impl.generator.reactor.BlockEntityReactorPart;
import com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor.BlockNuclearReactor;
import com.maciej916.indreb.common.multiblock.reactor.IReactorPart;
import com.maciej916.indreb.common.multiblock.reactor.ReactorMultiBlock;
import com.maciej916.indreb.common.multiblock.reactor.ReactorPartIndex;
import com.maciej916.indreb.common.tag.ModTagsBlock;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.BlockUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BlockReactorChamber extends IndRebEntityBlock implements IReactorPart {

    public BlockReactorChamber() {
        super(BlockUtil.BLOCK_MACHINE_NOT_ACTIVE);
        WrenchHelper.registerAction(this).add(WrenchHelper.multiblockToggleAction(ReactorMultiBlock.INSTANCE));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityReactorPart(pos, state);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
//        if (oldState.getBlock() != oldState.getBlock()) {
//            ReactorMultiBlock.formMultiBlock(level, pos, state);
//        }

        super.onPlace(state, level, pos, oldState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {

        if (state.is(ModTagsBlock.REACTOR_PART) && state.getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED) {
            BlockPos controllerPos = BlockNuclearReactor.getControllerPos(level, pos);
            if (controllerPos != null) {
                BlockState controllerState = level.getBlockState(controllerPos);
                return controllerState.getBlock().use(controllerState, level, controllerPos, player, hand, trace);
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState oldState, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (!level.isClientSide()) {
            if (oldState.getBlock() != newState.getBlock()) {
                MultiBlockTools.breakMultiblock(ReactorMultiBlock.INSTANCE, level, blockPos, oldState, newState);
            }
        }

        super.onRemove(oldState, level, blockPos, newState, isMoving);
    }

}
