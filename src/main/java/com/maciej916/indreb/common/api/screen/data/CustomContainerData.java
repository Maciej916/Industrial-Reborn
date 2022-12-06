package com.maciej916.indreb.common.api.screen.data;

import com.google.common.base.Supplier;
import com.maciej916.indreb.common.api.screen.data.interfaces.DataSync;
import com.maciej916.indreb.common.api.screen.data.sync.*;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import com.maciej916.indreb.common.api.util.ProgressInt;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketContainerDataSync;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.HashSet;

public class CustomContainerData {

    private HashMap<Integer, DataSync> data = new HashMap<>();

    public CustomContainerData() {

    }

    private void checkDataSlot(int dataSlot) {
        if (data.containsKey(dataSlot)) {
            throw new RuntimeException("Data slot for sync already exist. Data slot: " + dataSlot);
        }
    }

    public void syncProgressFloat(int dataSlot, ProgressFloat progressFloat) {
        checkDataSlot(dataSlot);
        data.put(dataSlot, new SyncProgressFloat(progressFloat));
    }

    public void syncProgressInt(int dataSlot, ProgressInt progressInt) {
        checkDataSlot(dataSlot);
        data.put(dataSlot, new SyncProgressInt(progressInt));
    }

    public void syncInt(int dataSlot, Supplier<Integer> value) {
        checkDataSlot(dataSlot);
        data.put(dataSlot, new SyncInteger(value));
    }

    public void syncBool(int dataSlot, Supplier<Boolean> value) {
        checkDataSlot(dataSlot);
        data.put(dataSlot, new SyncBoolean(value));
    }

    public void syncStack(int dataSlot, Supplier<ItemStack> value) {
        checkDataSlot(dataSlot);
        data.put(dataSlot, new SyncItemStack(value));
    }

    public void updateProgressFloatData(int dataSlot, ProgressFloat progressFloat) {
        if (data.get(dataSlot) instanceof SyncProgressFloat progress) {
            progressFloat.setData(progress.getCurrentProgress(), progress.getProgressMax());
        }
    }

    public void updateProgressIntData(int dataSlot, ProgressInt progressInt) {
        if (data.get(dataSlot) instanceof SyncProgressInt progress) {
            progressInt.setData(progress.getCurrentProgress(), progress.getProgressMax());
        }
    }

    public int getIntData(int dataSlot) {
        if (data.get(dataSlot) instanceof SyncInteger syncInt) {
            return syncInt.getStaValue();
        }

        return 0;
    }

    public boolean getBoolData(int dataSlot) {
        if (data.get(dataSlot) instanceof SyncBoolean syncBoolean) {
            return syncBoolean.getStaValue();
        }

        return false;
    }

    public ItemStack getItemStackData(int dataSlot) {
        if (data.get(dataSlot) instanceof SyncItemStack syncItemStack) {
            return syncItemStack.getStaValue();
        }

        return ItemStack.EMPTY;
    }



    public void handleClientData(HashMap<Integer, DataSync> syncData) {
        this.data.putAll(syncData);
    }

    private HashMap<Integer, DataSync> getSyncData(boolean fullData) {
        HashMap<Integer, DataSync> dataSync = new HashMap<>();

        data.forEach((key, data) -> {
            if (data.isChanged() || fullData) {
                dataSync.put(key, data);
            }
        });

//        if (dataSync.size() > 0) {
//            System.out.println("dataSync");
//            System.out.println(dataSync);
//        }

        return dataSync;
    }

    public void broadcastToPlayer(ServerPlayer player, int containerId) {
        HashMap<Integer, DataSync> data = getSyncData(true);
        if (data.size() > 0) {
            ModNetworking.sendToPlayer(player, new PacketContainerDataSync(containerId, data));
        }
    }

    public void broadcastChanges(HashSet<ServerPlayer> listeners, int containerId) {
        HashMap<Integer, DataSync> data = getSyncData(false);
        if (data.size() > 0) {
            for (ServerPlayer player : listeners) {
                ModNetworking.sendToPlayer(player, new PacketContainerDataSync(containerId, data));
            }
        }
    }

    public void broadcastFullState(HashSet<ServerPlayer> listeners, int containerId) {
        HashMap<Integer, DataSync> data = getSyncData(true);
        if (data.size() > 0) {
            for (ServerPlayer player : listeners) {
                ModNetworking.sendToPlayer(player, new PacketContainerDataSync(containerId, data));
            }
        }
    }

    @Override
    public String toString() {
        return "CustomContainerData{" +
                "data=" + data +
                '}';
    }
}
