package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.IndReb;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.Random;
import java.util.function.Supplier;

public class PacketParticle {

    private final BlockPos blockPos;

    public PacketParticle(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public PacketParticle(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LocalPlayer player = IndReb.PROXY.getLocalPlayer();
            if (player != null) {
                Level level = player.getLevel();
                Random random = player.getRandom();

                for (int i = 0; i < 5; i++) {
                    double x = blockPos.getX() + 0.3 + random.nextDouble() * 0.4;
                    double y = blockPos.getY() + 0.3 + random.nextDouble() * 0.4;
                    double z = blockPos.getZ() + 0.3 + random.nextDouble() * 0.4;
                    level.addParticle(random.nextInt(5) == 0 ? ParticleTypes.LARGE_SMOKE : ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
