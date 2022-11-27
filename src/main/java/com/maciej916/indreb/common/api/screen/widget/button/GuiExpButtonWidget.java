package com.maciej916.indreb.common.api.screen.widget.button;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseButtonWidget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.text.DecimalFormat;
import java.util.List;

public class GuiExpButtonWidget extends BaseButtonWidget {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final IHasExp expCollector;

    public GuiExpButtonWidget(IGuiHelper helper, int y, IHasExp expCollector) {
        super(helper, -19, y, GuiSprite.LEFT_BUTTON, expCollector.collectExpClient(), null);
        this.expCollector = expCollector;
    }

    @Override
    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderComponentTooltip(pPoseStack,
                    List.of(
                            Component.translatable("gui." + IndReb.MODID + ".collect_exp").withStyle(ChatFormatting.GREEN),
                            Component.literal(df.format(expCollector.getStoredExperience()) + " EXP")
                    ), pMouseX, pMouseY);
        }
        super.renderToolTip(screen, pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);

        RenderSystem.setShaderTexture(0, getResourceLocation());
        GuiSprite sprite = GuiSprite.EXP_ICON;

        blit(pPoseStack, getX() + sprite.getRenderOffsetLeft(), getY() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }
}
