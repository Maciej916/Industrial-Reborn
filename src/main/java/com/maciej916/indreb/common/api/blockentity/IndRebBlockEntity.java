package com.maciej916.indreb.common.api.blockentity;

import com.maciej916.indreb.common.api.blockentity.interfaces.*;
import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.enums.UpgradeType;
import com.maciej916.indreb.common.api.interfaces.block.IStateActive;
import com.maciej916.indreb.common.api.interfaces.item.IItemUpgrade;
import com.maciej916.indreb.common.api.slot.BaseSlotItemHandler;
import com.maciej916.indreb.common.api.slot.ElectricSlotItemHandler;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.item.*;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketExperience;
import com.maciej916.indreb.common.tag.ModTagsItem;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.SoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class IndRebBlockEntity extends BaseBlockEntity implements IIndRebBlockEntity {

    private BasicEnergyStorage energyStorage;
    private final LazyOptional<IEnergyStorage> energyStorageCap = LazyOptional.of(() -> energyStorage);

    private BaseItemStackHandler baseStorage;
    private final LazyOptional<IBaseItemStackHandler> baseStorageCap = LazyOptional.of(() -> baseStorage);

    private ElectricItemStackHandler electricStorage;
    private final LazyOptional<IElectricItemStackHandler> electricStorageCap = LazyOptional.of(() -> electricStorage);

    private UpgradesItemStackHandler upgradesStorage;
    private final LazyOptional<IUpgradesItemStackHandler> upgradesStorageCap = LazyOptional.of(() -> upgradesStorage);

    protected boolean isStateActive = false;
    protected boolean hasCooldown = false;
    protected boolean hasSound = false;
    protected boolean hasExp = false;
    protected boolean hasUpgrades = false;

    private int tickCounter = 0;
    private int cooldown = 0;

    private SoundEvent soundEvent = null;
    private SoundInstance activeSound;

    private Map<ResourceLocation, Integer> recipesUsed = new HashMap<>();

    protected boolean currentActiveState = false;
    protected boolean prevActiveState = false;
    protected boolean shouldUpdateState = false;

    public IndRebBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        init();
    }

    @Override
    public boolean hasEnergyStorage() {
        return energyStorage != null;
    }

    @Override
    public boolean hasBaseStorage() {
        return baseStorage != null;
    }

    @Override
    public boolean hasElectricStorage() {
        return electricStorage != null;
    }

    @Override
    public boolean hasUpgradesStorage() {
        return upgradesStorage != null;
    }

    @Override
    public BasicEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    @Override
    public BaseItemStackHandler getBaseStorage() {
        return baseStorage;
    }

    @Override
    public ElectricItemStackHandler getElectricStorage() {
        return electricStorage;
    }

    @Override
    public UpgradesItemStackHandler getUpgradesStorage() {
        return upgradesStorage;
    }

    private void init() {
        setSupportedTypes();
        initBaseStorage();
        initElectricStorage();
        initUpgradesStorage();
    }

    private void setSupportedTypes() {
        isStateActive = getBlock() instanceof IStateActive;
        hasCooldown = this instanceof IHasCooldown;
        hasSound = this instanceof IHasSound;
        hasExp = this instanceof IHasExp;
        hasUpgrades = this instanceof IHasUpgrades;
    }

    public void onBaseStorageContentsChanged(int slot) {

    }

    public int getBaseStorageSlotLimit(int slot) {
        return 64;
    }

    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        return true;
    }

    private void initBaseStorage() {
        ArrayList<BaseSlotItemHandler> slots = addBaseSlots(new ArrayList<>());
        if (slots.size() > 0) {
           this.baseStorage = new BaseItemStackHandler(slots) {
               @Override
               protected void onContentsChanged(int slot) {
                   onBaseStorageContentsChanged(slot);
                   setChanged();
               }

               @Override
               public int getSlotLimit(int slot) {
                   return getBaseStorageSlotLimit(slot);
               }

               @Override
               public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                   return isBaseStorageItemValid(slot, stack);
               }
           };
        }
    }

    public ArrayList<BaseSlotItemHandler> addBaseSlots(ArrayList<BaseSlotItemHandler> slots) {
        return slots;
    }

    public void onElectricStorageContentsChanged(int slot) {

    }

    public int getElectricStorageSlotLimit(int slot) {
        return 1;
    }

    public boolean isElectricStorageItemValid(int slot, @NotNull ItemStack stack) {
        return stack.is(ModTagsItem.ELECTRICS);
    }

    private void initElectricStorage() {
        ArrayList<ElectricSlotItemHandler> slots = addElectricSlots(new ArrayList<>());
        if (slots.size() > 0) {
            this.electricStorage = new ElectricItemStackHandler(slots) {
                @Override
                protected void onContentsChanged(int slot) {
                    onElectricStorageContentsChanged(slot);
                    setChanged();
                }

                @Override
                public int getSlotLimit(int slot) {
                    return getElectricStorageSlotLimit(slot);
                }

                @Override
                public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                    return isElectricStorageItemValid(slot, stack);
                }
            };
        }
    }

    public ArrayList<ElectricSlotItemHandler> addElectricSlots(ArrayList<ElectricSlotItemHandler> slots) {
        return slots;
    }

    public void onUpgradeStorageContentsChanged(int slot) {

    }

    public int getUpgradeStorageSlotLimit(int slot) {
        return 8;
    }

    public boolean isUpgradeStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (stack.getItem() instanceof IItemUpgrade itemUpgrade) {
            return getSupportedUpgrades().contains(itemUpgrade.getUpgradeType());
        }
        return false;
    }

    public List<UpgradeType> getSupportedUpgrades() {
        return new ArrayList<>();
    }

    public int getUpgradesSlots() {
        return 4;
    }

    private void initUpgradesStorage() {
        if (hasUpgrades) {
            this.upgradesStorage = new UpgradesItemStackHandler(getUpgradesSlots()) {
                @Override
                protected void onContentsChanged(int slot) {
                    onUpgradeStorageContentsChanged(slot);
                    setChanged();
                }

                @Override
                public int getSlotLimit(int slot) {
                    return getUpgradeStorageSlotLimit(slot);
                }

                @Override
                public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                    return isUpgradeStorageItemValid(slot, stack);
                }
            };
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getExperience(Recipe<?> recipe) {
        return 0;
    }

    public float getStoredExperience() {
        AtomicReference<Float> experience = new AtomicReference<>((float) 0);
        for(Map.Entry<ResourceLocation, Integer> entry : this.recipesUsed.entrySet()) {
            level.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe -> {
                experience.updateAndGet(v -> v + (entry.getValue() * getExperience(recipe)));
            }));
        }

        return experience.get();
    }

    public void collectExpServer(Player player) {
        List<Recipe<?>> list = new ArrayList<>();

        for(Map.Entry<ResourceLocation, Integer> entry : this.recipesUsed.entrySet()) {
            player.level.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe -> {
                list.add(recipe);
                BlockEntityUtil.spawnExpOrbs((ServerLevel) level, player, entry.getValue(), getExperience(recipe));
            }));
        }

        player.awardRecipes(list);
        this.recipesUsed.clear();
    }

    public Runnable collectExpClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketExperience(getBlockPos()));
    }

    public void addRecipeUsed(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            this.recipesUsed.compute(recipe.getId(), (key, val) -> 1 + (val == null ? 0 : val));
        }
    }

    @Override
    public void tick() {
        if (level.isClientSide()) {
            tickClient();
        } else {
            tickServer();
        }
    }

    @Override
    public void tickClient() {
        if (hasSound) {
            handleSound();
        }
    }

    @Override
    public void tickServer() {
        tickCounter = tickCounter == 20 ? 0 : tickCounter + 1;
        shouldUpdateState = false;
        currentActiveState = false;

        if (hasCooldown) {
            if (tickCounter == 20 && cooldown > 0) {
                --cooldown;
                setChanged();
            }
        }

        if (hasUpgrades) {
            tickUpgrades();
        }

        tickWork();


        if (shouldUpdateState) {
            setActiveState(currentActiveState);
        }
    }

    @Override
    public void tickWork() {

    }

    @Override
    public void tickUpgrades() {

    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        if (hasSound) {
            handleSound();
        }
    }

    @Override
    public boolean getActiveStateValue() {
        return isStateActive && ((IStateActive) getBlock()).isActive(getBlockState());
    }

    protected boolean canPlaySound() {
        return getActiveStateValue() && soundEvent != null;
    }

    protected void handleSound() {
        if (hasSound) return;
        if (canPlaySound() && !isRemoved()) {
            if (tickCounter > 0) {
                return;
            }
            if (activeSound == null || !Minecraft.getInstance().getSoundManager().isActive(activeSound)) {
                activeSound = SoundHandler.startTileSound(soundEvent, SoundSource.BLOCKS, 1F, getBlockPos());
            }
        } else if (activeSound != null) {
            SoundHandler.stopTileSound(getBlockPos());
            activeSound = null;
        }
    }

    @Override
    public void updateState() {
        shouldUpdateState = true;
    }

    private void setActiveState(boolean active) {
        if (!isStateActive) return;
        if (this.prevActiveState != active) {
            this.prevActiveState = active;
            BlockState newState = ((IStateActive) getBlock()).setActive(getBlockState(), active);
            level.setBlockAndUpdate(getBlockPos(), newState);
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {

        if (energyStorage != null) {
            if (cap == ModCapabilities.ENERGY) {
                return energyStorageCap.cast();
            }
        }

        if (baseStorage != null) {
            if (cap == ModCapabilities.BASE_ITEM_HANDLER) {
                return baseStorageCap.cast();
            }
        }

        if (electricStorage != null) {
            if (cap == ModCapabilities.ELECTRIC_ITEM_HANDLER) {
                return electricStorageCap.cast();
            }
        }

        if (upgradesStorage != null) {
            if (cap == ModCapabilities.UPGRADES_ITEM_HANDLER) {
                return upgradesStorageCap.cast();
            }
        }

        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {

        super.invalidateCaps();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
    }

    //on place
    //on break


}
