package com.maciej916.indreb.common.block.impl.generator.geo_generator;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityFluid;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasCooldown;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.fluid.FluidStorage;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import com.maciej916.indreb.common.api.top.impl.ProbeInfoFluidBar;
import com.maciej916.indreb.common.api.util.ProgressInt;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketFluidSync;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockEntityGeoGenerator extends IndRebBlockEntity implements IBlockEntityFluid, IHasCooldown, IBlockEntityEnergy, IHasSound {

    public static final int FILL_UP = 0;
    public static final int FILL_DOWN = 1;

    public ProgressInt progressFill = new ProgressInt(0, 1);
    public FluidStorage lavaStorage = new FluidStorage(ServerConfig.geo_generator_lava_capacity.get(), (fluidStack -> fluidStack.getFluid() == Fluids.LAVA)) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
        }
    };

    public BlockEntityGeoGenerator(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.GEO_GENERATOR.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.geo_generator_energy_capacity.get(), EnergyType.EXTRACT, EnergyTier.BASIC);

        this.containerData.syncProgressInt(0, this.progressFill);
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == FILL_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.LAVA;
            }
        }
        return false;
    }

    @Override
    public void tickWork() {
        BlockEntityUtil.fillTank(progressFill, lavaStorage, getBaseStorage(), FILL_UP, FILL_DOWN);

        if (getCooldown() == 0) {
            if (getEnergyStorage().generateEnergy(ServerConfig.geo_generator_tick_generate.get(), true) == ServerConfig.geo_generator_tick_generate.get() && lavaStorage.takeFluid(1, true) == 1) {
                lavaStorage.takeFluid(1, false);
                getEnergyStorage().generateEnergy(ServerConfig.geo_generator_tick_generate.get(), false);
                activeState = true;
            }

            if (activeState && getEnergyStorage().generateEnergy(ServerConfig.geo_generator_tick_generate.get(), true) < ServerConfig.geo_generator_tick_generate.get() && lavaStorage.takeFluid(1, true) == 1) {
                setCooldown(10);
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuGeoGenerator(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.GEO_GENERATOR.get();
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(FILL_UP, 61, 20, 60, 19, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new BaseSlot(FILL_DOWN, 61, 51, 60, 50, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        return super.addBaseSlots(slots);
    }

    @Override
    public ArrayList<ElectricSlot> addElectricSlots(ArrayList<ElectricSlot> slots) {
        slots.add(new ElectricSlot(0, 152, 62, InventorySlotType.ELECTRIC, GuiSlotBg.BATTERY, true, true));
        return super.addElectricSlots(slots);
    }

    @Override
    public boolean canExtractEnergyCustom(Direction side) {
        return true;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progressFill.deserializeNBT(tag.getCompound("progressFill"));
        this.lavaStorage.readFromNBT(tag.getCompound("lavaStorage"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressFill", this.progressFill.serializeNBT());
        tag.put("lavaStorage", this.lavaStorage.writeToNBT(tag.getCompound("lavaStorage")));
    }

    private final ArrayList<LazyOptional<?>> baseCapabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(() -> this.lavaStorage)
    ));

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) return baseCapabilities.get(0).cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<?> capability : baseCapabilities) capability.invalidate();
    }

    @Override
    public List<FluidStack> getStoredFluids() {
        return List.of(lavaStorage.getFluid());
    }

    @Override
    public void setStoredFluids(List<FluidStack> fluids) {
        this.lavaStorage.setFluid(fluids.get(0));
    }

    @Override
    public List<BaseOneProbeInfo> addProbeInfo(List<BaseOneProbeInfo> oneProbeInfo) {
        super.addProbeInfo(oneProbeInfo);

        oneProbeInfo.add(new ProbeInfoFluidBar(lavaStorage));

        return oneProbeInfo;
    }
}
