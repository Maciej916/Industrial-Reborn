package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.block.impl.machine.t_super.replicator.BlockEntityReplicator;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketReplicatorRun {

    private final BlockPos blockPos;
    private final boolean single;

    public PacketReplicatorRun(BlockPos blockPos, boolean single) {
        this.blockPos = blockPos;
        this.single = single;
    }

    public PacketReplicatorRun(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
        single = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
        buf.writeBoolean(single);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            if (player.level.getBlockEntity(blockPos) instanceof BlockEntityReplicator entityReplicator) {
                entityReplicator.runReplication(single);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
