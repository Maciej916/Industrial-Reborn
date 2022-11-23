package com.maciej916.indreb.common.item.impl.reactor;

import com.maciej916.indreb.common.capability.reactor.ReactorFuelRodCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class FuelRodItem extends BaseReactorItem {

    private final int energyMult;
    private final int heatMult;
    private final int rodCount;
    private final boolean moxStyle;

    public FuelRodItem(int maxDamage, int maxHeat, int energyMult, int heatMult, int rodCount, boolean moxStyle) {
        super(new Properties().stacksTo(1), maxDamage, maxHeat);
        this.energyMult = energyMult;
        this.heatMult = heatMult;
        this.rodCount = rodCount;
        this.moxStyle = moxStyle;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ReactorFuelRodCapability(stack, maxDamage, maxHeat, energyMult, heatMult, rodCount, moxStyle);
    }

}
