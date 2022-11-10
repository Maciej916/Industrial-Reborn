package com.maciej916.indreb.common.block.impl.machines.scanner.screen;

import com.maciej916.indreb.common.block.impl.machines.scanner.BlockEntityScanner;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.button.GuiButton;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class GuiScannerClearPattern extends GuiButton {

    BlockEntityScanner entity;

    public GuiScannerClearPattern(IGuiWrapper wrapper, int leftOffset, int topOffset, BlockEntityScanner blockEntityScanner, Runnable leftClick) {
        super(wrapper, leftOffset, topOffset, GuiSprite.SCANNER_CLEAR, leftClick, null);
        this.entity = blockEntityScanner;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        if (entity.getMode().getId() >= 4) {
            super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
        }
    }

    @Override
    protected boolean onLeftClick() {
        if (entity.getMode().getId() >= 4) {
            super.onLeftClick();
        }

        return false;
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused() && entity.getMode().getId() >= 4) {
            screen.renderTooltip(pPoseStack, EnumLang.CLEAR_PATTERN.getTranslationComponent(), pMouseX, pMouseY);
        }
    }
}