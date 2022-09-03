package com.maciej916.indreb.common.screen.text;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.interfaces.entity.IProgress;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.TranslatableComponent;

public class GuiTextHeat extends GuiElement {

    private final IProgress progress;

    public GuiTextHeat(IGuiWrapper wrapper, int width, int height, int leftOffset, int topOffset, IProgress progress) {
        super(wrapper, width, height, leftOffset, topOffset);
        this.progress = progress;
    }


    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        TranslatableComponent component = new TranslatableComponent("gui." + IndReb.MODID + ".heat", progress.getPercentProgressString() + "%");
        GuiUtil.renderScaled(pPoseStack, component.getString(), getLeftOffset(), getTopOffset(), 0.8f, 4210752, false);
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
