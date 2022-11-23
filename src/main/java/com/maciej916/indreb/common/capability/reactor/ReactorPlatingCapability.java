package com.maciej916.indreb.common.capability.reactor;

import com.maciej916.indreb.common.multiblock.reactor.Reactor;
import net.minecraft.world.item.ItemStack;

public class ReactorPlatingCapability extends ReactorComponentCapability {

    private final int heatAdjustment;

    public ReactorPlatingCapability(ItemStack stack, int maxDamage, int maxHeat, int heatAdjustment, double explosionPowerMultiplier) {
        super(stack, maxDamage, maxHeat);

        this.heatAdjustment = heatAdjustment;
        this.explosionPowerMultiplier = explosionPowerMultiplier;
    }

    @Override
    public void addToReactor(Reactor parent, int row, int col, int slotId) {
        super.addToReactor(parent, row, col, slotId);
        if (parent != null) {
            parent.adjustMaxHeat(heatAdjustment);
        }
    }

    @Override
    public void removeFromReactor() {
        if (parent != null) {
            parent.adjustMaxHeat(-heatAdjustment);
        }
        super.removeFromReactor();
    }

}
