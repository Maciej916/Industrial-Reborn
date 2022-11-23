package com.maciej916.indreb.common.api.block;

import com.maciej916.indreb.common.api.blockentity.interfaces.IIndRebBlockEntity;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.tag.ModTagsItem;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public abstract class IndRebBlock extends Block {

    public IndRebBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(BlockStateHelper.getDefaultState(this, stateDefinition.any()));
    }

    public BlockState setStateForPlacement(BlockPlaceContext pContext, BlockState blockState) {
        return blockState;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return BlockStateHelper.getStateForPlacement(this, setStateForPlacement(pContext, this.defaultBlockState()), pContext);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        BlockStateHelper.fillBlockStateContainer(this, pBuilder);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return BlockStateHelper.rotate(state, rot);
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return BlockStateHelper.mirror(pState, pMirror);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        if (WrenchHelper.hasAction(this) && stack.is(ModTagsItem.WRENCHES)) {
            return InteractionResult.PASS;
        }

        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof IIndRebBlockEntity indEntity && entity instanceof MenuProvider menuProvider && indEntity.hasMenu()) {
            if (!level.isClientSide()) {
                NetworkHooks.openScreen(((ServerPlayer)player), menuProvider, pos);
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        return super.use(state, level, pos, player, hand, trace);
    }

    @Override
    public void onPlace(BlockState pState, Level level, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {

        BlockEntity be = level.getBlockEntity(pPos);
        if (be instanceof IIndRebBlockEntity indRebBlockEntity) {
            indRebBlockEntity.onPlace(level.isClientSide());
        }

        if (!level.isClientSide()) {
            if (be instanceof IBlockEntityEnergy) {
                CapabilityUtil.getCapabilityHelper(level, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.addEnergyBlock(pPos));
            }
        }

        super.onPlace(pState, level, pPos, pOldState, pIsMoving);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (blockState.getBlock() != newState.getBlock() && blockState.hasBlockEntity()) {
            BlockEntity be = level.getBlockEntity(blockPos);
            if (be instanceof IIndRebBlockEntity indRebBlockEntity) {
                indRebBlockEntity.onBreak();
            }

            if (!level.isClientSide()) {
                if (be instanceof IBlockEntityEnergy) {
                    CapabilityUtil.getCapabilityHelper(level, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.removeEnergyBlock(blockPos));
                }
            }
        }

        super.onRemove(blockState, level, blockPos, newState, isMoving);
    }

    @Override
    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
//        if (world.isClientSide()) return;
//        if (state.hasBlockEntity()) {
//            BlockEntity be = world.getBlockEntity(pos);
//            if (be instanceof IEnergyBlock) {
//                CapabilityUtil.getCapabilityHelper(world, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.removeEnergyBlock(pos));
//            }
//        }

        super.onBlockExploded(state, world, pos, explosion);
    }
}
