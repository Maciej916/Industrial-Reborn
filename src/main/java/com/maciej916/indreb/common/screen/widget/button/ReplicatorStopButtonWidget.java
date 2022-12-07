package com.maciej916.indreb.common.screen.widget.button;

import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseButtonWidget;
import com.maciej916.indreb.common.enums.EnumLang;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

public class ReplicatorStopButtonWidget extends BaseButtonWidget {

    public ReplicatorStopButtonWidget(IGuiHelper helper, int x, int y, @Nullable Runnable onLeftClick) {
        super(helper, x, y, GuiSprite.REPLICATOR_STOP, onLeftClick, null);
    }

    @Override
    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderTooltip(pPoseStack, EnumLang.STOP_REPLICATION.getTranslationComponent(), pMouseX, pMouseY);
        }
        super.renderToolTip(screen, pPoseStack, pMouseX, pMouseY);
    }

}
