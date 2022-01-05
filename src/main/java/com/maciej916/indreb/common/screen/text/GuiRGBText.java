package com.maciej916.indreb.common.screen.text;

import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class GuiRGBText extends GuiElement {

    private final Component component;
    private final int color;

    public GuiRGBText(IGuiWrapper wrapper, int width, int height, int leftOffset, int topOffset, Component component, int red, int green, int blue) {
        super(wrapper, width, height, leftOffset, topOffset);
        this.component = component;
        this.color = new Color(red, green, blue, 255).getRGB();

//        rgb = 0xFFFF * r + 0xFF * g + b;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        GuiUtil.renderScaled(pPoseStack, component.getString(), getLeftOffset(), getTopOffset(), 0.8f, color, false);
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
