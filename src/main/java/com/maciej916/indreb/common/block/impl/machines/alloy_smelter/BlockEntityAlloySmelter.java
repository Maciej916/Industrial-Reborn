package com.maciej916.indreb.common.block.impl.machines.alloy_smelter;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.receipe.impl.AlloySmeltingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
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
import java.util.*;

import static com.maciej916.indreb.common.enums.EnumEnergyType.RECEIVE;

public class BlockEntityAlloySmelter extends IndRebBlockEntity implements IEnergyBlock, IExpCollector, ISupportUpgrades {

    public static final int INPUT_SLOT_0 = 0;
    public static final int INPUT_SLOT_1 = 1;
    public static final int INPUT_SLOT_2 = 2;
    public static final int OUTPUT_SLOT = 3;

    public BlockEntityProgress progress = new BlockEntityProgress();
    public BlockEntityProgress heatLevel = new BlockEntityProgress(0, 100);

    private boolean active = false;
    private ItemStack resultStack = ItemStack.EMPTY;
    protected AlloySmeltingRecipe recipe;
    private int energyCostPerTick = 0;
    private int duration = 0;

    private ItemStack cachedInputStack1 = ItemStack.EMPTY;
    private ItemStack cachedInputStack2 = ItemStack.EMPTY;
    private ItemStack cachedInputStack3 = ItemStack.EMPTY;
    private ItemStack cachedOutput = ItemStack.EMPTY;
    private boolean cachedWork;



    public BlockEntityAlloySmelter(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ALLOY_SMELTER, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.alloy_smelter_energy_capacity.get(), EnergyTier.STANDARD.getBasicTransfer(), 0, RECEIVE);
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT_0, 16, 33, InventorySlotType.INPUT, GuiSlotType.NORMAL, 15, 32));
        slots.add(new IndRebSlot(INPUT_SLOT_1, 37, 21, InventorySlotType.INPUT, GuiSlotType.NORMAL, 36, 20));
        slots.add(new IndRebSlot(INPUT_SLOT_2, 58, 33, InventorySlotType.INPUT, GuiSlotType.NORMAL, 57, 32));
        slots.add(new IndRebSlot(OUTPUT_SLOT, 118, 33, InventorySlotType.OUTPUT, GuiSlotType.LARGE, 113, 28));
        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false, List.of(EnergyTier.STANDARD)));
        return super.addBatterySlot(slots);
    }

    protected Optional<AlloySmeltingRecipe> getRecipe(ItemStack... input) {
        if (level == null) return Optional.empty();
        return level.getRecipeManager().getRecipeFor(ModRecipeType.ALLOY_SMELTING, new SimpleContainer(input), level);
    }

    protected ItemStack getRecipeResult(ItemStack... stack) {
        return this.recipe.assemble(new SimpleContainer(stack));
    }

    private boolean isValidInput(final ItemStack... stack) {
        return getRecipe(stack).isPresent();
    }

    boolean canWork(ItemStack inputStack0, ItemStack inputStack1, ItemStack inputStack2, ItemStack outputStack, ItemStack resultStack) {
        return isValidInput(inputStack0, inputStack1, inputStack2) && (outputStack.isEmpty() || (resultStack.getCount() + outputStack.getCount() <= outputStack.getMaxStackSize() && resultStack.getItem() == outputStack.getItem()));
    }

    @Override
    public void tickOperate(BlockState state) {
        active = false;

        final ItemStack inputStack0 = getStackHandler().getStackInSlot(INPUT_SLOT_0);
        final ItemStack inputStack1 = getStackHandler().getStackInSlot(INPUT_SLOT_1);
        final ItemStack inputStack2 = getStackHandler().getStackInSlot(INPUT_SLOT_2);
        final ItemStack outputStack = getStackHandler().getStackInSlot(OUTPUT_SLOT);

        if (cachedInputStack1 != inputStack0 || cachedInputStack2 != inputStack1 || cachedInputStack3 != inputStack2 || cachedOutput != outputStack) {
            cachedInputStack1 = inputStack0.copy();
            cachedInputStack2 = inputStack1.copy();
            cachedInputStack3 = inputStack2.copy();
            cachedOutput = outputStack.copy();

            if (getRecipe(inputStack0, inputStack1, inputStack2).isPresent()) {
                recipe = getRecipe(inputStack0, inputStack1, inputStack2).get();
                resultStack = getRecipeResult(inputStack0, inputStack1, inputStack2);
                energyCostPerTick = recipe.getPowerCost();
                duration = recipe.getDuration();
            } else {
                recipe = null;
            }

            cachedWork = false;
        }

        if (recipe != null && (cachedWork || canWork(inputStack0, inputStack1, inputStack2, outputStack, resultStack))) {
            if (progress.getProgress() == -1) {
                progress.setData(0, duration);
            }

            if (getEnergyStorage().consumeEnergy(energyCostPerTick, true) == energyCostPerTick && progress.getProgress() <= progress.getProgressMax()) {
                if (progress.getProgress() <= progress.getProgressMax()) {
                    active = true;
                    progress.incProgress(1 + (heatLevel.getPercentProgress() / 100f));
                    getEnergyStorage().consumeEnergy(energyCostPerTick, false);
                }
            }

            if (progress.getProgress() >= progress.getProgressMax()) {
                if (outputStack.isEmpty()) {
                    getStackHandler().setStackInSlot(OUTPUT_SLOT, resultStack.copy());
                } else {
                    outputStack.grow(resultStack.getCount());
                    getStackHandler().setStackInSlot(OUTPUT_SLOT, outputStack.copy());
                }

                int cost0 = this.recipe.getIngredientCost(inputStack0);
                if (cost0 > 0) {
                    inputStack0.shrink(cost0);
                    getStackHandler().setStackInSlot(INPUT_SLOT_0, inputStack0.copy());
                }

                int cost1 = this.recipe.getIngredientCost(inputStack1);
                if (cost1 > 0) {
                    inputStack1.shrink(cost1);
                    getStackHandler().setStackInSlot(INPUT_SLOT_1, inputStack1.copy());
                }

                int cost2 = this.recipe.getIngredientCost(inputStack2);
                if (cost2 > 0) {
                    inputStack2.shrink(cost2);
                    getStackHandler().setStackInSlot(INPUT_SLOT_2, inputStack2.copy());
                }

                this.setRecipeUsed(recipe);
                progress.setBoth(-1);
            }
        } else {
            progress.setBoth(-1);
        }

        if ((getRedstonePower() > 0 && getEnergyStorage().consumeEnergy(ServerConfig.alloy_smelter_heat_cost.get(), true) >= ServerConfig.alloy_smelter_heat_cost.get() || active)) {
            if (heatLevel.getProgress() < 100) {
                if (tickCounter == 20) {
                    heatLevel.incProgress(0.2f);
                    if (!active) {
                        getEnergyStorage().consumeEnergy(ServerConfig.alloy_smelter_heat_cost.get(), false);
                    }
                }
            }

        } else {
            if (heatLevel.getProgress() > 0) {
                if (tickCounter == 20) {
                    heatLevel.decProgress(Math.min(heatLevel.getProgress(), 1));
                }
            }
        }

        this.setActive(active);
        if (heatLevel.changed() || progress.changed()) {
            super.updateBlockState();
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT_0) return isValidInput(stack);
        if (slot == INPUT_SLOT_1) return isValidInput(stack);
        if (slot == INPUT_SLOT_2) return isValidInput(stack);
        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if ((slot == INPUT_SLOT_0 || slot == INPUT_SLOT_1 || slot == INPUT_SLOT_2) && !isValidInput(stack)) {
            return stack;
        }
        return super.insertItemForSlot(slot, stack, simulate);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.active = tag.getBoolean("active");
        this.progress.deserializeNBT(tag.getCompound("progress"));
        this.heatLevel.deserializeNBT(tag.getCompound("heatLevel"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("active", active);
        tag.put("progress", this.progress.serializeNBT());
        tag.put("heatLevel", this.heatLevel.serializeNBT());
        return super.save(tag);
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((AlloySmeltingRecipe) recipe).getExperience();
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
    }


    ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), INPUT_SLOT_0, INPUT_SLOT_2 + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), OUTPUT_SLOT, OUTPUT_SLOT + 1))
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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
}
