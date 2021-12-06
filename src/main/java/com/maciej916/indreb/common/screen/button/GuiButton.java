package com.maciej916.indreb.common.screen.button;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class GuiButton extends GuiElement {

    private final GuiSprite buttonSprite;

    private final Runnable onLeftClick;
    private final Runnable onRightClick;

    public GuiButton(IGuiWrapper wrapper, int leftOffset, int topOffset, GuiSprite buttonSprite, @Nullable Runnable onLeftClick, @Nullable Runnable onRightClick) {
        super(wrapper, buttonSprite.getWidth(), buttonSprite.getHeight(), leftOffset, topOffset);
        this.buttonSprite = buttonSprite;
        this.onLeftClick = onLeftClick;
        this.onRightClick = onRightClick;
    }

    public GuiSprite getButtonSprite() {
        return buttonSprite;
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (this.active && this.visible) {
            boolean flag = this.clicked(pMouseX, pMouseY);
            if (flag) {
                if (pButton == 0) {
                    return onLeftClick();
                } else if (pButton == 1) {
                    return onRightClick();
                }
            }
        }
        return false;
    }

    private boolean onLeftClick() {
        if (onLeftClick != null) {
            onLeftClick.run();
            playDownSound(Minecraft.getInstance().getSoundManager());
            return true;
        }

        return false;
    }

    protected boolean onRightClick() {
        if (onRightClick != null) {
            onRightClick.run();
            return true;
        }

        return false;
    }


    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, getResourceLocation());
        if (isHovered()) {
            blit(pPoseStack, getLeftOffset(), getTopOffset(), buttonSprite.getActiveOffsetLeft(), buttonSprite.getActiveOffsetTop(), buttonSprite.getActiveWidth(), buttonSprite.getActiveHeight());
        } else {
            blit(pPoseStack, getLeftOffset(), getTopOffset(), buttonSprite.getOffsetLeft(), buttonSprite.getOffsetTop(), buttonSprite.getWidth(), buttonSprite.getHeight());
        }

        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/buttons.png");
    }

}
