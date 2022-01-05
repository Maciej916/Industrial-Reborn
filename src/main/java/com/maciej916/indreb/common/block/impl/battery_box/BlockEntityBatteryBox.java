package com.maciej916.indreb.common.block.impl.battery_box;

import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.SlotElectric;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.tier.BatteryBoxTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class BlockEntityBatteryBox extends IndRebBlockEntity implements IEnergyBlock {

    public BlockEntityBatteryBox(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.BATTERY_BOX, pWorldPosition, pBlockState);

        BlockBatteryBox block = (BlockBatteryBox) getBlock();
        BatteryBoxTier batteryBoxTier = block.getBatteryBoxTier();
        EnergyTier energyTier =  batteryBoxTier.getEnergyTier();

        createEnergyStorage(batteryBoxTier.getEnergyStored(), batteryBoxTier.getEnergyCapacity(), EnergyType.BOTH, energyTier);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotElectric(0, 62, 52, InventorySlotType.ELECTRIC, GuiSlotType.NORMAL, false));
        slots.add(new SlotElectric(1, 62, 20, InventorySlotType.ELECTRIC, GuiSlotType.NORMAL, true));

        slots.add(new SlotElectric(2, 8, 84, InventorySlotType.HELMET, GuiSlotType.HELMET, true));
        slots.add(new SlotElectric(3, 26, 84, InventorySlotType.CHESTPLATE, GuiSlotType.CHESTPLATE, true));
        slots.add(new SlotElectric(4, 44, 84, InventorySlotType.LEGGINGS, GuiSlotType.LEGGINGS, true));
        slots.add(new SlotElectric(5, 62, 84, InventorySlotType.BOOTS, GuiSlotType.BOOTS, true));

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
}
