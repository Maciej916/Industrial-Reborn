package com.maciej916.indreb.common.api.screen.data.sync;

import com.google.common.base.Supplier;
import com.maciej916.indreb.common.api.screen.data.DataTypes;
import com.maciej916.indreb.common.api.screen.data.interfaces.DataSync;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public class SyncItemStack implements DataSync {

    private Supplier<ItemStack> dynValue;
    private ItemStack staValue;

    public SyncItemStack() {

    }

    public SyncItemStack(Supplier<ItemStack> value) {
        this.dynValue = value;
        this.staValue = value.get();
    }

    public ItemStack getStaValue() {
        return staValue;
    }

    @Override
    public int getDataTypeId() {
        return DataTypes.ITEM_STACK.getId();
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
        buf.writeItemStack(staValue, false);
    }

    @Override
    public void fromBuf(FriendlyByteBuf buf) {
        staValue = buf.readItem();
    }

    @Override
    public String toString() {
        return "SyncBoolean{" +
                "staValue=" + staValue +
                '}';
    }
}
