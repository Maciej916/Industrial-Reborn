package com.maciej916.indreb.common.block.impl.machines.pattern_storage.screen;

import com.maciej916.indreb.common.block.impl.machines.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;

public class GuiPatternStoragePage extends GuiElement {

    private final BlockEntityPatternStorage blockEntityPatternStorage;

    public GuiPatternStoragePage(IGuiWrapper wrapper, BlockEntityPatternStorage blockEntityPatternStorage) {
        super(wrapper, 30, 8, 66, 24);
        this.blockEntityPatternStorage = blockEntityPatternStorage;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {

        GuiUtil.renderScaled(pPoseStack, Math.min(blockEntityPatternStorage.getCurrentPattern() + 1, blockEntityPatternStorage.getPatternsStored()) + " / " + blockEntityPatternStorage.getPatternsStored(), getLeftOffset(), getTopOffset(), 0.85f, 4210752, false);

        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
