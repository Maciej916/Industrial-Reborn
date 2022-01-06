package com.maciej916.indreb.common.item.impl.nano;

import com.maciej916.indreb.common.interfaces.item.IArmorProperties;
import net.minecraft.world.entity.EquipmentSlot;

public class NanoHelmet extends ItemNanoArmor implements IArmorProperties {

    public NanoHelmet(EquipmentSlot slot) {
        super(slot);
    }

    @Override
    public boolean supportsNightVision() {
        return true;
    }
}
