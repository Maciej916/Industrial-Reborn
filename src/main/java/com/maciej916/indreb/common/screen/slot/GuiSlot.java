package com.maciej916.indreb.common.screen.slot;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class GuiSlot extends GuiElement {

    private final GuiSlotType slotType;

    public GuiSlot(GuiSlotType slotType, IGuiWrapper wrapper, int leftOffset, int topOffset) {
        super(wrapper, slotType.getWidth(), slotType.getHeight(), leftOffset, topOffset);
        this.slotType = slotType;
    }

    public GuiSlotType getSlotType() {
        return slotType;
    }


    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, new ResourceLocation(IndReb.MODID, "textures/gui/container/process.png"));
        blit(pPoseStack, getLeftOffset(), getTopOffset(), getSlotType().getOffsetLeft(), getSlotType().getOffsetTop(), getSlotType().getWidth(), getSlotType().getHeight());

        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

}
