package com.maciej916.indreb.common.block.impl.machines.replicator.screen;

import com.maciej916.indreb.common.block.impl.machines.replicator.BlockEntityReplicator;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

public class GuiReplicatorMode extends GuiElement {

    private final BlockEntityReplicator entity;

    public GuiReplicatorMode(IGuiWrapper wrapper, BlockEntityReplicator blockEntityReplicator) {
        super(wrapper, 85, 7, 54, 50);
        this.entity = blockEntityReplicator;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {

        String string;
        if (entity.getMode().getId() > 0) {
            string = Component.translatable(entity.getMode().getLang(), entity.progress.getPercentProgressString()).getString() + "%";
        } else {
            string = Component.translatable(entity.getMode().getLang()).getString();
        }

        GuiUtil.renderScaled(pPoseStack, string, getLeftOffset(), getTopOffset(), 0.8f, entity.getMode().getColor(), false);
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
