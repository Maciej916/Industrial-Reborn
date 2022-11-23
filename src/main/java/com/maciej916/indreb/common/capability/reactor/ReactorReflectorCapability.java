package com.maciej916.indreb.common.capability.reactor;

import net.minecraft.world.item.ItemStack;

public class ReactorReflectorCapability extends ReactorComponentCapability {

    public ReactorReflectorCapability(ItemStack stack, int maxDamage, int maxHeat) {
        super(stack, maxDamage, maxHeat);
    }

    @Override
    public boolean isNeutronReflector() {
        return !isBroken();
    }

    @Override
    public int generateHeat() {
        IReactorComponentCapability component = parent.getComponentAt(row - 1, col);
        if (component != null) {
            applyDamage(component.getRodCount());
        }
        component = parent.getComponentAt(row, col + 1);
        if (component != null) {
            applyDamage(component.getRodCount());
        }
        component = parent.getComponentAt(row + 1, col);
        if (component != null) {
            applyDamage(component.getRodCount());
        }
        component = parent.getComponentAt(row, col - 1);
        if (component != null) {
            applyDamage(component.getRodCount());
        }
        return 0;
    }

}
