package com.maciej916.indreb.common.capabilities.player;

import com.maciej916.indreb.common.registries.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerCapability implements IPlayerCapability, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<IPlayerCapability> playerCap = LazyOptional.of(() -> this);
    private boolean nightVision;

    public PlayerCapability() {
        this.nightVision = false;
    }

    @Override
    public boolean getNightVision() {
        return nightVision;
    }

    @Override
    public void setNightVision(boolean enabled) {
        this.nightVision = enabled;
    }

    @Override
    public void clone(IPlayerCapability capability) {
        this.nightVision = capability.getNightVision();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.PLAYER_CAPABILITY) {
            return playerCap.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("nightVision", this.nightVision);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
       this.nightVision = nbt.getBoolean("nightVision");
    }
}
