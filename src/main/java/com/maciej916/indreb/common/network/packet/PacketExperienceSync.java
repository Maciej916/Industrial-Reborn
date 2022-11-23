package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import com.maciej916.indreb.common.proxy.ModProxy;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketExperienceSync {

    private final float storedExperience;
    private final BlockPos blockPos;

    public PacketExperienceSync(float storedExperience, BlockPos blockPos) {
        this.storedExperience = storedExperience;
        this.blockPos = blockPos;
    }

    public PacketExperienceSync(FriendlyByteBuf buf) {
        storedExperience = buf.readFloat();
        blockPos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(storedExperience);
        buf.writeBlockPos(blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LocalPlayer player = ModProxy.PROXY.getLocalPlayer();
            if (player != null) {
                Level level = player.getLevel();
                if (level.getBlockEntity(blockPos) instanceof IHasExp exp) {
                    exp.setStoredExperience(storedExperience);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
