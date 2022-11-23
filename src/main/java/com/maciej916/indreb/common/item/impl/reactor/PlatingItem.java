package com.maciej916.indreb.common.item.impl.reactor;

import com.maciej916.indreb.common.capability.reactor.ReactorPlatingCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class PlatingItem extends BaseReactorItem {

    private final int heatAdjustment;
    private final double explosionPowerMultiplier;

    public PlatingItem(int maxDamage, int maxHeat, int heatAdjustment, double explosionPowerMultiplier) {
        super(new Properties().stacksTo(1), maxDamage, maxHeat);
        this.heatAdjustment = heatAdjustment;
        this.explosionPowerMultiplier = explosionPowerMultiplier;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ReactorPlatingCapability(stack, maxDamage, maxHeat, heatAdjustment, explosionPowerMultiplier);
    }

}
