package com.maciej916.indreb.common.screen.widget.text;

import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.block.impl.misc.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;

public class PatternStoragePageTextWidget extends BaseWidget {

    private final BlockEntityPatternStorage entity;

    public PatternStoragePageTextWidget(IGuiHelper helper, BlockEntityPatternStorage entity) {
        super(helper, 66, 24, 30, 8);
        this.entity = entity;
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {

        GuiUtil.renderScaled(poseStack, Math.min(entity.getCurrentPattern() + 1, entity.getPatternsStored()) + " / " + entity.getPatternsStored(), getX(), getY(), 0.85f, 4210752, false);

        super.renderButton(poseStack, mouseX, mouseY, partialTick);
    }
}
