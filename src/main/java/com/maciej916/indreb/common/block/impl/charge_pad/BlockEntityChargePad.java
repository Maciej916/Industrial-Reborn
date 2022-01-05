package com.maciej916.indreb.common.block.impl.charge_pad;

import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.SlotElectric;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModSounds;
import com.maciej916.indreb.common.tier.ChargePadTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class BlockEntityChargePad extends IndRebBlockEntity implements IEnergyBlock, ITileSound {

    public BlockEntityChargePad(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CHARGE_PAD, pWorldPosition, pBlockState);

        BlockChargePad block = (BlockChargePad) getBlock();
        ChargePadTier chargePadTier = block.getChargePadTier();

        createEnergyStorage(0, chargePadTier.getEnergyCapacity(), EnergyType.BOTH, chargePadTier.getEnergyTier());
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotElectric(0, 62, 52, InventorySlotType.ELECTRIC, GuiSlotType.NORMAL, false));
        slots.add(new SlotElectric(1, 62, 20, InventorySlotType.ELECTRIC, GuiSlotType.NORMAL, true));
        return super.addBatterySlot(slots);
    }

    @Override
    public boolean canExtractEnergyDir(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return facingDirection == side;
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return facingDirection != side;
    }

    @Override
    public boolean showVertical() {
        return false;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.CHARGE_PAD;
    }
}
