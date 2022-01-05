package com.maciej916.indreb.common.network.packet.energy;

import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.registries.ModCapabilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class PacketReqSyncEnergy {

    public PacketReqSyncEnergy() {
    }

    public PacketReqSyncEnergy(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                player.level.getCapability(ModCapabilities.ENERGY_CORE).ifPresent(iEnergyCore -> {
                    ModNetworking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketSyncEnergy(iEnergyCore.getNetworkTag(null)));
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
