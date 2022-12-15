package com.maciej916.indreb.common.subscribe.event;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.enums.CustomArmorMaterial;
import com.maciej916.indreb.common.api.interfaces.item.IElectricItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingHurt {

    @SubscribeEvent
    public static void livingHurtEvent(LivingHurtEvent event) {
        if (!event.getSource().isBypassInvul() && event.getEntity() instanceof Player player) {
            Iterable<ItemStack> stacks = player.getArmorSlots();
            for (ItemStack stack : stacks) {
                if (stack.getItem() instanceof IElectricItem iei) {
                    IEnergyStorage energy = iei.getEnergyStorage(stack);
                    energy.consumeEnergy((int) event.getAmount() * 500, false);
                }
            }
        }
    }

    @SubscribeEvent
    public static void event(LivingFallEvent event) {
        if (event.getEntity() instanceof Player player) {
            Iterable<ItemStack> stacks = player.getArmorSlots();
            for (ItemStack stack : stacks) {
                if (stack.getItem() instanceof ArmorItem armorItem) {
                    if (armorItem.getSlot() == EquipmentSlot.FEET && ((armorItem.getMaterial() == CustomArmorMaterial.RUBBER || armorItem.getMaterial() == CustomArmorMaterial.NANO)) ) {
                        event.setDistance((float) (event.getDistance() / 3.3));
                    }
                }
            }
        }
    }
}
