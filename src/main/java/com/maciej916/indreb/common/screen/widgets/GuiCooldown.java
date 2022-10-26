package com.maciej916.indreb.common.screen.widgets;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.interfaces.entity.ICooldown;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GuiCooldown extends GuiElement {

    private final ICooldown be = (ICooldown) getWrapper().getBlockEntity();

    public GuiCooldown(IGuiWrapper wrapper) {
        super(wrapper, 20, 16, 156, -15);
    }


    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            if (be.getCooldown() > 0) {
                Minecraft.getInstance().screen.renderTooltip(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".can_operate_in", be.getCooldown()), pMouseX, pMouseY);
            } else {
                Minecraft.getInstance().screen.renderTooltip(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".can_operate"), pMouseX, pMouseY);
            }
        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, getResourceLocation());
        if (be.getCooldown() == 0) {
            blit(pPoseStack, getLeftOffset(), getTopOffset(), 0, 100, getWidth(), getHeight());
        } else {
            blit(pPoseStack, getLeftOffset(), getTopOffset(), 0, 117, getWidth(), getHeight());
        }

        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

    @Override
    public @NotNull ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    }
}
