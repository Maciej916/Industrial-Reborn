package com.maciej916.indreb.common.screen.bar;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.interfaces.entity.IProgress;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.progress.GuiProgress;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class GuiElectricBarVertical extends GuiProgress {

    public GuiElectricBarVertical(IGuiWrapper wrapper, int leftOffset, int topOffset, IProgress progress) {
        super(wrapper, leftOffset, topOffset, progress, GuiSprite.ELECTRIC_VERTICAL, Direction.VERTICAL, true);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    }

    @Override
    public void renderToolTip(PoseStack pPoseStack, int pMouseX, int pMouseY) {

        if (isHoveredOrFocused()) {
            Minecraft.getInstance().screen.renderTooltip(pPoseStack, new TranslatableComponent("gui." + IndReb.MODID + ".energy", TextComponentUtil.getFormattedEnergyUnit(getProgress().getProgress()), TextComponentUtil.getFormattedEnergyUnit(getProgress().getProgressMax())), pMouseX, pMouseY);
        }

        super.renderToolTip(pPoseStack, pMouseX, pMouseY);
    }

}
