package com.maciej916.indreb.common.block;

import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.interfaces.block.IHasMenu;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.registries.ModTags;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.IReverseTag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

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
        List<ResourceLocation> itemTags = ForgeRegistries.ITEMS.tags().getReverseTag(player.getItemInHand(player.getUsedItemHand()).getItem())
                .map(IReverseTag::getTagKeys).map(tagKeyStream -> tagKeyStream.map(TagKey::location).toList()).orElse(new ArrayList<>());

        if (WrenchHelper.hasAction(this) && itemTags.contains(ModTags.WRENCH_RES)) {
            return InteractionResult.PASS;
        }

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (this instanceof IHasMenu hasContainer) {
            if (!level.isClientSide) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.translatable(getDescriptionId());
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                        return hasContainer.getMenu(windowId, level, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openScreen((ServerPlayer) player, containerProvider, blockEntity.getBlockPos());
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
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (blockState.getBlock() != newState.getBlock() && blockState.hasBlockEntity()) {
            BlockEntity be = level.getBlockEntity(blockPos);
            if (be instanceof IndRebBlockEntity ibe) {
                if (level.isClientSide()) {
                    ibe.onBreakClient();
                } else {
                    ibe.onBreakServer();
                }
            }

            if (be instanceof IEnergyBlock) {
                CapabilityUtil.getCapabilityHelper(level, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.removeEnergyBlock(blockPos));
            }
        }

        super.onRemove(blockState, level, blockPos, newState, isMoving);
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
