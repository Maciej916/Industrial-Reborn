package com.maciej916.indreb.common.item.impl.reactor;

import com.maciej916.indreb.common.capability.reactor.ReactorVentCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class VentItem extends BaseReactorItem {

    private final int selfVent;
    private final int hullDraw;
    private final int sideVent;

    public VentItem(int maxDamage, int maxHeat, int selfVent, int hullDraw, int sideVent) {
        super(new Properties().stacksTo(1), maxDamage, maxHeat);
        this.selfVent = selfVent;
        this.hullDraw = hullDraw;
        this.sideVent = sideVent;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ReactorVentCapability(stack, maxDamage, maxHeat, selfVent, hullDraw, sideVent);
    }

}
