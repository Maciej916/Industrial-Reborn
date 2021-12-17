package com.maciej916.indreb.common.screen.widgets;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class GuiUpgrades extends GuiElement {

    public GuiUpgrades(IGuiWrapper wrapper) {
        super(wrapper, 24, 80, 175, 4);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, getResourceLocation());
        blit(pPoseStack, getLeftOffset(), getTopOffset(), 0, 134, getWidth(), getHeight());
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    }
}
