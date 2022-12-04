package com.maciej916.indreb.common.api.screen.data.sync;

import com.maciej916.indreb.common.api.screen.data.DataTypes;
import com.maciej916.indreb.common.api.screen.data.interfaces.DataSync;
import com.maciej916.indreb.common.api.util.ProgressInt;
import net.minecraft.network.FriendlyByteBuf;

public class SyncProgressInt implements DataSync {

    private ProgressInt progressInt;
    private int currentProgress;
    private int progressMax;

    public SyncProgressInt() {

    }

    public SyncProgressInt(ProgressInt progressInt) {
        this.progressInt = progressInt;
        this.currentProgress = progressInt.getCurrentProgressInt();
        this.progressMax = progressInt.getProgressMaxInt();
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public int getProgressMax() {
        return progressMax;
    }

    @Override
    public int getDataTypeId() {
        return DataTypes.PROGRESS_INT.getId();
    }

    @Override
    public boolean isChanged() {
        if (progressInt.getCurrentProgressInt() != currentProgress || progressInt.getProgressMaxInt() != progressMax) {
            this.currentProgress = progressInt.getCurrentProgressInt();
            this.progressMax = progressInt.getProgressMaxInt();
            return true;
        }

        return false;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(currentProgress);
        buf.writeInt(progressMax);
    }

    @Override
    public void fromBuf(FriendlyByteBuf buf) {
        this.currentProgress = buf.readInt();
        this.progressMax = buf.readInt();
    }
}
