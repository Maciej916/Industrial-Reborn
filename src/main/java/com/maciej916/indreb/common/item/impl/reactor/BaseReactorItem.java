package com.maciej916.indreb.common.item.impl.reactor;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.item.base.BaseItem;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.reactor.IReactorComponentCapability;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.LazyOptionalHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaseReactorItem extends BaseItem {

    protected boolean isBarVisible;
    protected final int maxDamage;
    protected final int maxHeat;

    public BaseReactorItem(Properties properties, int maxDamage, int maxHeat) {
        super(CreativeModeTab.TAB_MISC, properties);
        this.isBarVisible = maxDamage > 1 || maxHeat > 1;
        this.maxDamage = maxDamage;
        this.maxHeat = maxHeat;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return isBarVisible;
    }

    public int getBarWidth(ItemStack stack) {
        LazyOptionalHelper<IReactorComponentCapability> cap = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.REACTOR_ITEM);
        if (cap.isPresent()) {
            IReactorComponentCapability reactorItemCap = cap.getValue();
            if (reactorItemCap.getMaxDurability() > 1) {
                return Math.round(13.0F - ((float)reactorItemCap.getDurability() / (float)reactorItemCap.getMaxDurability()) * 13.0F);
            }
        }
        return super.getBarWidth(stack);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        LazyOptionalHelper<IReactorComponentCapability> cap = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.REACTOR_ITEM);
        if (cap.isPresent()) {
            IReactorComponentCapability reactorItemCap = cap.getValue();
            if (reactorItemCap.getMaxDurability() > 1) {
                float f = Math.max(0.0F, (reactorItemCap.getMaxDurability() - (float)reactorItemCap.getDurability()) / reactorItemCap.getMaxDurability());
                return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
            }
        }
        return super.getBarColor(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag isAdvanced) {
        LazyOptionalHelper<IReactorComponentCapability> cap = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.REACTOR_ITEM);
        if (cap.isPresent()) {
            IReactorComponentCapability reactorItemCap = cap.getValue();
            if (reactorItemCap.getMaxDurability() > 1) {
                int durability = reactorItemCap.getMaxDurability() - reactorItemCap.getDurability();
                components.add(Component.translatable("tooltip." + IndReb.MODID + ".durability", durability, reactorItemCap.getMaxDurability()).withStyle(ChatFormatting.GRAY));
            }
        }
        super.appendHoverText(stack, level, components, isAdvanced);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

}
