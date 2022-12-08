package com.maciej916.indreb.common.api.blockitem;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.interfaces.block.IElectricMachine;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockItemElectric extends IndRebBlockItem {

    public BlockItemElectric(Block block, Rarity rarity) {
        super(block, rarity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> componentList, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, componentList, tooltipFlag);

        CompoundTag tag = itemStack.getOrCreateTag();
        if (tag.get("BlockEntityTag") instanceof CompoundTag ct) {
            int energy = ct.getInt("energy_stored");
            if (energy > 0) {
                if (getBlock() instanceof IElectricMachine bem) {
                    EnergyTier energyTier = bem.getEnergyTier();

                    componentList.add(TextComponentUtil.build(
                            Component.translatable(EnumLang.STORED.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                            Component.translatable(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(energy, Screen.hasShiftDown())).withStyle(energyTier.getColor())
                    ));
                }
            }
        }
    }


}
