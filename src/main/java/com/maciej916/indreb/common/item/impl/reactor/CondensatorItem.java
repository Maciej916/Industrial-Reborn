package com.maciej916.indreb.common.item.impl.reactor;

import com.maciej916.indreb.common.capability.reactor.ReactorCondensatorCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class CondensatorItem extends BaseReactorItem {

    public CondensatorItem(int maxDamage, int maxHeat) {
        super(new Properties().stacksTo(1), maxDamage, maxHeat);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ReactorCondensatorCapability(stack, maxDamage, maxHeat);
    }

}
