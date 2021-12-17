package com.maciej916.indreb.common.item.base;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModItemGroups;
import it.unimi.dsi.fastutil.Hash;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class BaseItem extends Item {
    public static final HashMap<BaseItem, int[]> COLORED_ITEMS = new HashMap<>();

    public BaseItem(Properties properties) {
        super(properties.tab(ModItemGroups.MAIN_ITEM_GROUP));
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    private static class ColorRegisterHandler {
        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerSpawnEggColors(ColorHandlerEvent.Item event) {
            COLORED_ITEMS.forEach((baseItem, ints) -> event.getItemColors().register((stack, layer) -> layer == 0 ? ints[0] : ints[1], baseItem));
        }
    }
}
