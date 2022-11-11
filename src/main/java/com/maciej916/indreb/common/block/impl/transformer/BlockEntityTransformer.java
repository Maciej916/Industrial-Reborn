package com.maciej916.indreb.common.block.impl.transformer;

import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.TransformerMode;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketTransformerMode;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.tier.TransformerTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityTransformer extends IndRebBlockEntity implements IEnergyBlock {

    private final TransformerTier tier;
    private TransformerMode transformerMode = TransformerMode.STEP_UP;

    public BlockEntityTransformer(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.TRANSFORMER.get(), pWorldPosition, pBlockState);

        BlockTransformer block = (BlockTransformer) getBlock();
        tier = block.getTransformerTier();
        createEnergyStorage(0, tier.getMaxTier().getBasicTransfer(), EnergyType.TRANSFORMER, tier.getMaxTier());
    }

    @Override
    public void tickWork(BlockState state) {
        super.tickWork(state);
    }

    @Override
    public boolean showBarInGui() {
        return false;
    }

    @Override
    public boolean canExtractEnergyDir(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return (transformerMode == TransformerMode.STEP_UP) == (facingDirection == side);
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return (transformerMode == TransformerMode.STEP_UP) == (facingDirection != side);
    }

    @Override
    public boolean showVertical() {
        return false;
    }

    @Override
    public int customEnergyExtractTick() {
        return energyExtractTier().getBasicTransfer();
    }

    @Override
    public int customEnergyReceiveTick() {
        return energyReceiveTier().getBasicTransfer();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.transformerMode = TransformerMode.getModeFromId(tag.getInt("transformerMode"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putInt("transformerMode", transformerMode.getModeFromId());
        return super.save(tag);
    }

    public Runnable changeMode() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketTransformerMode(getBlockPos()));
    }

    public void updateMode() {
        switch (transformerMode) {
            case STEP_UP -> transformerMode = TransformerMode.STEP_DOWN;
            case STEP_DOWN -> transformerMode = TransformerMode.STEP_UP;
        }

        updateBlockState();
    }

    public EnergyTier energyExtractTier() {
        return (transformerMode == TransformerMode.STEP_UP ? tier.getMaxTier() : tier.getMinTier());
    }

    public EnergyTier energyReceiveTier() {
        return (transformerMode == TransformerMode.STEP_UP ? tier.getMinTier() : tier.getMaxTier());
    }

    public TransformerTier getTransformerTier() {
        return tier;
    }

    public TransformerMode getTransformerMode() {
        return transformerMode;
    }
}
