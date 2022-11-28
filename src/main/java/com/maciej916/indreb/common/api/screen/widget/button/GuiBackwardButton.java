package com.maciej916.indreb.common.api.screen.widget.button;

import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseButtonWidget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GuiBackwardButton extends BaseButtonWidget {

    List<Component> tooltip;

    public GuiBackwardButton(IGuiHelper helper, int x, int y, @Nullable Runnable onLeftClick, List<Component> tooltip) {
        super(helper, x, y, GuiSprite.SMALL_BUTTON, onLeftClick, null);
        this.tooltip = tooltip;
    }

    @Override
    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            if (tooltip != null) {
                screen.renderComponentTooltip(pPoseStack, tooltip, pMouseX, pMouseY);
            }
        }
        super.renderToolTip(screen, pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);

        RenderSystem.setShaderTexture(0, getResourceLocation());
        GuiSprite sprite = GuiSprite.BACKWARD_ICON;

        blit(pPoseStack, getX() + sprite.getRenderOffsetLeft(), getY() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }

}
