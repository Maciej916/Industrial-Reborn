package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.energy.provider.comparator.EnergyReceiveComparator;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.item.base.ElectricItem;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChargingBattery extends ElectricItem {

    public ChargingBattery(int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        super(new Properties(), 0, maxEnergy, energyType, energyTier);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        if (player.isShiftKeyDown()) {
            CompoundTag tag = stack.getOrCreateTag();
            int currentDir = tag.getAllKeys().contains("Mode") ? tag.getInt("Mode") : 1;
            int newMode = currentDir + 1 > 3 ? 0 : currentDir + 1;
            tag.putInt("Mode", newMode);
            player.displayClientMessage(new TranslatableComponent( "charging." + IndReb.MODID + ".mode", new TranslatableComponent("mode." + IndReb.MODID + "." + newMode).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY), true);
        }

        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean p_41408_) {
        if (!level.isClientSide()) {
            if (entity instanceof Player player) {
                CompoundTag tag = stack.getOrCreateTag();
                int mode = tag.getAllKeys().contains("Mode") ? tag.getInt("Mode"): 1;
                if (mode != 0) {
                    IEnergy energy = getEnergy(stack);
                    if (energy != null) {
                        Inventory inventory = player.getInventory();
                        List<EnergyReceiveComparator> transferCharge = new ArrayList<>();
                        for (int j = 0; j <= 9; ++j) {
                            ItemStack invStack = inventory.getItem(j);
                            if (invStack.getItem() instanceof IElectricItem electricItem) {
                                IEnergy stackEnergy = electricItem.getEnergy(invStack);
                                if (stackEnergy != null && !(invStack.getItem() instanceof ChargingBattery) && stack != invStack) {
                                    boolean valid = false;
                                    if (mode == 1) {
                                        valid = true;
                                    } else if (mode == 2) {
                                        valid = player.getItemInHand(player.getUsedItemHand()) != invStack;
                                    } else if (mode == 3) {
                                        valid = player.getItemInHand(player.getUsedItemHand()) == invStack;
                                    }

                                    if (valid && stackEnergy.energyTier().getLvl() <= getEnergyTier().getLvl() && stackEnergy.canReceiveEnergy() && stackEnergy.energyStored() < energy.maxEnergy() && energy.maxExtract() > 0) {
                                        transferCharge.add(new EnergyReceiveComparator(stackEnergy, invStack));
                                    }
                                }
                            }
                        }

                        if (transferCharge.size() > 0) {
                            Collections.sort(transferCharge);
                            charge(energy, transferCharge);
                        }
                    }
                }
            }
        }
    }

    private void charge(IEnergy energyFrom, List<EnergyReceiveComparator> energyTo) {
        int chargeLeft = energyFrom.maxExtract();
        int size = energyTo.size();
        int chargeSplit = chargeLeft / size;

        for (EnergyReceiveComparator en: energyTo) {
            if (chargeLeft > 0 && chargeSplit > 0) {
                int maxEnergy = Math.min(chargeSplit, en.getEnergy().maxReceive());
                int distributed = energyFrom.extractEnergy(maxEnergy, false);

                en.getEnergy().receiveEnergy(distributed, false);

                CompoundTag tag = en.getStack().getOrCreateTag();
                tag.putInt("energyStored", en.getEnergy().energyStored());

                chargeLeft -= distributed;
                if (chargeLeft > 0 && --size > 0) {
                    chargeSplit = chargeLeft / size;
                } else {
                    chargeSplit = 0;
                }
            }
        }
    }
}
