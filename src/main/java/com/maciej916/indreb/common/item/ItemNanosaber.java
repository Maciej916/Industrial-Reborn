package com.maciej916.indreb.common.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.maciej916.indreb.common.energy.impl.CapEnergyStorage;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.LazyOptionalHelper;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class ItemNanosaber extends IndRebSword implements IElectricItem {

    private final int energyStored;
    private final int maxEnergy;
    private final EnumEnergyType energyType;
    private final EnergyTier energyTier;
    private final int baseAttackDamage;

    public ItemNanosaber(int pAttackDamageModifier, float pAttackSpeedModifier, int energyStored, int maxEnergy, EnumEnergyType energyType, EnergyTier energyTier) {
        super(Tiers.DIAMOND, pAttackDamageModifier, pAttackSpeedModifier);
        this.baseAttackDamage = pAttackDamageModifier;
        this.energyStored = energyStored;
        this.maxEnergy = maxEnergy;
        this.energyType = energyType;
        this.energyTier = energyTier;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new CapEnergyStorage(energyStored, maxEnergy, energyTier.getBasicTransfer(), energyTier.getBasicTransfer(), energyType);
    }
    
    public EnumEnergyType getEnergyType() {
        return energyType;
    }

    @Override
    public EnergyTier getEnergyTier() {
        return energyTier;
    }

    @Override
    public IEnergy getEnergy(ItemStack stack) {
        return CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY).getValue();
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    private int getCharge(ItemStack stack) {
        LazyOptionalHelper<IEnergy> cap = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY);
        return cap.getIfPresentElse(IEnergy::energyStored, 0);
    }

    private float getChargeRatio(ItemStack stack) {
        LazyOptionalHelper<IEnergy> cap = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY);
        return cap.getIfPresentElse(e -> (float) e.energyStored() / e.maxEnergy(), 0f);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return Math.round(13.0F - ((1 - getChargeRatio(pStack)) * 13.0F));
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
        if (allowdedIn(tab)) {
            list.add(new ItemStack(this));

            ItemStack full = new ItemStack(this);
            LazyOptionalHelper<IEnergy> cap = CapabilityUtil.getCapabilityHelper(full, ModCapabilities.ENERGY);
            cap.getIfPresent(e -> e.setEnergy(e.maxEnergy()));

            list.add(full);
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        pTooltipComponents.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(energyTier.getLang().getTranslationKey()).withStyle(energyTier.getColor())
        ));

        int energyStored = CapabilityUtil.getCapabilityHelper(pStack, ModCapabilities.ENERGY).getIfPresentElse(IEnergy::energyStored, 0);
        pTooltipComponents.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.STORED.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(energyStored)).withStyle(energyTier.getColor()),
                new TextComponent(" / ").withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(maxEnergy)).withStyle(energyTier.getColor())
        ));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return false;
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
                        modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", baseAttackDamage + getTier().getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
                    }

                    return modifiers;
                }
            }
        }
        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
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
                int energy = getCharge(stack);
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

    @org.jetbrains.annotations.Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        int energyStored = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY).getIfPresentElse(IEnergy::energyStored, 0);
        nbt.putInt("energyStored", energyStored);
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, @org.jetbrains.annotations.Nullable CompoundTag nbt) {
        if (nbt != null) {
            CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY).ifPresent(iEnergy -> {
                iEnergy.setEnergy(nbt.getInt("energyStored"));
            });
        }
        super.readShareTag(stack, nbt);
    }
}
