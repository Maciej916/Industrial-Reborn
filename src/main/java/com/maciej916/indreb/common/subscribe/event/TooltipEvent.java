package com.maciej916.indreb.common.subscribe.event;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.capability.radiation.IHasRadiation;
import com.maciej916.indreb.common.util.RadiationHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TooltipEvent {

    @SubscribeEvent
    public static void event(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        if (item instanceof IHasRadiation hasRadiation) {
            double radsAdd = hasRadiation.getAddRads() * stack.getCount();
            if (radsAdd > 0) {
                event.getToolTip().add(Component.translatable("tooltip." + IndReb.MODID + ".radiation", RadiationHelper.radsPrefix(radsAdd, true)).withStyle(ChatFormatting.YELLOW));
            }
        }

        if (RadiationHelper.RADIATION_FOOD.containsKey(item)) {
            double value = RadiationHelper.RADIATION_FOOD.get(item);
            event.getToolTip().add(Component.translatable("tooltip." + IndReb.MODID + ".radiation_remove", RadiationHelper.radsPrefix(value, false)).withStyle(ChatFormatting.GREEN));
        }
    }
}
