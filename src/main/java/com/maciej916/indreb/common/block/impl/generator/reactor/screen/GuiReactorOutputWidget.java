package com.maciej916.indreb.common.block.impl.generator.reactor.screen;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor.BlockEntityNuclearReactor;
import com.maciej916.indreb.common.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

public class GuiReactorOutputWidget extends BaseWidget {

    private final BlockEntityNuclearReactor entity;

    public GuiReactorOutputWidget(IGuiHelper helper, BlockEntityNuclearReactor entity) {
        super(helper, 120, 131, 84, 8);
        this.entity = entity;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        if (entity.getReactor().isFluid()) {
            GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".output_mode", entity.getReactor().getVentedHeat() + " HU/s").getString(), getX(), getY(), 0.85f, 0x008fd6, false);
        } else {
            GuiUtil.renderScaled(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".output_mode", (entity.getReactor().getCurrentIEOutput() / 20) + " IE/t").getString(), getX(), getY(), 0.85f, 0x008fd6, false);
        }
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTicks);
    }

}
