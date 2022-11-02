package com.maciej916.indreb.common.screen.widgets;

import com.maciej916.indreb.common.interfaces.entity.IProgress;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;

public class GuiTextCurrentProgress extends GuiElement {

    private final IProgress progress;
    private final String prepend;
    private final String append;

    public GuiTextCurrentProgress(IGuiWrapper wrapper, int width, int height, int leftOffset, int topOffset, IProgress progress, String prepend, String append) {
        super(wrapper, width, height, leftOffset, topOffset);
        this.progress = progress;
        this.prepend = prepend;
        this.append = append;
    }


    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        int currProgress = (int) progress.getProgress();
        GuiUtil.renderScaled(pPoseStack, prepend + currProgress + append, getLeftOffset(), getTopOffset(), 0.8f, 4210752, false);
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
