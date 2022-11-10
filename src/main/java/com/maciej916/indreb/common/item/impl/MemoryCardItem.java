package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.capabilities.scan_result.IScannerResult;
import com.maciej916.indreb.common.capabilities.scan_result.ScannerResult;
import com.maciej916.indreb.common.capabilities.scan_result.ScannerResultHandler;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.item.base.BaseItem;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public class MemoryCardItem extends BaseItem {

    public static final Rarity MEMORY_RARITY = Rarity.create("memory_rarity", ChatFormatting.DARK_PURPLE);

    public MemoryCardItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ScannerResultHandler(stack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        ScannerResult scannerResult = CapabilityUtil.getCapabilityHelper(pStack, ModCapabilities.SCANNER_RESULT).getIfPresentElse(IScannerResult::getResult, new ScannerResult());

        if (scannerResult.getResultStack().isEmpty()) {
            pTooltipComponents.add(TextComponentUtil.build(
                    Component.literal("< ").withStyle(ChatFormatting.GRAY),
                    EnumLang.REPLICATION_EMPTY.getTranslationComponent(),
                    Component.literal(" >").withStyle(ChatFormatting.GRAY)
            ));
        } else {
            pTooltipComponents.add(TextComponentUtil.build(
                    Component.literal("< ").withStyle(ChatFormatting.GRAY),
                    Component.literal(scannerResult.getResultStack().getItem().getName(scannerResult.getResultStack()).getString()),
                    Component.literal(" >").withStyle(ChatFormatting.GRAY)
            ));

            if (scannerResult.getMatterCost() > 0) {
                pTooltipComponents.add(TextComponentUtil.build(
                        Component.translatable(EnumLang.MATTER_COST.getTranslationKey()).withStyle(ChatFormatting.DARK_GRAY),
                        Component.literal(" " + scannerResult.getMatterCost() + " mB").withStyle(ChatFormatting.GRAY)
                ));
            }

            if (scannerResult.getEnergyCost() > 0) {
                pTooltipComponents.add(TextComponentUtil.build(
                        Component.translatable(EnumLang.ENERGY_COST.getTranslationKey()).withStyle(ChatFormatting.DARK_GRAY),
                        Component.literal(" " + TextComponentUtil.getFormattedEnergyUnit(scannerResult.getEnergyCost()) + " IE/t").withStyle(ChatFormatting.GRAY)
                ));
            }
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }


    @Override
    public Rarity getRarity(ItemStack stack) {
        return MEMORY_RARITY;
    }


    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        ScannerResult result = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.SCANNER_RESULT).getIfPresentElse(IScannerResult::getResult, new ScannerResult());
        nbt.put("result", result.serializeNBT());
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, @org.jetbrains.annotations.Nullable CompoundTag nbt) {
        if (nbt != null) {
            if (CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.SCANNER_RESULT).isPresent()) {
                ScannerResult scannerResult = new ScannerResult();
                scannerResult.deserializeNBT(nbt.getCompound("result"));
                CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.SCANNER_RESULT).getValue().setResult(scannerResult);
            }
        }
        super.readShareTag(stack, nbt);
    }

}
