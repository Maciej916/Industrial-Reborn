package com.maciej916.indreb.common.item;

import com.maciej916.indreb.common.registries.ModItemGroups;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.*;

public class IndRebPickaxe extends PickaxeItem {

    public IndRebPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, new Properties().tab(ModItemGroups.MAIN_ITEM_GROUP));

    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_COMBAT) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

}
