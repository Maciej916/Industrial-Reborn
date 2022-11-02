package com.maciej916.indreb.common.screen.button;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;

import static com.maciej916.indreb.IndReb.MODID;

public class GuiInfoButton extends GuiButton {

    ISupportUpgrades supportUpgrades;

    public GuiInfoButton(IGuiWrapper wrapper, ISupportUpgrades supportUpgrades, int topOffset) {
        super(wrapper, -19, 5, GuiSprite.LEFT_BUTTON, null, null);
        this.supportUpgrades = supportUpgrades;
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {

            List<Component> elements = new ArrayList<>();
            elements.add(Component.translatable(EnumLang.SUPPORTED_UPGRADES.getTranslationKey()).withStyle(ChatFormatting.AQUA));

            for (UpgradeType ut: supportUpgrades.getSupportedUpgrades()) {
                elements.add(ut.getLang().getTranslationComponent());
            }

            screen.renderComponentTooltip(pPoseStack, elements, pMouseX, pMouseY);
        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);

        RenderSystem.setShaderTexture(0, getResourceLocation());
        GuiSprite sprite = GuiSprite.INFO_ICON;

        blit(pPoseStack, getLeftOffset() + sprite.getRenderOffsetLeft(), getTopOffset() + sprite.getRenderOffsetLeft(), sprite.getOffsetLeft(), sprite.getOffsetTop(), sprite.getWidth(), sprite.getHeight());
    }
}
