package com.maciej916.indreb.common.item;

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
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public class ItemNanosaber extends IndRebSword implements IElectricItem {

    private final int energyStored;
    private final int maxEnergy;
    private final EnumEnergyType energyType;
    private final EnergyTier energyTier;

    public ItemNanosaber(int pAttackDamageModifier, float pAttackSpeedModifier, int energyStored, int maxEnergy, EnumEnergyType energyType, EnergyTier energyTier) {
        super(Tiers.DIAMOND, pAttackDamageModifier, pAttackSpeedModifier);
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
    public boolean isRepairable(ItemStack stack) {
        return false;
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
}
