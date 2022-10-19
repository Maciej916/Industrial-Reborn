package com.maciej916.indreb.common.screen.widgets;

import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

public class GuiText extends GuiElement {

    private final Component component;

    public GuiText(IGuiWrapper wrapper, int width, int height, int leftOffset, int topOffset, Component component) {
        super(wrapper, width, height, leftOffset, topOffset);
        this.component = component;
    }


    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {

//        Minecraft minecraft = Minecraft.getInstance();
//        Font font = minecraft.font;

        GuiUtil.renderScaled(pPoseStack, component.getString(), getLeftOffset(), getTopOffset(), 0.8f, 4210752, false);

//        drawString(pPoseStack, font, component, getLeftOffset(), getTopOffset(), getFGColor() | Mth.ceil(this.alpha * 255.0F) << 24);

        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
