package com.maciej916.indreb.common.block.impl.machines.electric_furnace;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
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

import static com.maciej916.indreb.common.enums.EnergyType.RECEIVE;

public class BlockEntityElectricFurnace extends IndRebBlockEntity implements IEnergyBlock, IExpCollector, ISupportUpgrades {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    public BlockEntityProgress progress = new BlockEntityProgress();
    private boolean active = false;

    private ItemStack cachedInputStack = ItemStack.EMPTY;
    private ItemStack resultStack = ItemStack.EMPTY;
    private SmeltingRecipe furnaceRecipe;

    public BlockEntityElectricFurnace(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ELECTRIC_FURNACE, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.electric_furnace_energy_capacity.get(), EnergyType. RECEIVE, EnergyTier.BASIC);
    }

    protected Optional<SmeltingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(input), level);
    }

    protected ItemStack getRecipeResult(ItemStack stack) {
        return this.furnaceRecipe.assemble(new SimpleContainer(stack));
    }

    private boolean canSmelt(ItemStack inputStack, ItemStack outputStack, ItemStack resultStack) {
        return !inputStack.isEmpty() && outputStack.getCount() < outputStack.getMaxStackSize() && (resultStack.getItem() == outputStack.getItem() || outputStack.isEmpty());
    }

    private int getSmeltTime() {
        return this.furnaceRecipe.getCookingTime();
    }

    private boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        return getRecipe(stack).isPresent();
    }

    @Override
    public void tickOperate(BlockState state) {
        progress.clearChanged();
        active = false;
        getEnergyStorage().updateConsumed(0);

        final ItemStack inputStack = getStackHandler().getStackInSlot(INPUT_SLOT);
        final ItemStack outputStack = getStackHandler().getStackInSlot(OUTPUT_SLOT);

        if (cachedInputStack.getItem() != inputStack.getItem()) {
            cachedInputStack = inputStack.copy();
            if (inputStack.getItem() != Items.AIR && getRecipe(inputStack).isPresent()) {
                furnaceRecipe = getRecipe(cachedInputStack).get();
                resultStack = getRecipeResult(inputStack);
            } else {
                furnaceRecipe = null;
            }
        }

        if (furnaceRecipe != null) {
            if (canSmelt(inputStack, outputStack, resultStack) && progress.getProgress() != -1) {

                progress.setProgressMax(getSpeedFactor() * furnaceRecipe.getCookingTime());
                int energyCost = (int) (ServerConfig.electric_furnace_tick_usage.get() * getEnergyUsageFactor());

                if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost) {
                    active = true;
                    progress.incProgress(1);
                    getEnergyStorage().consumeEnergy(energyCost, false);
                    getEnergyStorage().updateConsumed(energyCost);
                }

                if (progress.getProgress() >= progress.getProgressMax()) {
                    if (outputStack.isEmpty()) {
                        getStackHandler().setStackInSlot(OUTPUT_SLOT, resultStack.copy());
                    } else {
                        outputStack.grow(1);
                        getStackHandler().setStackInSlot(OUTPUT_SLOT, outputStack.copy());
                    }

                    inputStack.shrink(1);
                    getStackHandler().setStackInSlot(INPUT_SLOT, inputStack.copy());
                    this.setRecipeUsed(furnaceRecipe);
                    progress.setProgress(-1);
                }
            }
        } else {
            progress.setBoth(-1);
        }

        if (progress.getProgress() == -1) {
            if (canSmelt(inputStack, outputStack, resultStack)) {
                progress.setData(0, (int) (getSmeltTime() * 0.70));
            }
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
        slots.add(new IndRebSlot(OUTPUT_SLOT, 108, 35, InventorySlotType.OUTPUT, GuiSlotType.LARGE, 103, 30));
        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false));
        return super.addBatterySlot(slots);
    }


    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((SmeltingRecipe) recipe).getExperience();
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
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
            return switch (side) {
                case UP -> capabilities.get(1).cast();
                case DOWN -> capabilities.get(2).cast();
                default -> capabilities.get(0).cast();
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
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER);
    }
}
