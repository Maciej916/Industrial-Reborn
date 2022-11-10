package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.block.impl.machines.extruder.BlockEntityExtruder;
import com.maciej916.indreb.common.block.impl.machines.scanner.BlockEntityScanner;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketScannerCleanScan {

    private final BlockPos blockPos;

    public PacketScannerCleanScan(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public PacketScannerCleanScan(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            if (player.level.getBlockEntity(blockPos) instanceof BlockEntityScanner be) {
                be.cleanScan();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
