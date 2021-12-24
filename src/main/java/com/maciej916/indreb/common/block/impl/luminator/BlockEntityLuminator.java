package com.maciej916.indreb.common.block.impl.luminator;

import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

import static com.maciej916.indreb.common.enums.EnumEnergyType.RECEIVE;

public class BlockEntityLuminator extends IndRebBlockEntity implements IEnergyBlock {

    private boolean active = false;

    public BlockEntityLuminator(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.LUMINATOR, pWorldPosition, pBlockState);
        createEnergyStorage(0, 1, 1, 0, RECEIVE);
    }

    @Override
    public void tickOperate(BlockState state) {
        active = false;

        if (getEnergyStorage().energyStored() == 1) {
            active = true;
            getEnergyStorage().consumeEnergy(1, false);
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
