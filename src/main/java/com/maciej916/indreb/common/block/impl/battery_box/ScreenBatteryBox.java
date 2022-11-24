package com.maciej916.indreb.common.block.impl.battery_box;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.text.EnergyProgressTextWidget;
import com.maciej916.indreb.common.api.screen.widget.text.SimpleTextWidget;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenBatteryBox extends IndRebScreen<MenuBatteryBox> {

    BlockEntityBatteryBox entity;

    public ScreenBatteryBox(MenuBatteryBox menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityBatteryBox) getEntity();
        this.imageHeight = 198;
        this.inventoryLabelY = 104;
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new EnergyProgressTextWidget(this, 90, 24, 50, 10, entity.getEnergyStorage(), 0.8f, 4210752, false));
        addRenderableOnlyWidget(new SimpleTextWidget(this, 90, 58, 50, 10, TextComponentUtil.build(Component.translatable(EnumLang.TRANSFER.getTranslationKey()), Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(entity.getBatteryBoxTier().getEnergyTier().getBasicTransfer()))), 0.8f, 4210752, false));
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        super.renderLabels(poseStack, mouseX, mouseY);
        this.font.draw(poseStack, EnumLang.ARMOUR.getTranslationComponent(), 8, 72, 4210752);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/battery_box.png");
    }
}
