package com.maciej916.indreb.common.item.block;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.block.IElectricMachine;
import com.maciej916.indreb.common.registries.ModItemGroups;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockItemElectric extends BlockItem {

    public BlockItemElectric(Block block) {
        super(block, new Item.Properties().tab(ModItemGroups.MAIN_ITEM_GROUP));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> componentList, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, componentList, tooltipFlag);

        CompoundTag tag = itemStack.getOrCreateTag();
        if (tag.get("BlockEntityTag") instanceof CompoundTag ct) {
            int energy = ct.getInt("energy");
            if (energy > 0) {
                if (getBlock() instanceof IElectricMachine bem) {
                    EnergyTier energyTier = bem.getEnergyTier();

                    componentList.add(TextComponentUtil.build(
                            new TranslatableComponent(EnumLang.STORED.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                            new TranslatableComponent(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(energy)).withStyle(energyTier.getColor())
                    ));
                }
            }
        }
    }
}
