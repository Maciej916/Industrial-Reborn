package com.maciej916.indreb.common.energy.provider;

import com.maciej916.indreb.common.energy.impl.BasicEnergyStorage;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.energy.interfaces.IEnergyCore;
import com.maciej916.indreb.common.energy.provider.comparator.EnergyExtractComparator;
import com.maciej916.indreb.common.energy.provider.comparator.EnergyExtractComparatorNetwork;
import com.maciej916.indreb.common.energy.provider.comparator.EnergyReceiveComparator;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.util.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EnergyCore extends CapabilityFluidHandler implements IEnergyCore, ICapabilitySerializable<CompoundTag> {

    private final Level world;
    public EnergyNetworks networks;
    private HashSet<BlockPos> energyBlocks = new HashSet<>();
    private final LazyOptional<IEnergyCore> energyCoreLazyOptional = LazyOptional.of(() -> this);


    public EnergyCore(Level world) {
        this.world = world;
        this.networks = new EnergyNetworks(world);
    }

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


    //    private final HashSet<EnergyExtract> energyExtracts = new HashSet<>();


//    private void putTransfer(IEnergy energy) {
//        if (!energyTransfers.containsKey(energy)) energyTransfers.put(energy, new EnergyTransfer(energy));
//    }
//
//    private void putExtract(IEnergy energy, IEnergy energyTo, int amount, Direction direction) {
//        putTransfer(energy);
//        energyTransfers.get(energy).totalExtract += amount;
//        energyTransfers.get(energy).extract.add(new EnergyExtract(energy, energyTo, amount, direction));
//    }
//
//    private void putReceive(IEnergy energy, IEnergy energyTo, int amount, Direction direction) {
//        putTransfer(energy);
//        energyTransfers.get(energy).totalReceive += amount;
//        energyTransfers.get(energy).receive.add(new EnergyExtract(energyTo, energy, amount, direction));
//    }


    /**
     * Evenly charge internal energy providers
     * @param pos
     * @param energyFrom
     * @param energyTo
     * @param max
     */
    private void chargeInternal(BlockPos pos, BasicEnergyStorage energyFrom, List<EnergyReceiveComparator> energyTo, int max) {

        int chargeLeft = energyFrom.maxExtract();
        int size = energyTo.size();
        int chargeSplit = chargeLeft / size;

        for (EnergyReceiveComparator en: energyTo) {
            if (chargeLeft > 0 && chargeSplit > 0) {
                int maxEnergy = Math.min(chargeSplit, en.getEnergy().maxReceive());
                int distributed = energyFrom.extractEnergy(maxEnergy, false);

                addEnergyExtractedBlock(pos, distributed);
                en.getEnergy().receiveEnergy(distributed, false);

                CompoundTag tag = en.getStack().getOrCreateTag();
                tag.putInt("energyStored", en.getEnergy().energyStored());

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

    private void dischargeInternal(BlockPos pos, List<EnergyExtractComparator> energyFrom, IEnergy energyTo, int max) {
        int distributeLeft = Math.min(energyTo.maxReceive(), max);
        int size = energyFrom.size();
        int distributeSplit = distributeLeft / size;

        for (EnergyExtractComparator en: energyFrom) {
            if (distributeLeft > 0 && distributeSplit > 0) {
                int maxEnergy = Math.min(distributeSplit, en.getEnergy().maxExtract());
                int distributed = en.getEnergy().extractEnergy(maxEnergy, false);
                energyTo.receiveEnergy(distributed, false);

                CompoundTag tag = en.getStack().getOrCreateTag();
                tag.putInt("energyStored", en.getEnergy().energyStored());

                addEnergyReceivedBlock(pos, distributed);
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
        for (BlockPos pos : this.energyBlocks) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof IndRebBlockEntity ibe) {
                if (ibe.isHasBattery() && ibe.isHasEnergy()) {
                    BasicEnergyStorage energyStorage = ibe.getEnergyStorage();
                    if (energyStorage.canExtractEnergy() || energyStorage.canReceiveEnergy()) {
                        ItemStackHandler batteryHandler = ibe.getBatteryHandler();
                        AtomicInteger chargeMax = new AtomicInteger();
                        List<EnergyReceiveComparator> transferCharge = new ArrayList<>();

                        AtomicInteger dischargeMax = new AtomicInteger();
                        List<EnergyExtractComparator> transferDischarge = new ArrayList<>();

                        for (int i = 0; i < batteryHandler.getSlots(); i++) {
                            ItemStack stack = batteryHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                boolean isCharging = ibe.getBatteryHandlers().get(i).isCharging();
                                stack.getCapability(ModCapabilities.ENERGY).ifPresent(e -> {
                                    if (isCharging) {
                                        if (e.canReceiveEnergy() && energyStorage.canExtractEnergy() && energyStorage.maxExtract() > 0) {
                                            chargeMax.addAndGet(e.maxReceive());
                                            transferCharge.add(new EnergyReceiveComparator(e, stack));
                                        }
                                    } else {
                                        if (e.canExtractEnergy() && energyStorage.canReceiveEnergy() && energyStorage.maxReceive() > 0) {
                                            dischargeMax.addAndGet(e.maxExtract());
                                            transferDischarge.add(new EnergyExtractComparator(e , stack));
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
            }
        }
    }









    /**
     * Transfer energy between IEnergyTransmitter
     * Push energy from cables with higher energy to lower to make them even
     */
    private void transferTransmitter() {




        HashMap<EnergyNetwork, EnergyTransfer> energyTransfers = new HashMap<>();
        for (EnergyNetwork network : networks.getNetworks()) {
            if (network.maxExtract() > 0) {
                List<EnergyNetwork> connectedNetworks = networks.getConnectedNetworks(network);
                for (EnergyNetwork nCn : connectedNetworks) {
                    if (nCn.maxReceive() > 0 && network.energyStored() > nCn.energyStored() && network.energyStored() - nCn.energyStored() > 2) {
                        if (energyTransfers.containsKey(network)) {
                            energyTransfers.get(network).networksTo.add(nCn);
                        } else {
                            energyTransfers.put(network, new EnergyTransfer(null, network, null, nCn));
                        }
                    }
                }
            }
        }

        List<EnergyExtractComparatorNetwork> transferDischarge = new ArrayList<>();
        for (EnergyNetwork network : networks.getNetworks()) {
//            ta co otrzy,uje



            int dischargeMax = 0;
            List<EnergyNetwork> connectedNetworks = networks.getConnectedNetworks(network);

            for (EnergyNetwork nCn : connectedNetworks) {
                if (energyTransfers.containsKey(nCn)) {
                    EnergyTransfer et = energyTransfers.get(nCn);

                    int max = et.getMaxTransferToTransmitter(network);
                    dischargeMax += max;

//
//                    System.out.println("max");
//                    System.out.println(max);

                    transferDischarge.add(new EnergyExtractComparatorNetwork(nCn, max / 2));
                }
            }



//
//            System.out.println("network");
//            System.out.println(network);

                if (transferDischarge.size() > 0) {
                    Collections.sort(transferDischarge);
                    distributeDischargeNet(transferDischarge, network, dischargeMax);
                }




            }





//            if (network.maxExtract() > 0) {
//                HashSet<EnergyNetwork> networksChecked = new HashSet<>();
//                for (BlockPos ePos : network.getTransmitters()) {
//                    EnergyNetwork otherNetwork = this.networks.getNetwork(ePos);
//                    if (!networksChecked.contains(otherNetwork)) {
//                        networksChecked.add(otherNetwork);
//                        if (otherNetwork.maxReceive() > 0) {
//                            if (energyTransfers.containsKey(otherNetwork)) {
//                                energyTransfers.get(otherNetwork).networksTo.add(network);
//                            } else {
//                                energyTransfers.put(network, new EnergyTransfer(null, network, ePos, otherNetwork));
//                            }
//
//                        }
//                    }
//                }
//            }
//        }



//        System.out.println("energyTransfers");
//        System.out.println(energyTransfers.size());
//        System.out.println(energyTransfers);
//
//
//        for (EnergyNetwork network : networks.getNetworks()) {
//            if (energyTransfers.containsKey(network)) {
//                EnergyTransfer et = energyTransfers.get(network);
//
//                int dischargeMax = 0;
//                List<EnergyExtractComparatorNetwork> transferDischarge = new ArrayList<>();
//                HashSet<EnergyNetwork> networksChecked = new HashSet<>();
//
//
//
//                for (BlockPos ePos : network.getTransmitters()) {
//                    EnergyNetwork otherNetwork = this.networks.getNetwork(ePos);
//
//                    System.out.println("Other network");
//                    System.out.println(otherNetwork);
//
//                    if (otherNetwork != null && et.getNetworksTo().contains(otherNetwork) && !networksChecked.contains(otherNetwork)) {
//                        networksChecked.add(otherNetwork);
//
//
//                        int max = et.getMaxTransferToTransmitter(otherNetwork);
//                        dischargeMax += max;
//
//                        System.out.println("max");
//                        System.out.println(max);
//
//                        transferDischarge.add(new EnergyExtractComparatorNetwork(otherNetwork, max));
//                    }
//                }
//
//                if (transferDischarge.size() > 0) {
//                    Collections.sort(transferDischarge);
//                    distributeDischargeNet(transferDischarge, network, dischargeMax);
//                }
//                System.out.println("transferDischarge");
//                System.out.println(transferDischarge);
//
//
//            }
//        }
//
//
//
//


    }





    /**
     * This is to distribute calculated energy from generators to cable network
     * @param energyFrom
     * @param energyTo
     * @param max
     */
    private void distributeDischargeNet(List<EnergyExtractComparatorNetwork> energyFrom, IEnergy energyTo, int max) {
        int distributeLeft = Math.min(energyTo.maxReceive(), max);
        int size = energyFrom.size();
        int distributeSplit = distributeLeft / size;


//        System.out.println("distributeLeft");
//        System.out.println(distributeLeft);
//
//        System.out.println("size");
//        System.out.println(size);
//
//        System.out.println("distributeSplit");
//        System.out.println(distributeSplit);

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
    private void dischargeGenerator(BlockPos pos, IEnergy energyFrom, List<TransferTo> energyTo, int max) {
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
    }

    /**
     * Transfer energy to blocks around
     */
    private void transferTouching() {
        for (BlockPos pos : energyBlocks) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof IndRebBlockEntity ibe) {
                if (ibe.isHasEnergy()) {
                    BasicEnergyStorage energy = ibe.getEnergyStorage();
                    int leftExtract = Math.max(0, energy.maxExtract() - getEnergyExtractedBlock(pos));
                    if (energy.canExtractEnergy() && leftExtract > 0) {
                        List<TransferTo> transferTo = new ArrayList<>();
                        for (Direction direction : Constants.DIRECTIONS) {
                            BlockPos relativePos = pos.relative(direction);
                            if (energyBlocks.contains(relativePos)) {
                                if (energy.canExtractEnergy(direction)) {
                                    Direction oppositeDir = direction.getOpposite();
                                    BlockEntity oBe = world.getBlockEntity(relativePos);
                                    if (oBe instanceof IndRebBlockEntity oIbe) {
                                        if (oIbe.isHasEnergy()) {
                                            BasicEnergyStorage oEnergy = oIbe.getEnergyStorage();
                                            if (oEnergy.canReceiveEnergy(oppositeDir)) {
                                                int leftReceive = Math.max(0, oEnergy.maxReceive() - getEnergyReceivedBlock(relativePos));
                                                if (leftReceive > 0 && (oEnergy.energyType() == EnumEnergyType.BOTH || oEnergy.energyType() == EnumEnergyType.RECEIVE)) {
                                                    transferTo.add(new TransferTo(oEnergy, relativePos, leftReceive));
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
        for (EnergyNetwork network : networks.getNetworks()) {
            List<TransferTo> transferTo = new ArrayList<>();
            if (network.maxExtract() > 0) {
                HashSet<BlockPos> connections = network.getConnections();
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
                                        if (energy.maxReceive() > 0 && (energy.energyType() == EnumEnergyType.BOTH || energy.energyType() == EnumEnergyType.RECEIVE)) {
                                            int leftReceive = Math.max(0, energy.maxReceive() - getEnergyReceivedBlock(relativePos));
                                            transferTo.add(new TransferTo(energy, relativePos, leftReceive));
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
        for (EnergyNetwork network : networks.getNetworks()) {
            int leftReceive = Math.max(0, network.maxReceive() - getEnergyReceivedNetwork(network));
            if (leftReceive > 0) {
                List<TransferFrom> transferFrom = new ArrayList<>();
                HashSet<BlockPos> connections = network.getConnections();
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
                                        if (energy.maxExtract() > 0 && energy.energyType() == EnumEnergyType.EXTRACT) {
                                            int leftExtract = Math.max(0, energy.maxExtract() - getEnergyExtractedBlock(relativePos));
                                            if (leftExtract > 0) {
                                                transferFrom.add(new TransferFrom(energy, relativePos, leftExtract));
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
    }

    /**
     * Transfer energy from batteries to cables
     */
    private void transferFromBatteries() {
        for (EnergyNetwork network : networks.getNetworks()) {
            int leftReceive = Math.max(0, network.maxReceive() - getEnergyReceivedNetwork(network));
            if (leftReceive > 0) {
                List<TransferFrom> transferFrom = new ArrayList<>();
                HashSet<BlockPos> electrics = network.getElectrics();
                HashSet<BlockPos> connections = network.getConnections();
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
                                    if (energy.energyType() == EnumEnergyType.BOTH) {
                                        if (energy.canExtractEnergy(oppositeDir)) {
                                            if (energy.maxExtract() > 0) {
                                                int leftExtract = Math.max(0, energy.maxExtract() - getEnergyReceivedBlock(relativePos));
                                                if (leftExtract > 0) {
                                                    transferFrom.add(new TransferFrom(energy, relativePos, leftExtract));
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


    }

    @Override
    public void tick() {
        energyReceivedBlock.clear();
        energyExtractedBlock.clear();
        energyReceivedNetwork.clear();
        energyExtractedNetwork.clear();

        // Good?
        transferInternal();

        // Good?
        transferTouching();

        // Bad?
//        transferTransmitter();

        // Good?
        transferFromCables();

        // 50 - 50
        transferFromGenerators();

        // Good?
        transferFromBatteries();
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.ENERGY_CORE) {
            return energyCoreLazyOptional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();

        List<Long> blocks = new ArrayList<>();
        this.energyBlocks.forEach(pos -> blocks.add(pos.asLong()));
        nbt.putLongArray("energyBlocks", blocks);

        nbt.put("networks", networks.serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

        HashSet<BlockPos> nbtConnections = new HashSet<>();
        for (long longPos : nbt.getLongArray("energyBlocks")) {
            BlockPos pos = BlockPos.of(longPos);
            nbtConnections.add(pos);
        }
        this.energyBlocks = nbtConnections;

//        for (String key : nbt.getAllKeys()) {
//            BlockPos pos = BlockPos.of(nbt.getLong(key));
//            energyBlocks.add(pos);
//        }

        this.networks.deserializeNBT(nbt.getCompound("networks"));
    }
}