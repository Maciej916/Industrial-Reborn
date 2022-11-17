package com.maciej916.indreb.common.capability.entity;

import com.maciej916.indreb.common.capability.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityCapability implements IEntityCapability, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<IEntityCapability> entityCap = LazyOptional.of(() -> this);


    public EntityCapability() {

    }

    @Override
    public void tick() {

    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.PLAYER_CAPABILITY) {
            return entityCap.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public void clone(IEntityCapability capability) {

    }


    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();


        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }
}
