package com.maciej916.indreb.common.api.item.base;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.energy.BasicEnergyStorageProvider;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseElectricItem extends BaseItem implements IElectricItem {

    private final int energyStored;
    private final int maxEnergy;
    private final EnergyType energyType;
    private final EnergyTier energyTier;

    public BaseElectricItem(Properties properties, int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        super(null, properties.setNoRepair().stacksTo(1));
        this.energyStored = energyStored;
        this.maxEnergy = maxEnergy;
        this.energyType = energyType;
        this.energyTier = energyTier;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new BasicEnergyStorageProvider(energyStored, maxEnergy, energyType, energyTier);
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
    public IEnergyStorage getEnergyStorage(ItemStack stack) {
        return CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY).getValue();
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
    public int getBarColor(ItemStack pStack) {
        return Mth.hsvToRgb(0, 1.0F, 1.0F);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
        if (allowedIn(tab)) {
            list.add(new ItemStack(this));

            ItemStack full = new ItemStack(this);
            IEnergyStorage cap = CapabilityUtil.getCapabilityHelper(full, ModCapabilities.ENERGY).getValue();

            if (cap != null) {
                cap.setEnergy(cap.maxEnergy());
                full.getOrCreateTag().putInt("energyStored", cap.maxEnergy());
                full.getOrCreateTag().putInt("maxEnergy", cap.maxEnergy());
            }

            list.add(full);
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        pTooltipComponents.add(TextComponentUtil.build(
                Component.translatable(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(energyTier.getLang().getTranslationKey()).withStyle(energyTier.getColor())
        ));

        int energyStored = CapabilityUtil.getCapabilityHelper(pStack, ModCapabilities.ENERGY).getIfPresentElse(IEnergyStorage::energyStored, 0);
        pTooltipComponents.add(TextComponentUtil.build(
                Component.translatable(EnumLang.STORED.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(energyStored, Screen.hasShiftDown())).withStyle(energyTier.getColor()),
                Component.literal(" / ").withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(maxEnergy, Screen.hasShiftDown())).withStyle(energyTier.getColor())
        ));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        stack.getCapability(ModCapabilities.ENERGY).ifPresent(cap -> {
            nbt.putInt("energyStored", cap.energyStored());
            nbt.putInt("maxEnergy", cap.maxEnergy());
        });
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, @org.jetbrains.annotations.Nullable CompoundTag nbt) {
        if (nbt != null) {
            stack.getCapability(ModCapabilities.ENERGY).ifPresent(cap -> {
                cap.setEnergy(nbt.getInt("energyStored"));
                cap.setMaxEnergy(nbt.getInt("maxEnergy"));
            });
        }
        super.readShareTag(stack, nbt);
    }

    public static float getChargeRatio(ItemStack stack) {
        return CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY).getIfPresentElse(e -> (float) e.energyStored() / e.maxEnergy(), 0f);
    }

    public static int getChargeRatioModel(ItemStack stack) {
        float chargeRatio = getChargeRatio(stack);
        if (chargeRatio == 1) return 4;
        if (chargeRatio >= 0.75) return 3;
        if (chargeRatio >= 0.5) return 2;
        if (chargeRatio > 0) return 1;
        return 0;
    }
}
