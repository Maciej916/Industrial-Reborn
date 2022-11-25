package com.maciej916.indreb.common.screen.widget.text;

import com.maciej916.indreb.common.api.enums.TransformerMode;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.block.impl.transformer.BlockEntityTransformer;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class TransformerInfoTextWidget extends BaseWidget {

    private final BlockEntityTransformer entity;

    public TransformerInfoTextWidget(IGuiHelper helper, int x, int y, BlockEntityTransformer entity) {
        super(helper, x, y, 80, 27);
        this.entity = entity;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {

        TransformerMode mode = entity.getTransformerMode();

        if (mode == TransformerMode.STEP_UP) {
            int color = new Color(33, 178, 13).getRGB();
            GuiUtil.renderScaled(pPoseStack, net.minecraft.network.chat.Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedLong(entity.getTransformerTier().getMinTier().getBasicTransfer())).getString(), getX() + 3, getY() + 2, 0.8f, color, false);
            GuiUtil.renderScaled(pPoseStack, net.minecraft.network.chat.Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedLong(entity.getTransformerTier().getMaxTier().getBasicTransfer())).getString(), getX() + 3, getY() + 18, 0.8f, color, false);
        } else {
            int color = new Color(178, 13, 13).getRGB();
            GuiUtil.renderScaled(pPoseStack, net.minecraft.network.chat.Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedLong(entity.getTransformerTier().getMaxTier().getBasicTransfer())).getString(), getX() + 3, getY() + 2, 0.8f, color, false);
            GuiUtil.renderScaled(pPoseStack, Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedLong(entity.getTransformerTier().getMinTier().getBasicTransfer())).getString(), getX() + 3, getY() + 18, 0.8f, color, false);
        }

        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
