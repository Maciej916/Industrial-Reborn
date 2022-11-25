package com.maciej916.indreb.common.screen.widget.text;

import com.maciej916.indreb.common.api.blockentity.interfaces.IProgress;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class SolarPanelTextWidget extends BaseWidget {

    private final IProgress progress;

    public SolarPanelTextWidget(IGuiHelper helper, int x, int y, IProgress progress) {
        super(helper, x, y, 56, 7);
        this.progress = progress;
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        MutableComponent component = Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(progress.currentProgress()));
        GuiUtil.renderScaledCenter(poseStack, component, getX(), getWidth(), getY(), 0.8f, 4210752, false);
        super.renderButton(poseStack, mouseX, mouseY, partialTick);
    }
}
