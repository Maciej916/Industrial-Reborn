package com.maciej916.indreb.common.block.impl.machines.standard.alloy_smelter;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasUpgrades;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.*;
import com.maciej916.indreb.common.api.recipe.interfaces.IBaseRecipe;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.slot.OutputSlot;
import com.maciej916.indreb.common.api.util.Progress;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.AlloySmeltingRecipe;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.StackHandlerHelper;
import com.maciej916.indreb.common.util.WrappedHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BlockEntityAlloySmelter extends IndRebBlockEntity implements IHasExp, IHasUpgrades, IBlockEntityEnergy, IHasSound {

    public static final int SYNC_DATA_SLOTS = 4;
    protected final ContainerData data;

    public static final int INPUT_SLOT_0 = 0;
    public static final int INPUT_SLOT_1 = 1;
    public static final int INPUT_SLOT_2 = 2;
    public static final int OUTPUT_SLOT = 3;

    protected static List<AlloySmeltingRecipe> recipes;
    protected AlloySmeltingRecipe recipe;

    private ItemStack cachedInputStack0 = ItemStack.EMPTY;
    private ItemStack cachedInputStack1 = ItemStack.EMPTY;
    private ItemStack cachedInputStack2 = ItemStack.EMPTY;

    public Progress progressRecipe = new Progress();
    public Progress progressHeat = new Progress(0, 100);

    public BlockEntityAlloySmelter(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ALLOY_SMELTER.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.alloy_smelter_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.STANDARD);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlockEntityAlloySmelter.this.progressRecipe.getContainerDataCurrent();
                    case 1 -> BlockEntityAlloySmelter.this.progressRecipe.getContainerDataMax();
                    case 2 -> BlockEntityAlloySmelter.this.progressHeat.getContainerDataCurrent();
                    case 3 -> BlockEntityAlloySmelter.this.progressHeat.getContainerDataMax();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlockEntityAlloySmelter.this.progressRecipe.setContainerDataCurrent(value);
                    case 1 -> BlockEntityAlloySmelter.this.progressRecipe.setContainerDataMax(value);
                    case 2 -> BlockEntityAlloySmelter.this.progressHeat.setContainerDataCurrent(value);
                    case 3 -> BlockEntityAlloySmelter.this.progressHeat.setContainerDataMax(value);
                }
            }

            @Override
            public int getCount() {
                return SYNC_DATA_SLOTS;
            }
        };
    }

    @Override
    public void tickWork() {
        ItemStack inputStack0 = getBaseStorage().getStackInSlot(INPUT_SLOT_0);
        ItemStack inputStack1 = getBaseStorage().getStackInSlot(INPUT_SLOT_1);
        ItemStack inputStack2 = getBaseStorage().getStackInSlot(INPUT_SLOT_2);

        if (recipes == null) {
            initRecipes();
        }

        if (
                baseSlotsChangedForTick.contains(INPUT_SLOT_0) ||
                baseSlotsChangedForTick.contains(INPUT_SLOT_1) ||
                baseSlotsChangedForTick.contains(INPUT_SLOT_2) ||
                (recipe == null && (!inputStack0.isEmpty() || !inputStack1.isEmpty() || !inputStack2.isEmpty())) ||
                cachedInputStack0 != inputStack0 ||
                cachedInputStack1 != inputStack1 ||
                cachedInputStack2 != inputStack2
        ) {
            AlloySmeltingRecipe oldRecipe = recipe;

            cachedInputStack0 = inputStack0.copy();
            cachedInputStack1 = inputStack1.copy();
            cachedInputStack2 = inputStack2.copy();

            if (inputStack0.isEmpty() && inputStack1.isEmpty() && inputStack2.isEmpty()) {
                recipe = null;
            } else {
                ArrayList<ItemStack> inputStacks = new ArrayList<>(List.of(inputStack0, inputStack1, inputStack2));
                inputStacks.remove(ItemStack.EMPTY);
                recipe = getRecipe(inputStacks.toArray(ItemStack[]::new)).orElse(null);
            }

            if (oldRecipe != recipe && oldRecipe != null) {
                progressRecipe.resetProgress();
            }
        }

        if (recipe != null) {
            if (progressRecipe.currentProgress() == -1) {
                progressRecipe.setData(0, recipe.getDuration());
            }

            progressRecipe.setProgressMax(getUpgradesDuration(recipe.getDuration()));
            int energyCost = getUpgradesEnergyCost(recipe.getTickEnergyCost());

            if (canWork()) {
                int costStack0 = recipe.getIngredientCost(inputStack0);
                int costStack1 = recipe.getIngredientCost(inputStack1);
                int costStack2 = recipe.getIngredientCost(inputStack2);

                if (
                        getEnergyStorage().consumeEnergy(energyCost, true) == energyCost &&
                        (costStack0 == 0 ||  inputStack0.getCount() >= costStack0) &&
                        (costStack1 == 0 ||  inputStack1.getCount() >= costStack1) &&
                        (costStack2 == 0 ||  inputStack2.getCount() >= costStack2) &&
                        !progressRecipe.isCurrentAboveEqualMax()
                ) {
                    activeState = true;
                    progressRecipe.incProgress(1 + (progressHeat.currentProgress() / 100f));
                    getEnergyStorage().consumeEnergy(energyCost, false);
                }

                if (progressRecipe.isCurrentAboveEqualMax()) {
                    if (costStack0 != 0) StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT_0, costStack0);
                    if (costStack1 != 0) StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT_1, costStack1);
                    if (costStack2 != 0) StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT_2, costStack2);
                    StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT, recipe.getResultItem());
                    progressRecipe.resetProgress();
                    addRecipeUsed(recipe);
                }
            }
        }

        if ((getRedstonePower() > 0 && getEnergyStorage().consumeEnergy(ServerConfig.alloy_smelter_heat_cost.get(), true) >= ServerConfig.alloy_smelter_heat_cost.get() || activeState)) {

            if (getTickCounter() == 20) {
                if (!activeState) {
                    getEnergyStorage().consumeEnergy(ServerConfig.alloy_smelter_heat_cost.get(), false);
                }

                if (progressHeat.currentProgress() + 0.2f < 100) {
                    progressHeat.incProgress(0.2f);
                }
            }

            activeState = true;

        } else {
            if (progressHeat.currentProgress() > 0) {
                if (getTickCounter() == 20) {
                    progressHeat.decProgress(Math.min(progressHeat.currentProgress(), 1));
                }
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuAlloySmelter(this, containerId, playerInventory, player, data);
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == INPUT_SLOT_0 ||  slot == INPUT_SLOT_1 || slot == INPUT_SLOT_2) return getRecipe(stack).isPresent();
        return false;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(INPUT_SLOT_0, 16, 33, 15, 32, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new BaseSlot(INPUT_SLOT_1, 37, 21, 36, 20, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new BaseSlot(INPUT_SLOT_2, 58, 33, 57, 32, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(OUTPUT_SLOT,  118, 33, 113, 28, GuiSlotBg.LARGE));

        return super.addBaseSlots(slots);
    }

    @Override
    public ArrayList<ElectricSlot> addElectricSlots(ArrayList<ElectricSlot> slots) {
        slots.add(new ElectricSlot(0, 152, 62, InventorySlotType.ELECTRIC, GuiSlotBg.BATTERY, true, false));
        return super.addElectricSlots(slots);
    }

    @Override
    public boolean canReceiveEnergyCustom(Direction side) {
        return true;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progressRecipe.deserializeNBT(tag.getCompound("progressRecipe"));
        this.progressHeat.deserializeNBT(tag.getCompound("progressHeat"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
        tag.put("progressHeat", this.progressHeat.serializeNBT());
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((IBaseRecipe) recipe).getExperience();
    }

    private final Map<Direction, LazyOptional<WrappedHandler>> itemCapabilities = Map.of(
            Direction.UP, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> i == OUTPUT_SLOT, (i, stack) -> false)),
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
                if (side == Direction.UP || side == Direction.DOWN) return itemCapabilities.get(side).cast(); // UP - DOWN

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

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.ALLOY_SMELTER.get();
    }

    public void initRecipes() {
        recipes = Objects.requireNonNull(getLevel()).getRecipeManager().getAllRecipesFor(ModRecipeType.ALLOY_SMELTING.get());
    }

    protected Optional<AlloySmeltingRecipe> getRecipe(ItemStack... input) {
        if (level != null) {
            if (recipes == null) initRecipes();
            if (recipes != null) {
                for (AlloySmeltingRecipe recipe : recipes) {
                    if (recipe.matches(new SimpleContainer(input), level)) {
                        return Optional.of(recipe);
                    }
                }
            }
        }

        return Optional.empty();
    }

    private boolean canWork() {
        final ItemStack outputStack = getBaseStorage().getStackInSlot(OUTPUT_SLOT);
        return outputStack.isEmpty() || (outputStack.getCount() + recipe.getResultItem().getCount() <= outputStack.getMaxStackSize() && recipe.getResultItem().getItem() == outputStack.getItem());
    }
}
