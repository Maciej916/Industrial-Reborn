package com.maciej916.indreb.common.screen.active;

import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;

public class GuiActive extends GuiElement {

    boolean active;
    GuiSprite progressType;

    public GuiActive(IGuiWrapper wrapper, GuiSprite progressType, int leftOffset, int topOffset, boolean active) {
        super(wrapper, progressType.getWidth(), progressType.getHeight(), leftOffset, topOffset);
        this.active = active;
        this.progressType = progressType;
    }

    public GuiSprite getProgressType() {
        return progressType;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {

        RenderSystem.setShaderTexture(0, getResourceLocation());
        if (active) {
            blit(pPoseStack, getLeftOffset(), getTopOffset(), progressType.getActiveOffsetLeft(), progressType.getActiveOffsetTop(), progressType.getActiveWidth(), progressType.getActiveHeight());
        } else {
            blit(pPoseStack, getLeftOffset(), getTopOffset(), progressType.getOffsetLeft(), progressType.getOffsetTop(), progressType.getWidth(), progressType.getHeight());
        }

        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }
}
