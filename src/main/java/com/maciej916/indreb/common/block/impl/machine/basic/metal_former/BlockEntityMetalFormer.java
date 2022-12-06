package com.maciej916.indreb.common.block.impl.machine.basic.metal_former;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasUpgrades;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.*;
import com.maciej916.indreb.common.api.recipe.BaseRecipe;
import com.maciej916.indreb.common.api.recipe.interfaces.IBaseRecipe;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.slot.OutputSlot;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.enums.MetalFormerMode;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketMetalFormerMode;
import com.maciej916.indreb.common.recipe.ModRecipeType;
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
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BlockEntityMetalFormer extends IndRebBlockEntity implements IHasExp, IHasUpgrades, IBlockEntityEnergy, IHasSound {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    protected MetalFormerMode mode = MetalFormerMode.CUTTING;
    protected BaseRecipe recipe;
    public ProgressFloat progressRecipe = new ProgressFloat();

    public BlockEntityMetalFormer(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.METAL_FORMER.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.metal_former_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.BASIC);

        this.containerData.syncProgressFloat(0, this.progressRecipe);
        this.containerData.syncInt(1, ()-> getMode().getId());
    }

    @Override
    public void tickWork() {
        ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);

        if (baseSlotsChangedForTick.contains(INPUT_SLOT) || (recipe == null && !inputStack.isEmpty())) {
            BaseRecipe oldRecipe = recipe;

            if (inputStack.isEmpty()) {
                recipe = null;
            } else {
                recipe = (BaseRecipe) getRecipe(inputStack).orElse(null);
            }

            if (oldRecipe != recipe && oldRecipe != null) {
                progressRecipe.resetProgress();
            }
        }

        if (recipe != null) {
            if (progressRecipe.getCurrentProgress() == -1) {
                progressRecipe.setData(0, recipe.getDuration());
            }

            progressRecipe.setProgressMax(getUpgradesDuration(recipe.getDuration()) * 1.5f);
            int energyCost = getUpgradesEnergyCost(recipe.getTickEnergyCost());

            if (canWork()) {
                if (inputStack.getCount() >= recipe.getIngredientCount().getIngredientsCount().get(0)) {
                    if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost && !progressRecipe.isCurrentAboveEqualMax()) {
                        activeState = true;
                        progressRecipe.incProgress(1);
                        getEnergyStorage().consumeEnergy(energyCost, false);
                    }

                    if (progressRecipe.isCurrentAboveEqualMax()) {
                        StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT, recipe.getResultItem());
                        StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT, recipe.getIngredientCount().getIngredientsCount().get(0));
                        progressRecipe.resetProgress();
                        addRecipeUsed(recipe);
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuMetalFormer(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == INPUT_SLOT) return getRecipe(stack).isPresent();
        return false;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(INPUT_SLOT, 46, 35, 45, 34, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(OUTPUT_SLOT,  108, 35, 103, 30, GuiSlotBg.LARGE));

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
        this.mode = MetalFormerMode.getModeFromId(tag.getInt("mode"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
        tag.putInt("mode", mode.getId());
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
        return ModSounds.METAL_FORMER.get();
    }

    protected Optional<?> getRecipe(ItemStack input) {
        if (level != null) {
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
        }
        return Optional.empty();
    }

    private boolean canWork() {
        final ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        final ItemStack outputStack = getBaseStorage().getStackInSlot(OUTPUT_SLOT);

        return !inputStack.isEmpty() && (outputStack.isEmpty() || (outputStack.getCount() + recipe.getResultItem().getCount() <= outputStack.getMaxStackSize() && recipe.getResultItem().getItem() == outputStack.getItem()));
    }

    public Runnable changeModeClient () {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketMetalFormerMode(getBlockPos()));
    }

    public void updateModeServer() {
        switch (mode.getId()) {
            case 1 -> mode = MetalFormerMode.ROLLING;
            case 2 -> mode = MetalFormerMode.EXTRUDING;
            case 3 -> mode = MetalFormerMode.CUTTING;
        }

        final ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        Optional<?> optionalRecipe = getRecipe(inputStack);
        if (optionalRecipe.isPresent()) {
            recipe = (BaseRecipe) optionalRecipe.get();
        } else {
            recipe = null;
            setActiveState(false);
        }

        setBlockUpdated();
        progressRecipe.resetProgress();
    }

    public MetalFormerMode getMode() {
        return mode;
    }

    public void setMode(MetalFormerMode mode) {
        this.mode = mode;
    }
}
