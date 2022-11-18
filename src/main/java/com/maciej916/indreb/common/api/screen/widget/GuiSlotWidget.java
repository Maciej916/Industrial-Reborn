package com.maciej916.indreb.common.api.screen.widget;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class GuiSlotWidget extends BaseWidget {

    private final GuiSlotBg guiSlotBg;

    public GuiSlotWidget(IGuiHelper helper, BaseSlot baseSlot) {
        super(helper, baseSlot.getSlotGuiX(), baseSlot.getSlotGuiY(), baseSlot.getGuiSlotBg().getWidth(), baseSlot.getGuiSlotBg().getHeight());
        this.guiSlotBg = baseSlot.getGuiSlotBg();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, new ResourceLocation(IndReb.MODID, "textures/gui/container/process.png"));
        blit(pPoseStack, getX(), getY(), guiSlotBg.getOffsetLeft(), guiSlotBg.getOffsetTop(), guiSlotBg.getWidth(), guiSlotBg.getHeight());

        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

}
