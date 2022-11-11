package com.maciej916.indreb.common.block.impl.machines.ore_washing_plant;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.FluidStorage;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.recipe.impl.OreWashingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BlockEntityOreWashingPlant extends IndRebBlockEntity implements IEnergyBlock, ISupportUpgrades, ITileSound, IExpCollector {

    public static final int FILL_BUCKET_UP = 0;
    public static final int FILL_BUCKET_DOWN = 1;
    public static final int INPUT_SLOT = 2;
    public static final int OUTPUT_SLOT_1 = 3;
    public static final int OUTPUT_SLOT_2 = 4;

    public BlockEntityProgress progress = new BlockEntityProgress();
    public BlockEntityProgress progressFill = new BlockEntityProgress(0, 1);

    private int cachedInput = 0;
    private ItemStack cachedInputStack = ItemStack.EMPTY;

    private boolean active = false;
    public FluidStorage fluidStorage = new FluidStorage(ServerConfig.ore_washing_plant_fluid_capacity.get());

    protected OreWashingRecipe recipe;

    public BlockEntityOreWashingPlant(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ORE_WASHING_PLANT.get(), pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.ore_washing_plant_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.BASIC);
    }

    protected Optional<OreWashingRecipe> getRawRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.ORE_WASHING.get(), new SimpleContainer(input), level);
    }

    protected Optional<OreWashingRecipe> getRecipe(ItemStack input) {
        List<OreWashingRecipe> recipes = level.getRecipeManager().getAllRecipesFor(ModRecipeType.ORE_WASHING.get());
        for (OreWashingRecipe recipe : recipes) {
            if (recipe.matches(new SimpleContainer(input), level)) {
                if (recipe.getFluidInput().getFluid() == fluidStorage.getFluid().getFluid()) {
                    return Optional.of(recipe);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((OreWashingRecipe) recipe).getExperience();
    }

    private boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        return getRawRecipe(stack).isPresent();
    }

    private boolean canWork(ItemStack inputStack) {

        int resultSize = 0;
        for(ItemStack stack : recipe.getResults()) {
            resultSize += !stack.isEmpty() ? 1 : 0;
        }

        return
                inputStack.getCount() >= recipe.getIngredientCount() &&
                resultSize == 1 ? (
                        (
                            getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).isEmpty()
                        ) ||
                        (
                            getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).getItem() == recipe.getResults().get(0).getItem() &&
                            getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).getCount() + recipe.getResults().get(0).getCount() <= getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize()
                        )
                ) : (
                        (
                            getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).isEmpty() &&
                            getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2).isEmpty()
                        ) ||
                        (
                            (
                                getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).getItem() == recipe.getResults().get(0).getItem() &&
                                getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).getCount() + recipe.getResults().get(0).getCount() <= getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize()
                            ) &&
                            (
                                getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2).getItem() == recipe.getResults().get(1).getItem() &&
                                getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2).getCount() + recipe.getResults().get(1).getCount() <= getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2).getMaxStackSize()
                            )
                        ) ||
                        (
                            (
                                getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).getItem() == recipe.getResults().get(0).getItem() &&
                                getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).getCount() + recipe.getResults().get(0).getCount() <= getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize()
                            ) &&
                            getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2).isEmpty()
                        ) ||
                        (
                            getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).isEmpty() &&
                            (
                                getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2).getItem() == recipe.getResults().get(1).getItem() &&
                                getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2).getCount() + recipe.getResults().get(1).getCount() <= getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2).getMaxStackSize()
                            )
                        )
                );
    }

    @Override
    public void tickWork(BlockState state) {
        active = false;
        boolean updateState = false;
        getEnergyStorage().updateConsumed(0);

        final ItemStack fillBucketUp = getItemStackHandler().getStackInSlot(FILL_BUCKET_UP);
        final ItemStack fillBucketDown = getItemStackHandler().getStackInSlot(FILL_BUCKET_DOWN);
        final ItemStack inputStack = getItemStackHandler().getStackInSlot(INPUT_SLOT);
        final ItemStack outputSlot1 = getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1);
        final ItemStack outputSlot2 = getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2);

        if (cachedInput != fluidStorage.getFluidAmount()) {
            cachedInput = fluidStorage.getFluidAmount();
            updateState = true;
        }

        if (progressFill.getProgress() == 0) {
            boolean filled = BlockEntityUtil.fillTank(fillBucketUp, fillBucketDown, fluidStorage, getItemStackHandler(), FILL_BUCKET_DOWN);
            if (filled) {
                progressFill.setProgress(1);
            }
        } else {
            progressFill.setProgress(0);
        }

        if (progressFill.changed()) {
            updateState = true;
        }

        if (cachedInputStack.getItem() != inputStack.getItem()) {
            cachedInputStack = inputStack.copy();
            if (inputStack.getItem() != Items.AIR && getRecipe(inputStack).isPresent()) {
                recipe = getRecipe(inputStack).get();
            } else {
                recipe = null;
            }
        }

        if (recipe != null) {
            if (fluidStorage.getFluidAmount() >= recipe.getFluidInput().getAmount()) {

                if (progress.getProgress() == -1) {
                    progress.setData(0, recipe.getDuration());
                }

                progress.setProgressMax(getSpeedFactor() * recipe.getDuration());
                int energyCost = (int) (recipe.getPowerCost() * getEnergyUsageFactor());

                if (canWork(inputStack)) {
                    if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost && progress.getProgress() <= progress.getProgressMax()) {
                        active = true;
                        progress.incProgress(1);
                        getEnergyStorage().consumeEnergy(energyCost, false);
                        getEnergyStorage().updateConsumed(energyCost);
                    }

                    if (progress.getProgress() >= progress.getProgressMax()) {
                        inputStack.shrink(recipe.getIngredientCount());
                        getItemStackHandler().setStackInSlot(INPUT_SLOT, inputStack.copy());
                        fluidStorage.drain(recipe.getFluidInput().getAmount(), IFluidHandler.FluidAction.EXECUTE);

                        List<ItemStack> results = recipe.getResults();

                        if (outputSlot1.isEmpty()) {
                            getItemStackHandler().setStackInSlot(OUTPUT_SLOT_1, results.get(0).copy());
                        } else {
                            getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1).grow(results.get(0).getCount());
                        }

                        if (outputSlot2.isEmpty() && !results.get(1).isEmpty()) {
                            getItemStackHandler().setStackInSlot(OUTPUT_SLOT_2, results.get(1).copy());
                        } else {
                            getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2).grow(results.get(1).getCount());
                        }

                        this.addRecipeUsed(recipe);
                        progress.setBoth(-1);
                    }
                }
            }
        } else {
            progress.setBoth(-1);
        }


        if (progress.changed()) {
            updateState = true;
        }

        this.setActive(active);
        if (updateState) {
            this.updateBlockState();
        }
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(FILL_BUCKET_UP, 8, 19, InventorySlotType.INPUT, GuiSlotType.NORMAL, 7, 18));
        slots.add(new IndRebSlot(FILL_BUCKET_DOWN, 8, 50, InventorySlotType.OUTPUT, GuiSlotType.NORMAL, 7, 49));
        slots.add(new IndRebSlot(INPUT_SLOT, 65, 34, InventorySlotType.INPUT, GuiSlotType.NORMAL, 64, 33));
        slots.add(new IndRebSlot(OUTPUT_SLOT_1, 122, 25, InventorySlotType.OUTPUT, GuiSlotType.NORMAL_BLANK, 121, 24));
        slots.add(new IndRebSlot(OUTPUT_SLOT_2, 122, 44, InventorySlotType.OUTPUT, GuiSlotType.NORMAL_BLANK, 121, 43));

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
        return ModSounds.ORE_WASHING_PLANT.get();
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return isValidInput(stack);

        if (slot == FILL_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                return (
                        (cap.getTanks() > 0 && fluidStorage.getFluidInTank(1).getFluid() == Fluids.EMPTY) ||
                        (cap.getFluidInTank(1).getFluid() == fluidStorage.getFluid().getFluid())
                );
            }
        }

        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == INPUT_SLOT && !isValidInput(stack)) return stack;

        if (slot == FILL_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                if (
                        (cap.getTanks() > 0 && fluidStorage.getFluidInTank(1).getFluid() == Fluids.EMPTY) ||
                        (cap.getFluidInTank(1).getFluid() == fluidStorage.getFluid().getFluid())

                ) {
                    return null;
                }
            }
            return stack;
        }

        return super.insertItemForSlot(slot, stack, simulate);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.active = tag.getBoolean("active");
        this.progress.deserializeNBT(tag.getCompound("progress"));
        this.progressFill.deserializeNBT(tag.getCompound("progressFill"));
        this.fluidStorage.readFromNBT(tag.getCompound("fluidStorage"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("active", active);
        tag.put("progress", this.progress.serializeNBT());
        tag.put("progressFill", this.progressFill.serializeNBT());
        tag.put("fluidStorage", this.fluidStorage.writeToNBT(tag.getCompound("fluidStorage")));
        return super.save(tag);
    }

    private final ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getItemStackHandler),
            LazyOptional.of(() -> this.fluidStorage),
            LazyOptional.of(() -> new RangedWrapper(getItemStackHandler(), INPUT_SLOT, INPUT_SLOT + 1)),
            LazyOptional.of(() -> new RangedWrapper(getItemStackHandler(), FILL_BUCKET_UP, FILL_BUCKET_DOWN)),
            LazyOptional.of(() -> new RangedWrapper(getItemStackHandler(), OUTPUT_SLOT_1, OUTPUT_SLOT_2))
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) return capabilities.get(0).cast();
            return switch (side) {
                case DOWN -> capabilities.get(4).cast();
                case UP, NORTH, SOUTH, WEST, EAST -> capabilities.get(2).cast();
            };
        }

        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return capabilities.get(1).cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onBreakServer() {
        for (LazyOptional<?> capability : capabilities) capability.invalidate();
        super.onBreakServer();
    }

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.FLUID_PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER);
    }
}
