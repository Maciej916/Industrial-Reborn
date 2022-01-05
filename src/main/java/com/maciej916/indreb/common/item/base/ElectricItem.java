package com.maciej916.indreb.common.item.base;

import com.maciej916.indreb.common.energy.impl.CapEnergyStorage;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public class ElectricItem extends BaseItem implements IElectricItem {

    private final int energyStored;
    private final int maxEnergy;
    private final EnergyType energyType;
    private final EnergyTier energyTier;

    public ElectricItem(Properties properties, int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        super(properties.setNoRepair().stacksTo(1));
        this.energyStored = energyStored;
        this.maxEnergy = maxEnergy;
        this.energyType = energyType;
        this.energyTier = energyTier;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new CapEnergyStorage(energyStored, maxEnergy, energyType, energyTier);
    }

    @Override
    public EnergyTier getEnergyTier() {
        return energyTier;
    }

    @Override
    public EnergyType getEnergyType() {
        return energyType;
    }

    @Override
    public IEnergy getEnergy(ItemStack stack) {
        return CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY).getValue();
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return Math.round(13.0F - ((1 - getChargeRatio(pStack)) * 13.0F));
    }

    public int getBarColor(ItemStack pStack) {
        return Mth.hsvToRgb(0, 1.0F, 1.0F);
    }

    public static float getChargeRatio(ItemStack stack) {
        return CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY).getIfPresentElse(e -> (float) e.energyStored() / e.maxEnergy(), 0f);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
        if (allowdedIn(tab)) {
            list.add(new ItemStack(this));

            ItemStack full = new ItemStack(this);
            IEnergy cap = CapabilityUtil.getCapabilityHelper(full, ModCapabilities.ENERGY).getValue();

            if (cap != null) {
                cap.setEnergy(cap.maxEnergy());
                full.getOrCreateTag().putInt("energyStored", cap.maxEnergy());
            }

            list.add(full);
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    public static int getChargeRatioModel(ItemStack stack) {
        float chargeRatio = getChargeRatio(stack);
        if (chargeRatio == 1) return 4;
        if (chargeRatio >= 0.75) return 3;
        if (chargeRatio >= 0.5) return 2;
        if (chargeRatio > 0) return 1;
        return 0;
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
}
