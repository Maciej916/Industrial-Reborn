package com.maciej916.indreb.common.entity.block;

import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.interfaces.receipe.IChanceRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

public class BlockEntityStandardMachine extends IndRebBlockEntity implements IEnergyBlock, ITileSound, IExpCollector, ISupportUpgrades {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int BONUS_SLOT = 2;

    private ItemStack cachedInputStack = ItemStack.EMPTY;
    private ItemStack resultStack = ItemStack.EMPTY;
    protected IChanceRecipe recipe;

    private boolean rolledChance = false;
    private ItemStack chanceDrop = ItemStack.EMPTY;

    public BlockEntityProgress progress = new BlockEntityProgress();
    private boolean active = false;
    private int energyCostPerTick = 0;
    private int duration = 0;

    public BlockEntityStandardMachine(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState, int energyCapacity) {
        super(pType, pWorldPosition, pBlockState);
        createEnergyStorage(0, energyCapacity, EnergyType.RECEIVE, EnergyTier.BASIC);
    }

    protected Optional<?> getRecipe(ItemStack input) {
        return Optional.empty();
    }

    protected ItemStack getRecipeResult(ItemStack stack) {
        return this.recipe.assemble(new SimpleContainer(stack));
    }

    private boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        return getRecipe(stack).isPresent();
    }

    boolean canWork(ItemStack inputStack, ItemStack outputStack, ItemStack resultStack, ItemStack bonusStack) {
        return isValidInput(inputStack) &&
                inputStack.getCount() >= recipe.getIngredientCount() &&
                (outputStack.isEmpty() || (resultStack.getCount() + outputStack.getCount() <= outputStack.getMaxStackSize() &&
                resultStack.getItem() == outputStack.getItem())) &&
                bonusStack.getCount() < bonusStack.getMaxStackSize();
    }

    @Override
    public void tickOperate(BlockState state) {
        progress.clearChanged();
        active = false;
        getEnergyStorage().updateConsumed(0);

        final ItemStack inputStack = getStackHandler().getStackInSlot(INPUT_SLOT);
        final ItemStack outputStack = getStackHandler().getStackInSlot(OUTPUT_SLOT);
        final ItemStack bonusStack = getStackHandler().getStackInSlot(BONUS_SLOT);

        if (cachedInputStack.getItem() != inputStack.getItem()) {
            cachedInputStack = inputStack.copy();
            if (inputStack.getItem() != Items.AIR && getRecipe(inputStack).isPresent()) {
                recipe = (IChanceRecipe) getRecipe(inputStack).get();
                resultStack = getRecipeResult(inputStack);
                energyCostPerTick = recipe.getPowerCost();
                duration = recipe.getDuration();
            } else {
                recipe = null;
            }
        }

        if (recipe != null) {
            if (!rolledChance) {
                chanceDrop = recipe.rollChanceResult().copy();
                rolledChance = true;
            }

            if (progress.getProgress() == -1) {
                progress.setData(0, duration);
            }

            if (canWork(inputStack, outputStack, resultStack, bonusStack)) {

                progress.setProgressMax(getSpeedFactor() * duration);
                int energyCost = (int) (energyCostPerTick * getEnergyUsageFactor());

                if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost && progress.getProgress() <= progress.getProgressMax()) {
                    if (progress.getProgress() <= progress.getProgressMax()) {
                        active = true;
                        progress.incProgress(1);
                        getEnergyStorage().consumeEnergy(energyCost, false);
                        getEnergyStorage().updateConsumed(energyCost);
                    }
                }

                if (progress.getProgress() >= progress.getProgressMax()) {

                    if (bonusStack.isEmpty() || chanceDrop.isEmpty() || (chanceDrop.getCount() + bonusStack.getCount() <= bonusStack.getMaxStackSize() && chanceDrop.getItem() == bonusStack.getItem())) {
                        if (outputStack.isEmpty()) {
                            getStackHandler().setStackInSlot(OUTPUT_SLOT, resultStack.copy());
                        } else {
                            outputStack.grow(resultStack.getCount());
                            getStackHandler().setStackInSlot(OUTPUT_SLOT, outputStack.copy());
                        }

                        if (!chanceDrop.isEmpty()) {
                            if (bonusStack.isEmpty()) {
                                getStackHandler().setStackInSlot(BONUS_SLOT, chanceDrop.copy());
                            } else {
                                bonusStack.grow(chanceDrop.getCount());
                                getStackHandler().setStackInSlot(BONUS_SLOT, bonusStack.copy());
                            }
                        }

                        inputStack.shrink(recipe.getIngredientCount());
                        getStackHandler().setStackInSlot(INPUT_SLOT, inputStack.copy());

                        this.setRecipeUsed(recipe);

                        progress.setBoth(-1);
                        rolledChance = false;
                    }
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

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 48, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 47, 34));
        slots.add(new IndRebSlot(OUTPUT_SLOT, 108, 24, InventorySlotType.OUTPUT, GuiSlotType.LARGE, 103, 19));
        slots.add(new IndRebSlot(BONUS_SLOT, 108, 50, InventorySlotType.NORMAL, GuiSlotType.NORMAL, 107, 49));
        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false));
        return super.addBatterySlot(slots);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return null;
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((IChanceRecipe) recipe).getExperience();
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
    }

    ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), INPUT_SLOT, INPUT_SLOT + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), OUTPUT_SLOT, BONUS_SLOT + 1))
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
