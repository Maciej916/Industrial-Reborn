package com.maciej916.indreb.common.block.impl.machines.pattern_storage.screen;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.capabilities.scan_result.ScannerResult;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

public class GuiPatternStorageInfo extends GuiElement {

    private final BlockEntityPatternStorage blockEntityPatternStorage;

    public GuiPatternStorageInfo(IGuiWrapper wrapper, BlockEntityPatternStorage blockEntityPatternStorage) {
        super(wrapper, 158, 24, 7, 41);
        this.blockEntityPatternStorage = blockEntityPatternStorage;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        if (blockEntityPatternStorage.getPatternsStored() > 0) {
            ScannerResult scannerResult = blockEntityPatternStorage.getScannerResultMap().get(blockEntityPatternStorage.getCurrentPattern());

            GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.item").getString() + " " + scannerResult.getResultStack().getItem().getName(scannerResult.getResultStack()).getString(), getLeftOffset() + 3, getTopOffset(), 0.75f, 0x00a200, false);
            GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.matter_cost").getString() + " " + scannerResult.getMatterCost() + " mB", getLeftOffset() + 3, getTopOffset() + 8, 0.75f, 0x00a200, false);
            GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.energy_cost").getString() + " " + TextComponentUtil.getFormattedEnergyUnit(scannerResult.getEnergyCost()) + " IE", getLeftOffset() + 3, getTopOffset() + 16, 0.75f, 0x00a200, false);
        } else {
            GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.item").getString() + " -", getLeftOffset() + 3, getTopOffset(), 0.75f, 0xd60000, false);
            GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.matter_cost").getString() + " 0.00 mB", getLeftOffset() + 3, getTopOffset() + 8, 0.75f, 0xd60000, false);
            GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.energy_cost").getString() + " 0 IE/t", getLeftOffset() + 3, getTopOffset() + 16, 0.75f, 0xd60000, false);
        }

        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }
}
