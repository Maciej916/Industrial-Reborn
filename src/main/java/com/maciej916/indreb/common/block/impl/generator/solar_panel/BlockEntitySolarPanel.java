package com.maciej916.indreb.common.block.impl.generator.solar_panel;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.tier.SolarPanelTier;
import com.maciej916.indreb.common.api.util.Progress;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class BlockEntitySolarPanel extends IndRebBlockEntity implements IBlockEntityEnergy, IHasSound {

    public static final int SYNC_DATA_SLOTS = 2;
    protected final ContainerData data;

    SolarPanelTier tier;
    public Progress progressAmount;

    public BlockEntitySolarPanel(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SOLAR_PANEL.get(), pos, blockState);

        BlockSolarPanel block = (BlockSolarPanel) getBlock();
        this.tier = block.getSolarTier();
        this.progressAmount = new Progress(0, tier.getDayGenerate());

        createEnergyStorage(0, tier.getEnergyCapacity(), EnergyType.EXTRACT, tier.getEnergyTier());
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlockEntitySolarPanel.this.progressAmount.getContainerDataCurrent();
                    case 1 -> BlockEntitySolarPanel.this.progressAmount.getContainerDataMax();

                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlockEntitySolarPanel.this.progressAmount.setContainerDataCurrent(value);
                    case 1 -> BlockEntitySolarPanel.this.progressAmount.setContainerDataMax(value);
                }
            }

            @Override
            public int getCount() {
                return SYNC_DATA_SLOTS;
            }
        };
    }

    @Override
    public void tickWork() {
        if (level != null && level.canSeeSky(getBlockPos())) {
            if (level.isDay() && !level.isThundering()) {
                progressAmount.setCurrentProgress(tier.getDayGenerate());
            }

            if (!level.isDay() || level.isThundering() || level.isRaining()) {
                progressAmount.setCurrentProgress(tier.getNightGenerate());
            }
        } else {
            progressAmount.setCurrentProgress(0);
        }

        int maxGenerate = Math.min(getEnergyStorage().maxEnergy() - getEnergyStorage().energyStored(), getEnergyStorage().generateEnergy((int) progressAmount.currentProgress(), true));
        if (maxGenerate > 0) {
            getEnergyStorage().generateEnergy(maxGenerate, false);
            getEnergyStorage().updateGenerated(maxGenerate);
            activeState = true;
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuSolarPanel(this, containerId, playerInventory, player, data);
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
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressAmount", this.progressAmount.serializeNBT());
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
