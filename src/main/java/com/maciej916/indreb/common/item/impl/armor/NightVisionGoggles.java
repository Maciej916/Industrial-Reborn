package com.maciej916.indreb.common.item.impl.armor;

import com.maciej916.indreb.common.enums.ModArmorMaterials;
import com.maciej916.indreb.common.interfaces.item.IArmorProperties;
import com.maciej916.indreb.common.item.base.BaseArmor;
import net.minecraft.world.entity.EquipmentSlot;

public class NightVisionGoggles extends BaseArmor implements IArmorProperties {

    public NightVisionGoggles() {
        super(ModArmorMaterials.NIGHTVISION, EquipmentSlot.HEAD);
    }


    @Override
    public boolean supportsNightVision() {
        return true;
    }
}
