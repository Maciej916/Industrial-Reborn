package com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import com.maciej916.indreb.common.api.top.impl.ProbeInfoSimpleText;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.radiation.IHasRadiation;
import com.maciej916.indreb.common.capability.reactor.IReactorComponentCapability;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.multiblock.reactor.Reactor;
import com.maciej916.indreb.common.multiblock.reactor.ReactorPartIndex;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketPlayPauseReactor;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.StackHandlerHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityNuclearReactor extends IndRebBlockEntity implements IHasSound, IHasRadiation {

    private int totalRodCount = 0;
    private final Reactor reactor = new Reactor();

    public BlockEntityNuclearReactor(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.NUCLEAR_REACTOR.get(), pos, blockState);
        createEnergyStorage(0, 0, EnergyType.EXTRACT, EnergyTier.BASIC);

        this.containerData.syncBool(0, () -> getReactor().getEnabled());
        this.containerData.syncInt(1, () -> getReactor().getVentedHeat());
        this.containerData.syncInt(2, () -> getReactor().getCurrentIEOutput());
        this.containerData.syncInt(3, () -> getReactor().getCurrentHeat());
        this.containerData.syncInt(4, () -> getReactor().getMaxHeat());
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        return stack.is(ModItemTags.REACTOR_FUSION);
    }

    @Override
    public void tickWork() {
        boolean isFormed = getBlockState().getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED;

        if (baseSlotsChangedForTick.size() > 0) {
            for (int slotId: baseSlotsChangedForTick) {
                ItemStack stack = getBaseStorage().getStackInSlot(slotId);

                int colId = 0;
                int rowId = 0;
                int currentSlot = 0;
                for (int row = 0; row < 6; row++) {
                    boolean isBreak = false;
                    for (int col = 0; col < 9; col++) {
                        if (currentSlot == slotId) {
                            rowId = row;
                            colId = col;
                            isBreak = true;
                            break;
                        }
                        currentSlot++;
                    }
                    if (isBreak) {
                        break;
                    }
                }

                if (stack.isEmpty()) {
                    reactor.setComponentAt(rowId, colId, null, slotId);
                } else {
                    IReactorComponentCapability cap = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.REACTOR_ITEM).getValue();
                    if (cap != null) {
                        reactor.setComponentAt(rowId, colId, cap, slotId);
                    }
                }
            }
        }

        if (level.getGameTime() % 20 == 0 && isFormed) {
            reactor.clearVentedHeat();
            reactor.clearIEOutput();

            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 9; col++) {
                    IReactorComponentCapability component = reactor.getComponentAt(row, col);
                    if (component != null) {
                        totalRodCount += component.getRodCount();
                    }
                }
            }

            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 9; col++) {
                    IReactorComponentCapability component = reactor.getComponentAt(row, col);
                    if (component != null) {
                        component.preReactorTick();
                    }
                }
            }

            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 9; col++) {
                    IReactorComponentCapability component = reactor.getComponentAt(row, col);
                    if (component != null && !component.isBroken()) {
                        if (reactor.getEnabled()) {
                            component.generateHeat();
                        }
                        component.dissipate();
                        component.transfer();
                    }
                }
            }

            if (reactor.getEnabled()) {
                for (int row = 0; row < 6; row++) {
                    for (int col = 0; col < 9; col++) {
                        IReactorComponentCapability component = reactor.getComponentAt(row, col);
                        if (component != null && !component.isBroken()) {
                            component.generateEnergy();
                        }
                    }
                }
            }

            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 9; col++) {
                    IReactorComponentCapability component = reactor.getComponentAt(row, col);
                    if (component != null) {
                        if (component.isBroken()) {
                            float random = level.getRandom().nextFloat();
                            if (random <= 0.25f && component.getRodCount() > 0) {
                                ItemStack newStack = getBaseStorage().getStackInSlot(component.getSlotId()).copy();
                                if (newStack.getItem() == ModItems.FUEL_ROD_URANIUM.get()) {
                                    newStack = new ItemStack(ModItems.FUEL_ROD_URANIUM.get());
                                } else if (newStack.getItem() == ModItems.FUEL_ROD_URANIUM_DUAL.get()) {
                                    newStack = new ItemStack(ModItems.FUEL_ROD_URANIUM_DUAL_DEPLETED.get());
                                } else if (newStack.getItem() == ModItems.FUEL_ROD_URANIUM_QUAD.get()) {
                                    newStack = new ItemStack(ModItems.FUEL_ROD_URANIUM_QUAD_DEPLETED.get());
                                }

                                getBaseStorage().setStackInSlot(component.getSlotId(), newStack);
                            } else {
                                StackHandlerHelper.shrinkStack(getBaseStorage(), component.getSlotId(), 1);
                            }

                            reactor.setComponentAt(row, col, null, component.getSlotId());
                        }
                    }
                }
            }

            if (reactor.getEnabled()) {
                if (reactor.getCurrentHeat() >= reactor.getMaxHeat()) {
                    reactor.explodeReactor((ServerLevel) level, getBlockPos(), totalRodCount);
                }
            }

            setChanged();

            for (EnergyTier tier: EnergyTier.values()) {
                if (tier.getBasicTransfer() > reactor.getCurrentIEOutput() / 20) {
                    getEnergyStorage().setMaxEnergy(tier.getBasicTransfer());
                    if (getEnergyStorage().energyTier() != tier) {
                        getEnergyStorage().setEnergyTier(tier);
                    }
                    break;
                }
            }
        }

        if (reactor.getCurrentIEOutput() > 0 && !reactor.isFluid()) {
            int generateEnergy = getEnergyStorage().generateEnergy(reactor.getCurrentIEOutput() / 20, true);

            if (generateEnergy > 0) {
                getEnergyStorage().generateEnergy(generateEnergy, false);
            }

            activeState = reactor.getEnabled();

            getEnergyStorage().setLastGenerated((reactor.getCurrentIEOutput() / 20));
        }
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuNuclearReactor(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        int startX = 7 + 18;
        int startY = 17 ;
        int slotId = 0;
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 9; i++) {
                slots.add(new BaseSlot(slotId, startX + (i * 18) + 1, startY + (j * 18) + 1, startX + (i * 18), startY + (j * 18), InventorySlotType.INPUT, GuiSlotBg.NORMAL));
                slotId++;
            }
        }

        return super.addBaseSlots(slots);
    }

    @Override
    public int getBaseStorageSlotLimit(int slot) {
        return 1;
    }

    @Override
    public boolean canExtractEnergyCustom(Direction side) {
        return true;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        reactor.deserializeNBT(tag.getCompound("reactor"));
        reactor.initComponents(getBaseStorage());
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("reactor", reactor.serializeNBT());
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ModCapabilities.ENERGY) {
            BlockState state = getBlockState();
            if (state.getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED) {
                return energyStorageCap.cast();
            }

            return LazyOptional.empty();
        }

        return super.getCapability(cap, side);
    }

    public Runnable clickPlayPauseClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketPlayPauseReactor(getBlockPos()));
    }

    public void clickPlayPauseServer() {
        reactor.setEnabled(!reactor.getEnabled());
    }

    public Reactor getReactor() {
        return reactor;
    }

    @Override
    public boolean showEnergyBarProbe() {
        return false;
    }

    @Override
    public List<BaseOneProbeInfo> addProbeInfo(List<BaseOneProbeInfo> oneProbeInfo) {
        BlockState state = getBlockState();
        if (state.getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED) {
            super.addProbeInfo(oneProbeInfo);

            oneProbeInfo.add(new ProbeInfoSimpleText(Component.translatable("tooltip." + IndReb.MODID + ".core_heat", reactor.getPercentProgressString() + "%")));

            if (reactor.isFluid()) {
                oneProbeInfo.add(new ProbeInfoSimpleText(Component.translatable("gui." + IndReb.MODID + ".output_mode", reactor.getVentedHeat() + " HU/s")));
            } else {
                oneProbeInfo.add(new ProbeInfoSimpleText(Component.translatable("gui." + IndReb.MODID + ".output_mode", (reactor.getCurrentIEOutput() / 20) + " IE/t")));
            }

        }

        return oneProbeInfo;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.NUCLEAR_REACTOR.get();
    }

    public int getTotalRodCount() {
        return totalRodCount;
    }

    @Override
    public double getAddRads() {
        double rads = 0;

        BlockState state = getBlockState();
        if (state.getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED) {
            for (int slotId = 0; slotId < 54; slotId++) {
                ItemStack stack = getBaseStorage().getStackInSlot(slotId);
                if (!stack.isEmpty()) {
                    if (stack.getItem() instanceof IHasRadiation hasRadiation) {
                        rads += hasRadiation.getAddRads();
                    }
                }
            }
        }

        float coreHeatPercent = getReactor().getPercentProgress() / 5;


        System.out.println(getReactor().getPercentProgress());

        if (getReactor().getPercentProgress() >= 99) {
            return rads * 1000;
        }

        return coreHeatPercent * rads;
    }
}
