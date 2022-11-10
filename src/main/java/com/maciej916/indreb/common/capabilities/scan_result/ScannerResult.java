package com.maciej916.indreb.common.capabilities.scan_result;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;

public class ScannerResult implements INBTSerializable<CompoundTag> {

    private ItemStack result;
    private int matterCost;
    private int energyCost;

    public ScannerResult(ItemStack result, int matterCost, int energyCost) {
        this.result = result;
        this.matterCost = matterCost;
        this.energyCost = energyCost;
    }

    public ScannerResult() {
        this(ItemStack.EMPTY, 0, 0);
    }

    public ItemStack getResultStack() {
        return result;
    }

    public int getMatterCost() {
        return matterCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putBoolean("has_result", !this.result.isEmpty());

        if (!this.result.isEmpty()) {
            CompoundTag itemTag = new CompoundTag();
            result.save(itemTag);
            tag.put("result", itemTag);
        }

        tag.putInt("energyCost", this.energyCost);
        tag.putFloat("matterCost", this.matterCost);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

        if (nbt.getBoolean("has_result")) {
            this.result = ItemStack.of(nbt.getCompound("result"));
        } else {
            this.result = ItemStack.EMPTY;
        }

        this.energyCost = nbt.getInt("energyCost");
        this.matterCost = nbt.getInt("matterCost");
    }
}
