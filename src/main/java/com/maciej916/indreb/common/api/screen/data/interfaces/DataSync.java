package com.maciej916.indreb.common.api.screen.data.interfaces;

import net.minecraft.network.FriendlyByteBuf;

public interface DataSync {

    int getDataTypeId();

    boolean isChanged();
    void toBytes(FriendlyByteBuf buf);
    void fromBuf(FriendlyByteBuf buf);
}
