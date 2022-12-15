package com.maciej916.indreb.common.item.impl.armor;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.maciej916.indreb.common.api.enums.CustomArmorMaterial;
import com.maciej916.indreb.common.api.interfaces.item.IArmorProperties;
import com.maciej916.indreb.common.api.item.base.BaseArmor;
import com.maciej916.indreb.common.attributes.ModAttributes;
import com.maciej916.indreb.common.capability.radiation.IHasRadiation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

public class HazmatArmor extends BaseArmor implements IArmorProperties, IHasRadiation {

    public final int radiationProtection;

    public HazmatArmor(EquipmentSlot slot, int radiationProtection) {
        super(CustomArmorMaterial.HAZMAT, slot);
        this.radiationProtection = radiationProtection;
    }

    @Override
    public int getRadiationProtection() {
        return radiationProtection;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (stack.getItem().equals(this)) {
            if (slot == getSlot()) {
                Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
                modifiers.putAll(super.getAttributeModifiers(slot, stack));
                modifiers.put(ModAttributes.RADIATION_PROTECTION.get(), new AttributeModifier(ModAttributes.RADIATION_PROTECTION_MODIFIER.toString(), this.getRadiationProtection(), AttributeModifier.Operation.ADDITION));
                return modifiers;
            }
        }
        return super.getAttributeModifiers(slot, stack);
    }
}
