package com.maciej916.indreb.common.block.impl.charge_pad;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.widget.text.EnergyProgressTextWidget;
import com.maciej916.indreb.common.api.screen.widget.text.SimpleTextWidget;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenChargePad extends IndRebScreen<MenuChargePad> {

    BlockEntityChargePad entity;

    public ScreenChargePad(MenuChargePad menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityChargePad) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new EnergyProgressTextWidget(this, 90, 24, 50, 10, entity.getEnergyStorage(), 0.8f, 4210752, false));
        addRenderableOnlyWidget(new SimpleTextWidget(this, 90, 58, 50, 10, TextComponentUtil.build(Component.translatable(EnumLang.TRANSFER.getTranslationKey()), Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(entity.getChargePadTier().getEnergyTier().getBasicTransfer()))), 0.8f, 4210752, false));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/charge_pad.png");
    }
}
