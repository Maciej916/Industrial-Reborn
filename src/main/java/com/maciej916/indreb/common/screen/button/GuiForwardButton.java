package com.maciej916.indreb.common.screen.button;

import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;

public class GuiForwardButton extends GuiButton {

    public GuiForwardButton(IGuiWrapper wrapper, int leftOffset, int topOffset, Runnable leftClick) {
        super(wrapper, leftOffset, topOffset, GuiSprite.SMALL_BUTTON, leftClick, null);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);

        RenderSystem.setShaderTexture(0, getResourceLocation());
        GuiSprite sprite = GuiSprite.FORWARD_ICON;

        blit(pPoseStack, getLeftOffset() + sprite.getRenderOffsetLeft(), getTopOffset() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }
}
