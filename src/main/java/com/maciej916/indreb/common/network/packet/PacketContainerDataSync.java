package com.maciej916.indreb.common.network.packet;

import com.maciej916.indreb.common.api.screen.IndRebContainerMenu;
import com.maciej916.indreb.common.api.screen.data.interfaces.DataSync;
import com.maciej916.indreb.common.api.screen.data.DataTypes;
import com.maciej916.indreb.common.api.screen.data.sync.SyncBoolean;
import com.maciej916.indreb.common.api.screen.data.sync.SyncInteger;
import com.maciej916.indreb.common.api.screen.data.sync.SyncProgressFloat;
import com.maciej916.indreb.common.api.screen.data.sync.SyncProgressInt;
import com.maciej916.indreb.common.proxy.ModProxy;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

public class PacketContainerDataSync {

    private final int containerId;
    private final HashMap<Integer, DataSync> data;

    public PacketContainerDataSync(int containerId, HashMap<Integer, DataSync> dataSync) {
        this.containerId = containerId;
        this.data = dataSync;
    }

    public PacketContainerDataSync(FriendlyByteBuf buf) {
        HashMap<Integer, DataSync> netDataSync = new HashMap<>();
        int mapSize = buf.readInt();
        for (int i = 0; i < mapSize; i++) {
            int dataSlot = buf.readInt();
            int dataType = buf.readInt();
            DataSync dataSync = null;

            if (DataTypes.getFromId(dataType) == DataTypes.PROGRESS_FLOAT) {
                dataSync = new SyncProgressFloat();
            }

            if (DataTypes.getFromId(dataType) == DataTypes.PROGRESS_INT) {
                dataSync = new SyncProgressInt();
            }

            if (DataTypes.getFromId(dataType) == DataTypes.BOOL) {
                dataSync = new SyncBoolean();
            }

            if (DataTypes.getFromId(dataType) == DataTypes.INT) {
                dataSync = new SyncInteger();
            }

            // Add more types

            if (dataSync != null) {
                dataSync.fromBuf(buf);
                netDataSync.put(dataSlot, dataSync);
            }
        }

        this.containerId = buf.readInt();
        this.data = netDataSync;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(data.size());

        data.forEach((key, data) -> {
            buf.writeInt(key);
            buf.writeInt(data.getDataTypeId());
            data.toBytes(buf);
        });

        buf.writeInt(containerId);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LocalPlayer player = ModProxy.PROXY.getLocalPlayer();
            if (player != null) {
                if (containerId == player.containerMenu.containerId) {
                    if (player.containerMenu instanceof IndRebContainerMenu rebContainerMenu) {
                        if (rebContainerMenu.getContainerData() != null) {
                            rebContainerMenu.getContainerData().handleClientData(data);
                        }
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
