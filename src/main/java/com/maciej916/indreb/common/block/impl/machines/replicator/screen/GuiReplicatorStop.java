package com.maciej916.indreb.common.block.impl.machines.replicator.screen;

import com.maciej916.indreb.common.block.impl.machines.replicator.BlockEntityReplicator;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.button.GuiButton;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;

public class GuiReplicatorStop extends GuiButton {

    BlockEntityReplicator blockEntityReplicator;

    public GuiReplicatorStop(IGuiWrapper wrapper, BlockEntityReplicator blockEntityReplicator, Runnable leftClick) {
        super(wrapper, 86, 61, GuiSprite.REPLICATOR_STOP, leftClick, null);
        this.blockEntityReplicator = blockEntityReplicator;
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderTooltip(pPoseStack, EnumLang.STOP_REPLICATION.getTranslationComponent(), pMouseX, pMouseY);
        }
    }
}
