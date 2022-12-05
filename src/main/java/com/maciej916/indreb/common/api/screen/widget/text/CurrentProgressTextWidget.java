package com.maciej916.indreb.common.api.screen.widget.text;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;

public class CurrentProgressTextWidget extends BaseWidget {

    private final IBaseProgress progress;
    private final String prepend;
    private final String append;
    private float scale;
    private int color;
    private boolean shadow;

    public CurrentProgressTextWidget(IGuiHelper helper, int x, int y, int width, int height, IBaseProgress progress, String prepend, String append, float scale, int color, boolean shadow) {
        super(helper, x, y, width, height);
        this.progress = progress;
        this.prepend = prepend;
        this.append = append;
        this.scale = scale;
        this.color = color;
        this.shadow = shadow;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        GuiUtil.renderScaled(pPoseStack, prepend + (int) progress.getCurrentProgress() + append, getX(), getY(), scale, color, shadow);
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }

}
