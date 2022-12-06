package com.maciej916.indreb.common.capability.scanner;

import com.maciej916.indreb.common.capability.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ScannerResultHandler implements ICapabilityProvider, IScannerResult, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<IScannerResult> scanCap = LazyOptional.of(() -> this);
    protected ScannerResult result;

    public ScannerResultHandler(@NotNull ItemStack container) {
        this.result = new ScannerResult();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.SCANNER_RESULT) {
            return scanCap.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public ScannerResult getResult() {
        return result;
    }

    @Override
    public void setResult(ScannerResult result) {
        this.result = result;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("result", this.result.serializeNBT());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.result.deserializeNBT(nbt.getCompound("result"));
    }
}