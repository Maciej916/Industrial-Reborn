package com.maciej916.indreb.common.block.impl.charge_pad;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityChargePad;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.tier.ChargePadTier;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class BlockEntityChargePad extends IndRebBlockEntity implements IBlockEntityEnergy, IHasSound, IBlockEntityChargePad {

    private final ChargePadTier chargePadTier;

    public BlockEntityChargePad(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CHARGE_PAD.get(), pos, blockState);

        BlockChargePad block = (BlockChargePad) getBlock();
        this.chargePadTier = block.getChargePadTier();
        EnergyTier energyTier = chargePadTier.getEnergyTier();

        createEnergyStorage(chargePadTier.getEnergyStored(), chargePadTier.getEnergyCapacity(), EnergyType.BOTH, energyTier);
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuChargePad(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public ArrayList<ElectricSlot> addElectricSlots(ArrayList<ElectricSlot> slots) {

        slots.add(new ElectricSlot(0, 62, 20, InventorySlotType.ELECTRIC, GuiSlotBg.BATTERY, true, true));
        slots.add(new ElectricSlot(1, 62, 52, InventorySlotType.ELECTRIC, GuiSlotBg.BATTERY, true, false));

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
    public SoundEvent getSoundEvent() {
        return ModSounds.CHARGE_PAD.get();
    }

    @Override
    public ChargePadTier getChargePadTier() {
        return chargePadTier;
    }
}
