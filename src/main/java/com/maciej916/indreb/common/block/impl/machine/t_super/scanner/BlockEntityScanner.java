package com.maciej916.indreb.common.block.impl.machine.t_super.scanner;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.recipe.interfaces.IBaseRecipe;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import com.maciej916.indreb.common.block.impl.misc.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.scanner.IScannerResult;
import com.maciej916.indreb.common.capability.scanner.ScannerResult;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.enums.ScannerMode;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketScannerCleanScan;
import com.maciej916.indreb.common.network.packet.PacketScannerSaveScan;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.ScanningRecipe;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.Constants;
import com.maciej916.indreb.common.util.LazyOptionalHelper;
import com.maciej916.indreb.common.util.StackHandlerHelper;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;

public class BlockEntityScanner extends IndRebBlockEntity implements IBlockEntityEnergy, IHasSound, IHasExp {

    public static final int INPUT_SLOT = 0;
    public static final int MEMORY_SLOT = 1;

    public ProgressFloat progressRecipe = new ProgressFloat();
    protected ScannerMode mode = ScannerMode.NO_POWER;
    protected ScanningRecipe recipe;
    protected ScannerResult result = new ScannerResult();
    protected int currentModeTick = 0;

    public BlockEntityScanner(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SCANNER.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.scanner_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.SUPER);

        this.containerData.syncProgressFloat(0, this.progressRecipe);
        this.containerData.syncInt(1, () -> mode.getId());
        this.containerData.syncInt(2, () -> currentModeTick);

        this.containerData.syncStack(3, () -> result.getResultStack());
        this.containerData.syncInt(4, () -> result.getMatterCost());
        this.containerData.syncInt(5, () -> result.getEnergyCost());
    }

    @Override
    public void tickWork() {

        if (mode.getId() < 4) {

            ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
            if (baseSlotsChangedForTick.contains(INPUT_SLOT) || (recipe == null && !inputStack.isEmpty())) {
                ScanningRecipe oldRecipe = recipe;

                if (inputStack.isEmpty()) {
                    recipe = null;
                    result = new ScannerResult();
                } else {
                    recipe = getRecipe(inputStack).orElse(null);
                    if (recipe != null) {
                        result = new ScannerResult(inputStack.copy(), recipe.getMatterCost(), recipe.getEnergyCost());
                    }
                }

                if (oldRecipe != recipe && oldRecipe != null) {
                    progressRecipe.resetProgress();
                }
            }

            if (getEnergyStorage().energyStored() > 0) {
                if (recipe != null && result.getResultStack() != ItemStack.EMPTY) {
                    if (progressRecipe.getCurrentProgress() == -1) {
                        progressRecipe.setData(0, recipe.getDuration());
                    }

                    if (getEnergyStorage().consumeEnergy(recipe.getTickEnergyCost(), true) == recipe.getTickEnergyCost()) {
                        activeState =  true;
                        progressRecipe.incProgress(1);
                        getEnergyStorage().consumeEnergy(recipe.getTickEnergyCost(), false);
                    }

                    if (progressRecipe.isCurrentAboveEqualMax()) {
                        StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT, 1);
                        mode = ScannerMode.RESULT;
                        addRecipeUsed(recipe);
                        progressRecipe.resetProgress();
                    } else {
                        mode = ScannerMode.PROGRESS;
                    }
                } else {
                    progressRecipe.resetProgress();
                    mode = ScannerMode.IDLE;
                }
            } else {
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

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuScanner(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == INPUT_SLOT) return getRecipe(stack).isPresent() && mode.getId() < 4;
        if (slot == MEMORY_SLOT) return stack.getItem() == ModItems.MEMORY_CARD.get();
        return false;
    }

    @Override
    public int getBaseStorageSlotLimit(int slot) {
        return 1;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(INPUT_SLOT, 30, 29, 25, 24, InventorySlotType.INPUT, GuiSlotBg.LARGE));
        slots.add(new BaseSlot(MEMORY_SLOT, 130, 55, 129, 54, InventorySlotType.INPUT, GuiSlotBg.NORMAL));


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
        this.result.deserializeNBT(tag.getCompound("result"));
        this.mode = ScannerMode.getModeFromId(tag.getInt("mode"));
        this.currentModeTick = tag.getInt("currentModeTick");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
        tag.put("result", this.result.serializeNBT());
        tag.putInt("mode", mode.getId());
        tag.putInt("currentModeTick", this.currentModeTick);
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((IBaseRecipe) recipe).getExperience();
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.SCANNER.get();
    }

    protected Optional<ScanningRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.SCANNING.get(), new SimpleContainer(input), level);
    }

    public ScannerResult getResult() {
        return result;
    }

    public ScannerMode getMode() {
        return mode;
    }

    public void setMode(ScannerMode mode) {
        this.mode = mode;
    }

    public void setCurrentModeTick(int currentModeTick) {
        this.currentModeTick = currentModeTick;
    }

    public Runnable clickCleanScanClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketScannerCleanScan(getBlockPos()));
    }

    public Runnable clickSaveScanClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketScannerSaveScan(getBlockPos()));
    }

    public void cleanScanServer() {
        result = new ScannerResult();
        recipe = null;
        mode = getEnergyStorage().energyStored() > 0 ? ScannerMode.IDLE : ScannerMode.NO_POWER;
        setBlockUpdated();
    }

    public void saveScanServer() {
        ItemStack memoryStack = getBaseStorage().getStackInSlot(MEMORY_SLOT);
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
            recipe = null;
        } else {
            mode = alreadyStored ? ScannerMode.ALREADY_RECORDED : ScannerMode.TRANSFER_NO_STORAGE;
        }

        setBlockUpdated();
    }

    public void setResult(ScannerResult result) {
        this.result = result;
    }

}
