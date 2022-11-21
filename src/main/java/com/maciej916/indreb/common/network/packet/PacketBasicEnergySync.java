package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.api.blockentity.interfaces.IIndRebBlockEntity;
import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.proxy.ModProxy;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketBasicEnergySync {

    private final int energyStored;
    private final int maxEnergy;

    private final int energyTypeId;
    private final int energyTierLvl;

    private final int lastGenerated;
    private final int totalGenerated;

    private final int lastConsumed;
    private final int totalConsumed;

    private final BlockPos blockPos;

    public PacketBasicEnergySync(BasicEnergyStorage energyStorage, BlockPos blockPos) {
        this.energyStored = energyStorage.energyStored();
        this.maxEnergy = energyStorage.maxEnergy();

        this.energyTypeId = energyStorage.energyType().getId();
        this.energyTierLvl = energyStorage.energyTier().getLvl();

        this.lastGenerated = energyStorage.lastGenerated();
        this.totalGenerated = energyStorage.totalGenerated();

        this.lastConsumed = energyStorage.lastConsumed();
        this.totalConsumed = energyStorage.totalConsumed();

        this.blockPos = blockPos;
    }

    public PacketBasicEnergySync(FriendlyByteBuf buf) {
        energyStored = buf.readInt();
        maxEnergy = buf.readInt();

        energyTypeId = buf.readInt();
        energyTierLvl = buf.readInt();

        lastGenerated = buf.readInt();
        totalGenerated = buf.readInt();

        lastConsumed = buf.readInt();
        totalConsumed = buf.readInt();

        blockPos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(energyStored);
        buf.writeInt(maxEnergy);

        buf.writeInt(energyTypeId);
        buf.writeInt(energyTierLvl);

        buf.writeInt(lastGenerated);
        buf.writeInt(totalGenerated);

        buf.writeInt(lastConsumed);
        buf.writeInt(totalConsumed);

        buf.writeBlockPos(blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LocalPlayer player = ModProxy.PROXY.getLocalPlayer();
            if (player != null) {
                Level level = player.getLevel();
                if (level.getBlockEntity(blockPos) instanceof IIndRebBlockEntity entity) {
                    if (entity.hasEnergyStorage()) {
                        BasicEnergyStorage basicEnergyStorage = entity.getEnergyStorage();

                        basicEnergyStorage.energyStored = energyStored;
                        basicEnergyStorage.maxEnergy = maxEnergy;

                        basicEnergyStorage.energyType = EnergyType.getTierFromId(energyTypeId);
                        basicEnergyStorage.energyTier = EnergyTier.getTierFromLvl(energyTierLvl);

                        basicEnergyStorage.lastGenerated = lastGenerated;
                        basicEnergyStorage.totalGenerated = totalGenerated;

                        basicEnergyStorage.lastConsumed = lastConsumed;
                        basicEnergyStorage.totalConsumed = totalConsumed;
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
