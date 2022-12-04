package com.maciej916.indreb.common.api.screen.data.sync;

import com.maciej916.indreb.common.api.screen.data.DataTypes;
import com.maciej916.indreb.common.api.screen.data.interfaces.DataSync;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import net.minecraft.network.FriendlyByteBuf;

public class SyncProgressFloat implements DataSync {

    private ProgressFloat progressFloat;
    private float currentProgress;
    private float progressMax;

    public SyncProgressFloat() {

    }

    public SyncProgressFloat(ProgressFloat progressFloat) {
        this.progressFloat = progressFloat;
        this.currentProgress = progressFloat.getCurrentProgress();
        this.progressMax = progressFloat.getProgressMax();
    }

    public float getCurrentProgress() {
        return currentProgress;
    }

    public float getProgressMax() {
        return progressMax;
    }

    @Override
    public int getDataTypeId() {
        return DataTypes.PROGRESS_FLOAT.getId();
    }

    @Override
    public boolean isChanged() {
        if (progressFloat.getCurrentProgress() != currentProgress || progressFloat.getProgressMax() != progressMax) {
            this.currentProgress = progressFloat.getCurrentProgress();
            this.progressMax = progressFloat.getProgressMax();
            return true;
        }

        return false;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(currentProgress);
        buf.writeFloat(progressMax);
    }

    @Override
    public void fromBuf(FriendlyByteBuf buf) {
        this.currentProgress = buf.readFloat();
        this.progressMax = buf.readFloat();
    }
}
