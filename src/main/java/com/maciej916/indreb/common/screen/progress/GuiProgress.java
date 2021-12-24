package com.maciej916.indreb.common.screen.progress;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.entity.IProgress;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class GuiProgress extends GuiElement {

    private final IProgress progress;
    private final GuiSprite progressType;
    private final Direction direction;
    private final boolean reverse;

    public GuiProgress(IGuiWrapper wrapper, int leftOffset, int topOffset, IProgress progress, GuiSprite progressType, Direction direction, boolean reverse) {
        super(wrapper, progressType.getWidth(), progressType.getHeight(), leftOffset, topOffset);
        this.progress = progress;
        this.progressType = progressType;
        this.direction = direction;
        this.reverse = reverse;
    }

    public IProgress getProgress() {
        return progress;
    }

    public GuiSprite getProgressType() {
        return progressType;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/process.png");
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {

        RenderSystem.setShaderTexture(0, getResourceLocation());

        blit(pPoseStack, getLeftOffset(), getTopOffset(), progressType.getOffsetLeft(), progressType.getOffsetTop(), progressType.getWidth(), progressType.getHeight());

        float currProgress = progress.getPercentProgress();
        int scaleX = Math.round(currProgress / 100 * progressType.getActiveHeight());
        int scaleY = Math.round(currProgress / 100 * progressType.getActiveWidth());

        switch (direction) {
            case VERTICAL:
                if (reverse) {
                    blit(
                            pPoseStack,
                            getLeftOffset() + progressType.getRenderOffsetLeft(),
                            getTopOffset() + progressType.getRenderOffsetTop() + progressType.getActiveHeight() - scaleX,
                            progressType.getActiveOffsetLeft(),
                            progressType.getActiveOffsetTop() + progressType.getActiveHeight() - scaleX,
                            progressType.getActiveWidth(),
                            scaleX
                    );
                } else {
                    blit(
                            pPoseStack,
                            getLeftOffset() + progressType.getRenderOffsetLeft(),
                            getTopOffset() + progressType.getRenderOffsetTop() + scaleX,
                            progressType.getActiveOffsetLeft(), progressType.getActiveOffsetTop() + scaleX,
                            progressType.getActiveWidth(),
                            scaleX * -1
                    );
                }
                break;
            case HORIZONTAL:
                if (reverse) {
                    blit(
                            pPoseStack,
                            getLeftOffset() + progressType.getRenderOffsetLeft(),
                            getTopOffset() + progressType.getRenderOffsetTop(),
                            progressType.getActiveOffsetLeft(),
                            progressType.getActiveOffsetTop(),
                            progressType.getActiveWidth() - scaleY,
                            progressType.getActiveHeight()
                    );
                } else {
                    blit(
                            pPoseStack,
                            getLeftOffset() + progressType.getRenderOffsetLeft(),
                            getTopOffset() + progressType.getRenderOffsetTop(),
                            progressType.getActiveOffsetLeft(),
                            progressType.getActiveOffsetTop(),
                            scaleY,
                            progressType.getActiveHeight()
                    );
                }
                break;
        }

        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

    public enum Direction {
        VERTICAL,
        HORIZONTAL
    }
}
