package com.maciej916.indreb.common.block.impl.machines.scanner.screen;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.scanner.BlockEntityScanner;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

public class GuiScannerResult extends GuiElement {

    private final BlockEntityScanner blockEntityScanner;

    public GuiScannerResult(IGuiWrapper wrapper, BlockEntityScanner blockEntityScanner) {
        super(wrapper, 71, 21, 75, 16);
        this.blockEntityScanner = blockEntityScanner;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {

        GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.replication_cost").getString(), getLeftOffset() + 3, getTopOffset(), 0.65f, 0x00a200, false);
        GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.matter_cost").getString() + " " + blockEntityScanner.getResult().getMatterCost() + " mB", getLeftOffset() + 3, getTopOffset() + 6, 0.65f, 0x00a200, false);
        GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.energy_cost").getString() + " " + TextComponentUtil.getFormattedEnergyUnit(blockEntityScanner.getResult().getEnergyCost()) + " IE/t", getLeftOffset() + 3, getTopOffset() + 12, 0.65f, 0x00a200, false);

        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
