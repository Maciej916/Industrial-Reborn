package com.maciej916.indreb.common.util;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public final class CapabilityUtil {

    @Nonnull
    public static <T> LazyOptionalHelper<T> getCapabilityHelper(ICapabilityProvider provider, Capability<T> cap) {
        if (provider == null || cap == null) {
            return new LazyOptionalHelper<>(LazyOptional.empty());
        }
        return new LazyOptionalHelper<>(provider.getCapability(cap));
    }

    @Nonnull
    public static <T> LazyOptionalHelper<T> getCapabilityHelper(ICapabilityProvider provider, Capability<T> cap, Direction side) {
        if (provider == null || cap == null) {
            return new LazyOptionalHelper<>(LazyOptional.empty());
        }
        return new LazyOptionalHelper<>(provider.getCapability(cap, side));
    }

}
