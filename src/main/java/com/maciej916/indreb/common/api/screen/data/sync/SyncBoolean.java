package com.maciej916.indreb.common.api.screen.data.sync;

import com.google.common.base.Supplier;
import com.maciej916.indreb.common.api.screen.data.DataTypes;
import com.maciej916.indreb.common.api.screen.data.interfaces.DataSync;
import net.minecraft.network.FriendlyByteBuf;

public class SyncBoolean implements DataSync {

    private Supplier<Boolean> dynValue;
    private boolean staValue;

    public SyncBoolean() {

    }

    public SyncBoolean(Supplier<Boolean> value) {
        this.dynValue = value;
        this.staValue = value.get();
    }

    public boolean getStaValue() {
        return staValue;
    }

    @Override
    public int getDataTypeId() {
        return DataTypes.BOOL.getId();
    }

    @Override
    public boolean isChanged() {
        if (staValue != dynValue.get()) {
            staValue = dynValue.get();
            return true;
        }

        return false;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(staValue);
    }

    @Override
    public void fromBuf(FriendlyByteBuf buf) {
        staValue = buf.readBoolean();
    }

    @Override
    public String toString() {
        return "SyncBoolean{" +
                "staValue=" + staValue +
                '}';
    }
}
