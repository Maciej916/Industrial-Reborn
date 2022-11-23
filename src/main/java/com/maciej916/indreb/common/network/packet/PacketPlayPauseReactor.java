package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor.BlockEntityNuclearReactor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPlayPauseReactor {

    private final BlockPos blockPos;

    public PacketPlayPauseReactor(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public PacketPlayPauseReactor(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player.level.getBlockEntity(blockPos) instanceof BlockEntityNuclearReactor be) {
                be.clickPlayPauseServer();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
