package com.maciej916.indreb.common.item.impl.reactor;

import com.maciej916.indreb.common.capability.radiation.IHasRadiation;
import com.maciej916.indreb.common.capability.reactor.ReactorFuelRodCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class FuelRodItem extends BaseReactorItem implements IHasRadiation {

    private final int energyMult;
    private final int heatMult;
    private final int rodCount;
    private final boolean moxStyle;
    private final double rads;

    public FuelRodItem(int maxDamage, int maxHeat, int energyMult, int heatMult, int rodCount, boolean moxStyle, double rads) {
        super(new Properties().stacksTo(1), maxDamage, maxHeat);
        this.energyMult = energyMult;
        this.heatMult = heatMult;
        this.rodCount = rodCount;
        this.moxStyle = moxStyle;
        this.rads = rads;
    }

    @Override
    public double getAddRads() {
        return rads;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ReactorFuelRodCapability(stack, maxDamage, maxHeat, energyMult, heatMult, rodCount, moxStyle);
    }

}
