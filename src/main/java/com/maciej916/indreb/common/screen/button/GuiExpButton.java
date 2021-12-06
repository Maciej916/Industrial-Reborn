package com.maciej916.indreb.common.screen.button;

import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;

import static com.maciej916.indreb.IndReb.MODID;

public class GuiExpButton extends GuiButton {

    public GuiExpButton(IGuiWrapper wrapper, IExpCollector leftClick) {
        super(wrapper, -19, 5, GuiSprite.LEFT_BUTTON, leftClick.collectExp(), null);
    }

    @Override
    public void renderToolTip(PoseStack pPoseStack, int pMouseX, int pMouseY) {

        Minecraft.getInstance().screen.renderTooltip(pPoseStack, new TranslatableComponent("gui." + MODID + ".collect_exp"), pMouseX, pMouseY);

        super.renderToolTip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);

        RenderSystem.setShaderTexture(0, getResourceLocation());
        GuiSprite sprite = GuiSprite.EXP_ICON;

        blit(pPoseStack, getLeftOffset() + sprite.getRenderOffsetLeft(), getTopOffset() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }
}
