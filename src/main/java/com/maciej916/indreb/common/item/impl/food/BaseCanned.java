package com.maciej916.indreb.common.item.impl.food;

import com.maciej916.indreb.common.api.item.base.BaseItem;
import com.maciej916.indreb.common.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class BaseCanned extends BaseItem {

    public BaseCanned(Properties properties) {
        super(CreativeModeTab.TAB_FOOD, properties);
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        ItemStack itemstack = super.finishUsingItem(pStack, pLevel, pEntityLiving);
        return pEntityLiving instanceof Player && ((Player)pEntityLiving).getAbilities().instabuild ? itemstack : new ItemStack(ModItems.EMPTY_CAN.get());
    }

}
