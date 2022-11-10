package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.block.impl.machines.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.block.impl.machines.scanner.BlockEntityScanner;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPatternStoragePage {

    private final BlockPos blockPos;
    private final boolean next;

    public PacketPatternStoragePage(BlockPos blockPos, boolean next) {
        this.blockPos = blockPos;
        this.next = next;
    }

    public PacketPatternStoragePage(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
        next = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
        buf.writeBoolean(next);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            if (player.level.getBlockEntity(blockPos) instanceof BlockEntityPatternStorage be) {
                be.changePattern(next);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
