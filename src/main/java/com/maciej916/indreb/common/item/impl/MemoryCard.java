package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.api.item.base.BaseItem;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.scanner.IScannerResult;
import com.maciej916.indreb.common.capability.scanner.ScannerResult;
import com.maciej916.indreb.common.capability.scanner.ScannerResultHandler;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public class MemoryCard extends BaseItem {

    public static final Rarity MEMORY_RARITY = Rarity.create("memory_rarity", ChatFormatting.DARK_PURPLE);

    public MemoryCard() {
        super(CreativeModeTab.TAB_MATERIALS, new Item.Properties().stacksTo(1));
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
                        Component.literal(" " + TextComponentUtil.getFormattedStorageUnit(scannerResult.getMatterCost(), Screen.hasShiftDown()) + " mB").withStyle(ChatFormatting.GRAY)
                ));
            }

            if (scannerResult.getEnergyCost() > 0) {
                pTooltipComponents.add(TextComponentUtil.build(
                        Component.translatable(EnumLang.ENERGY_COST.getTranslationKey()).withStyle(ChatFormatting.DARK_GRAY),
                        Component.literal(" " + TextComponentUtil.getFormattedStorageUnit(scannerResult.getEnergyCost(), Screen.hasShiftDown()) + " IE/t").withStyle(ChatFormatting.GRAY)
                ));
            }
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return MEMORY_RARITY;
    }


    @org.jetbrains.annotations.Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        stack.getCapability(ModCapabilities.SCANNER_RESULT).ifPresent(cap -> {
            nbt.put("result", cap.getResult().serializeNBT());
        });
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, @org.jetbrains.annotations.Nullable CompoundTag nbt) {
        if (nbt != null) {
            stack.getCapability(ModCapabilities.SCANNER_RESULT).ifPresent(cap -> {
                ScannerResult scannerResult = new ScannerResult();
                scannerResult.deserializeNBT(nbt.getCompound("result"));
                cap.setResult(scannerResult);
            });
        }
        super.readShareTag(stack, nbt);
    }

}
