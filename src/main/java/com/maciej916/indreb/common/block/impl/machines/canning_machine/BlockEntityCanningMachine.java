package com.maciej916.indreb.common.block.impl.machines.canning_machine;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.receipe.impl.CanningRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BlockEntityCanningMachine extends IndRebBlockEntity implements IEnergyBlock, IExpCollector, ISupportUpgrades, ITileSound {

    public static final int INPUT_SLOT_0 = 0;
    public static final int INPUT_SLOT_1 = 1;
    public static final int OUTPUT_SLOT = 2;

    public BlockEntityProgress progress = new BlockEntityProgress();
    private CanningRecipe recipe;
    private boolean active = false;
    private ItemStack cachedInputStack0 = ItemStack.EMPTY;
    private ItemStack cachedInputStack1 = ItemStack.EMPTY;

    public BlockEntityCanningMachine(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CANNING_MACHINE, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.canning_machine_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.BASIC);
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT_0, 8, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 7, 34));
        slots.add(new IndRebSlot(INPUT_SLOT_1, 46, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 45, 34));
        slots.add(new IndRebSlot(OUTPUT_SLOT, 121, 35, InventorySlotType.OUTPUT, GuiSlotType.LARGE, 116, 30));
        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false));
        return super.addBatterySlot(slots);
    }

    protected Optional<CanningRecipe> getRecipe(ItemStack... input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.CANNING, new SimpleContainer(input), level);
    }

    protected ItemStack getRecipeResult(ItemStack stack) {
        return this.recipe.assemble(new SimpleContainer(stack));
    }

    private boolean isValidInput(final ItemStack stack, int slot) {
        if (stack.isEmpty()) return false;
        Optional<CanningRecipe> recipe = getRecipe(stack);
        if (recipe.isPresent()) {
            if (slot == 0) {
                return recipe.get().getFirstIngredient().getItems()[0].getItem() == stack.getItem();
            } else {
                return recipe.get().getSecondIngredient().getItems()[0].getItem() == stack.getItem();
            }
        }
        return false;
    }

    boolean canWork(ItemStack outputStack, ItemStack resultStack) {
        return outputStack.isEmpty() || (resultStack.getCount() + outputStack.getCount() <= outputStack.getMaxStackSize());
    }

    @Override
    public void tickOperate(BlockState state) {
        active = false;
        getEnergyStorage().updateConsumed(0);

        final ItemStack inputStack0 = getStackHandler().getStackInSlot(INPUT_SLOT_0);
        final ItemStack inputStack1 = getStackHandler().getStackInSlot(INPUT_SLOT_1);
        final ItemStack outputStack = getStackHandler().getStackInSlot(OUTPUT_SLOT);

        if (cachedInputStack0.getItem() != inputStack0.getItem() || cachedInputStack1.getItem() != inputStack1.getItem()) {
            cachedInputStack0 = inputStack0.copy();
            cachedInputStack1 = inputStack1.copy();

            if (getRecipe(inputStack0, inputStack1).isPresent()) {
                recipe = getRecipe(inputStack0, inputStack1).get();
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

            if (canWork(outputStack, recipe.getResultItem())) {
                if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost && progress.getProgress() <= progress.getProgressMax()) {
                    active = true;
                    progress.incProgress(1);
                    getEnergyStorage().consumeEnergy(energyCost, false);
                    getEnergyStorage().updateConsumed(energyCost);
                }

                if (progress.getProgress() >= progress.getProgressMax()) {

                    inputStack0.shrink(recipe.getFirstIngredientCount());
                    getStackHandler().setStackInSlot(INPUT_SLOT_0, inputStack0.copy());

                    inputStack1.shrink(recipe.getSecondIngredientCount());
                    getStackHandler().setStackInSlot(INPUT_SLOT_1, inputStack1.copy());

                    if (outputStack.isEmpty()) {
                        getStackHandler().setStackInSlot(OUTPUT_SLOT, recipe.getResultItem().copy());
                    } else {
                        outputStack.grow(recipe.getResultItem().getCount());
                        getStackHandler().setStackInSlot(OUTPUT_SLOT, outputStack.copy());
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
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT_0) return isValidInput(stack, slot);
        if (slot == INPUT_SLOT_1) return isValidInput(stack, slot);
        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == INPUT_SLOT_0 && !isValidInput(stack, slot)) {
            return stack;
        }
        if (slot == INPUT_SLOT_1 && !isValidInput(stack, slot)) {
            return stack;
        }
        return super.insertItemForSlot(slot, stack, simulate);
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((CanningRecipe) recipe).getExperience();
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
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), INPUT_SLOT_0, INPUT_SLOT_1 + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), OUTPUT_SLOT, OUTPUT_SLOT + 1))
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null) return capabilities.get(0).cast();
            return switch (side) {
                case DOWN -> capabilities.get(2).cast();
                case UP, NORTH, SOUTH, WEST, EAST -> capabilities.get(1).cast();
            };
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onBreak() {
        for (LazyOptional<?> capability : capabilities) capability.invalidate();
        super.onBreak();
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.CANNING_MACHINE;
    }

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER);
    }
}