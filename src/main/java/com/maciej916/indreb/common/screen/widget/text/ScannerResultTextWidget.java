package com.maciej916.indreb.common.screen.widget.text;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.block.impl.machine.t_super.scanner.BlockEntityScanner;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

public class ScannerResultTextWidget extends BaseWidget {

    private final BlockEntityScanner entity;

    public ScannerResultTextWidget(IGuiHelper helper, BlockEntityScanner entity) {
        super(helper, 75, 16, 71, 21);
        this.entity = entity;
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {

        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.replication_cost").getString(), getX() + 3, getY(), 0.65f, 0x00a200, false);
        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.matter_cost").getString() + " " + entity.getResult().getMatterCost() + " mB", getX() + 3, getY() + 6, 0.65f, 0x00a200, false);
        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.energy_cost").getString() + " " + TextComponentUtil.getFormattedStorageUnit(entity.getResult().getEnergyCost()) + " IE/t", getX() + 3, getY() + 12, 0.65f, 0x00a200, false);

        super.renderButton(poseStack, mouseX, mouseY, partialTick);
    }
}
