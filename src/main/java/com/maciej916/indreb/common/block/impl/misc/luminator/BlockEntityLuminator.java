package com.maciej916.indreb.common.block.impl.misc.luminator;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityLuminator extends IndRebBlockEntity implements IBlockEntityEnergy {

    public BlockEntityLuminator(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.LUMINATOR.get(), pos, blockState);
        createEnergyStorage(0, 20, EnergyType.RECEIVE, EnergyTier.ULTRA);
    }

    @Override
    public void tickWork() {
        if (getEnergyStorage().energyStored() > 0) {
            activeState = true;
            getEnergyStorage().consumeEnergy(1, false);
            getEnergyStorage().updateConsumed(1);
        }
    }

    @Override
    public boolean canReceiveEnergyCustom(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return side.getOpposite() == facingDirection;
    }

    @Override
    public boolean hasMenu() {
        return false;
    }
}
