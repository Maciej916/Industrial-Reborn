package com.maciej916.indreb.common.item.impl.armor;

import com.maciej916.indreb.common.api.enums.CustomArmorMaterial;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.item.base.BaseElectricArmor;
import net.minecraft.world.entity.EquipmentSlot;

public class NanoArmor extends BaseElectricArmor {

    public NanoArmor(EquipmentSlot slot) {
        super(CustomArmorMaterial.NANO, slot, new Properties(), 0, 100000, EnergyType.RECEIVE, EnergyTier.ADVANCED);
    }

}
