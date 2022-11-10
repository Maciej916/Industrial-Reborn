package com.maciej916.indreb.common.block.impl.battery_box;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.energy.impl.BasicEnergyStorage;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.text.GuiTextElectricProgress;
import com.maciej916.indreb.common.screen.widgets.GuiText;
import com.maciej916.indreb.common.tier.BatteryBoxTier;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenBatteryBox extends BetterScreen<MenuBatteryBox> {

    public ScreenBatteryBox(MenuBatteryBox container, Inventory inv, Component name) {
        super(container, inv, name);
        this.topPos = 0;
        this.imageHeight = 198;
        this.inventoryLabelY = 104;
    }

    @Override
    public void init() {
        super.init();

        BasicEnergyStorage energyStorage = getBlockEntity().getEnergyStorage();
        BlockBatteryBox block = (BlockBatteryBox) getBlockEntity().getBlock();
        BatteryBoxTier batteryBoxTier = block.getBatteryBoxTier();

        addRenderableOnlyComponent(new GuiTextElectricProgress(this, 50, 10, 90, 24, energyStorage));
        addRenderableOnlyComponent(new GuiText(this, 50, 10, 90, 58, TextComponentUtil.build(
                Component.translatable(EnumLang.TRANSFER.getTranslationKey()),
                Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(batteryBoxTier.getEnergyTier().getBasicTransfer()))
        )));

        drawComponents(true);
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
