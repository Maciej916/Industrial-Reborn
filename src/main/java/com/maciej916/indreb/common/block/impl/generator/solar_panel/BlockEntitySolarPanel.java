package com.maciej916.indreb.common.block.impl.generator.solar_panel;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.tier.SolarPanelTier;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import com.maciej916.indreb.common.api.util.ProgressInt;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class BlockEntitySolarPanel extends IndRebBlockEntity implements IBlockEntityEnergy, IHasSound {

    SolarPanelTier tier;
    public ProgressInt progressActive = new ProgressInt(0, 2);
    public ProgressFloat progressAmount;

    public BlockEntitySolarPanel(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SOLAR_PANEL.get(), pos, blockState);

        BlockSolarPanel block = (BlockSolarPanel) getBlock();
        this.tier = block.getSolarTier();
        this.progressAmount = new ProgressFloat(0, tier.getDayGenerate());

        createEnergyStorage(0, tier.getEnergyCapacity(), EnergyType.EXTRACT, tier.getEnergyTier());

        this.containerData.syncProgressFloat(0, this.progressAmount);
        this.containerData.syncProgressInt(1, this.progressActive);
    }

    @Override
    public void tickWork() {
        if (level != null && level.canSeeSky(getBlockPos())) {
            if (level.isDay() && !level.isThundering()) {
                progressAmount.setCurrentProgress(tier.getDayGenerate());
                progressActive.setCurrentProgress(2);
            }

            if (!level.isDay() || level.isThundering() || level.isRaining()) {
                progressAmount.setCurrentProgress(tier.getNightGenerate());
                progressActive.setCurrentProgress(tier.getNightGenerate() > 0 ? 1 : 0);
            }
        } else {
            progressAmount.setCurrentProgress(0);
            progressActive.setCurrentProgress(0);
        }

        int maxGenerate = Math.min(getEnergyStorage().maxEnergy() - getEnergyStorage().energyStored(), getEnergyStorage().generateEnergy((int) progressAmount.getCurrentProgress(), true));
        if (maxGenerate > 0) {
            getEnergyStorage().generateEnergy(maxGenerate, false);
            activeState = true;
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuSolarPanel(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.SOLAR_PANEL.get();
    }

    @Override
    public ArrayList<ElectricSlot> addElectricSlots(ArrayList<ElectricSlot> slots) {
        BlockSolarPanel block = (BlockSolarPanel) getBlock();
        SolarPanelTier localTier = block.getSolarTier();
        int leftOffset = 130 - (18 * localTier.getTierLevel()) / 2;
        for (int i = 0; i < localTier.getTierLevel(); i++) {
            slots.add(new ElectricSlot(i, leftOffset + (18 * i), 57, InventorySlotType.ELECTRIC, GuiSlotBg.BATTERY, true, true));
        }

        return super.addElectricSlots(slots);
    }

    @Override
    public boolean canExtractEnergyCustom(Direction side) {
        return side == Direction.DOWN;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progressAmount.deserializeNBT(tag.getCompound("progressAmount"));
        this.progressActive.deserializeNBT(tag.getCompound("progressActive"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressAmount", this.progressAmount.serializeNBT());
        tag.put("progressActive", this.progressActive.serializeNBT());
    }

    @Override
    public boolean showVertical() {
        return false;
    }

    @Override
    public int leftOffsetHorizontal() {
        return 105;
    }

    @Override
    public int topOffsetHorizontal() {
        return 35;
    }
}
