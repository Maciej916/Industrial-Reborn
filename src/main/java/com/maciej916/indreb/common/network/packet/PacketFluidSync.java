package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityFluid;
import com.maciej916.indreb.common.proxy.ModProxy;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PacketFluidSync {

    private final List<FluidStack> fluidStacks;
    private final BlockPos blockPos;

    public PacketFluidSync(List<FluidStack> fluidStacks, BlockPos blockPos) {
        this.fluidStacks = fluidStacks;
        this.blockPos = blockPos;
    }

    public PacketFluidSync(FriendlyByteBuf buf) {
        fluidStacks = buf.readCollection(ArrayList::new, FriendlyByteBuf::readFluidStack);
        blockPos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeCollection(fluidStacks, FriendlyByteBuf::writeFluidStack);
        buf.writeBlockPos(blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LocalPlayer player = ModProxy.PROXY.getLocalPlayer();
            if (player != null) {
                Level level = player.getLevel();
                if (level.getBlockEntity(blockPos) instanceof IBlockEntityFluid entity) {
                    entity.setStoredFluids(fluidStacks);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
