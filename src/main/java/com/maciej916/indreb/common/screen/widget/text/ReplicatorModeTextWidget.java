package com.maciej916.indreb.common.screen.widget.text;

import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.block.impl.machine.t_super.replicator.BlockEntityReplicator;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

public class ReplicatorModeTextWidget extends BaseWidget {

    private final BlockEntityReplicator entity;

    public ReplicatorModeTextWidget(IGuiHelper helper, BlockEntityReplicator entity) {
        super(helper, 54, 50, 85, 7);
        this.entity = entity;
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {

        String string;
        if (entity.getMode().getId() > 0) {
            string = Component.translatable(entity.getMode().getLang(), entity.progressRecipe.getPercentProgressString()).getString() + "%";
        } else {
            string = Component.translatable(entity.getMode().getLang()).getString();
        }

        GuiUtil.renderScaled(poseStack, string, getX(), getY(), 0.8f, entity.getMode().getColor(), false);
        super.renderButton(poseStack, mouseX, mouseY, partialTick);
    }
}
