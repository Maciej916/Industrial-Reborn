package com.maciej916.indreb.common.screen.button;

import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static com.maciej916.indreb.IndReb.MODID;

public class GuiExpButton extends GuiButton {

    IExpCollector expCollector;

    public GuiExpButton(IGuiWrapper wrapper, IExpCollector expCollector, int topOffset) {
        super(wrapper, -19, topOffset, GuiSprite.LEFT_BUTTON, expCollector.collectExp(), null);
        this.expCollector = expCollector;
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderComponentTooltip(pPoseStack,
                    List.of(
                            Component.translatable("gui." + MODID + ".collect_exp").withStyle(ChatFormatting.GREEN),
                            Component.literal(expCollector.getStoredExperience() + " EXP")
                    ), pMouseX, pMouseY);
        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);

        RenderSystem.setShaderTexture(0, getResourceLocation());
        GuiSprite sprite = GuiSprite.EXP_ICON;

        blit(pPoseStack, getLeftOffset() + sprite.getRenderOffsetLeft(), getTopOffset() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }
}
