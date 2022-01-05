package com.maciej916.indreb.common.item.impl.nano;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.item.base.SwordElectricItem;
import com.maciej916.indreb.common.registries.ModCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class ItemNanosaber extends SwordElectricItem {

    public ItemNanosaber() {
        super(Tiers.DIAMOND, 1, -3F, new Properties().setNoRepair().stacksTo(1), 0, 160000, EnergyType.RECEIVE, EnergyTier.ADVANCED);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (stack.getItem().equals(this)) {
            if (slot == EquipmentSlot.MAINHAND) {
                CompoundTag tag = stack.getTag();
                if (tag != null) {
                    Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
                    modifiers.putAll(super.getAttributeModifiers(slot, stack));

                    modifiers.removeAll(Attributes.ATTACK_DAMAGE);

                    if (tag.getBoolean("active")) {
                        modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Nano modifier", 19, AttributeModifier.Operation.ADDITION));
                    } else {
                        modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage() + getTier().getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
                    }

                    return modifiers;
                }
            }
        }
        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack.getOrCreateTag().getBoolean("reequip") != newStack.getOrCreateTag().getBoolean("reequip");
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        if (!level.isClientSide()) {
            CompoundTag tag = stack.getOrCreateTag();
            if (level.getGameTime() % 20 == 0) {
                stack.getCapability(ModCapabilities.ENERGY).ifPresent(energy -> {
                    if (tag.getBoolean("active")) {
                        energy.consumeEnergy(200, false);
                    }

                    if (energy.energyStored() == 0) {
                        tag.putBoolean("active", false);
                        stack.setTag(tag);
                    }
                });
            }

            tag.putBoolean("reequip", tag.getBoolean("active"));
            stack.setTag(tag);
        }
    }

    public static float isActivated(ItemStack stack) {
        if (stack.hasTag()) {
            CompoundTag tag = stack.getOrCreateTag();
            return tag.getBoolean("active") && tag.getBoolean("reequip") ? 1 : 0;
        } else {
            return 0;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (interactionHand == InteractionHand.MAIN_HAND) {
            ItemStack stack = player.getItemInHand(interactionHand);

            if (stack.hasTag()) {
                CompoundTag tag = stack.getOrCreateTag();
                int energy = getEnergy(stack).energyStored();
                if (energy > 0) {
                    boolean active = tag.getBoolean("active");
                    tag.putBoolean("active", !active);
                    stack.setTag(tag);
                }
            } else {
                CompoundTag tag = stack.getOrCreateTag();
                tag.putBoolean("active", true);
                stack.setTag(tag);
            }
        }

        return super.use(level, player, interactionHand);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, Player player) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("active", false);
        stack.setTag(tag);
        return super.onDroppedByPlayer(stack, player);
    }
}
