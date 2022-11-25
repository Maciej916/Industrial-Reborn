package com.maciej916.indreb.common.block.impl.machines.simple.simple_compressor;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.OutputSlot;
import com.maciej916.indreb.common.api.util.Progress;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.StackHandlerHelper;
import com.maciej916.indreb.common.util.WrappedHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class BlockEntitySimpleCompressor extends IndRebBlockEntity implements IHasExp {

    public static final int SYNC_DATA_SLOTS = 4;
    protected final ContainerData data;

    public static final int FUEL_SLOT = 0;
    public static final int INPUT_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    public static final int BONUS_SLOT = 3;

    public Progress progressBurn = new Progress();
    public Progress progressSmelting = new Progress();

    private SmeltingRecipe recipe;

    public BlockEntitySimpleCompressor(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SIMPLE_COMPRESSOR.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlockEntitySimpleCompressor.this.progressBurn.getContainerDataCurrent();
                    case 1 -> BlockEntitySimpleCompressor.this.progressBurn.getContainerDataMax();
                    case 2 -> BlockEntitySimpleCompressor.this.progressSmelting.getContainerDataCurrent();
                    case 3 -> BlockEntitySimpleCompressor.this.progressSmelting.getContainerDataMax();

                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlockEntitySimpleCompressor.this.progressBurn.setContainerDataCurrent(value);
                    case 1 -> BlockEntitySimpleCompressor.this.progressBurn.setContainerDataMax(value);
                    case 2 -> BlockEntitySimpleCompressor.this.progressSmelting.setContainerDataCurrent(value);
                    case 3 -> BlockEntitySimpleCompressor.this.progressSmelting.setContainerDataMax(value);
                }
            }

            @Override
            public int getCount() {
                return SYNC_DATA_SLOTS;
            }
        };
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == INPUT_SLOT) return getRecipe(stack).isPresent();
        if (slot == FUEL_SLOT) return FurnaceBlockEntity.isFuel(stack);
        return false;
    }

    @Override
    public void tickWork() {
        ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);

        if (baseSlotsChangedForTick.contains(INPUT_SLOT)) {
            SmeltingRecipe oldRecipe = recipe;

            if (inputStack.isEmpty()) {
                recipe = null;
            } else {
                recipe = getRecipe(inputStack).orElse(null);
            }

            if (oldRecipe != recipe) {
                progressSmelting.resetProgress();
            }
        }

        if (recipe != null) {
            if (progressSmelting.currentProgress() == -1) {
                progressSmelting.setData(0, recipe.getCookingTime() * 0.80f);
            }

            if (canWork() && progressBurn.currentProgress() > 0) {
                if (progressSmelting.isCurrentAboveEqualMax()) {
                    StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT, recipe.getResultItem());
                    StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT, 1);
                    progressSmelting.resetProgress();
                    addRecipeUsed(recipe);
                } else {
                    progressSmelting.incProgress(1);
                }

                activeState = true;
            } else {
                progressSmelting.decProgress(1);
            }
        }

        if (progressBurn.currentProgress() > 0) {
            progressBurn.decProgress(1);
        } else {
            final ItemStack fuelStack = getBaseStorage().getStackInSlot(FUEL_SLOT);
            if (recipe != null) {
                int burnTime = ForgeHooks.getBurnTime(fuelStack, RecipeType.SMELTING);
                progressBurn.setBoth(burnTime * 1.20f);
                StackHandlerHelper.shrinkStack(getBaseStorage(), FUEL_SLOT, 1);
            }
        }

        if (progressBurn.currentProgress() > 0) {
            activeState = true;
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuSimpleCompressor(this, containerId, playerInventory, player, data);
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(INPUT_SLOT, 56, 35, 55, 34, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new BaseSlot(FUEL_SLOT, 21, 44, 20, 43, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(OUTPUT_SLOT, 116, 24, 111, 19, GuiSlotBg.LARGE));
        slots.add(new BaseSlot(BONUS_SLOT, 116, 50, 115, 49, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        return super.addBaseSlots(slots);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progressBurn.deserializeNBT(tag.getCompound("progressBurn"));
        this.progressSmelting.deserializeNBT(tag.getCompound("progressSmelting"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressBurn", this.progressBurn.serializeNBT());
        tag.put("progressSmelting", this.progressSmelting.serializeNBT());
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((SmeltingRecipe) recipe).getExperience();
    }

    @Override
    public boolean hasExpButton() {
        return false;
    }

    private final Map<Direction, LazyOptional<WrappedHandler>> itemCapabilities = Map.of(
            Direction.UP, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> i == OUTPUT_SLOT || i == BONUS_SLOT, (i, stack) -> false)),
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
                if (side == Direction.UP || side == Direction.DOWN) return itemCapabilities.get(side).cast();

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

    protected Optional<SmeltingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(input), level);
    }

    private boolean canWork() {
        final ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        final ItemStack outputStack = getBaseStorage().getStackInSlot(OUTPUT_SLOT);

        return !inputStack.isEmpty() && (outputStack.isEmpty() || outputStack.getCount() + recipe.getResultItem().getCount() <= outputStack.getMaxStackSize());
    }

}
