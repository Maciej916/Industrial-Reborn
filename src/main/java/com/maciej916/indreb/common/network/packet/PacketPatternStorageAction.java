package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.block.impl.machines.pattern_storage.BlockEntityPatternStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPatternStorageAction {

    private final BlockPos blockPos;
    private final boolean export;

    public PacketPatternStorageAction(BlockPos blockPos, boolean export) {
        this.blockPos = blockPos;
        this.export = export;
    }

    public PacketPatternStorageAction(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
        export = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
        buf.writeBoolean(export);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            if (player.level.getBlockEntity(blockPos) instanceof BlockEntityPatternStorage be) {
                be.exportImportPattern(export);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
