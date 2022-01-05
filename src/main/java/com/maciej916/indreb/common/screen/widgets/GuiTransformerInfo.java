package com.maciej916.indreb.common.screen.widgets;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.transformer.BlockEntityTransformer;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.enums.TransformerMode;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.text.GuiRGBText;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.awt.*;

import static com.maciej916.indreb.IndReb.MODID;

public class GuiTransformerInfo extends GuiElement {

    private final BlockEntityTransformer be;

    public GuiTransformerInfo(IGuiWrapper wrapper, BlockEntityTransformer be) {
        super(wrapper, 80, 27, 48, 28);
        this.be = be;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {

        TransformerMode mode = be.getTransformerMode();

        if (mode == TransformerMode.STEP_UP) {
            int color = new Color(33, 178, 13).getRGB();
            GuiUtil.renderScaled(pPoseStack, new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedLong(be.getTransformerTier().getMinTier().getBasicTransfer())).getString(), getLeftOffset() + 3, getTopOffset() + 2, 0.8f, color, false);
            GuiUtil.renderScaled(pPoseStack, new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedLong(be.getTransformerTier().getMaxTier().getBasicTransfer())).getString(), getLeftOffset() + 3, getTopOffset() + 18, 0.8f, color, false);
        } else {
            int color = new Color(178, 13, 13).getRGB();
            GuiUtil.renderScaled(pPoseStack, new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedLong(be.getTransformerTier().getMaxTier().getBasicTransfer())).getString(), getLeftOffset() + 3, getTopOffset() + 2, 0.8f, color, false);
            GuiUtil.renderScaled(pPoseStack, new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedLong(be.getTransformerTier().getMinTier().getBasicTransfer())).getString(), getLeftOffset() + 3, getTopOffset() + 18, 0.8f, color, false);
        }

        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
