package com.maciej916.indreb.common.block.impl.machines.metal_former.screen;

import com.maciej916.indreb.common.block.impl.machines.metal_former.BlockEntityMeralFormer;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.button.GuiButton;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static com.maciej916.indreb.IndReb.MODID;

public class GuiMetalFormerMode extends GuiButton {

    BlockEntityMeralFormer entity;

    public GuiMetalFormerMode(IGuiWrapper wrapper, int leftOffset, int topOffset, BlockEntityMeralFormer entity, Runnable leftClick) {
        super(wrapper, leftOffset, topOffset, GuiSprite.LARGE_BUTTON, leftClick, null);
        this.entity = entity;
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderTooltip(pPoseStack, Component.translatable("gui." + MODID + ".change_mode"), pMouseX, pMouseY);
        }

        super.renderWidgetToolTip(screen, pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);

        RenderSystem.setShaderTexture(0, getResourceLocation());
        GuiSprite sprite = GuiSprite.TRANSFORMER_ICON;

        blit(pPoseStack, getLeftOffset() + sprite.getRenderOffsetLeft(), getTopOffset() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }
}
