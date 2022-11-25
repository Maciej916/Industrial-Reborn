package com.maciej916.indreb.common.block.impl.cable;

import com.maciej916.indreb.common.api.block.BlockVoxel;
import com.maciej916.indreb.common.api.interfaces.block.IBlockCable;
import com.maciej916.indreb.common.api.tier.CableTier;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.Constants;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;
import java.util.List;

public class BlockCable extends BlockVoxel implements SimpleWaterloggedBlock, IBlockCable {

    private final CableTier cableTier;

    public BlockCable(float apothem, CableTier cableTier) {
        super(cableTier.getProperties(), apothem);
        this.cableTier = cableTier;
        WrenchHelper.registerAction(this).add(WrenchHelper.dropAction());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(cableTier.getEnergyTier().getLang().getTranslationKey()).withStyle(cableTier.getEnergyTier().getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.TRANSFER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(cableTier.getEnergyTier().getBasicTransfer(), Screen.hasShiftDown())).withStyle(cableTier.getEnergyTier().getColor())
        ));

        if (!cableTier.isInsulated()) {
            pTooltip.add(TextComponentUtil.build(
                    Component.translatable(EnumLang.CABLE_UNISOLATED.getTranslationKey()).withStyle(ChatFormatting.RED)
            ));
        }
    }

    public CableTier getCableTier() {
        return cableTier;
    }

    @Override
    protected boolean canConnect(LevelAccessor world, BlockPos pos, Direction direction) {
        BlockEntity be = world.getBlockEntity(pos);
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() instanceof IBlockCable bc) {
            return bc.getCableTier().getEnergyTier() == cableTier.getEnergyTier();
        }

        if (be == null) return false;
        return CapabilityUtil.getCapabilityHelper(be, ModCapabilities.ENERGY, direction).getIfPresentElse(e -> e.canExtractEnergy(direction.getOpposite()) || e.canReceiveEnergy(direction.getOpposite()), false);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (level.isClientSide()) return;

        if (oldState.getBlock() != state.getBlock()) {
            CapabilityUtil.getCapabilityHelper(level, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.getNetworks().onPlaced(pos, state, cableTier.getEnergyTier()));
        }

        super.onPlace(state, level, pos, oldState, isMoving);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (level.isClientSide()) return;

        if (newState.getBlock() != blockState.getBlock()) {
            CapabilityUtil.getCapabilityHelper(level, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.getNetworks().onRemove(blockPos));
        }

        super.onRemove(blockState, level, blockPos, newState, isMoving);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (pLevel.isClientSide()) return;

        CapabilityUtil.getCapabilityHelper(pLevel, ModCapabilities.ENERGY_CORE).ifPresent(e -> e.getNetworks().neighborChanged(pPos, pFromPos));

        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
    }

    @Override
    @Deprecated
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateHelper.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    @Deprecated
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
        if (state.getValue(BlockStateHelper.WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        for (Direction direction : Constants.DIRECTIONS) {
            boolean valid = canConnect(level, pos.relative(direction), direction);
            state = state.setValue(FACING_TO_PROPERTY_MAP.get(direction), valid);
        }

        return state;
    }

}
