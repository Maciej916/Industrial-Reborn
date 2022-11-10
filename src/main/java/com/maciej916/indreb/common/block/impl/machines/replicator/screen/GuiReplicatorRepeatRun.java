package com.maciej916.indreb.common.block.impl.machines.replicator.screen;

import com.maciej916.indreb.common.block.impl.machines.replicator.BlockEntityReplicator;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.button.GuiButton;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;

public class GuiReplicatorRepeatRun extends GuiButton {

    BlockEntityReplicator blockEntityReplicator;

    public GuiReplicatorRepeatRun(IGuiWrapper wrapper, BlockEntityReplicator blockEntityReplicator, Runnable leftClick) {
        super(wrapper, 124, 61, GuiSprite.REPLICATOR_REPEAT, leftClick, null);
        this.blockEntityReplicator = blockEntityReplicator;
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderTooltip(pPoseStack, EnumLang.REPEAT_RUN.getTranslationComponent(), pMouseX, pMouseY);
        }
    }
}
