package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.player.IPlayerCapability;
import com.maciej916.indreb.common.proxy.ModProxy;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketRadiationSync {

    private final double radsLevel;
    private final double playerRads;
    private final int radiationImmune;
    private final int radiationProtection;

    public PacketRadiationSync(IPlayerCapability playerCap) {
        this.radsLevel = playerCap.getRadsLevel();
        this.playerRads = playerCap.getPlayerRads();
        this.radiationImmune = playerCap.getRadiationImmune();
        this.radiationProtection = playerCap.getRadsProtection();
    }

    public PacketRadiationSync(FriendlyByteBuf buf) {
        radsLevel = buf.readDouble();
        playerRads = buf.readDouble();
        radiationImmune = buf.readInt();
        radiationProtection = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeDouble(radsLevel);
        buf.writeDouble(playerRads);
        buf.writeInt(radiationImmune);
        buf.writeInt(radiationProtection);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LocalPlayer player = ModProxy.PROXY.getLocalPlayer();
            if (player != null) {
                player.getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(cap -> {
                    cap.setRadsLevel(radsLevel);
                    cap.setPlayerRads(playerRads);
                    cap.setRadiationImmune(radiationImmune);
                    cap.setRadsProtection(radiationProtection);
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
