package com.maciej916.indreb.common.item.impl.armor;

import com.maciej916.indreb.common.api.enums.CustomArmorMaterial;
import com.maciej916.indreb.common.api.interfaces.item.IArmorProperties;
import com.maciej916.indreb.common.api.item.base.BaseArmor;
import net.minecraft.world.entity.EquipmentSlot;

public class RubberBoots extends BaseArmor implements IArmorProperties {

    public RubberBoots() {
        super(CustomArmorMaterial.RUBBER, EquipmentSlot.FEET);
    }

}
