package com.maciej916.indreb.common.screen.widgets;

import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public abstract class GuiElement extends AbstractWidget {

    private final IGuiWrapper wrapper;

    public GuiElement(IGuiWrapper wrapper, int width, int height, int leftOffset, int topOffset) {
        super(wrapper.getGuiLeft() + leftOffset, wrapper.getGuiTop() + topOffset, width, height, Component.literal(""));
        this.wrapper = wrapper;
        this.width = width;
        this.height = height;
    }

    public IGuiWrapper getWrapper() {
        return wrapper;
    }

    public int getLeftOffset() {
        return x;
    }

    public int getTopOffset() {
        return y;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        this.isHovered = pMouseX >= this.x && pMouseX <= getLeftOffset() + this.width && pMouseY >= getTopOffset() && pMouseY <= getTopOffset() + this.height;

        Minecraft minecraft = Minecraft.getInstance();
        this.renderBg(pPoseStack, minecraft, pMouseX, pMouseY);

        if (isHovered) {
            renderToolTip(pPoseStack, pMouseX, pMouseY);
        }
    }

    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {

    }

    @NotNull
    public ResourceLocation getResourceLocation() {
        return WIDGETS_LOCATION;
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
