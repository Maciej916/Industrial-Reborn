package com.maciej916.indreb.common.screen.widget.text;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.block.impl.misc.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.capability.scanner.ScannerResult;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class PatternStorageInfoTextWidget extends BaseWidget {

    private final BlockEntityPatternStorage entity;

    public PatternStorageInfoTextWidget(IGuiHelper helper, BlockEntityPatternStorage entity) {
        super(helper, 7, 41, 158, 24);
        this.entity = entity;
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {

        ScannerResult result = entity.getResult();

        if (result.getResultStack() != ItemStack.EMPTY) {
            GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.item").getString() + " " + result.getResultStack().getItem().getName(result.getResultStack()).getString(), getX() + 3, getY(), 0.75f, 0x00a200, false);
            GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.matter_cost").getString() + " " + TextComponentUtil.getFormattedStorageUnit(result.getMatterCost(), true) + " mB", getX() + 3, getY() + 8, 0.75f, 0x00a200, false);
            GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.energy_cost").getString() + " " + TextComponentUtil.getFormattedStorageUnit(result.getEnergyCost(), true) + " IE/t", getX() + 3, getY() + 16, 0.75f, 0x00a200, false);
        } else {
            GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.item").getString() + " -", getX() + 3, getY(), 0.75f, 4210752, false);
            GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.matter_cost").getString() + " 0.00 mB", getX() + 3, getY() + 8, 0.75f, 4210752, false);
            GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.energy_cost").getString() + " 0 IE/t", getX() + 3, getY() + 16, 0.75f, 4210752, false);
        }

        super.renderButton(poseStack, mouseX, mouseY, partialTick);
    }
}
