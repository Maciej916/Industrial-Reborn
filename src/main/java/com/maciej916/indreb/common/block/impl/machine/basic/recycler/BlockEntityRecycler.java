package com.maciej916.indreb.common.block.impl.machine.basic.recycler;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasUpgrades;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.*;
import com.maciej916.indreb.common.api.recipe.interfaces.IBaseRecipe;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCountStack;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.slot.OutputSlot;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.RecyclingRecipe;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public class BlockEntityRecycler extends IndRebBlockEntity implements IHasExp, IHasUpgrades, IBlockEntityEnergy, IHasSound {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    protected static List<RecyclingRecipe> recipes;
    protected RecyclingRecipe recipe;
    protected int inputCount;
    public ProgressFloat progressRecipe = new ProgressFloat();

    public BlockEntityRecycler(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.RECYCLER.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.recycler_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.BASIC);

        this.containerData.syncProgressFloat(0, this.progressRecipe);
    }

    @Override
    public void tickWork() {
        ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);

        if (recipes == null) {
            initRecipes();
        }

        if (baseSlotsChangedForTick.contains(INPUT_SLOT) || (recipe == null && !inputStack.isEmpty())) {
            RecyclingRecipe oldRecipe = recipe;

            if (inputStack.isEmpty()) {
                recipe = null;
            } else {
                recipe = getRecipe(inputStack).orElse(null);

                if (recipe != null) {
                    inputCount = 1;
                    IngredientCount ingredientCount = recipe.getIngredientCount();
                    if (ingredientCount.getIngredientStack(0).ingredient() != Ingredient.EMPTY) {
                        for (int i = 0; i < ingredientCount.getSize(); i++) {
                            IngredientCountStack countStack1 = ingredientCount.getIngredientStack(i);
                            List<ItemStack> icStack = Stream.of(countStack1.ingredient().getItems()).peek(itemStack -> itemStack.setCount(countStack1.getCount())).toList();
                            for (ItemStack stack : icStack) {
                                if (stack.getItem() == getBaseStorage().getStackInSlot(INPUT_SLOT).getItem()) {
                                    inputCount = stack.getCount();
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            if (oldRecipe != recipe && oldRecipe != null) {
                progressRecipe.resetProgress();
            }
        }

        if (recipe != null) {
            if (progressRecipe.getCurrentProgress() == -1) {
                progressRecipe.setData(0, recipe.getDuration());
            }

            progressRecipe.setProgressMax(getUpgradesDuration(recipe.getDuration()));
            int energyCost = getUpgradesEnergyCost(recipe.getTickEnergyCost());

            if (canWork()) {
                if (inputStack.getCount() >= inputCount) {
                    if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost && !progressRecipe.isCurrentAboveEqualMax()) {
                        activeState = true;
                        progressRecipe.incProgress(1);
                        getEnergyStorage().consumeEnergy(energyCost, false);
                    }

                    if (progressRecipe.isCurrentAboveEqualMax()) {
                        float random = new Random().nextFloat(100);
                        if (random <= recipe.getChance()) {
                            ItemStack resultStack = recipe.getResultItem();
                            if (recipe.getResultItem().getCount() > 1) resultStack.setCount(Math.max(new Random().nextInt(recipe.getResultItem().getCount()), 1));
                            StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT, resultStack);

                        }

                        StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT, inputCount);
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
        return new MenuRecycler(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == INPUT_SLOT) return getRecipe(stack).isPresent();
        return false;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(INPUT_SLOT, 48, 35, 47, 34, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
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
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
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
        return ModSounds.RECYCLER.get();
    }

    public void initRecipes() {
        recipes = Objects.requireNonNull(getLevel()).getRecipeManager().getAllRecipesFor(ModRecipeType.RECYCLING.get());
    }

    protected Optional<RecyclingRecipe> getRecipe(ItemStack input) {
        if (level != null) {
            if (recipes == null) initRecipes();
            if (recipes != null) {
                for (RecyclingRecipe recipe : recipes) {
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
        final ItemStack outputStack = getBaseStorage().getStackInSlot(OUTPUT_SLOT);

        return !inputStack.isEmpty() && ( outputStack.isEmpty() || (outputStack.getCount() + recipe.getResultItem().getCount() <= outputStack.getMaxStackSize() && recipe.getResultItem().getItem() == outputStack.getItem()));
    }

}
