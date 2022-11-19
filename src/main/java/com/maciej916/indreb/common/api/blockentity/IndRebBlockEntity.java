package com.maciej916.indreb.common.api.blockentity;

import com.maciej916.indreb.common.api.blockentity.interfaces.*;
import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.enums.UpgradeType;
import com.maciej916.indreb.common.api.interfaces.block.IStateActive;
import com.maciej916.indreb.common.api.interfaces.item.IItemUpgrade;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.item.BaseItemStackHandler;
import com.maciej916.indreb.common.capability.item.ElectricItemStackHandler;
import com.maciej916.indreb.common.capability.item.UpgradesItemStackHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IBaseItemStackHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IElectricItemStackHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IUpgradesItemStackHandler;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketBasicEnergySync;
import com.maciej916.indreb.common.network.packet.PacketExperience;
import com.maciej916.indreb.common.network.packet.PacketFluidSync;
import com.maciej916.indreb.common.tag.ModTagsItem;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.SoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class IndRebBlockEntity extends BaseBlockEntity implements IIndRebBlockEntity, IBlockEntityChunkSync {

    private BasicEnergyStorage energyStorage;
    private final LazyOptional<IEnergyStorage> energyStorageCap = LazyOptional.of(() -> energyStorage);

    private BaseItemStackHandler baseStorage;
    private final LazyOptional<IBaseItemStackHandler> baseStorageCap = LazyOptional.of(() -> baseStorage);

    private ElectricItemStackHandler electricStorage;
    private final LazyOptional<IElectricItemStackHandler> electricStorageCap = LazyOptional.of(() -> electricStorage);

    private UpgradesItemStackHandler upgradesStorage;
    private final LazyOptional<IUpgradesItemStackHandler> upgradesStorageCap = LazyOptional.of(() -> upgradesStorage);

    public Set<Integer> baseSlotsChangedForTick = new HashSet<>();

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

    protected boolean activeState = false;
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

        if (hasSound) {
            soundEvent = ((IHasSound) this).getSoundEvent();
        }
    }

    private void setSupportedTypes() {
        isStateActive = getBlock() instanceof IStateActive;
        hasCooldown = this instanceof IHasCooldown;
        hasSound = this instanceof IHasSound;
        hasExp = this instanceof IHasExp;
        hasUpgrades = this instanceof IHasUpgrades;
    }

    public boolean canExtractEnergyCustom(Direction side) {
        return false;
    }

    public boolean canReceiveEnergyCustom(Direction side) {
        return false;
    }

    public int energyReceiveTickCustom() {
        return -1;
    }

    public int energyExtractTickCustom() {
        return -1;
    }

    public void createEnergyStorage(int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        this.energyStorage = new BasicEnergyStorage(energyStored, maxEnergy, energyType, energyTier) {

            @Override
            public boolean canExtractEnergy(Direction side) {
                return canExtractEnergyCustom(side);
            }

            @Override
            public boolean canReceiveEnergy(Direction side) {
                return canReceiveEnergyCustom(side);
            }

            @Override
            public int maxReceiveTick() {
                int customReceive = energyReceiveTickCustom();
                return customReceive == -1 ? super.maxReceiveTick() : customReceive;
            }

            @Override
            public int maxExtractTick() {
                int customExtract = energyExtractTickCustom();
                return customExtract == -1 ? super.maxExtractTick() : customExtract;
            }

            @Override
            public void updated() {
                setChanged();
                ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketBasicEnergySync(this, getBlockPos()));
            }
        };
    }

    public void onBaseStorageContentsChanged(int slot) {
        baseSlotsChangedForTick.add(slot);
    }

    public int getBaseStorageSlotLimit(int slot) {
        return 64;
    }

    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        return false;
    }

    private void initBaseStorage() {
        ArrayList<BaseSlot> slots = addBaseSlots(new ArrayList<>());
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

    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
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
        ArrayList<ElectricSlot> slots = addElectricSlots(new ArrayList<>());
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

    public ArrayList<ElectricSlot> addElectricSlots(ArrayList<ElectricSlot> slots) {
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
        handleSound();
    }

    @Override
    public void tickServer() {
        tickCounter = tickCounter == 20 ? 0 : tickCounter + 1;
        shouldUpdateState = false;
        activeState = false;

        if (hasEnergyStorage()) {
            getEnergyStorage().updateGenerated(0);
            getEnergyStorage().updateConsumed(0);
        }

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

        }

        setActiveState(activeState);
        baseSlotsChangedForTick.clear();
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
        handleSound();
    }

    @Override
    public boolean getActiveStateValue() {
        return isStateActive && ((IStateActive) getBlock()).isActive(getBlockState());
    }

    protected boolean canPlaySound() {
        return getActiveStateValue();
    }

    protected void handleSound() {
        if (hasSound && soundEvent != null) {
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

    public Component getDisplayName() {
        return Component.translatable(getBlock().getDescriptionId());
    }

    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
//        ModNetworking.sendToClients(new PacketBasicEnergySync(energyStorage, getBlockPos()));
        return null;
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        if (energyStorage != null) {
            energyStorageCap.invalidate();
        }

        if (baseStorage != null) {
            baseStorageCap.invalidate();
        }

        if (electricStorage != null) {
            electricStorageCap.invalidate();
        }

        if (upgradesStorage != null) {
            upgradesStorageCap.invalidate();
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (isStateActive) {
            this.activeState = tag.getBoolean("activeState");
        }

        if (hasCooldown) {
            this.cooldown = tag.getInt("cooldown");
        }

        if (hasExp) {
            int i = tag.getShort("RecipesUsedSize");
            Map<ResourceLocation, Integer> recipesUsedData = new HashMap<>();
            for(int j = 0; j < i; ++j) {
                ResourceLocation resourcelocation = new ResourceLocation(tag.getString("RecipeLocation" + j));
                int k = tag.getInt("RecipeAmount" + j);
                recipesUsedData.put(resourcelocation, k);
            }
            this.recipesUsed = recipesUsedData;
        }

        if (energyStorage != null) {
            this.energyStorage.deserializeNBT(tag.getCompound("energy"));
        }

        if (baseStorage != null) {
            baseStorage.deserializeNBT(tag.getCompound("baseStorage"));
        }

        if (electricStorage != null) {
            electricStorage.deserializeNBT(tag.getCompound("electricStorage"));
        }

        if (upgradesStorage != null) {
            upgradesStorage.deserializeNBT(tag.getCompound("upgradesStorage"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        if (isStateActive) {
            tag.putBoolean("activeState", activeState);
        }

        if (hasCooldown) {
            tag.putInt("cooldown", cooldown);
        }

        if (hasExp) {
            tag.putShort("RecipesUsedSize", (short)this.recipesUsed.size());
            int i = 0;
            for(Map.Entry<ResourceLocation, Integer> entry : this.recipesUsed.entrySet()) {
                tag.putString("RecipeLocation" + i, entry.getKey().toString());
                tag.putInt("RecipeAmount" + i, entry.getValue());
                ++i;
            }
        }

        if (energyStorage != null) {
            tag.put("energy", energyStorage.serializeNBT());
        }

        if (baseStorage != null) {
            tag.put("baseStorage", baseStorage.serializeNBT());
        }

        if (electricStorage != null) {
            tag.put("electricStorage", electricStorage.serializeNBT());
        }

        if (upgradesStorage != null) {
            tag.put("upgradesStorage", upgradesStorage.serializeNBT());
        }

        super.saveAdditional(tag);
    }

    public void onPlace(boolean isClient) {

    }

    public void onBreak() {
        NonNullList<ItemStack> stacks = NonNullList.create();

        if (baseStorage != null) {
            for (BaseSlot slot : baseStorage.getBaseSlots()) {
                if (slot.getInventorySlotType() != InventorySlotType.DISABLED) {
                    stacks.add(baseStorage.getStackInSlot(slot.getSlotId()));
                }
            }
        }

        if (electricStorage != null) {
            for (BaseSlot slot : electricStorage.getElectricSlots()) {
                if (slot.getInventorySlotType() != InventorySlotType.DISABLED) {
                    stacks.add(electricStorage.getStackInSlot(slot.getSlotId()));
                }
            }
        }

        if (upgradesStorage != null) {
            for (BaseSlot slot : upgradesStorage.getUpgradeSlots()) {
                if (slot.getInventorySlotType() != InventorySlotType.DISABLED) {
                    stacks.add(upgradesStorage.getStackInSlot(slot.getSlotId()));
                }
            }
        }

        Containers.dropContents(getLevel(), getBlockPos(), stacks);
    }

    @Override
    public void syncWithChunk(ServerPlayer player) {
        if (energyStorage != null) {
            ModNetworking.sendToPlayer(player, new PacketBasicEnergySync(energyStorage, getBlockPos()));
        }

        if (this instanceof IBlockEntityFluid entityFluid) {
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(entityFluid.getStoredFluids(), getBlockPos()));
        }
    }
}