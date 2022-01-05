package com.maciej916.indreb.common.entity.block;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.maciej916.indreb.common.energy.impl.BasicEnergyStorage;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotItemHandlerDisabled;
import com.maciej916.indreb.common.entity.slot.SlotItemHandlerOutput;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.interfaces.entity.*;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.item.impl.upgrade.ItemUpgrade;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketExperience;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.Constants;
import com.maciej916.indreb.common.util.SoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.*;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.maciej916.indreb.common.registries.ModTags.*;

public class IndRebBlockEntity extends BlockEntity implements IHasSlot {

    private ItemStackHandler stackHandler;
    private ArrayList<IndRebSlot> item = new ArrayList<>();
    private final ArrayList<SlotItemHandler> itemHandlers = new ArrayList<>();

    private ArrayList<IElectricSlot> electricSlot = new ArrayList<>();
    private BasicEnergyStorage energyStorage;
    private final LazyOptional<IEnergy> energy = LazyOptional.of(() -> energyStorage);

    private ItemStackHandler batteryHandler;
    private final ArrayList<ElectricSlotHandler> batteryHandlers = new ArrayList<>();

    private ArrayList<IElectricSlot> upgradeSlot = new ArrayList<>();
    private ItemStackHandler upgradesHandler;
    private final ArrayList<UpgradeSlotHandler> upgradeHandlers = new ArrayList<>();

    Block block;
    protected int tickCounter = 0;
    private int cooldown = 0;
    private boolean invertRedstone = false;

    protected boolean isActivate;
    protected boolean hasCooldown;
    protected boolean hasInventory = false;
    protected boolean hasEnergy = false;
    protected boolean hasBattery = false;
    protected boolean hasSound = false;
    protected boolean hasExp = false;
    protected boolean hasUpgrades = false;

    private SoundEvent soundEvent = null;
    private SoundInstance activeSound;

    private final Map<ResourceLocation, Integer> recipesUsed = Maps.newHashMap();


    float speedFactor = 1f;
    float energyUsageFactor = 1f;

    public IndRebBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
        this.init();
    }

    public Block getBlock() {
        return block;
    }

    public boolean hasBattery() {
        return hasBattery;
    }

    public boolean hasEnergy() {
        return hasEnergy;
    }

    public boolean hasUpgrades() {
        return hasUpgrades;
    }

    public void init() {
        this.block = getBlockState().getBlock();

        this.setSupportedTypes();
        this.initSlots();

        if (hasSound) {
            soundEvent = ((ITileSound) this).getSoundEvent();
        }

        if (hasUpgrades) {
            initUpgradeHandler();
        }
    }

    private void setSupportedTypes() {
        isActivate = block instanceof IStateActive;
        hasCooldown = this instanceof ICooldown;
        hasSound = this instanceof ITileSound;
        hasExp = this instanceof IExpCollector;
        hasUpgrades = this instanceof ISupportUpgrades;
    }

    public void updateBlockState() {
        if (level != null) {
            this.setChanged();

            // TODO
            // I don't think its good idea/ practice to do it like that

            level.setBlockAndUpdate(getBlockPos(), getBlockState());
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
        }
    }

    public void tickServer(BlockState state) {
        if (tickCounter == 20) {
            tickCounter = 0;
        } else {
            tickCounter++;
        }

        if (hasCooldown) {
            if (tickCounter == 20 && cooldown > 0) {
                --cooldown;
                this.updateBlockState();
            }
        }

        if (hasUpgrades()) {
            tickUpgrades(state);
        }

        tickOperate(state);
    }

    public void tickOperate(BlockState state) {

    }

    public void tickUpgrades(BlockState state) {

        int countSpeed = 0;
        int countEnergyUsage = 0;
        int countEnergyStorage = 0;
        int countTransformer = 0;

        invertRedstone = false;

        for (int i = 0; i < upgradesHandler.getSlots(); i++) {
            ItemStack stack = upgradesHandler.getStackInSlot(i);
            CompoundTag tag  = stack.getOrCreateTag();
            if (!stack.isEmpty() && stack.getItem() instanceof ItemUpgrade itemUpgrade) {
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
                        itemUpgrade.getUpgradeType() == UpgradeType.EJECTOR ||
                        itemUpgrade.getUpgradeType() == UpgradeType.FLUID_EJECTOR ||
                        itemUpgrade.getUpgradeType() == UpgradeType.FLUID_PULLING
                ) {
                    if (level.getGameTime() % 5 == 0) {
                        int direction = tag.getAllKeys().contains("Direction") ? tag.getInt("Direction") : -1;

                        ArrayList<Direction> directions = new ArrayList<>();
                        if (direction == -1) {
                            directions.addAll(Arrays.stream(Constants.DIRECTIONS).collect(Collectors.toList()));
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

    public void pullItems(ArrayList<Direction> directions, int count) {
        for (Direction dir : directions) {
            IItemHandler myHandler = CapabilityUtil.getCapabilityHelper(this, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir).getValue();
            if (myHandler != null) {
                BlockEntity blockEntity = level.getBlockEntity(getBlockPos().relative(dir));
                if (blockEntity != null) {
                    IItemHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir.getOpposite()).getValue();
                    if (otherHandler != null) {
                        for (int i = 0; i < otherHandler.getSlots(); i++) {
                            int amount = Math.min(otherHandler.getStackInSlot(i).getCount(), count);
                            ItemStack extractItem = otherHandler.extractItem(i, amount, true);
                            boolean found = false;
                            if (!extractItem.isEmpty()) {
                                for (int j = 0; j < myHandler.getSlots(); j++) {
                                    ItemStack insertItem = myHandler.insertItem(j, extractItem, true);
                                    if (insertItem.isEmpty()) {
                                        otherHandler.extractItem(i, amount, false);
                                        myHandler.insertItem(j, extractItem, false);
                                        found = true;
                                        break;
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

    public void ejectItems(ArrayList<Direction> directions, int count) {
        for (Direction dir : directions) {
            IItemHandler myHandler = CapabilityUtil.getCapabilityHelper(this, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir).getValue();
            if (myHandler != null) {
                BlockEntity blockEntity = level.getBlockEntity(getBlockPos().relative(dir));
                if (blockEntity != null) {
                    IItemHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir.getOpposite()).getValue();
                    if (otherHandler != null) {
                        for (int i = 0; i < myHandler.getSlots(); i++) {
                            int amount = Math.min(myHandler.getStackInSlot(i).getCount(), count);
                            ItemStack extractItem = myHandler.extractItem(i, amount, true);
                            boolean found = false;
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
                            if (found) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void pullFluids(ArrayList<Direction> directions, int count) {
        for (Direction dir : directions) {
            IFluidHandler myHandler = CapabilityUtil.getCapabilityHelper(this, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir).getValue();
            if (myHandler != null) {
                BlockEntity blockEntity = level.getBlockEntity(getBlockPos().relative(dir));
                if (blockEntity != null) {
                    IFluidHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir.getOpposite()).getValue();
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
            IFluidHandler myHandler = CapabilityUtil.getCapabilityHelper(this, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir).getValue();
            if (myHandler != null) {
                BlockEntity blockEntity = level.getBlockEntity(getBlockPos().relative(dir));
                if (blockEntity != null) {
                    IFluidHandler otherHandler = CapabilityUtil.getCapabilityHelper(blockEntity, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir.getOpposite()).getValue();
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

    public void tickClient(BlockState state) {
        if (hasSound) {
            handleSound();
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        if (hasSound) {
            handleSound();
        }

        if (hasEnergy) {
            energy.invalidate();
        }
    }

    public void initSlots() {
        ArrayList<IndRebSlot> slots = addInventorySlot(new ArrayList<>());
        if (slots.size() > 0) {
            this.initStackHandler(slots);
            this.hasInventory = true;
        }
    }

    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        return slots;
    }

    public boolean isItemValidForSlot(final int slot, @Nonnull final ItemStack stack) {
        return true;
    }

    @Nullable
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return null;
    }

    public void initStackHandler(ArrayList<IndRebSlot> slots) {
        this.item = slots;
        this.stackHandler = new ItemStackHandler(slots.size()) {
            @Override
            public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
                return isItemValidForSlot(slot, stack);
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                ItemStack returnedStack = insertItemForSlot(slot, stack, simulate);
                return Objects.requireNonNullElseGet(returnedStack, () -> super.insertItem(slot, stack, simulate));
            }

            @Override
            protected void onContentsChanged(final int slot) {
                super.onContentsChanged(slot);
                setChanged();
            }
        };

        slots.forEach(sl -> {
            if (sl.getInventorySlotType() == InventorySlotType.OUTPUT) {
                itemHandlers.add(new SlotItemHandlerOutput(this, stackHandler, sl.getSlotId(), sl.getXPosition(), sl.getYPosition()));
            } else if (sl.getInventorySlotType() == InventorySlotType.DISABLED) {
                itemHandlers.add(new SlotItemHandlerDisabled(stackHandler, sl.getSlotId(), sl.getXPosition(), sl.getYPosition()));
            }else {
                itemHandlers.add(new SlotItemHandler(stackHandler, sl.getSlotId(), sl.getXPosition(), sl.getYPosition()));
            }
        });
    }

    public ArrayList<IndRebSlot> getItem() {
        return item;
    }

    public @NotNull ItemStackHandler getStackHandler() {
        return stackHandler;
    }

    @Override
    public ArrayList<SlotItemHandler> getItemHandlers() {
        return itemHandlers;
    }

    public void initBatterySlots() {
        ArrayList<IElectricSlot> slots = addBatterySlot(new ArrayList<>());
        if (slots.size() > 0) {
            this.initBatteryStackHandler(slots);
            this.hasBattery = true;
        }
    }

    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        return slots;
    }

    public void initBatteryStackHandler(ArrayList<IElectricSlot> slots) {
        this.electricSlot = slots;
        this.batteryHandler = new ItemStackHandler(slots.size()) {
            @Override
            public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
                return stack.getItem().getTags().contains(ELECTRICS_RES);
            }

            @Override
            protected void onContentsChanged(final int slot) {
                super.onContentsChanged(slot);
                setChanged();
            }
        };

        slots.forEach(sl -> batteryHandlers.add(
                new ElectricSlotHandler(batteryHandler, sl.getSlotId(), sl.getXPosition(), sl.getYPosition(), sl.isCharging(), sl.getInventorySlotType(), energyStorage))
        );
    }

    public ItemStackHandler getBatteryHandler() {
        return batteryHandler;
    }

    public ArrayList<ElectricSlotHandler> getBatteryHandlers() {
        return batteryHandlers;
    }

    public ArrayList<IElectricSlot> getElectricSlot() {
        return electricSlot;
    }

    public boolean canExtractEnergyDir(Direction side) {
        return false;
    }

    public boolean canReceiveEnergyDir(Direction side) {
        return false;
    }

    public int customEnergyReceiveTick() {
        return -1;
    }

    public int customEnergyExtractTick() {
        return -1;
    }

    public void createEnergyStorage(int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        this.energyStorage = new BasicEnergyStorage(energyStored, maxEnergy, energyType, energyTier) {
            @Override
            public boolean canExtractEnergy(Direction side) {
                return canExtractEnergyDir(side);
            }

            @Override
            public boolean canReceiveEnergy(Direction side) {
                return canReceiveEnergyDir(side);
            }

            @Override
            public int maxReceiveTick() {
                int customReceive = customEnergyReceiveTick();
                return customReceive == -1 ? super.maxReceiveTick() : customReceive;
            }

            @Override
            public int maxExtractTick() {
                int customExtract = customEnergyExtractTick();
                return customExtract == -1 ? super.maxExtractTick() : customExtract;
            }

            @Override
            public void updated() {
                updateBlockState();
            }
        };
        this.hasEnergy = true;
        this.initBatterySlots();
    }

    public BasicEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public float getSpeedFactor() {
        return speedFactor;
    }

    public float getEnergyUsageFactor() {
        return energyUsageFactor;
    }

    public List<UpgradeType> getSupportedUpgrades() {
      return new ArrayList<>();
    }

    public void initUpgradeHandler() {
        this.upgradesHandler = new ItemStackHandler(4) {
            @Override
            public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
                if (stack.getItem() instanceof ItemUpgrade itemUpgrade) {
                    return getSupportedUpgrades().contains(itemUpgrade.getUpgradeType());
                }
                return false;
            }

            @Override
            protected void onContentsChanged(final int slot) {
                super.onContentsChanged(slot);
                setChanged();
            }
        };

        for(int i = 0; i < 4; ++i) {
            upgradeHandlers.add(new UpgradeSlotHandler(upgradesHandler, i, 178, 9 + (i * 18), getSupportedUpgrades()));
        }
    }

    public ArrayList<UpgradeSlotHandler> getUpgradeHandlers() {
        return upgradeHandlers;
    }

    public int getTotalMachineSlotCount() {
        return itemHandlers.size() + batteryHandlers.size() + upgradeHandlers.size();
    }

    public int getRedstonePower() {
        if (level == null) return 0;
        int power = level.getDirectSignalTo(getBlockPos());
        return invertRedstone ? 15 - power : power;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int time) {
        this.cooldown = time;
    }

    protected boolean getActive() {
        if (isActivate) {
            return ((IStateActive) block).isActive(getBlockState());
        }
        return false;
    }

    public boolean setActive(boolean active) {
        if (isActivate) {
            assert level != null;
            if (getActive() != active) {
                BlockState state = ((IStateActive) block).setActive(getBlockState(), active);
                level.setBlockAndUpdate(getBlockPos(), state);
                return true;
            }
        }
        return false;
    }

    protected boolean canPlaySound() {
        return getActive();
    }

    private void handleSound() {
        if (hasSound) {
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

    public Runnable collectExp() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketExperience(getBlockPos()));
    }

    public float getExperience(Recipe<?> recipe) {
        return 0;
    }

    public void collectExp(Player player) {
        List<Recipe<?>> list = Lists.newArrayList();

        for(Map.Entry<ResourceLocation, Integer> entry : this.recipesUsed.entrySet()) {
            player.level.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe -> {
                list.add(recipe);
                spawnExpOrbs(player, entry.getValue(), getExperience(recipe));
            }));
        }

        player.awardRecipes(list);
        this.recipesUsed.clear();
    }

    public void setRecipeUsed(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            this.recipesUsed.compute(recipe.getId(), (key, val) -> 1 + (val == null ? 0 : val));
        }
    }

    private void spawnExpOrbs(Player player, int entry, float experience) {
        if (experience == 0.0F) {
            entry = 0;
        } else if (experience < 1.0F) {
            int i = (int) Math.floor((float)entry * experience);
            if (i < Math.ceil((float)entry * experience) && Math.random() < (double)((float)entry * experience - (float)i)) {
                ++i;
            }
            entry = i;
        }

        while(entry > 0) {
            int j = ExperienceOrb.getExperienceValue(entry);
            entry -= j;
            level.addFreshEntity(new ExperienceOrb(level, player.getX(), player.getY() + 0.5D, player.getZ() + 0.5D, j));
        }
    }

    public void onPlace() {

    }

    public void onBreak() {
        NonNullList<ItemStack> stacks = NonNullList.create();

        if (hasInventory) {
            for (int slot = 0; slot < stackHandler.getSlots(); slot++) {
                if (item.get(slot).getInventorySlotType() != InventorySlotType.DISABLED) {
                    stacks.add(stackHandler.getStackInSlot(slot));
                }
            }
        }

        if (hasBattery) {
            for (int slot = 0; slot < batteryHandler.getSlots(); slot++) {
                stacks.add(batteryHandler.getStackInSlot(slot));
            }
        }

        if (hasUpgrades) {
            for (int slot = 0; slot < upgradesHandler.getSlots(); slot++) {
                stacks.add(upgradesHandler.getStackInSlot(slot));
            }
        }

        Containers.dropContents(getLevel(), getBlockPos(), stacks);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (hasEnergy) {
            if (cap == ModCapabilities.ENERGY) {
                return energy.cast();
            }
        }

        return super.getCapability(cap, side);
    }
















// sync block data its not good because it always sync

    // chunk loads
    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    // create tag for load
    @Override
    public CompoundTag getUpdateTag() {
        return save(new CompoundTag());
    }


    // on block update

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if (pkt.getTag() != null) {
            load(pkt.getTag());
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (hasInventory) {
            stackHandler.deserializeNBT(tag.getCompound("inventory"));
        }

        if (hasBattery) {
            batteryHandler.deserializeNBT(tag.getCompound("battery"));
        }

        if (hasUpgrades) {
            upgradesHandler.deserializeNBT(tag.getCompound("upgrade"));
        }

        if (hasEnergy) {
            energyStorage.setEnergy(tag.getInt("energy"));
        }

        if (hasCooldown) {
            this.cooldown = tag.getInt("cooldown");
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
    }

    @Override
    public CompoundTag save(CompoundTag tag) {

        if (hasInventory) {
            tag.put("inventory", stackHandler.serializeNBT());
        }

        if (hasBattery) {
            tag.put("battery", batteryHandler.serializeNBT());
        }

        if (hasUpgrades) {
            tag.put("upgrade", upgradesHandler.serializeNBT());
        }

        if (hasEnergy) {
            tag.putInt("energy", energyStorage.energyStored());
        }

        if (hasCooldown) {
            tag.putInt("cooldown", cooldown);
        }

        if (hasExp) {
            int i = tag.getShort("RecipesUsedSize");
            for(int j = 0; j < i; ++j) {
                ResourceLocation resourcelocation = new ResourceLocation(tag.getString("RecipeLocation" + j));
                int k = tag.getInt("RecipeAmount" + j);
                this.recipesUsed.put(resourcelocation, k);
            }
        }

        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        save(tag);
    }
}
