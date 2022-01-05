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

    private boolean init = false;
    private TransformerMode transformerMode;
    private EnergyTier minTier;
    private EnergyTier maxTier;

    public BlockEntityTransformer(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.TRANSFORMER, pWorldPosition, pBlockState);
        BlockTransformer block = (BlockTransformer) getBlock();
        tier = block.getTransformerTier();
        createEnergyStorage(0, tier.getMaxTier().getBasicTransfer(), EnergyType.TRANSFORMER, tier.getMaxTier());

        transformerMode = TransformerMode.STEP_UP;
        initMode();
    }

    public TransformerTier getTransformerTier() {
        return tier;
    }

    public TransformerMode getTransformerMode() {
        return transformerMode;
    }

    @Override
    public boolean showInGui() {
        return false;
    }

    @Override
    public boolean canExtractEnergyDir(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return (this.transformerMode == TransformerMode.STEP_UP) == (facingDirection == side);
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return (this.transformerMode == TransformerMode.STEP_UP) == (facingDirection != side);
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

    public EnergyTier energyExtractTier() {
        return maxTier;
    }

    public EnergyTier energyReceiveTier() {
        return minTier;
    }

    private void initMode() {
        if (transformerMode == TransformerMode.STEP_UP) {
            this.minTier = tier.getMinTier();
            this.maxTier = tier.getMaxTier();
        } else {
            this.minTier = tier.getMaxTier();
            this.maxTier = tier.getMinTier();
        }
        this.init = true;
    }

    public void updateMode() {
        if (transformerMode == TransformerMode.STEP_UP) {
            this.minTier = tier.getMaxTier();
            this.maxTier = tier.getMinTier();
            this.transformerMode = TransformerMode.STEP_DOWN;
        } else {
            this.minTier = tier.getMinTier();
            this.maxTier = tier.getMaxTier();
            this.transformerMode = TransformerMode.STEP_UP;
        }

        this.updateBlockState();
    }

    public Runnable changeMode() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketTransformerMode(getBlockPos()));
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.transformerMode = TransformerMode.getMode(tag.getInt("transformerMode"));
        if (!init) initMode();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putInt("transformerMode", transformerMode.getMode());
        return super.save(tag);
    }
}
