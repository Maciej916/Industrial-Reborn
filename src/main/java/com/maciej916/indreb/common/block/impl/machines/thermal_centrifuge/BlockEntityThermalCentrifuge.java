package com.maciej916.indreb.common.block.impl.machines.thermal_centrifuge;

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
import com.maciej916.indreb.common.recipe.impl.ThermalCentrifugingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
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

public class BlockEntityThermalCentrifuge extends IndRebBlockEntity implements IEnergyBlock, ISupportUpgrades, ITileSound, IExpCollector {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT_1 = 1;
    public static final int OUTPUT_SLOT_2 = 2;

    public BlockEntityProgress progress = new BlockEntityProgress();
    public BlockEntityProgress tempLevel = new BlockEntityProgress(0, 3000);

    private int cachedInput = 0;
    private ItemStack cachedInputStack = ItemStack.EMPTY;

    private boolean active = false;
    protected ThermalCentrifugingRecipe recipe;

    public BlockEntityThermalCentrifuge(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.THERMAL_CENTRIFUGE.get(), pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.thermal_centrifuge_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.STANDARD);
    }

    protected Optional<ThermalCentrifugingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.THERMAL_CENTRIFUGING.get(), new SimpleContainer(input), level);
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((IBaseRecipe) recipe).getExperience();
    }

    private boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        return getRecipe(stack).isPresent();
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
        boolean decHeat = false;
        boolean updateState = false;
        getEnergyStorage().updateConsumed(0);

        final ItemStack inputStack = getItemStackHandler().getStackInSlot(INPUT_SLOT);
        final ItemStack outputSlot1 = getItemStackHandler().getStackInSlot(OUTPUT_SLOT_1);
        final ItemStack outputSlot2 = getItemStackHandler().getStackInSlot(OUTPUT_SLOT_2);

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

           if (tempLevel.getProgress() >= recipe.getTemperature()) {
               int energyCost = (int) (recipe.getPowerCost() * getEnergyUsageFactor());

               if (canWork(inputStack) && getEnergyStorage().consumeEnergy(energyCost, true) >= energyCost) {

                   if (progress.getProgress() <= progress.getProgressMax()) {
                       active = true;
                       progress.incProgress(1);
                       getEnergyStorage().consumeEnergy(energyCost, false);
                       getEnergyStorage().updateConsumed(energyCost);
                   }

                   if (progress.getProgress() >= progress.getProgressMax()) {
                       inputStack.shrink(recipe.getIngredientCount());
                       getItemStackHandler().setStackInSlot(INPUT_SLOT, inputStack.copy());

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

                   if (tickCounter == 20) {
                       if (tempLevel.getProgress() < tempLevel.getProgressMax()) {
                           tempLevel.incProgress(1.5f);
                       }
                   }
               } else {
                   decHeat = true;
               }
           } else {
               if (tickCounter == 20) {
                   if (tempLevel.getProgress() < tempLevel.getProgressMax() && getEnergyStorage().consumeEnergy(ServerConfig.thermal_centrifuge_temp_cost.get(), true) >= ServerConfig.thermal_centrifuge_temp_cost.get()) {
                       tempLevel.incProgress(1.5f);
                       getEnergyStorage().consumeEnergy(ServerConfig.thermal_centrifuge_temp_cost.get(), false);
                   }
               }
           }
        } else {
            decHeat = true;
            progress.setBoth(-1);
        }






//        if ((getRedstonePower() > 0 && getEnergyStorage().consumeEnergy(ServerConfig.thermal_centrifuge_temp_cost.get(), true) >= ServerConfig.alloy_smelter_heat_cost.get() || active)) {
//            if (tempLevel.getProgress() < 100) {
//                if (tickCounter == 20) {
//                    tempLevel.incProgress(1.5f);
//                    if (!active) {
//                        getEnergyStorage().consumeEnergy(ServerConfig.thermal_centrifuge_temp_cost.get(), false);
//                    }
//                }
//            }

        if (decHeat) {
            if (tempLevel.getProgress() > 0) {
                if (tickCounter == 20) {
                    tempLevel.decProgress(Math.min(tempLevel.getProgress(), 2.5f));
                }
            }
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
        slots.add(new IndRebSlot(INPUT_SLOT, 48, 33, InventorySlotType.INPUT, GuiSlotType.NORMAL, 47, 32));
        slots.add(new IndRebSlot(OUTPUT_SLOT_1, 109, 25, InventorySlotType.OUTPUT, GuiSlotType.NORMAL_BLANK, 108, 24));
        slots.add(new IndRebSlot(OUTPUT_SLOT_2, 109, 44, InventorySlotType.OUTPUT, GuiSlotType.NORMAL_BLANK, 108, 43));

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
        return ModSounds.THERMAL_CENTRIFUGE.get();
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return isValidInput(stack);
        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == INPUT_SLOT && !isValidInput(stack)) return stack;
        return super.insertItemForSlot(slot, stack, simulate);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.active = tag.getBoolean("active");
        this.progress.deserializeNBT(tag.getCompound("progress"));
        this.tempLevel.deserializeNBT(tag.getCompound("tempLevel"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("active", active);
        tag.put("progress", this.progress.serializeNBT());
        tag.put("tempLevel", this.tempLevel.serializeNBT());
        return super.save(tag);
    }

    private final ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getItemStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getItemStackHandler(), INPUT_SLOT, INPUT_SLOT + 1)),
            LazyOptional.of(() -> new RangedWrapper(getItemStackHandler(), OUTPUT_SLOT_1, OUTPUT_SLOT_2))
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
    public void onBreakServer() {
        for (LazyOptional<?> capability : capabilities) capability.invalidate();
        super.onBreakServer();
    }

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER);
    }
}
