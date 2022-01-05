package com.maciej916.indreb.common.network.packet.energy;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModCapabilities;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSyncEnergy {

    private final CompoundTag tag;

    public PacketSyncEnergy(CompoundTag tag) {
        this.tag = tag;
    }

    public PacketSyncEnergy(FriendlyByteBuf buf) {
        tag = buf.readAnySizeNbt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(tag);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LocalPlayer player = IndReb.PROXY.getLocalPlayer();
            if (player != null) {
                Level level = player.getLevel();
                level.getCapability(ModCapabilities.ENERGY_CORE).ifPresent(iEnergyCore -> {
                    iEnergyCore.setNetworkTag(tag);
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
