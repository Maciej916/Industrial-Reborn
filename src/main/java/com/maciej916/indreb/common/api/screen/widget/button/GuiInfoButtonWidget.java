package com.maciej916.indreb.common.api.screen.widget.button;

import com.maciej916.indreb.common.api.blockentity.interfaces.IHasUpgrades;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.enums.UpgradeType;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseButtonWidget;
import com.maciej916.indreb.common.enums.EnumLang;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class GuiInfoButtonWidget extends BaseButtonWidget {

    private final IHasUpgrades upgrades;

    public GuiInfoButtonWidget(IGuiHelper helper, int y, IHasUpgrades upgrades) {
        super(helper, -19, y, GuiSprite.LEFT_BUTTON, null, null);
        this.upgrades = upgrades;
    }

    @Override
    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            List<Component> elements = new ArrayList<>();
            elements.add(Component.translatable(EnumLang.SUPPORTED_UPGRADES.getTranslationKey()).withStyle(ChatFormatting.AQUA));
            for (UpgradeType ut: upgrades.getSupportedUpgrades()) {
                elements.add(ut.getLang().getTranslationComponent());
            }
            screen.renderComponentTooltip(pPoseStack, elements, pMouseX, pMouseY);
        }

        super.renderToolTip(screen, pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);

        RenderSystem.setShaderTexture(0, getResourceLocation());
        GuiSprite sprite = GuiSprite.INFO_ICON;

        blit(pPoseStack, getX() + sprite.getRenderOffsetLeft(), getY() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }
}
