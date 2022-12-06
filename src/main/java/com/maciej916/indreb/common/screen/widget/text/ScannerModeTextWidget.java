package com.maciej916.indreb.common.screen.widget.text;

import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.block.impl.machine.t_super.scanner.BlockEntityScanner;
import com.maciej916.indreb.common.enums.ScannerMode;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

public class ScannerModeTextWidget extends BaseWidget {

    private final BlockEntityScanner entity;

    public ScannerModeTextWidget(IGuiHelper helper, BlockEntityScanner entity) {
        super(helper, 10, 61, 115, 6);
        this.entity = entity;
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {

        String string;
        if (entity.getMode() == ScannerMode.PROGRESS) {
            string = Component.translatable(entity.getMode().getLang(), entity.progressRecipe.getPercentProgressString()).getString() + "%";
        } else {
            string = Component.translatable(entity.getMode().getLang()).getString();
        }

        GuiUtil.renderScaled(poseStack, string, getX(), getY(), 0.8f, entity.getMode().getColor(), false);
        super.renderButton(poseStack, mouseX, mouseY, partialTick);
    }
}
