package com.maciej916.indreb.common.block.impl.machines.metal_former;

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
import com.maciej916.indreb.common.interfaces.receipe.IBaseRecipe;
import com.maciej916.indreb.common.interfaces.receipe.IRecipeSingleIngredient;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketMetalFormerChangeMode;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
import com.maciej916.indreb.common.util.StackHandlerHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BlockEntityMeralFormer extends IndRebBlockEntity implements IEnergyBlock, ISupportUpgrades, ITileSound, IExpCollector {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    public BlockEntityProgress progress = new BlockEntityProgress();
    protected MetalFormerMode mode = MetalFormerMode.CUTTING;
    protected IRecipeSingleIngredient recipe;

    public BlockEntityMeralFormer(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.METAL_FORMER.get(), blockPos, blockState);
        createEnergyStorage(0, ServerConfig.metal_former_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.BASIC);

        newUpdateStateTempVar = true;
    }

    @Override
    public boolean inputSlotChanged(int slotId, ItemStack oldStack, ItemStack newStack) {
        boolean validChange = false;
        IRecipeSingleIngredient oldRecipe = recipe;

        if (oldStack.getItem() != newStack.getItem()) {
            if (newStack.getItem() != Items.AIR) {
                Optional<?> optionalRecipe = getRecipe(newStack);
                if (optionalRecipe.isPresent()) {
                    recipe = (IRecipeSingleIngredient) optionalRecipe.get();
                } else {
                    recipe = null;
                }
            } else {
                recipe = null;
            }

            validChange = true;
        } else {
            if (recipe != null) {
                if (newStack.getCount() < recipe.getIngredientCount()) {
                    recipe = null;
                    validChange = true;
                }
            }
        }

        if (oldRecipe == null && recipe != null) {
            validChange = false;
        }

        return validChange;
    }

    @Override
    public void tickWork(BlockState state) {
        boolean validChange = checkInputSlotChange(INPUT_SLOT);

        if (validChange) {
            progress.setBoth(-1);
        }

        if (recipe != null) {
            if (progress.getProgress() == -1) {
                progress.setData(0, recipe.getDuration());
            }

            if (canWork()) {
                progress.setProgressMax(getSpeedFactor() * recipe.getDuration());

                int energyCost = (int) (recipe.getPowerCost() * getEnergyUsageFactor());

                if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost && progress.getProgress() <= progress.getProgressMax()) {
                    if (progress.getProgress() <= progress.getProgressMax()) {
                        activeState = true;
                        progress.incProgress(1);
                        getEnergyStorage().consumeEnergy(energyCost, false);
                        getEnergyStorage().updateConsumed(energyCost);
                    }
                }

                if (progress.getProgress() >= progress.getProgressMax()) {
                    StackHandlerHelper.shrinkInputStack(getItemStackHandler(), INPUT_SLOT, recipe.getIngredientCount());
                    StackHandlerHelper.incMachineOutputStack(getItemStackHandler(), OUTPUT_SLOT, recipe.getResultItem());

                    addRecipeUsed(recipe);
                    progress.setBoth(-1);
                }
            }
        }

        if (progress.changed()) {
            shouldUpdateState = true;
        }
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 47, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 46, 34));
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
        return ModSounds.METAL_FORMER.get();
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return isValidInput(stack);
        return false;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progress.deserializeNBT(tag.getCompound("progress"));
        this.mode = MetalFormerMode.getModeFromId(tag.getInt("mode"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("progress", this.progress.serializeNBT());
        tag.putInt("mode", mode.getId());

        return super.save(tag);
    }

    private final ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getItemStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getItemStackHandler(), INPUT_SLOT, INPUT_SLOT + 1))
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) return capabilities.get(0).cast();
            return switch (side) {
                case DOWN -> capabilities.get(2).cast();
                case UP, NORTH, SOUTH, WEST, EAST -> capabilities.get(1).cast();
            };
        }

        return super.getCapability(cap, side);
    }

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER);
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((IBaseRecipe) recipe).getExperience();
    }

    protected Optional<?> getRecipe(ItemStack input) {
        switch (mode.getId()) {
            case 1 -> {
                return level.getRecipeManager().getRecipeFor(ModRecipeType.CUTTING.get(), new SimpleContainer(input), level);
            }
            case 2 -> {
                return level.getRecipeManager().getRecipeFor(ModRecipeType.ROLLING.get(), new SimpleContainer(input), level);
            }
            case 3 -> {
                return level.getRecipeManager().getRecipeFor(ModRecipeType.EXTRUDING.get(), new SimpleContainer(input), level);
            }
        }
        return Optional.empty();
    }

    protected boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        return getRecipe(stack).isPresent();
    }

    public MetalFormerMode getMode() {
        return mode;
    }

    public Runnable clientClickChangeMode () {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketMetalFormerChangeMode(getBlockPos()));
    }

    public void changeMode() {
        switch (mode.getId()) {
            case 1 -> mode = MetalFormerMode.ROLLING;
            case 2 -> mode = MetalFormerMode.EXTRUDING;
            case 3 -> mode = MetalFormerMode.CUTTING;
        }

        final ItemStack inputStack = getItemStackHandler().getStackInSlot(INPUT_SLOT);
        Optional<?> optionalRecipe = getRecipe(inputStack);
        if (optionalRecipe.isPresent()) {
            recipe = (IRecipeSingleIngredient) optionalRecipe.get();
        } else {
            recipe = null;
            setActiveState(false);
        }

        progress.setBoth(-1);
        updateBlockState();
    }

    private boolean canWork() {
        final ItemStack outputStack = getItemStackHandler().getStackInSlot(OUTPUT_SLOT);
        return outputStack.isEmpty() || (outputStack.getItem() == recipe.getResultItem().getItem() && outputStack.getCount() + recipe.getResultItem().getCount() <= outputStack.getMaxStackSize());
    }
}
