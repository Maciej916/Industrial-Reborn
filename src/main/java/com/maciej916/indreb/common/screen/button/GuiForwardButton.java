package com.maciej916.indreb.common.screen.button;

import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public class GuiForwardButton extends GuiButton {

    List<Component> tooltip;

    public GuiForwardButton(IGuiWrapper wrapper, int leftOffset, int topOffset, Runnable leftClick, List<Component> tooltip) {
        super(wrapper, leftOffset, topOffset, GuiSprite.SMALL_BUTTON, leftClick, null);
        this.tooltip = tooltip;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);

        RenderSystem.setShaderTexture(0, getResourceLocation());
        GuiSprite sprite = GuiSprite.FORWARD_ICON;

        blit(pPoseStack, getLeftOffset() + sprite.getRenderOffsetLeft(), getTopOffset() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            if (tooltip != null) {
                screen.renderComponentTooltip(pPoseStack, tooltip, pMouseX, pMouseY);
            }
        }
    }
}
