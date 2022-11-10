package com.maciej916.indreb.common.block.impl.machines.replicator.screen;

import com.maciej916.indreb.common.block.impl.machines.replicator.BlockEntityReplicator;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.button.GuiButton;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;

public class GuiReplicatorSingleRun extends GuiButton {

    BlockEntityReplicator blockEntityReplicator;

    public GuiReplicatorSingleRun(IGuiWrapper wrapper, BlockEntityReplicator blockEntityReplicator, Runnable leftClick) {
        super(wrapper, 105, 61, GuiSprite.REPLICATOR_SINGLE, leftClick, null);
        this.blockEntityReplicator = blockEntityReplicator;
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderTooltip(pPoseStack, EnumLang.SINGLE_RUN.getTranslationComponent(), pMouseX, pMouseY);
        }
    }
}
