package com.maciej916.indreb.common.block.impl.machine.t_standard.thermal_centrifuge;

import com.maciej916.indreb.IndReb;
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
import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import com.maciej916.indreb.common.api.top.impl.ProbeInfoSimpleText;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.ThermalCentrifugingRecipe;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.StackHandlerHelper;
import com.maciej916.indreb.common.util.WrappedHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BlockEntityThermalCentrifuge extends IndRebBlockEntity implements IHasExp, IHasUpgrades, IBlockEntityEnergy, IHasSound {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT_1 = 1;
    public static final int OUTPUT_SLOT_2 = 2;

    protected static List<ThermalCentrifugingRecipe> recipes;
    protected ThermalCentrifugingRecipe recipe;

    public ProgressFloat progressRecipe = new ProgressFloat();
    public ProgressFloat progressTemp = new ProgressFloat(0, 3000);

    private boolean rolledChance = false;
    private ItemStack chanceResult;

    public BlockEntityThermalCentrifuge(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.THERMAL_CENTRIFUGE.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.thermal_centrifuge_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.STANDARD);

        this.containerData.syncProgressFloat(0, this.progressRecipe);
        this.containerData.syncProgressFloat(1, this.progressTemp);
    }

    @Override
    public void tickWork() {
        ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        ItemStack outputStack1 = getBaseStorage().getStackInSlot(OUTPUT_SLOT_1);
        ItemStack outputStack2 = getBaseStorage().getStackInSlot(OUTPUT_SLOT_2);

        if (recipes == null) {
            initRecipes();
        }

        if (baseSlotsChangedForTick.contains(INPUT_SLOT) || (recipe == null && !inputStack.isEmpty())) {
            ThermalCentrifugingRecipe oldRecipe = recipe;

            if (inputStack.isEmpty()) {
                recipe = null;
            } else {
                recipe = getRecipe(inputStack).orElse(null);
            }

            if (oldRecipe != recipe && oldRecipe != null) {
                progressRecipe.resetProgress();
            }
        }

        if (recipe != null) {
            if (progressRecipe.getCurrentProgress() == -1) {
                progressRecipe.setData(0, recipe.getDuration());
            }

            if (!rolledChance) {
                chanceResult = recipe.rollChanceResult();
                rolledChance = true;
            }

            progressRecipe.setProgressMax(getUpgradesDuration(recipe.getDuration()));
            int energyCost = getUpgradesEnergyCost(recipe.getTickEnergyCost());

            if (canWork()) {
                if (
                        getEnergyStorage().consumeEnergy(energyCost, true) == energyCost &&
                        (outputStack1.isEmpty() || (recipe.getResultItem().getCount() + outputStack1.getCount() <= outputStack1.getMaxStackSize() && recipe.getResultItem().getItem() == outputStack1.getItem())) &&
                        !progressRecipe.isCurrentAboveEqualMax()
                ) {
                    getEnergyStorage().consumeEnergy(energyCost, false);
                    activeState = true;

                    if (progressTemp.getCurrentProgress() >= recipe.getTemperature()) {
                        progressRecipe.incProgress(1);
                    }

                    if (getTickCounter() == 20) {
                        if (progressTemp.getCurrentProgress() + 1.5f <= progressTemp.getProgressMax()) {
                            progressTemp.incProgress(1.5f);
                        }
                    }
                }

                if (progressRecipe.isCurrentAboveEqualMax()) {
                    if (outputStack2.isEmpty() || chanceResult.isEmpty() || (chanceResult.getCount() + outputStack2.getCount() <= outputStack2.getMaxStackSize() && chanceResult.getItem() == outputStack2.getItem())) {
                        StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT_1, recipe.getResultItem());
                        StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT, recipe.getIngredientCount().getIngredientsCount().get(0));
                        progressRecipe.resetProgress();
                        addRecipeUsed(recipe);
                        rolledChance = false;

                        if (!chanceResult.isEmpty()) {
                            StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT_2, chanceResult);
                        }
                    }
                }
            }
        }

        if (activeState) {
            if (getTickCounter() == 20) {
                if (progressTemp.getCurrentProgress() + 1.5f < progressTemp.getProgressMax()) {
                    progressTemp.incProgress(1.5f);
                }
            }
        } else {
            if (progressTemp.getCurrentProgress() > 0) {
                if (getTickCounter() == 20) {
                    progressTemp.decProgress(Math.min(progressTemp.getCurrentProgress(), 2.5f));
                }
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuThermalCentrifuge(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == INPUT_SLOT) return getRecipe(stack).isPresent();
        return false;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(INPUT_SLOT, 48, 33, 47, 32, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(OUTPUT_SLOT_1,  109, 25, 108, 24, GuiSlotBg.NORMAL_BLANK));
        slots.add(new OutputSlot(OUTPUT_SLOT_2, 109, 44, 108, 43, GuiSlotBg.NORMAL_BLANK));

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
        this.progressTemp.deserializeNBT(tag.getCompound("progressTemp"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
        tag.put("progressTemp", this.progressTemp.serializeNBT());
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((IBaseRecipe) recipe).getExperience();
    }

    private final Map<Direction, LazyOptional<WrappedHandler>> itemCapabilities = Map.of(
            Direction.UP, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> i == OUTPUT_SLOT_1 || i == OUTPUT_SLOT_2, (i, stack) -> false)),
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
        return ModSounds.THERMAL_CENTRIFUGE.get();
    }

    public void initRecipes() {
        recipes = Objects.requireNonNull(getLevel()).getRecipeManager().getAllRecipesFor(ModRecipeType.THERMAL_CENTRIFUGING.get());
    }

    protected Optional<ThermalCentrifugingRecipe> getRecipe(ItemStack input) {
        if (level != null) {
            if (recipes == null) initRecipes();
            if (recipes != null) {
                for (ThermalCentrifugingRecipe recipe : recipes) {
                    if (recipe.matches(new SimpleContainer(input), level)) {
                        return Optional.of(recipe);
                    }
                }
            }
        }

        return Optional.empty();
    }

    private boolean canWork() {
        final ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        final ItemStack outputStack1 = getBaseStorage().getStackInSlot(OUTPUT_SLOT_1);

        return !inputStack.isEmpty() && (outputStack1.isEmpty() || (outputStack1.getCount() + recipe.getResultItem().getCount() <= outputStack1.getMaxStackSize() && recipe.getResultItem().getItem() == outputStack1.getItem()));
    }

    @Override
    public List<BaseOneProbeInfo> addProbeInfo(List<BaseOneProbeInfo> oneProbeInfo) {
        super.addProbeInfo(oneProbeInfo);

        oneProbeInfo.add(new ProbeInfoSimpleText(Component.translatable("gui." + IndReb.MODID + ".temperature", GuiUtil.DECIMAL_FORMAT_1.format(progressTemp.getCurrentProgress()) + " " + GuiUtil.DEGREE_SYMBOL + "C")));

        return oneProbeInfo;
    }
}
