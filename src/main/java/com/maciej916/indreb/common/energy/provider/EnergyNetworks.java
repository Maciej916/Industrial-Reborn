package com.maciej916.indreb.common.energy.provider;

import com.maciej916.indreb.common.block.impl.cable.BlockCable;
import com.maciej916.indreb.common.energy.interfaces.IEnergyNetworks;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.util.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EnergyNetworks implements IEnergyNetworks, INBTSerializable<CompoundTag> {

    private final ArrayList<EnergyNetwork> networks = new ArrayList<>();
    private final Level level;

    public EnergyNetworks(Level world) {
        this.level = world;
    }

    public ArrayList<EnergyNetwork> getNetworks() {
        return networks;
    }

    public void clearNetworks() {
        networks.clear();
    }

    public void addNetwork(EnergyNetwork network) {
        networks.add(network);
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public EnergyNetwork getNetwork(BlockPos pos) {
        for (EnergyNetwork network : this.networks) {
            if (network.hasConnection(pos)) {
                return network;
            }
        }
        return null;
    }

    public List<EnergyNetwork> getConnectedNetworks(EnergyNetwork en) {
        List<EnergyNetwork> networksChecked = new ArrayList();
        for (BlockPos pos : en.getTransmitters()) {
            EnergyNetwork enP = getNetwork(pos);
            if (!networksChecked.contains(enP)) {
                networksChecked.add(enP);
            }
        }
        return networksChecked;
    }

    @Override
    public EnergyNetwork getNetworkOther(BlockPos pos) {
        for (EnergyNetwork network : this.networks) {
            if (network.hasConnection(pos) || network.hasElectric(pos) || network.hasTransmitter(pos)) {
                return network;
            }
        }
        return null;
    }

    @Override
    public EnergyNetwork createNetwork(BlockPos pos, EnergyTier energyTier) {
        EnergyNetwork net = new EnergyNetwork(pos, energyTier);
        this.networks.add(net);
        return net;
    }

    @Override
    public void removeNetwork(EnergyNetwork network) {
        this.networks.remove(network);
    }

    @Override
    public void onPlaced(BlockPos pos, BlockState state, EnergyTier energyTier) {
        HashSet<EnergyNetwork> networksAround = new HashSet<>();
        HashSet<BlockPos> electricsAround = new HashSet<>();
        HashSet<BlockPos> transmittersAround = new HashSet<>();

        for (Direction direction : Constants.DIRECTIONS) {
            BlockPos offsetPos = pos.relative(direction);
            BlockEntity dirTile = getLevel().getBlockEntity(offsetPos);
            BlockState dirState = getLevel().getBlockState(offsetPos);

            if (dirState.getBlock() instanceof BlockCable dirBlock) {
                EnergyTier dirTier = dirBlock.getCableTier().getEnergyTier();
                if (energyTier == dirTier) {
                    EnergyNetwork network = getNetwork(offsetPos);
                    networksAround.add(network);
                } else {
                    transmittersAround.add(offsetPos);
                }
            }

            if (dirTile != null && dirTile.getCapability(ModCapabilities.ENERGY).isPresent()) {
                electricsAround.add(offsetPos);
            }
        }

        EnergyNetwork network;
        if (networksAround.size() > 1) {
            network = createNetwork(pos, energyTier);
            networksAround.forEach((net)-> {
                network.add(network.getConnections(), net.getConnections());
                network.add(network.getElectrics(), net.getElectrics());
                network.add(network.getTransmitters(), net.getTransmitters());
                networks.remove(net);
            });
        } else if (networksAround.size() == 1) {
            network = networksAround.iterator().next();
            network.add(network.getConnections(), pos);
        } else {
            network = createNetwork(pos, energyTier);
        }

        network.add(network.getElectrics(), electricsAround);
        network.add(network.getTransmitters(), transmittersAround);
    }

    @Override
    public void onRemove(BlockPos pos) {
        EnergyNetwork network = getNetwork(pos);
        if (network == null) return;
        HashSet<BlockPos> allConnections = new HashSet<>();
        HashSet<BlockPos> netConnections = network.getConnections();
        network.remove(network.getConnections(), pos);

        if (network.getConnections().size() > 0) {
            for (Direction direction : Constants.DIRECTIONS) {
                BlockPos offsetPos = pos.relative(direction);
                if (netConnections.contains(offsetPos) && !allConnections.contains(offsetPos)) {
                    EnergyNetwork net = recheckConnections(allConnections, network, new EnergyNetwork(network.energyStored(), network.getEnergyTier()), offsetPos);
                    networks.add(net);
                }
            }
        }

        removeNetwork(network);
    }
    public EnergyNetwork recheckConnections(HashSet<BlockPos> allConnections, EnergyNetwork oldNet, EnergyNetwork newNet, BlockPos pos) {
        newNet.add(newNet.getConnections(), pos);

        for (Direction direction : Constants.DIRECTIONS) {
            BlockPos offsetPos = pos.relative(direction);

            if (oldNet.getElectrics().contains(offsetPos)) {
                newNet.add(newNet.getElectrics(), offsetPos);
            }

            if (oldNet.getTransmitters().contains(offsetPos)) {
                newNet.add(newNet.getTransmitters(), offsetPos);
            }

            if (!allConnections.contains(offsetPos)) {
                allConnections.add(offsetPos);

                if (oldNet.hasConnection(offsetPos)) {
                    recheckConnections(allConnections, oldNet, newNet, offsetPos);
                }
            }
        }

        return newNet;
    }

    @Override
    public void neighborChanged(BlockPos pos, BlockPos neighborPos) {
        EnergyNetwork network = getNetwork(pos);
        if (network != null) {
            BlockEntity neighborTile = getLevel().getBlockEntity(neighborPos);
            BlockState state = getLevel().getBlockState(pos);
            BlockState neighborState = getLevel().getBlockState(neighborPos);
            EnergyTier tier = ((BlockCable) state.getBlock()).getCableTier().getEnergyTier();
            if (neighborState.getBlock() instanceof BlockCable be) {
                if (tier == be.getCableTier().getEnergyTier()) {
                    if (network != getNetwork(neighborPos)) {
                        network.add(network.getTransmitters(), neighborPos);
                    }
                }
            } else if (neighborTile != null) {
                if (neighborTile.getCapability(ModCapabilities.ENERGY).isPresent()) {
                    network.add(network.getElectrics(), neighborPos);
                }
            } else {
                EnergyNetwork neighborNetwork = getNetworkOther(neighborPos);
                if (neighborNetwork != null) {
                    neighborNetwork.remove(neighborNetwork.getTransmitters(), neighborPos);
                }
            }
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        int i = 0;
        for (EnergyNetwork net : this.networks) {
            nbt.put(String.valueOf(++i), net.serializeNBT());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        for (String key : nbt.getAllKeys()) {
            CompoundTag nbtNetwork = nbt.getCompound(key);
            EnergyNetwork net = new EnergyNetwork();
            net.deserializeNBT(nbtNetwork);
            networks.add(net);
        }
    }

    @Override
    public String toString() {
        return "EnergyNetworks{" +
                "networks=" + networks +
                ", level=" + level +
                '}';
    }
}
