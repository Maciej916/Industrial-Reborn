package com.maciej916.indreb.common.api.screen.widget;

import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.interfaces.screen.IWidget;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public abstract class BaseWidget extends AbstractWidget implements IWidget {

    boolean isShiftDown;
    IGuiHelper helper;

    public BaseWidget(IGuiHelper helper, int x, int y, int width, int height) {
        super(helper.getGuiLeft() + x, helper.getGuiTop() + y, width, height, Component.literal(""));
        this.helper = helper;
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {

    }

    @Override
    public boolean addsExtraArea() {
        return false;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        this.isShiftDown = InputConstants.isKeyDown(minecraft.getWindow().getWindow(), 340) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 344);
        this.renderBg(poseStack, minecraft, mouseX, mouseY);
    }

    public boolean isShiftDown() {
        return isShiftDown;
    }

    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {

    }

    @NotNull
    public ResourceLocation getResourceLocation() {
        return WIDGETS_LOCATION;
    }
}
