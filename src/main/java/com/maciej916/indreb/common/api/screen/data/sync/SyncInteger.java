package com.maciej916.indreb.common.api.screen.data.sync;

import com.google.common.base.Supplier;
import com.maciej916.indreb.common.api.screen.data.DataTypes;
import com.maciej916.indreb.common.api.screen.data.interfaces.DataSync;
import net.minecraft.network.FriendlyByteBuf;

public class SyncInteger implements DataSync {

    private Supplier<Integer> dynValue;
    private int staValue;

    public SyncInteger() {

    }

    public SyncInteger(Supplier<Integer> value) {
        this.dynValue = value;
        this.staValue = value.get();
    }

    public int getStaValue() {
        return staValue;
    }

    @Override
    public int getDataTypeId() {
        return DataTypes.INT.getId();
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
        buf.writeInt(staValue);
    }

    @Override
    public void fromBuf(FriendlyByteBuf buf) {
        staValue = buf.readInt();
    }

    @Override
    public String toString() {
        return "SyncInteger{" +
                "staValue=" + staValue +
                '}';
    }
}
