package com.maciej916.indreb.common.screen.text;

import com.maciej916.indreb.common.block.impl.generators.solar_panels.BlockEntitySolarGenerator;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class GuiTextSolar extends GuiElement {

    private final BlockEntitySolarGenerator be;

    public GuiTextSolar(IGuiWrapper wrapper, int width, int height, int leftOffset, int topOffset, BlockEntitySolarGenerator be) {
        super(wrapper, width, height, leftOffset, topOffset);
        this.be = be;
    }


    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        MutableComponent component = Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(be.amount));
        Font font = Minecraft.getInstance().font;
        float left = (float)(getLeftOffset() + 5 - font.width(component) / 2);
        GuiUtil.renderScaled(pPoseStack, component.getString(), (int) left, getTopOffset(), 0.8f, 4210752, false);
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
