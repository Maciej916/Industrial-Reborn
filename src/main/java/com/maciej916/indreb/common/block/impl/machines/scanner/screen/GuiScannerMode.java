package com.maciej916.indreb.common.block.impl.machines.scanner.screen;

import com.maciej916.indreb.common.block.impl.machines.scanner.BlockEntityScanner;
import com.maciej916.indreb.common.enums.ScannerMode;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

public class GuiScannerMode extends GuiElement {

    private final BlockEntityScanner entity;

    public GuiScannerMode(IGuiWrapper wrapper, BlockEntityScanner blockEntityScanner) {
        super(wrapper, 115, 6, 10, 61);
        this.entity = blockEntityScanner;
    }


    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {

        String string;
        if (entity.getMode() == ScannerMode.PROGRESS) {
            string = Component.translatable(entity.getMode().getLang(), entity.progress.getPercentProgressString()).getString() + "%";
        } else {
            string = Component.translatable(entity.getMode().getLang()).getString();
        }

        GuiUtil.renderScaled(pPoseStack, string, getLeftOffset(), getTopOffset(), 0.8f, entity.getMode().getColor(), false);
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
