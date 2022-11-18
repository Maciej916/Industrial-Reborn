package com.maciej916.indreb.common.energy;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityChargePad;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityTransformer;
import com.maciej916.indreb.common.api.blockentity.interfaces.IIndRebBlockEntity;
import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.energy.comparator.EnergyExtractComparator;
import com.maciej916.indreb.common.energy.comparator.EnergyExtractComparatorNetwork;
import com.maciej916.indreb.common.energy.comparator.EnergyReceiveComparator;
import com.maciej916.indreb.common.energy.interfaces.IEnergyCore;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketParticle;
import com.maciej916.indreb.common.util.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EnergyCore implements IEnergyCore, ICapabilitySerializable<CompoundTag> {

    private final Level world;
    public EnergyNetworks networks;
    private ArrayList<BlockPos> energyBlocks = new ArrayList<>();
    private final LazyOptional<IEnergyCore> energyCoreLazyOptional = LazyOptional.of(() -> this);

    public EnergyCore(Level world) {
        this.world = world;
        this.networks = new EnergyNetworks(world);
    }

    @Override
    public EnergyNetworks getNetworks() {
        return networks;
    }

    @Override
    public void addEnergyBlock(BlockPos pos) {
        energyBlocks.add(pos);
    }

    @Override
    public void removeEnergyBlock(BlockPos pos) {
        energyBlocks.remove(pos);
    }

    private final HashMap<BlockPos, Integer> energyReceivedBlock = new HashMap<>();

    public int getEnergyReceivedBlock(BlockPos pos) {
        return energyReceivedBlock.getOrDefault(pos, 0);
    }

    public void addEnergyReceivedBlock(BlockPos pos, int amount) {
        if (energyReceivedBlock.containsKey(pos)) {
            energyReceivedBlock.put(pos, energyReceivedBlock.get(pos) + amount);
        } else {
            energyReceivedBlock.put(pos, amount);
        }
    }

    private final HashMap<BlockPos, Integer> energyExtractedBlock = new HashMap<>();

    public int getEnergyExtractedBlock(BlockPos pos) {
        return energyExtractedBlock.getOrDefault(pos, 0);
    }

    public void addEnergyExtractedBlock(BlockPos pos, int amount) {
        if (energyExtractedBlock.containsKey(pos)) {
            energyExtractedBlock.put(pos, energyExtractedBlock.get(pos) + amount);
        } else {
            energyExtractedBlock.put(pos, amount);
        }
    }

    private final HashMap<EnergyNetwork, Integer> energyReceivedNetwork = new HashMap<>();

    public int getEnergyReceivedNetwork(EnergyNetwork network) {
        return energyReceivedNetwork.getOrDefault(network, 0);
    }

    public void addEnergyReceivedNetwork(EnergyNetwork network, int amount) {
        if (energyReceivedNetwork.containsKey(network)) {
            energyReceivedNetwork.put(network, energyReceivedNetwork.get(network) + amount);
        } else {
            energyReceivedNetwork.put(network, amount);
        }
    }

    private final HashMap<EnergyNetwork, Integer> energyExtractedNetwork = new HashMap<>();

    public int getEnergyExtractedNetwork(EnergyNetwork network) {
        return energyExtractedNetwork.getOrDefault(network, 0);
    }

    public void addEnergyExtractedNetwork(EnergyNetwork network, int amount) {
        if (energyExtractedNetwork.containsKey(network)) {
            energyExtractedNetwork.put(network, energyExtractedNetwork.get(network) + amount);
        } else {
            energyExtractedNetwork.put(network, amount);
        }
    }

    private void createExplosion(BlockPos pos, int lvl) {
        world.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 2, false, Explosion.BlockInteraction.DESTROY);
    }

    private void createBurn(EnergyNetwork network, BlockPos blockPos) {
        HashSet<BlockPos> connections = (HashSet<BlockPos>) network.getConnections().clone();
        for (BlockPos netPos : connections) {
            world.removeBlock(netPos, false);
            networks.onRemove(netPos);
            world.playSound(null, netPos, SoundEvents.GENERIC_BURN, SoundSource.BLOCKS, 1F, 0.9F / (new Random().nextFloat() * 0.4F + 0.8F));
            ModNetworking.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> world.getChunkAt(netPos)), new PacketParticle(netPos));
        }
    }

    /**
     * Evenly charge internal energy providers
     * @param pos
     * @param energyFrom
     * @param energyTo
     * @param max
     */
    private void chargeInternal(BlockPos pos, BasicEnergyStorage energyFrom, List<EnergyReceiveComparator> energyTo, int max) {
        int chargeLeft = energyFrom.maxExtract() - getEnergyExtractedBlock(pos);
        int size = energyTo.size();
        int chargeSplit = chargeLeft / size;

        for (EnergyReceiveComparator en: energyTo) {
            if (chargeLeft > 0 && chargeSplit > 0) {
                int maxEnergy = Math.min(chargeSplit, en.getEnergy().maxReceive());
                int distributed = energyFrom.extractEnergy(maxEnergy, false);

//                addEnergyExtractedBlock(pos, distributed);
                en.getEnergy().receiveEnergy(distributed, false);

                CompoundTag tag = en.getStack().getOrCreateTag();
                tag.putInt("energyStored", en.getEnergy().energyStored());

                if (en.getStack().getItem() instanceof IElectricItem electricItem) {
                    electricItem.tickElectric(en.getStack());
                }

                chargeLeft -= distributed;
                if (chargeLeft > 0 && --size > 0) {
                    chargeSplit = chargeLeft / size;
                } else {
                    chargeSplit = 0;
                }
            }
        }
    }

    /**
     * Evenly internal energy providers
     * @param pos
     * @param energyFrom
     * @param energyTo
     * @param max
     */

    private void dischargeInternal(BlockPos pos, List<EnergyExtractComparator> energyFrom, IEnergyStorage energyTo, int max) {
        int distributeLeft = Math.min(energyTo.maxReceive() - getEnergyReceivedBlock(pos), max);
        int size = energyFrom.size();
        int distributeSplit = distributeLeft / size;

        for (EnergyExtractComparator en: energyFrom) {
            if (distributeLeft > 0 && distributeSplit > 0) {
                int maxEnergy = Math.min(distributeSplit, en.getEnergy().maxExtract());
                int distributed = en.getEnergy().extractEnergy(maxEnergy, false);
                energyTo.receiveEnergy(distributed, false);

                CompoundTag tag = en.getStack().getOrCreateTag();
                tag.putInt("energyStored", en.getEnergy().energyStored());

                if (en.getStack().getItem() instanceof IElectricItem electricItem) {
                    electricItem.tickElectric(en.getStack());
                }

//                addEnergyReceivedBlock(pos, distributed);
                distributeLeft -= distributed;
                if (distributeLeft > 0 && --size > 0) {
                    distributeSplit = distributeLeft / size;
                } else {
                    distributeSplit = 0;
                }
            }
        }
    }

    /**
     * Transfer energy inside energy blocks
     */
    private void transferInternal() {
        for (int i = this.energyBlocks.size() - 1; i >= 0; i--) {
            BlockPos pos = this.energyBlocks.get(i);
            if (pos == null) continue;
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof IIndRebBlockEntity ibe) {
                BasicEnergyStorage energyStorage = ibe.getEnergyStorage();
                if (ibe.hasEnergyStorage()) {
                    if (ibe.hasElectricStorage()) {
                        if (energyStorage.canExtractEnergy() || energyStorage.canReceiveEnergy()) {
                            ItemStackHandler batteryHandler = ibe.getElectricStorage();
                            AtomicInteger chargeMax = new AtomicInteger();
                            List<EnergyReceiveComparator> transferCharge = new ArrayList<>();

                            AtomicInteger dischargeMax = new AtomicInteger();
                            List<EnergyExtractComparator> transferDischarge = new ArrayList<>();

                            for (int j = 0; j < batteryHandler.getSlots(); j++) {
                                ItemStack stack = batteryHandler.getStackInSlot(j);
                                if (!stack.isEmpty()) {
                                    boolean isCharging = ibe.getElectricStorage().isSlotCharging(j);
                                    stack.getCapability(ModCapabilities.ENERGY).ifPresent(e -> {
                                        if (isCharging) {
                                            if (e.canReceiveEnergy() && energyStorage.canExtractEnergy() && energyStorage.maxExtract() > 0) {
                                                chargeMax.addAndGet(e.maxReceive());
                                                transferCharge.add(new EnergyReceiveComparator(e, stack));
                                            }
                                        } else {
                                            if (e.canExtractEnergy() && energyStorage.canReceiveEnergy() && energyStorage.maxReceive() > 0) {
                                                if (e.energyTier().getLvl() <= energyStorage.energyTier().getLvl()) {
                                                    dischargeMax.addAndGet(e.maxExtract());
                                                    transferDischarge.add(new EnergyExtractComparator(e, stack));
                                                } else {
                                                    createExplosion(pos, energyStorage.energyTier().getLvl());
                                                }
                                            }
                                        }
                                    });
                                }
                            }

                            if (transferCharge.size() > 0) {
                                Collections.sort(transferCharge);
                                chargeInternal(pos, energyStorage, transferCharge, chargeMax.get());
                            }

                            if (transferDischarge.size() > 0) {
                                Collections.sort(transferDischarge);
                                dischargeInternal(pos, transferDischarge, energyStorage, dischargeMax.get());
                            }
                        }
                    }

                    if (be instanceof IBlockEntityChargePad) {
                        List<EnergyReceiveComparator> transferCharge = new ArrayList<>();
                        int chargeMax = 0;

                        List<ServerPlayer> list = world.getEntitiesOfClass(ServerPlayer.class, AABB.of(new BoundingBox(pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY() + 1, pos.getZ())));
                        for (ServerPlayer player : list) {
                            Inventory inventory = player.getInventory();
                            for (int j = 0; j < inventory.getContainerSize(); ++j) {
                                ItemStack stack = inventory.getItem(j);
                                if (stack.getItem() instanceof IElectricItem electricItem) {
                                    IEnergyStorage energy = electricItem.getEnergy(stack);
                                    if (energy != null) {
                                        if (energy.energyTier().getLvl() <= energyStorage.energyTier().getLvl() && energy.canReceiveEnergy() && energy.energyStored() < energy.maxEnergy() && energyStorage.maxExtract() > 0) {
                                            chargeMax += energy.maxReceive();
                                            transferCharge.add(new EnergyReceiveComparator(energy, stack));
                                        }
                                    }
                                }
                            }
                        }

                        if (transferCharge.size() > 0) {
                            Collections.sort(transferCharge);
                            chargeInternal(pos, energyStorage, transferCharge, chargeMax);
                            ibe.getEnergyStorage().updated();
                        }
                    }
                }
            }
        }
    }

    /**
     * This is to distribute calculated energy from generators to cable network
     * @param energyFrom
     * @param energyTo
     * @param max
     */
    private void distributeDischargeNet(List<EnergyExtractComparatorNetwork> energyFrom, IEnergyStorage energyTo, int max) {
        int distributeLeft = Math.min(energyTo.maxReceive(), max);
        int size = energyFrom.size();
        int distributeSplit = distributeLeft / size;
        for (EnergyExtractComparatorNetwork en: energyFrom) {
            if (distributeLeft > 0 && distributeSplit > 0) {
                int maxEnergy = Math.min(distributeSplit, en.getMaxExtract());
                int distributed = en.getEnergy().extractEnergy(maxEnergy, false);
                energyTo.receiveEnergy(distributed, false);
                distributeLeft -= distributed;
                if (distributeLeft > 0 && --size > 0) {
                    distributeSplit = distributeLeft / size;
                } else {
                    distributeSplit = 0;
                }
            }
        }
    }


    /**
     * Discharge generator to block accept energy
     * @param pos
     * @param energyFrom
     * @param energyTo
     * @param max
     */
    private void dischargeGenerator(BlockPos pos, IEnergyStorage energyFrom, List<TransferTo> energyTo, int max) {
        int left = max;
        int size = energyTo.size();
        int spit = left / size;

        for (TransferTo en: energyTo) {
            if (left > 0 && spit > 0) {

                int maxEnergy = Math.min(spit, en.getLeftReceive());
                int distributed = energyFrom.extractEnergy(maxEnergy, false);

                addEnergyReceivedBlock(en.getPos(), distributed);
                addEnergyExtractedBlock(pos, distributed);

                en.getEnergy().receiveEnergy(distributed, false);

                left -= distributed;
                if (left > 0 && --size > 0) {
                    spit = left / size;
                } else {
                    spit = 0;
                }
            }
        }
    }

    /**
     * Charge network from providers
     * @param energyFrom
     * @param energyTo
     * @param max
     */
    private void chargeNetwork(List<TransferFrom> energyFrom, EnergyNetwork energyTo, int max) {
        int left = Math.min(energyTo.maxReceive(), max);
        int size = energyFrom.size();
        int split = left / size;
        for (TransferFrom en: energyFrom) {
            if (left > 0 && split > 0) {
                int maxEnergy = Math.min(split, en.getMaxExtract());
                int distributed = en.getEnergy().extractEnergy(maxEnergy, false);

                addEnergyExtractedBlock(en.getPos(), distributed);
                addEnergyReceivedNetwork(energyTo, distributed);

                energyTo.receiveEnergy(distributed, false);
                left -= distributed;
                if (left > 0 && --size > 0) {
                    split = left / size;
                } else {
                    split = 0;
                }
            }
        }
    }

    /**
     * Discharge network power to outputs
     * @param energyFrom
     * @param energyTo
     * @param max
     */
    private void dischargeNetwork(EnergyNetwork energyFrom, List<TransferTo> energyTo, int max) {
        int left = energyFrom.maxExtract();
        int size = energyTo.size();
        int spit = left / size;

        for (TransferTo en: energyTo) {
            if (left > 0 && spit > 0) {

                int maxEnergy = Math.min(spit, en.getLeftReceive());
                int distributed = energyFrom.extractEnergy(maxEnergy, false);

                addEnergyReceivedBlock(en.getPos(), distributed);
                addEnergyExtractedNetwork(energyFrom, distributed);

                en.getEnergy().receiveEnergy(distributed, false);

                left -= distributed;
                if (left > 0 && --size > 0) {
                    spit = left / size;
                } else {
                    spit = 0;
                }
            }
        }

        energyFrom.updated();
    }

    /**
     * Transfer energy to blocks around
     */
    private void transferTouching() {
        ArrayList<BlockPos> clone = (ArrayList<BlockPos>) energyBlocks.clone();
        for (BlockPos pos : clone) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof IIndRebBlockEntity ibe) {
                if (ibe.hasEnergyStorage()) {
                    BasicEnergyStorage energy = ibe.getEnergyStorage();
                    int leftExtract = Math.max(0, energy.maxExtract() - getEnergyExtractedBlock(pos));
                    if (leftExtract > 0) {
                        List<TransferTo> transferTo = new ArrayList<>();
                        for (Direction direction : Constants.DIRECTIONS) {
                            if (energy.canExtractEnergy(direction)) {
                                BlockPos relativePos = pos.relative(direction);
                                if (energyBlocks.contains(relativePos)) {
                                    if (energy.canExtractEnergy(direction)) {
                                        Direction oppositeDir = direction.getOpposite();
                                        BlockEntity oBe = world.getBlockEntity(relativePos);
                                        if (oBe instanceof IIndRebBlockEntity oIbe) {
                                            if (oIbe.hasEnergyStorage()) {
                                                BasicEnergyStorage oEnergy = oIbe.getEnergyStorage();
                                                if (oEnergy.canReceiveEnergy(oppositeDir)) {
                                                    int leftReceive = Math.max(0, oEnergy.maxReceive() - getEnergyReceivedBlock(relativePos));
                                                    if (leftReceive > 0) {
                                                        if (be instanceof IBlockEntityTransformer beT && oBe instanceof IBlockEntityTransformer iBeT) {
                                                            EnergyTier fromTier = beT.energyExtractTier();
                                                            EnergyTier toTier = iBeT.energyReceiveTier();
                                                            if (Objects.equals(fromTier.getLvl(), toTier.getLvl())) {
                                                                transferTo.add(new TransferTo(oEnergy, relativePos, leftReceive));
                                                            } else {
                                                                if (fromTier.getLvl() > toTier.getLvl()) {
                                                                    createExplosion(relativePos, fromTier.getLvl());
                                                                }
                                                            }
                                                        } else if (be instanceof IBlockEntityTransformer beT) {
                                                            EnergyTier fromTier = beT.energyExtractTier();
                                                            if (fromTier.getLvl() <= oEnergy.energyTier().getLvl()) {
                                                                transferTo.add(new TransferTo(oEnergy, relativePos, leftReceive));
                                                            } else {
                                                                createExplosion(relativePos, fromTier.getLvl());
                                                            }
                                                        } else if (oBe instanceof IBlockEntityTransformer iBeT) {
                                                            EnergyTier toTier = iBeT.energyReceiveTier();
                                                            if (Objects.equals(energy.energyTier().getLvl(), toTier.getLvl())) {
                                                                transferTo.add(new TransferTo(oEnergy, relativePos, leftReceive));
                                                            } else {
                                                                if (energy.energyTier().getLvl() > toTier.getLvl()) {
                                                                    createExplosion(relativePos, energy.energyTier().getLvl());
                                                                }
                                                            }
                                                        } else {
                                                            if (oEnergy.energyType() == EnergyType.BOTH || oEnergy.energyType() == EnergyType.RECEIVE) {
                                                                if (oEnergy.energyTier().getLvl() >= energy.energyTier().getLvl()) {
                                                                    transferTo.add(new TransferTo(oEnergy, relativePos, leftReceive));
                                                                } else {
                                                                    createExplosion(relativePos, energy.energyTier().getLvl());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (transferTo.size() > 0) {
                            Collections.sort(transferTo);
                            dischargeGenerator(pos, energy, transferTo, leftExtract);
                        }
                    }
                }
            }
        }
    }

    /**
     * Transfer energy from cables to machines
     */
    private void transferFromCables() {
        ArrayList<EnergyNetwork> clone = (ArrayList<EnergyNetwork>) networks.getNetworks().clone();
        for (EnergyNetwork network : clone) {
            List<TransferTo> transferTo = new ArrayList<>();
            if (network.maxExtract() > 0) {
                HashSet<BlockPos> connections = (HashSet<BlockPos>) network.getConnections().clone();
                HashSet<BlockPos> electrics = network.getElectrics();
                List<BlockPos> posChecked = new ArrayList<>();

                for (BlockPos pos : connections) {
                    for (Direction direction : Constants.DIRECTIONS) {
                        BlockPos relativePos = pos.relative(direction);
                        if (electrics.contains(relativePos) && !posChecked.contains(relativePos)) {
                            posChecked.add(relativePos);
                            Direction oppositeDir = direction.getOpposite();
                            BlockEntity be = world.getBlockEntity(relativePos);
                            if (be != null) {
                                be.getCapability(ModCapabilities.ENERGY).ifPresent(energy -> {
                                    if (energy.canReceiveEnergy(oppositeDir)) {
                                        EnergyTier currentTier = network.getCurrentTier();

                                        if (energy.energyType() == EnergyType.BOTH || energy.energyType() == EnergyType.RECEIVE) {
                                            if (energy.energyTier().getLvl() >= currentTier.getLvl()) {
                                                if (energy.maxReceive() > 0) {
                                                    int leftReceive = Math.max(0, energy.maxReceive() - getEnergyReceivedBlock(relativePos));
                                                    transferTo.add(new TransferTo(energy, relativePos, leftReceive));
                                                }
                                            } else {
                                                createExplosion(relativePos, currentTier.getLvl());
                                            }
                                        }

                                        if (energy.energyType() == EnergyType.TRANSFORMER) {
                                            IBlockEntityTransformer beTransformer = (IBlockEntityTransformer) be;
                                            EnergyTier transformerTier = beTransformer.energyReceiveTier();
                                            if ( transformerTier.getLvl() >= currentTier.getLvl()) {
                                                if (energy.maxReceive() > 0) {
                                                    int leftReceive = Math.max(0, energy.maxReceive() - getEnergyReceivedBlock(relativePos));
                                                    transferTo.add(new TransferTo(energy, relativePos, leftReceive));
                                                }
                                            } else {
                                                createExplosion(relativePos, currentTier.getLvl());
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }

            if (transferTo.size() > 0) {
                Collections.sort(transferTo);
                dischargeNetwork(network, transferTo, network.maxEnergy());
            }
        }
    }


    /**
     * Transfer energy from generators to cables
     */
    private void transferFromGenerators() {
        ArrayList<EnergyNetwork> clone = (ArrayList<EnergyNetwork>) networks.getNetworks().clone();
        for (EnergyNetwork network : clone) {
            int leftReceive = Math.max(0, network.maxReceive() - getEnergyReceivedNetwork(network));
            List<TransferFrom> transferFrom = new ArrayList<>();
            HashSet<BlockPos> connections =  (HashSet<BlockPos>) network.getConnections().clone();
            HashSet<BlockPos> electrics = network.getElectrics();
            List<BlockPos> posChecked = new ArrayList<>();

            for (BlockPos pos : connections) {
                for (Direction direction : Constants.DIRECTIONS) {
                    BlockPos relativePos = pos.relative(direction);
                    if (electrics.contains(relativePos) && !posChecked.contains(relativePos)) {
                        posChecked.add(relativePos);
                        Direction oppositeDir = direction.getOpposite();
                        BlockEntity be = world.getBlockEntity(relativePos);
                        if (be != null) {
                            be.getCapability(ModCapabilities.ENERGY).ifPresent(energy -> {
                                if (energy.canExtractEnergy(oppositeDir)) {
                                    if (energy.energyType() == EnergyType.EXTRACT) {


                                        if (energy.maxExtract() > 0) {
                                            int leftExtract = Math.max(0, energy.maxExtract() - getEnergyExtractedBlock(relativePos));
                                            if (leftReceive > 0 && leftExtract > 0) {
                                                network.setCurrentTier(energy.energyTier());

                                                if (energy.energyTier().getLvl() <= network.getEnergyTier().getLvl()) {
                                                    transferFrom.add(new TransferFrom(energy, relativePos, leftExtract));
                                                } else {
                                                    createBurn(network, pos);
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }

            if (transferFrom.size() > 0) {
                Collections.sort(transferFrom);
                chargeNetwork(transferFrom, network, leftReceive);
            }
        }
    }

    /**
     * Transfer energy from batteries to cables
     */
    private void transferFromBatteries() {
        ArrayList<EnergyNetwork> clone = (ArrayList<EnergyNetwork>) networks.getNetworks().clone();
        for (EnergyNetwork network : clone) {
            int leftReceive = Math.max(0, network.maxReceive() - getEnergyReceivedNetwork(network));
            List<TransferFrom> transferFrom = new ArrayList<>();
            HashSet<BlockPos> electrics = network.getElectrics();
            HashSet<BlockPos> connections = (HashSet<BlockPos>) network.getConnections().clone();
            List<BlockPos> posChecked = new ArrayList<>();

            for (BlockPos pos : connections) {
                for (Direction direction : Constants.DIRECTIONS) {
                    BlockPos relativePos = pos.relative(direction);
                    if (electrics.contains(relativePos) && !posChecked.contains(relativePos)) {
                        posChecked.add(relativePos);
                        Direction oppositeDir = direction.getOpposite();
                        BlockEntity be = world.getBlockEntity(relativePos);
                        if (be != null) {
                            be.getCapability(ModCapabilities.ENERGY).ifPresent(energy -> {
                                if (energy.energyType() == EnergyType.BOTH) {
                                    if (energy.canExtractEnergy(oppositeDir)) {
                                        if (energy.maxExtract() > 0) {
                                            int leftExtract = Math.max(0, energy.maxExtract() - getEnergyExtractedBlock(relativePos));

                                            if (leftReceive > 0 && leftExtract > 0) {
                                                network.setCurrentTier(energy.energyTier());

                                                if (energy.energyTier().getLvl() <= network.getCurrentTier().getLvl() && energy.energyTier().getLvl() <= network.energyTier().getLvl()) {
                                                    transferFrom.add(new TransferFrom(energy, relativePos, leftExtract));
                                                } else {
                                                    createBurn(network, pos);
                                                }
                                            }
                                        }
                                    }
                                }

                                if (energy.energyType() == EnergyType.TRANSFORMER) {
                                    if (energy.canExtractEnergy(oppositeDir)) {
                                        IBlockEntityTransformer beTransformer = (IBlockEntityTransformer) be;
                                        EnergyTier tier = beTransformer.energyExtractTier();

                                        int leftExtract = Math.max(0, energy.maxExtract() - getEnergyExtractedBlock(relativePos));
                                        if (leftReceive > 0 && leftExtract > 0) {
                                            network.setCurrentTier(tier);

                                            if (tier.getLvl() <= network.getCurrentTier().getLvl()) {
                                                transferFrom.add(new TransferFrom(energy, relativePos, leftExtract));
                                            } else {
                                                createBurn(network, pos);
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }

            if (transferFrom.size() > 0) {
                Collections.sort(transferFrom);
                chargeNetwork(transferFrom, network, leftReceive);
            }
        }
    }

    @Override
    public void tick() {
        energyReceivedBlock.clear();
        energyExtractedBlock.clear();
        energyReceivedNetwork.clear();
        energyExtractedNetwork.clear();

        for (EnergyNetwork network: networks.getNetworks()) {
            network.resetCurrentTier();
        }

        transferInternal();
        transferTouching();
        transferFromGenerators();
        transferFromBatteries();
        transferFromCables();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.ENERGY_CORE) {
            return energyCoreLazyOptional.cast();
        }
        return LazyOptional.empty();
    }


    public CompoundTag serializeData() {
        CompoundTag nbt = new CompoundTag();

        List<Long> blocks = new ArrayList<>();
        this.energyBlocks.forEach(pos -> blocks.add(pos.asLong()));
        nbt.putLongArray("energyBlocks", blocks);

        nbt.put("networks", networks.serializeNBT());
        return nbt;
    }

    public void deserializeData(CompoundTag nbt) {
        ArrayList<BlockPos> nbtConnections = new ArrayList<>();
        for (long longPos : nbt.getLongArray("energyBlocks")) {
            BlockPos pos = BlockPos.of(longPos);
            nbtConnections.add(pos);
        }
        this.energyBlocks = nbtConnections;

        this.networks.deserializeNBT(nbt.getCompound("networks"));
    }

    @Override
    public CompoundTag serializeNBT() {
        return serializeData();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        deserializeData(nbt);
    }

    @Override
    public CompoundTag getNetworkTag(@Nullable BlockPos pos) {
        if (pos == null) {
            return serializeData();
        } else {
            CompoundTag nbt = new CompoundTag();
            EnergyNetworks net = new EnergyNetworks(world);
            net.addNetwork(networks.getNetwork(pos));
            nbt.putLongArray("energyBlock", new ArrayList<>());
            nbt.put("networks", net.serializeNBT());
            return nbt;
        }
    }

    @Override
    public void setNetworkTag(CompoundTag tag) {
        this.networks.clearNetworks();
        deserializeData(tag);
    }
}