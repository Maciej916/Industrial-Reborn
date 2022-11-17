package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketExperience {

    private final BlockPos blockPos;

    public PacketExperience(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public PacketExperience(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player.level.getBlockEntity(blockPos) instanceof IHasExp be) {
                be.collectExpServer(player);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
