package com.maciej916.indreb.common.block.impl.generator.generator;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasCooldown;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.util.Progress;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.StackHandlerHelper;
import com.maciej916.indreb.common.util.WrappedHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Map;

public class BlockEntityGenerator extends IndRebBlockEntity implements IHasCooldown, IBlockEntityEnergy, IHasSound {

    public static final int SYNC_DATA_SLOTS = 3;
    protected final ContainerData data;

    public static final int INPUT_SLOT = 0;

    public Progress progressBurn = new Progress();

    public BlockEntityGenerator(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.GENERATOR.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.generator_energy_capacity.get(), EnergyType.EXTRACT, EnergyTier.BASIC);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlockEntityGenerator.this.getCooldown();
                    case 1 -> BlockEntityGenerator.this.progressBurn.getContainerDataCurrent();
                    case 2 -> BlockEntityGenerator.this.progressBurn.getContainerDataMax();

                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlockEntityGenerator.this.setCooldown(value);
                    case 1 -> BlockEntityGenerator.this.progressBurn.setContainerDataCurrent(value);
                    case 2 -> BlockEntityGenerator.this.progressBurn.setContainerDataMax(value);
                }
            }

            @Override
            public int getCount() {
                return SYNC_DATA_SLOTS;
            }
        };
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == INPUT_SLOT) return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0 && !stack.getItem().equals(Items.LAVA_BUCKET);
        return false;
    }

    @Override
    public void tickWork() {
        if (getCooldown() == 0) {
            final ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
            if (getEnergyStorage().generateEnergy(ServerConfig.generator_tick_generate.get(), true) == ServerConfig.generator_tick_generate.get()) {
                if (progressBurn.currentProgress() > 0) {
                    activeState = true;
                    progressBurn.decProgress(1);
                    getEnergyStorage().generateEnergy(ServerConfig.generator_tick_generate.get(), false);
                } else {
                    activeState = false;
                    progressBurn.resetProgress();

                    if (!inputStack.isEmpty()) {
                        final int burnTime = ForgeHooks.getBurnTime(inputStack, RecipeType.SMELTING);
                        if (burnTime > 1) {
                            progressBurn.setBoth(burnTime * 0.60f);
                            StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT, 1);
                            activeState = true;
                        }
                    }
                }
            }

            if (activeState && progressBurn.currentProgress() > 0 && getEnergyStorage().generateEnergy(ServerConfig.generator_tick_generate.get(), true) < ServerConfig.generator_tick_generate.get()) {
                setCooldown(10);
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuGenerator(this, containerId, playerInventory, player, data);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.GENERATOR.get();
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(INPUT_SLOT, 80, 35, 79, 34, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
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
        this.progressBurn.deserializeNBT(tag.getCompound("progressBurn"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressBurn", this.progressBurn.serializeNBT());
    }

    private final Map<Direction, LazyOptional<WrappedHandler>> itemCapabilities = Map.of(
            Direction.UP, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> i == 0, (i, stack) -> false)),
            Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.EAST, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.WEST, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(),  (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack)))
    );

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) return LazyOptional.empty();

            if (itemCapabilities.containsKey(side)) {
                if (side == Direction.UP || side == Direction.DOWN) return itemCapabilities.get(side).cast();

                Direction localDir = this.getBlockState().getValue(BlockStateHelper.HORIZONTAL_FACING_PROPERTY);
                return switch (localDir) {
                    default -> itemCapabilities.get(side).cast(); // SOUTH
                    case NORTH -> itemCapabilities.get(side.getOpposite()).cast();
                    case WEST -> itemCapabilities.get(side.getCounterClockWise()).cast();
                    case EAST -> itemCapabilities.get(side.getClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<?> capability : itemCapabilities.values()) capability.invalidate();
    }

}
