package com.maciej916.indreb.common.screen.widgets;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.PanelScreen;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import org.antlr.v4.runtime.misc.NotNull;

public abstract class GuiElement extends AbstractWidget {

    private final IGuiWrapper wrapper;

    public GuiElement(IGuiWrapper wrapper, int width, int height, int leftOffset, int topOffset) {
        super(wrapper.getGuiLeft() + leftOffset, wrapper.getGuiTop() + topOffset, width, height, new TextComponent(""));
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
