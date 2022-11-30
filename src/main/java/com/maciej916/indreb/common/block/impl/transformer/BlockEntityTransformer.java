package com.maciej916.indreb.common.block.impl.transformer;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityTransformer;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.TransformerMode;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.api.tier.TransformerTier;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketTransformerMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityTransformer extends IndRebBlockEntity implements IBlockEntityEnergy, IBlockEntityTransformer {

    public static final int SYNC_DATA_SLOTS = 1;
    protected final ContainerData data;

    private final TransformerTier transformerTier;
    private TransformerMode transformerMode = TransformerMode.STEP_UP;

    public BlockEntityTransformer(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TRANSFORMER.get(), pos, blockState);

        BlockTransformer block = (BlockTransformer) getBlock();
        this.transformerTier = block.getTransformerTier();
        EnergyTier energyTier = transformerTier.getMaxTier();

        createEnergyStorage(0, transformerTier.getMaxTier().getBasicTransfer(), EnergyType.TRANSFORMER, energyTier);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlockEntityTransformer.this.getTransformerMode().getModeFromId();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlockEntityTransformer.this.setTransformerMode(TransformerMode.getModeFromId(index));
                }
            }

            @Override
            public int getCount() {
                return SYNC_DATA_SLOTS;
            }
        };
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuTransformer(this, containerId, playerInventory, player, data);
    }

    @Override
    public boolean canExtractEnergyCustom(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return (transformerMode == TransformerMode.STEP_UP) == (facingDirection == side);
    }

    @Override
    public boolean canReceiveEnergyCustom(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return (transformerMode == TransformerMode.STEP_UP) == (facingDirection != side);
    }

    @Override
    public boolean showBarInGui() {
        return false;
    }

    @Override
    public int energyExtractTickCustom() {
        return energyExtractTier().getBasicTransfer();
    }

    @Override
    public int energyReceiveTickCustom() {
        return energyReceiveTier().getBasicTransfer();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.transformerMode = TransformerMode.getModeFromId(tag.getInt("transformerMode"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("transformerMode", transformerMode.getModeFromId());
    }

    @Override
    public EnergyTier energyExtractTier() {
        return (transformerMode == TransformerMode.STEP_UP ? transformerTier.getMaxTier() : transformerTier.getMinTier());
    }

    @Override
    public EnergyTier energyReceiveTier() {
        return (transformerMode == TransformerMode.STEP_UP ? transformerTier.getMinTier() : transformerTier.getMaxTier());
    }

    public TransformerTier getTransformerTier() {
        return transformerTier;
    }

    public TransformerMode getTransformerMode() {
        return transformerMode;
    }

    public void setTransformerMode(TransformerMode transformerMode) {
        this.transformerMode = transformerMode;
    }

    public Runnable changeModeClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketTransformerMode(getBlockPos()));
    }

    public void updateModeServer() {
        switch (transformerMode) {
            case STEP_UP -> transformerMode = TransformerMode.STEP_DOWN;
            case STEP_DOWN -> transformerMode = TransformerMode.STEP_UP;
        }

        setBlockUpdated();
    }

    @Override
    public boolean showEnergyInfoProbe() {
        return false;
    }
}
