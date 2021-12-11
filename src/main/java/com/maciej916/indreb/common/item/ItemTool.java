package com.maciej916.indreb.common.item;

import com.maciej916.indreb.common.registries.ModItemGroups;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemTool extends Item {

    public ItemTool(int maxDamage) {
        super(new Properties().tab(ModItemGroups.MAIN_ITEM_GROUP).stacksTo(1).durability(maxDamage).setNoRepair());
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack result = itemStack.copy();
        result.setDamageValue(itemStack.getDamageValue() + 1);
        return result.getDamageValue() >= result.getMaxDamage() ? ItemStack.EMPTY : result;
    }


    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }


    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_TOOLS) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }


}
