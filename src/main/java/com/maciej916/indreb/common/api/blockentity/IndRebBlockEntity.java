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
import com.maciej916.indreb.common.api.item.base.BaseUpgradeItem;
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
import com.maciej916.indreb.common.network.packet.PacketExperienceCollect;
import com.maciej916.indreb.common.network.packet.PacketExperienceSync;
import com.maciej916.indreb.common.network.packet.PacketFluidSync;
import com.maciej916.indreb.common.tag.ModTagsItem;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.Constants;
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
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

import static com.maciej916.indreb.common.api.enums.UpgradeType.*;

public class IndRebBlockEntity extends BaseBlockEntity implements IIndRebBlockEntity, IBlockEntityChunkSync, MenuProvider {

    private BasicEnergyStorage energyStorage;
    protected final LazyOptional<IEnergyStorage> energyStorageCap = LazyOptional.of(() -> energyStorage);

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
    private float storedExperience = 0;

    private SoundEvent soundEvent = null;
    private SoundInstance activeSound;

    private Map<ResourceLocation, Integer> recipesUsed = new HashMap<>();

    protected boolean activeState = false;
    protected boolean prevActiveState = false;
    protected boolean shouldUpdateState = false;

    private boolean invertRedstone = false;
    float speedFactor = 1f;
    float energyUsageFactor = 1f;

    public IndRebBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        init();
    }

    @Override
    public boolean hasMenu() {
        return true;
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
           this.baseStorage = new BaseItemStackHandler(this, slots) {
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

    public float getExperience(Recipe<?> recipe) {
        return 0;
    }

    public float getStoredExperience() {
        return storedExperience;
    }

    public void setStoredExperience(float storedExperience) {
        this.storedExperience = storedExperience;
    }

    public void collectExpServer(@Nullable ServerPlayer player) {
        List<Recipe<?>> list = new ArrayList<>();

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipesUsed.entrySet()) {
            level.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe -> {
                list.add(recipe);
                BlockEntityUtil.spawnExpOrbs((ServerLevel) level, player, getBlockPos(), entry.getValue(), getExperience(recipe));
            }));
        }

        if (player != null) {
            player.awardRecipes(list);
        }

        this.recipesUsed.clear();
        this.storedExperience = 0;
        ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketExperienceSync(storedExperience, getBlockPos()));
    }

    public Runnable collectExpClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketExperienceCollect(getBlockPos()));
    }

    public void addRecipeUsed(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            this.recipesUsed.compute(recipe.getId(), (key, val) -> 1 + (val == null ? 0 : val));
            this.storedExperience += getExperience(recipe);
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketExperienceSync(storedExperience, getBlockPos()));
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
            setBlockUpdated();
        }

        setActiveState(activeState);
        baseSlotsChangedForTick.clear();
    }

    @Override
    public void tickWork() {

    }

    @Override
    public void tickUpgrades() {
        int countSpeed = 0;
        int countEnergyUsage = 0;
        int countEnergyStorage = 0;
        int countTransformer = 0;

        invertRedstone = false;

        for (int i = 0; i < getUpgradesStorage().getSlots(); i++) {
            ItemStack stack = getUpgradesStorage().getStackInSlot(i);
            CompoundTag tag = stack.getOrCreateTag();
            if (!stack.isEmpty() && stack.getItem() instanceof BaseUpgradeItem itemUpgrade) {
                if (itemUpgrade.getUpgradeType() == UpgradeType.OVERCLOCKER) {
                    countSpeed += stack.getCount();
                    countEnergyUsage += stack.getCount();
                }

                if (itemUpgrade.getUpgradeType() == UpgradeType.ENERGY_STORAGE) {
                    countEnergyStorage += stack.getCount();
                }

                if (itemUpgrade.getUpgradeType() == UpgradeType.TRANSFORMER) {
                    countTransformer += stack.getCount();
                }

                if (itemUpgrade.getUpgradeType() == UpgradeType.REDSTONE_SIGNAL_INVERTER && !invertRedstone) {
                    invertRedstone = true;
                }

                if (itemUpgrade.getUpgradeType() == UpgradeType.PULLING ||
                        itemUpgrade.getUpgradeType() == EJECTOR ||
                        itemUpgrade.getUpgradeType() == FLUID_EJECTOR ||
                        itemUpgrade.getUpgradeType() == FLUID_PULLING
                ) {
                    if (level.getGameTime() % 5 == 0) {
                        int direction = tag.getAllKeys().contains("Direction") ? tag.getInt("Direction") : -1;

                        ArrayList<Direction> directions = new ArrayList<>();
                        if (direction == -1) {
                            directions.addAll(Arrays.stream(Constants.DIRECTIONS).toList());
                        } else {
                            directions.add(Direction.from3DDataValue(direction));
                        }
                        switch (itemUpgrade.getUpgradeType()) {
                            case PULLING -> pullItems(directions, stack.getCount());
                            case EJECTOR -> ejectItems(directions, stack.getCount());
                            case FLUID_PULLING -> pullFluids(directions, stack.getCount());
                            case FLUID_EJECTOR -> ejectFluids(directions, stack.getCount());
                        }
                    }
                }
            }
        }

        double speedFactor = Math.pow(0.7, countSpeed);
        double energyUsageFactor = Math.pow(1.6, countEnergyUsage);
        double energyStorageAdd = countEnergyStorage * 10000;

        this.speedFactor = (float) speedFactor;
        this.energyUsageFactor = (float) energyUsageFactor;

        double calculateEnergy = (energyStorage.origEnergy * energyUsageFactor) + energyStorageAdd;

        int newEnergy = calculateEnergy < Integer.MAX_VALUE ? (int) calculateEnergy : Integer.MAX_VALUE;
        energyStorage.setMaxEnergy(newEnergy);

        int newTier = energyStorage.origTier.getLvl() + countTransformer;
        energyStorage.setEnergyTier(newTier > 5 ? EnergyTier.ULTRA : EnergyTier.getTierFromLvl(newTier));
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
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {

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

        return super.getCapability(cap, side);
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

        this.invertRedstone = tag.getBoolean("invertRedstone");
        this.speedFactor = tag.getFloat("speedFactor");
        this.energyUsageFactor = tag.getFloat("energyUsageFactor");

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
            this.storedExperience = tag.getFloat("storedExperience");
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
        tag.putBoolean("invertRedstone", invertRedstone);
        tag.putFloat("speedFactor", speedFactor);
        tag.putFloat("energyUsageFactor", energyUsageFactor);

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
            tag.putFloat("storedExperience", storedExperience);
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

        if (hasExp) {
            collectExpServer(null);
        }

        Containers.dropContents(getLevel(), getBlockPos(), stacks);
    }

    @Override
    public void syncWithChunk(ServerPlayer player) {
        if (energyStorage != null) {
            ModNetworking.sendToPlayer(player, new PacketBasicEnergySync(energyStorage, getBlockPos()));
        }

        if (hasExp) {
            ModNetworking.sendToPlayer(player, new PacketExperienceSync(storedExperience, getBlockPos()));
        }

        if (this instanceof IBlockEntityFluid entityFluid) {
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(entityFluid.getStoredFluids(), getBlockPos()));
        }
    }

    public void setBlockUpdated() {
        if (level != null) {
            setChanged();
            level.setBlockAndUpdate(getBlockPos(), getBlockState());
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
        }
    }

    /* THIS CAN BE IMPROVED WITH CAPABILITIES */

    public void pullItems(ArrayList<Direction> directions, int count) {
        IItemHandler myHandler = CapabilityUtil.getCapabilityHelper(this, ForgeCapabilities.ITEM_HANDLER).getValue();
        if (myHandler != null) {
            for (int i = 0; i < myHandler.getSlots(); i++) {
                if (getBaseStorage().getBaseSlots().get(i).getInventorySlotType() == InventorySlotType.INPUT) {
                    boolean found = false;

                    for (Direction dir : directions) {
                        BlockEntity blockEntity = level.getBlockEntity(getBlockPos().relative(dir));
                        if (blockEntity != null) {
                            if (blockEntity instanceof IndRebBlockEntity ibe) {
                                IItemHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, ForgeCapabilities.ITEM_HANDLER).getValue();
                                if (otherHandler != null) {
                                    for (int j = 0; j < ibe.getBaseStorage().getBaseSlots().size(); j++) {
                                        if (ibe.getBaseStorage().getBaseSlots().get(j).getInventorySlotType() == InventorySlotType.OUTPUT || ibe.getBaseStorage().getBaseSlots().get(j).getInventorySlotType() == InventorySlotType.BONUS) {
                                            int amount = Math.min(otherHandler.getStackInSlot(j).getCount(), count);
                                            ItemStack extractItem = otherHandler.extractItem(j, amount, true);
                                            if (!extractItem.isEmpty()) {
                                                ItemStack insertItem = myHandler.insertItem(i, extractItem, true);
                                                if (insertItem.isEmpty()) {
                                                    otherHandler.extractItem(j, amount, false);
                                                    myHandler.insertItem(i, extractItem, false);
                                                    found = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                IItemHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, ForgeCapabilities.ITEM_HANDLER, dir.getOpposite()).getValue();
                                if (otherHandler != null) {
                                    for (int j = 0; j < otherHandler.getSlots(); j++) {
                                        int amount = Math.min(otherHandler.getStackInSlot(j).getCount(), count);
                                        ItemStack extractItem = otherHandler.extractItem(j, amount, true);
                                        if (!extractItem.isEmpty()) {
                                            ItemStack insertItem = myHandler.insertItem(i, extractItem, true);
                                            if (insertItem.isEmpty()) {
                                                otherHandler.extractItem(j, amount, false);
                                                myHandler.insertItem(i, extractItem, false);
                                                found = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (found) {
                        break;
                    }
                }
            }
        }
    }

    public void ejectItems(ArrayList<Direction> directions, int count) {
        IItemHandler myHandler = CapabilityUtil.getCapabilityHelper(this, ForgeCapabilities.ITEM_HANDLER).getValue();
        if (myHandler != null) {
            for (int i = 0; i < myHandler.getSlots(); i++) {
                if (getBaseStorage().getBaseSlots().get(i).getInventorySlotType() == InventorySlotType.OUTPUT || getBaseStorage().getBaseSlots().get(i).getInventorySlotType() == InventorySlotType.BONUS) {
                    boolean found = false;

                    for (Direction dir : directions) {
                        BlockEntity blockEntity = level.getBlockEntity(getBlockPos().relative(dir));
                        if (blockEntity != null) {
                            if (blockEntity instanceof IndRebBlockEntity ibe) {
                                IItemHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, ForgeCapabilities.ITEM_HANDLER).getValue();
                                if (otherHandler != null) {
                                    int amount = Math.min(myHandler.getStackInSlot(i).getCount(), count);
                                    ItemStack extractItem = myHandler.extractItem(i, amount, true);

                                    if (!extractItem.isEmpty()) {
                                        for (int j = 0; j < ibe.getBaseStorage().getBaseSlots().size(); j++) {
                                            if (ibe.getBaseStorage().getBaseSlots().get(j).getInventorySlotType() == InventorySlotType.INPUT) {
                                                ItemStack insertItem = otherHandler.insertItem(j, extractItem, true);
                                                if (insertItem.isEmpty()) {
                                                    myHandler.extractItem(i, amount, false);
                                                    otherHandler.insertItem(j, extractItem, false);
                                                    found = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                IItemHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, ForgeCapabilities.ITEM_HANDLER, dir.getOpposite()).getValue();
                                if (otherHandler != null) {
                                    int amount = Math.min(myHandler.getStackInSlot(i).getCount(), count);
                                    ItemStack extractItem = myHandler.extractItem(i, amount, true);

                                    if (!extractItem.isEmpty()) {
                                        for (int j = 0; j < otherHandler.getSlots(); j++) {
                                            ItemStack insertItem = otherHandler.insertItem(j, extractItem, true);
                                            if (insertItem.isEmpty()) {
                                                myHandler.extractItem(i, amount, false);
                                                otherHandler.insertItem(j, extractItem, false);
                                                found = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (found) {
                        break;
                    }
                }
            }
        }
    }

    public void pullFluids(ArrayList<Direction> directions, int count) {
        for (Direction dir : directions) {
            IFluidHandler myHandler = CapabilityUtil.getCapabilityHelper(this, ForgeCapabilities.FLUID_HANDLER, dir).getValue();
            if (myHandler != null) {
                BlockEntity blockEntity = level.getBlockEntity(getBlockPos().relative(dir));
                if (blockEntity != null) {
                    IFluidHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, ForgeCapabilities.FLUID_HANDLER, dir.getOpposite()).getValue();
                    if (otherHandler != null) {
                        for (int i = 0; i < otherHandler.getTanks(); i++) {
                            FluidStack stack = otherHandler.getFluidInTank(i);
                            if (!stack.isEmpty()) {
                                stack.setAmount(Math.min(stack.getAmount(), 100 * count));
                                FluidStack extractFluid = otherHandler.drain(stack, IFluidHandler.FluidAction.SIMULATE);
                                boolean found = false;
                                if (!extractFluid.isEmpty()) {
                                    for (int j = 0; j < myHandler.getTanks(); j++) {
                                        if (myHandler.isFluidValid(j, extractFluid)) {
                                            int insertAmount = myHandler.fill(stack, IFluidHandler.FluidAction.SIMULATE);
                                            if (insertAmount > 0) {
                                                extractFluid.setAmount(insertAmount);
                                                otherHandler.drain(extractFluid, IFluidHandler.FluidAction.EXECUTE);
                                                myHandler.fill(extractFluid, IFluidHandler.FluidAction.EXECUTE);
                                                found = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (found) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void ejectFluids(ArrayList<Direction> directions, int count) {
        for (Direction dir : directions) {
            IFluidHandler myHandler = CapabilityUtil.getCapabilityHelper(this, ForgeCapabilities.FLUID_HANDLER, dir).getValue();
            if (myHandler != null) {
                BlockEntity blockEntity = level.getBlockEntity(getBlockPos().relative(dir));
                if (blockEntity != null) {
                    IFluidHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, ForgeCapabilities.FLUID_HANDLER, dir.getOpposite()).getValue();
                    if (otherHandler != null) {
                        for (int i = 0; i < myHandler.getTanks(); i++) {
                            FluidStack stack = myHandler.getFluidInTank(i);
                            if (!stack.isEmpty()) {
                                stack.setAmount(Math.min(stack.getAmount(), 100 * count));
                                FluidStack extractFluid = myHandler.drain(stack, IFluidHandler.FluidAction.SIMULATE);
                                boolean found = false;
                                if (!extractFluid.isEmpty()) {
                                    for (int j = 0; j < otherHandler.getTanks(); j++) {
                                        if (otherHandler.isFluidValid(j, extractFluid)) {
                                            int insertAmount = otherHandler.fill(stack, IFluidHandler.FluidAction.SIMULATE);
                                            if (insertAmount > 0) {
                                                extractFluid.setAmount(insertAmount);
                                                myHandler.drain(extractFluid, IFluidHandler.FluidAction.EXECUTE);
                                                otherHandler.fill(extractFluid, IFluidHandler.FluidAction.EXECUTE);
                                                found = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (found) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
