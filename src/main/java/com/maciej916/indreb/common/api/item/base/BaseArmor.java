package com.maciej916.indreb.common.api.item.base;

import com.maciej916.indreb.common.item.ModItemGroups;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public abstract class BaseArmor extends ArmorItem {

    public BaseArmor(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Properties().tab(ModItemGroups.MAIN));
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_COMBAT) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

}
