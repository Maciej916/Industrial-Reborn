package com.maciej916.indreb.common.item.impl.armor;

import com.maciej916.indreb.common.api.enums.CustomArmorMaterial;
import com.maciej916.indreb.common.api.interfaces.item.IArmorProperties;
import com.maciej916.indreb.common.api.item.base.BaseArmor;
import net.minecraft.world.entity.EquipmentSlot;

public class BronzeArmor extends BaseArmor implements IArmorProperties {

    public BronzeArmor(EquipmentSlot slot) {
        super(CustomArmorMaterial.BRONZE, slot);
    }

}
