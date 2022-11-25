package com.maciej916.indreb.common.block.impl.battery_box;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityBatteryBox;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.tier.BatteryBoxTier;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class BlockEntityBatteryBox extends IndRebBlockEntity implements IBlockEntityEnergy, IBlockEntityBatteryBox {

    private final BatteryBoxTier batteryBoxTier;

    public BlockEntityBatteryBox(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BATTERY_BOX.get(), pos, blockState);

        BlockBatteryBox block = (BlockBatteryBox) getBlock();
        this.batteryBoxTier = block.getBatteryBoxTier();
        EnergyTier energyTier = batteryBoxTier.getEnergyTier();

        createEnergyStorage(batteryBoxTier.getEnergyStored(), batteryBoxTier.getEnergyCapacity(), EnergyType.BOTH, energyTier);
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuBatteryBox(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public ArrayList<ElectricSlot> addElectricSlots(ArrayList<ElectricSlot> slots) {

        slots.add(new ElectricSlot(0, 62, 20, InventorySlotType.ELECTRIC, GuiSlotBg.BATTERY, true, true));
        slots.add(new ElectricSlot(1, 62, 52, InventorySlotType.ELECTRIC, GuiSlotBg.BATTERY, true, false));

        slots.add(new ElectricSlot(2, 8, 84, InventorySlotType.HELMET, GuiSlotBg.HELMET, true, true));
        slots.add(new ElectricSlot(3, 26, 84, InventorySlotType.CHESTPLATE, GuiSlotBg.CHESTPLATE, true, true));
        slots.add(new ElectricSlot(4, 44, 84, InventorySlotType.LEGGINGS, GuiSlotBg.LEGGINGS, true, true));
        slots.add(new ElectricSlot(5, 62, 84, InventorySlotType.BOOTS, GuiSlotBg.BOOTS, true, true));

        return super.addElectricSlots(slots);
    }

    @Override
    public boolean canExtractEnergyCustom(Direction side) {
        if (side == null) return true;
        IStateFacing blockFacing = (IStateFacing) getBlock();
        Direction facingDirection = blockFacing.getDirection(getBlockState());
        return facingDirection == side;
    }

    @Override
    public boolean canReceiveEnergyCustom(Direction side) {
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
    public BatteryBoxTier getBatteryBoxTier() {
        return batteryBoxTier;
    }
}
