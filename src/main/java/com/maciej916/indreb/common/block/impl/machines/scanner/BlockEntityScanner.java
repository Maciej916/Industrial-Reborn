package com.maciej916.indreb.common.block.impl.machines.scanner;

import com.maciej916.indreb.common.block.impl.machines.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.capabilities.scan_result.ScannerResult;
import com.maciej916.indreb.common.capabilities.scan_result.IScannerResult;
import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.interfaces.receipe.IBaseRecipe;
import com.maciej916.indreb.common.interfaces.receipe.IRecipeSingleIngredient;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketScannerCleanScan;
import com.maciej916.indreb.common.network.packet.PacketScannerSaveScan;
import com.maciej916.indreb.common.recipe.impl.ScannerRecipe;
import com.maciej916.indreb.common.registries.*;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.Constants;
import com.maciej916.indreb.common.util.LazyOptionalHelper;
import com.maciej916.indreb.common.util.StackHandlerHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Optional;

public class BlockEntityScanner extends IndRebBlockEntity implements IEnergyBlock, IExpCollector, ITileSound {

    public static final int INPUT_SLOT = 0;
    public static final int MEMORY_SLOT = 1;

    public BlockEntityProgress progress = new BlockEntityProgress();
    protected ScannerMode mode = ScannerMode.NO_POWER;
    protected int currentModeTick = 0;
    protected ScannerRecipe recipe;
    protected ScannerResult result = new ScannerResult();

    public BlockEntityScanner(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.SCANNER.get(), blockPos, blockState);
        createEnergyStorage(0, ServerConfig.scanner_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.SUPER);

        newUpdateStateTempVar = true;
    }

    @Override
    public boolean inputSlotChanged(int slotId, ItemStack oldStack, ItemStack newStack) {
        boolean validChange = false;
        ScannerRecipe oldRecipe = recipe;

        if (oldStack.getItem() != newStack.getItem()) {
            if (newStack.getItem() != Items.AIR) {
                Optional<?> optionalRecipe = getRecipe(newStack);
                if (optionalRecipe.isPresent()) {
                    recipe = (ScannerRecipe) optionalRecipe.get();
                    ItemStack stack = new ItemStack(getStackHandler().getStackInSlot(INPUT_SLOT).getItem());
                    result = new ScannerResult(stack, recipe.getMatterCost(), recipe.getEnergyCost());
                } else {
                    recipe = null;
                }
            } else {
                recipe = null;
            }

            validChange = true;
        } else {
            if (recipe != null) {
                recipe = null;
                validChange = true;
            }
        }

        if (oldRecipe == null && recipe != null) {
            validChange = false;
        }

        if (validChange && mode.getId() < 4) {
            result = new ScannerResult();
        }

        return validChange;
    }

    @Override
    public void tickWork(BlockState state) {
        boolean validChange = checkInputSlotChange(INPUT_SLOT);

        if (validChange) {
            progress.setBoth(-1);
        }

        if (mode.getId() < 4) {
            if (getEnergyStorage().energyStored() > 0) {
                if (recipe != null) {
                    if (progress.getProgress() == -1) {
                        progress.setData(0, recipe.getDuration());
                    }

                    if (getEnergyStorage().consumeEnergy(recipe.getPowerCost(), true) >= recipe.getPowerCost()) {
                        activeState =  true;
                        progress.incProgress(1);
                        getEnergyStorage().consumeEnergy(recipe.getPowerCost(), false);
                        getEnergyStorage().updateConsumed(recipe.getPowerCost());
                    }

                    if (progress.getProgress() >= progress.getProgressMax()) {
                        StackHandlerHelper.shrinkInputStack(getStackHandler(), INPUT_SLOT, 1);
                        mode = ScannerMode.RESULT;
                        addRecipeUsed(recipe);
                        progress.setBoth(-1);
                    } else {
                        shouldUpdateState = true;
                        mode = ScannerMode.PROGRESS;
                    }
                } else {
                    progress.setBoth(-1);
                    mode = ScannerMode.IDLE;
                }
            } else {
                shouldUpdateState = true;
                mode = ScannerMode.NO_POWER;
            }
        } else {
            if (mode.getId() > 4) {
                currentModeTick++;
                if (currentModeTick >= 60) {
                    currentModeTick = 0;
                    mode = ScannerMode.RESULT;
                    shouldUpdateState = true;
                }
            }
        }

        if (progress.changed()) {
            shouldUpdateState = true;
        }
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 30, 29, InventorySlotType.INPUT, GuiSlotType.LARGE, 25, 24));
        slots.add(new IndRebSlot(MEMORY_SLOT, 130, 55, InventorySlotType.INPUT, GuiSlotType.NORMAL, 129, 54));

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
        return ModSounds.SCANNER.get();
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return isValidInput(stack);
        if (slot == MEMORY_SLOT) return stack.getItem() == ModItems.MEMORY_CARD.get();
        return false;
    }

    @Override
    public int getCustomSlotLimit(int slot) {
        return 1;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progress.deserializeNBT(tag.getCompound("progress"));
        this.result.deserializeNBT(tag.getCompound("result"));
        this.mode = ScannerMode.getModeFromId(tag.getInt("mode"));
        this.currentModeTick = tag.getInt("currentModeTick");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("progress", this.progress.serializeNBT());
        tag.put("result", this.result.serializeNBT());
        tag.putInt("mode", mode.getId());
        tag.putInt("currentModeTick", this.currentModeTick);
        return super.save(tag);
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((ScannerRecipe) recipe).getExperience();
    }

    protected Optional<ScannerRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.SCANNER.get(), new SimpleContainer(input), level);
    }

    protected boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        if (mode.getId() >= 4) return false;
        return getRecipe(stack).isPresent();
    }

    public Runnable clientClickCleanScan() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketScannerCleanScan(getBlockPos()));
    }

    public Runnable clientClickSaveScan() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketScannerSaveScan(getBlockPos()));
    }

    public void cleanScan() {
        result = new ScannerResult();
        mode = getEnergyStorage().energyStored() > 0 ? ScannerMode.IDLE : ScannerMode.NO_POWER;
        updateBlockState();
    }

    public void saveScan() {
        final ItemStack memoryStack = getStackHandler().getStackInSlot(MEMORY_SLOT);
        LazyOptionalHelper<IScannerResult> cap = CapabilityUtil.getCapabilityHelper(memoryStack, ModCapabilities.SCANNER_RESULT);
        boolean success = false;
        boolean alreadyStored = false;

        if (!memoryStack.isEmpty() && cap.isPresent() && cap.getValue().getResult().getResultStack() == ItemStack.EMPTY) {
            cap.getValue().setResult(result);
            success = true;
        } else {

            for (Direction direction : Constants.DIRECTIONS) {
                BlockPos offsetPos = getBlockPos().relative(direction);
                BlockEntity dirTile = getLevel().getBlockEntity(offsetPos);
                if (dirTile instanceof BlockEntityPatternStorage bePatternStorage) {
                    if (!bePatternStorage.hasScannerResult(result.getResultStack())) {
                        bePatternStorage.addScannerResult(result);
                        success = true;
                        break;
                    } else {
                        alreadyStored = true;
                    }
                }
            }
        }

        if (success) {
            mode = getEnergyStorage().energyStored() > 0 ? ScannerMode.IDLE : ScannerMode.NO_POWER;
            result = new ScannerResult();
        } else {
            mode = alreadyStored ? ScannerMode.ALREADY_RECORDED : ScannerMode.TRANSFER_NO_STORAGE;
        }

        updateBlockState();
    }

    public ScannerMode getMode() {
        return mode;
    }

    public ScannerResult getResult() {
        return result;
    }
}