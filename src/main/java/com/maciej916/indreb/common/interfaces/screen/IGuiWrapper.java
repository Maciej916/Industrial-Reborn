package com.maciej916.indreb.common.interfaces.screen;

import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public interface IGuiWrapper {

    IndRebBlockEntity getBlockEntity();
    ResourceLocation getGuiLocation();

    int getGuiLeft();
    int getGuiTop();

//    void renderTooltip(PoseStack pMatrixStack, Component pText, int pMouseX, int pMouseY);
}
