package com.maciej916.indreb.common.api.screen.widget.button;

import com.maciej916.indreb.common.api.blockentity.interfaces.IGetEnabled;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseButtonWidget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.util.List;

public class GuiPlayPauseButtonWidget extends BaseButtonWidget {

    private final IGetEnabled enabled;
    private final List<Component> tooltip;

    public GuiPlayPauseButtonWidget(IGuiHelper helper, int x, int y, IGetEnabled enabled, @Nullable Runnable onLeftClick, List<Component> tooltip) {
        super(helper, x, y, GuiSprite.SMALL_BUTTON, onLeftClick, null);
        this.enabled = enabled;
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
        GuiSprite sprite = enabled.getEnabled() ?  GuiSprite.PAUSE_ICON : GuiSprite.PLAY_ICON;

        blit(pPoseStack, getX() + sprite.getRenderOffsetLeft(), getY() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }
}
