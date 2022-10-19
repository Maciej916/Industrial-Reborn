package com.maciej916.indreb.common.block.impl.machines.iron_furnace;

import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
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
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class BlockEntityIronFurnace extends IndRebBlockEntity implements IExpCollector {

    public static final int FUEL_SLOT = 0;
    public static final int INPUT_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;

    public BlockEntityProgress smelting = new BlockEntityProgress();
    public BlockEntityProgress fuel = new BlockEntityProgress();

    private boolean active = false;

    private ItemStack cachedInputStack = ItemStack.EMPTY;
    private ItemStack resultStack = ItemStack.EMPTY;
    private SmeltingRecipe furnaceRecipe;

    public BlockEntityIronFurnace(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.IRON_FURNACE, pWorldPosition, pBlockState);
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
        fuel.clearChanged();
        smelting.clearChanged();

        final ItemStack inputStack = getStackHandler().getStackInSlot(INPUT_SLOT);
        if (cachedInputStack.getItem() != inputStack.getItem()) {
            cachedInputStack = inputStack.copy();
            if (inputStack.getItem() != Items.AIR && getRecipe(inputStack).isPresent()) {
                furnaceRecipe = getRecipe(cachedInputStack).orElseThrow();
                resultStack = getRecipeResult(inputStack);
            } else {
                furnaceRecipe = null;
            }
        }

        final ItemStack fuelItemStack = getStackHandler().getStackInSlot(FUEL_SLOT);
        final ItemStack outputItemStack = getStackHandler().getStackInSlot(OUTPUT_SLOT);

        if (fuel.getProgress() > 0) {
            active = true;
            fuel.decProgress(1);

            if (cachedInputStack.isEmpty()) {
                smelting.setBoth(-1);
            } else {
                if (canSmelt(inputStack, outputItemStack, resultStack)) {
                    if (smelting.getProgress() == -1) {
                        smelting.setData(0, (int) (getSmeltTime() * 0.80));
                    } else {
                        smelting.incProgress(1);
                        if (smelting.getProgress() >= smelting.getProgressMax()) {
                            if (outputItemStack.isEmpty()) {
                                getStackHandler().setStackInSlot(OUTPUT_SLOT, resultStack.copy());
                            } else {
                                outputItemStack.grow(1);
                                getStackHandler().setStackInSlot(OUTPUT_SLOT, outputItemStack);
                            }

                            inputStack.shrink(1);
                            getStackHandler().setStackInSlot(INPUT_SLOT, inputStack);

                            this.setRecipeUsed(furnaceRecipe);

                            smelting.setBoth(-1);
                        }
                    }
                }
            }
        } else {
            active = false;
            fuel.setBoth(-1);

            if (smelting.getProgress() > 0) {
                smelting.decProgress(1);
            }

            if (canSmelt(inputStack, outputItemStack, resultStack)) {
                final int burnTime = ForgeHooks.getBurnTime(fuelItemStack, RecipeType.SMELTING);
                if (burnTime > 0) {
                    fuel.setBoth((int) (burnTime * 1.20));
                    fuelItemStack.shrink(1);
                    active = true;
                }
            }
        }

        this.setActive(active);

        if (fuel.changed() || smelting.changed()) {
            super.updateBlockState();
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return isValidInput(stack);
        if (slot == FUEL_SLOT) return FurnaceBlockEntity.isFuel(stack);
        return false;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.active = tag.getBoolean("active");
        this.smelting.deserializeNBT(tag.getCompound("smelting"));
        this.fuel.deserializeNBT(tag.getCompound("fuel"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("active", active);
        tag.put("smelting", this.smelting.serializeNBT());
        tag.put("fuel", this.fuel.serializeNBT());
        return super.save(tag);
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 56, 17, InventorySlotType.INPUT, GuiSlotType.NORMAL, 55, 16));
        slots.add(new IndRebSlot(FUEL_SLOT, 56, 53, InventorySlotType.INPUT, GuiSlotType.NORMAL, 55, 52));
        slots.add(new IndRebSlot(OUTPUT_SLOT, 116, 35, InventorySlotType.OUTPUT, GuiSlotType.LARGE, 111, 30));
        return super.addInventorySlot(slots);
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((SmeltingRecipe) recipe).getExperience();
    }


    @Override
    public boolean hasExpButton() {
        return false;
    }

    private final ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), FUEL_SLOT, FUEL_SLOT + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), INPUT_SLOT, INPUT_SLOT + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), OUTPUT_SLOT, OUTPUT_SLOT + 1))
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null) return capabilities.get(0).cast();
            return switch (side) {
                case UP -> capabilities.get(2).cast();
                case DOWN -> capabilities.get(3).cast();
                default -> capabilities.get(1).cast();
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
