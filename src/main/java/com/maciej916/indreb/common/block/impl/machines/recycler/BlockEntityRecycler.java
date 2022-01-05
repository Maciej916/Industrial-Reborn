package com.maciej916.indreb.common.block.impl.machines.recycler;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.receipe.impl.RecyclingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

import static com.maciej916.indreb.common.enums.EnergyType.RECEIVE;

public class BlockEntityRecycler extends IndRebBlockEntity implements IEnergyBlock, ISupportUpgrades, ITileSound {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    public BlockEntityProgress progress = new BlockEntityProgress();

    private boolean active = false;
    private RecyclingRecipe recipe;
    private ItemStack cachedInputStack = ItemStack.EMPTY;


    public BlockEntityRecycler(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.RECYCLER, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.recycler_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.BASIC);
    }

    protected Optional<RecyclingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.RECYCLING, new SimpleContainer(input), level);
    }

    protected ItemStack getRecipeResult(ItemStack stack) {
        return this.recipe.assemble(new SimpleContainer(stack));
    }

    private boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        return getRecipe(stack).isPresent();
    }

    boolean canWork(ItemStack outputStack, ItemStack resultStack) {
        return outputStack.isEmpty() || (resultStack.getCount() + outputStack.getCount() <= outputStack.getMaxStackSize());
    }

    @Override
    public void tickOperate(BlockState state) {
        active = false;
        getEnergyStorage().updateConsumed(0);

        final ItemStack inputStack = getStackHandler().getStackInSlot(INPUT_SLOT);
        final ItemStack outputStack = getStackHandler().getStackInSlot(OUTPUT_SLOT);

        if (cachedInputStack.getItem() != inputStack.getItem()) {
            cachedInputStack = inputStack.copy();
            if (inputStack.getItem() != Items.AIR && getRecipe(inputStack).isPresent()) {
                recipe = getRecipe(inputStack).get();
            } else {
                recipe = null;
            }
        }

        if (recipe != null) {
            if (progress.getProgress() == -1) {
                progress.setData(0, recipe.getDuration());
            }

            progress.setProgressMax(getSpeedFactor() * recipe.getDuration());
            int energyCost = (int) (recipe.getPowerCost() * getEnergyUsageFactor());

            ItemStack resultStack = new ItemStack(ModItems.SCRAP);
            if (canWork(outputStack, resultStack)) {
                if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost && progress.getProgress() <= progress.getProgressMax()) {
                    active = true;
                    progress.incProgress(1);
                    getEnergyStorage().consumeEnergy(energyCost, false);
                    getEnergyStorage().updateConsumed(energyCost);
                }

                if (progress.getProgress() >= progress.getProgressMax()) {
                    inputStack.shrink(1);
                    getStackHandler().setStackInSlot(INPUT_SLOT, inputStack.copy());

                    if (Math.random() <= recipe.getChance()) {
                        if (outputStack.isEmpty()) {
                            getStackHandler().setStackInSlot(OUTPUT_SLOT, resultStack.copy());
                        } else {
                            outputStack.grow(resultStack.getCount());
                            getStackHandler().setStackInSlot(OUTPUT_SLOT, outputStack.copy());
                        }
                    }
                    progress.setBoth(-1);
                }
            }
        } else {
            progress.setBoth(-1);
        }

        this.setActive(active);
        if (progress.changed()) {
            super.updateBlockState();
        }
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 48, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 47, 34));
        slots.add(new IndRebSlot(OUTPUT_SLOT, 108, 35, InventorySlotType.OUTPUT, GuiSlotType.LARGE, 103, 30));
        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false));
        return super.addBatterySlot(slots);
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.RECYCLER;
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return isValidInput(stack);
        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == INPUT_SLOT && !isValidInput(stack)) {
            return stack;
        }
        return super.insertItemForSlot(slot, stack, simulate);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.active = tag.getBoolean("active");
        this.progress.deserializeNBT(tag.getCompound("progress"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("active", active);
        tag.put("progress", this.progress.serializeNBT());
        return super.save(tag);
    }

    ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), INPUT_SLOT, INPUT_SLOT + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), OUTPUT_SLOT, OUTPUT_SLOT + 1))
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null) return capabilities.get(0).cast();
            switch (side) {
                case DOWN: return capabilities.get(2).cast();
                case UP:
                case NORTH:
                case SOUTH:
                case WEST:
                case EAST: return capabilities.get(1).cast();
                default: return capabilities.get(0).cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onBreak() {
        for (LazyOptional<?> capability : capabilities) capability.invalidate();
        super.onBreak();
    }

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER);
    }
}
