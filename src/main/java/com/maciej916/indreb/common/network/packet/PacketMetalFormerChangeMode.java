package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.block.impl.machines.metal_former.BlockEntityMeralFormer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketMetalFormerChangeMode {

    private final BlockPos blockPos;

    public PacketMetalFormerChangeMode(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public PacketMetalFormerChangeMode(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            if (player.level.getBlockEntity(blockPos) instanceof BlockEntityMeralFormer be) {
                be.changeMode();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
