package com.maciej916.indreb.common.block;

import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.interfaces.block.IHasContainer;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.registries.ModTags;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
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

public class IndRebBlock extends Block {

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

        if (WrenchHelper.hasAction(this) && player.getItemInHand(player.getUsedItemHand()).getItem().getTags().contains(ModTags.WRENCH_RES)) {
            return InteractionResult.PASS;
        }

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (this instanceof IHasContainer hasContainer) {
            if (!level.isClientSide) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return new TranslatableComponent(getDescriptionId());
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                        return hasContainer.getContainer(windowId, level, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayer) player, containerProvider, blockEntity.getBlockPos());
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {

        BlockEntity be = pLevel.getBlockEntity(pPos);
        if (be instanceof IndRebBlockEntity ibe) {
            ibe.onPlace();
        }

        if (be instanceof IEnergyBlock) {
            CapabilityUtil.getCapabilityHelper(pLevel, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.addEnergyBlock(pPos));
        }

        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }



    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock() && pState.hasBlockEntity()) {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof IndRebBlockEntity ibe) {
                ibe.onBreak();
            }

            if (be instanceof IEnergyBlock) {
                CapabilityUtil.getCapabilityHelper(pLevel, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.removeEnergyBlock(pPos));
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
        if (world.isClientSide()) return;
        if (state.hasBlockEntity()) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof IEnergyBlock) {
                CapabilityUtil.getCapabilityHelper(world, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.removeEnergyBlock(pos));
            }
        }

        super.onBlockExploded(state, world, pos, explosion);
    }

}
