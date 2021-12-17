package com.maciej916.indreb.common.item;

import com.maciej916.indreb.common.energy.impl.CapEnergyStorage;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.registries.ModItemGroups;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.LazyOptionalHelper;
import com.maciej916.indreb.common.util.TextComponentUtil;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemEnergy extends Item implements IElectricItem {

    int energyStored;
    int maxEnergy;
    private final EnumEnergyType energyType;
    EnergyTier energyTier;

    public ItemEnergy(int energyStored, int maxEnergy, EnumEnergyType energyType, EnergyTier energyTier) {
        super(new Properties().tab(ModItemGroups.MAIN_ITEM_GROUP).stacksTo(1));
        this.energyStored = energyStored;
        this.maxEnergy = maxEnergy;
        this.energyType = energyType;
        this.energyTier = energyTier;
    }

//    public static ItemStack getSubtype(ItemStack stack) {
////        ItemStack full = new ItemStack(stack);
////        LazyOptionalHelper<IEnergy> cap = CapabilityUtil.getCapabilityHelper(full, ModCapabilities.ENERGY);
////        cap.getIfPresent(e -> e.setEnergy(e.maxEnergy()));
//        return stack;
//    }

//    @Nonnull
//    public static String getSubtype(ItemStack stack) {
//        return stacktack.getOrCreateTag().getInt("energyStored") ? "full" : "none");
//    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new CapEnergyStorage(energyStored, maxEnergy, energyTier.getBasicTransfer(), energyTier.getBasicTransfer(), energyType);
    }

    public EnergyTier getEnergyTier() {
        return energyTier;
    }

    public EnumEnergyType getEnergyType() {
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

    public static float getChargeRatio(ItemStack stack) {
        LazyOptionalHelper<IEnergy> cap = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.ENERGY);
        return cap.getIfPresentElse(e -> (float) e.energyStored() / e.maxEnergy(), 0f);
    }

    public static int getChargeRatioModel(ItemStack stack) {
        float chargeRatio = getChargeRatio(stack);
        if (chargeRatio == 1) return 4;
        if (chargeRatio >= 0.75) return 3;
        if (chargeRatio >= 0.5) return 2;
        if (chargeRatio > 0) return 1;
        return 0;
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

        pTooltipComponents.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.TRANSFER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(energyTier.getBasicTransfer())).withStyle(energyTier.getColor())
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
