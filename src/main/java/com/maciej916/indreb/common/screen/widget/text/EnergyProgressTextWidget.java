package com.maciej916.indreb.common.screen.widget.text;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class EnergyProgressTextWidget extends BaseWidget {

    private IBaseProgress progress;
    private float scale;
    private int color;
    private boolean shadow;

    public EnergyProgressTextWidget(IGuiHelper helper, int x, int y, int width, int height, IBaseProgress progress, float scale, int color, boolean shadow) {
        super(helper, x, y, width, height);
        this.progress = progress;
        this.scale = scale;
        this.color = color;
        this.shadow = shadow;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        MutableComponent component = Component.translatable("gui." + IndReb.MODID + ".energy", TextComponentUtil.getFormattedStorageUnit(progress.currentProgress()), TextComponentUtil.getFormattedStorageUnit(progress.getProgressMax()));
        GuiUtil.renderScaled(pPoseStack, component.getString(), getX(), getY(), scale, color, shadow);
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
