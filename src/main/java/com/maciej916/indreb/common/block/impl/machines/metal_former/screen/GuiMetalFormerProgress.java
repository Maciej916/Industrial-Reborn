package com.maciej916.indreb.common.block.impl.machines.metal_former.screen;

import com.maciej916.indreb.common.block.impl.machines.metal_former.BlockEntityMeralFormer;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.progress.GuiProgress;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;

public class GuiMetalFormerProgress extends GuiProgress {

    BlockEntityMeralFormer entity;

    public GuiMetalFormerProgress(IGuiWrapper wrapper, int leftOffset, int topOffset, BlockEntityMeralFormer entity) {
        super(wrapper, leftOffset, topOffset, entity.progress, GuiSprite.CUTTING, Direction.HORIZONTAL, false);
        this.entity = entity;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        this.setProgressType(entity.getMode().getSprite());
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }
}
