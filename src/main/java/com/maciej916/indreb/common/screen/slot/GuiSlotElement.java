package com.maciej916.indreb.common.screen.slot;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.interfaces.entity.ISlot;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class GuiSlotElement extends GuiElement {

    private final ISlot slot;

    public GuiSlotElement(IGuiWrapper wrapper, ISlot slot) {
        super(wrapper, slot.guiSlotType().getWidth(), slot.guiSlotType().getHeight(), slot.getGuiX(), slot.getGuiY());
        this.slot = slot;
    }

    public GuiSlotType getSlotType() {
        return slot.guiSlotType();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, new ResourceLocation(IndReb.MODID, "textures/gui/container/process.png"));
        blit(pPoseStack, getLeftOffset(), getTopOffset(), getSlotType().getOffsetLeft(), getSlotType().getOffsetTop(), getSlotType().getWidth(), getSlotType().getHeight());

        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

}
