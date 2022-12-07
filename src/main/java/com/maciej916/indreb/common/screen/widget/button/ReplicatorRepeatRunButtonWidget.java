package com.maciej916.indreb.common.screen.widget.button;

import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseButtonWidget;
import com.maciej916.indreb.common.enums.EnumLang;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

public class ReplicatorRepeatRunButtonWidget extends BaseButtonWidget {

    public ReplicatorRepeatRunButtonWidget(IGuiHelper helper, int x, int y, @Nullable Runnable onLeftClick) {
        super(helper, x, y, GuiSprite.REPLICATOR_REPEAT, onLeftClick, null);
    }

    @Override
    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderTooltip(pPoseStack, EnumLang.REPEAT_RUN.getTranslationComponent(), pMouseX, pMouseY);
        }
        super.renderToolTip(screen, pPoseStack, pMouseX, pMouseY);
    }

}
