package com.maciej916.indreb.common.subscribers.events;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.ModArmorMaterials;
import com.maciej916.indreb.common.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.item.base.BaseArmor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
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
                    IEnergy energy = iei.getEnergy(stack);
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
                if (stack.getItem() instanceof BaseArmor baseArmor) {
                    if (baseArmor.getSlot() == EquipmentSlot.FEET && baseArmor.getMaterial() == ModArmorMaterials.RUBBER) {
                        event.setDistance((float) (event.getDistance() / 3.3));
                    }
                }
            }
        }
    }
}
