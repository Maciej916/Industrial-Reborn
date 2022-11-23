package com.maciej916.indreb.common.item.impl.reactor;

import com.maciej916.indreb.common.capability.reactor.ReactorHeatExchangerCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class ExchangerItem extends BaseReactorItem {

    private final int switchSide;
    private final int switchReactor;

    public ExchangerItem(int maxDamage, int maxHeat, int switchSide, int switchReactor) {
        super(new Properties().stacksTo(1), maxDamage, maxHeat);
        this.switchSide = switchSide;
        this.switchReactor = switchReactor;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ReactorHeatExchangerCapability(stack, maxDamage, maxHeat, switchSide, switchReactor);
    }

}
