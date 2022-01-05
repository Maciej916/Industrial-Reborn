package com.maciej916.indreb.common.block.impl.luminator;

import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityLuminator extends IndRebBlockEntity implements IEnergyBlock {

    private boolean active = false;

    public BlockEntityLuminator(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.LUMINATOR, pWorldPosition, pBlockState);
        createEnergyStorage(0, 1, EnergyType.RECEIVE, EnergyTier.ULTRA);
    }

    @Override
    public void tickOperate(BlockState state) {
        active = false;
        getEnergyStorage().updateConsumed(0);

        if (getEnergyStorage().energyStored() == 1) {
            active = true;
            getEnergyStorage().consumeEnergy(1, false);
            getEnergyStorage().updateConsumed(1);
        }

        this.setActive(active);
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.active = tag.getBoolean("active");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("active", active);
        return super.save(tag);
    }
}
