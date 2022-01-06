package com.maciej916.indreb.common.item.impl.nano;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.ModArmorMaterials;
import com.maciej916.indreb.common.item.base.ElectricArmorItem;
import net.minecraft.world.entity.EquipmentSlot;

public class ItemNanoArmor extends ElectricArmorItem {

    public ItemNanoArmor(EquipmentSlot slot) {
        super(ModArmorMaterials.NANO, slot, new Properties(), 0, 1000000, EnergyType.RECEIVE, EnergyTier.ADVANCED);
    }

}
