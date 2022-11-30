package com.maciej916.indreb.common.block.impl.generator.reactor.screen;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseProgressWidget;
import com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor.BlockEntityNuclearReactor;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GuiReactorTempVar extends BaseProgressWidget {

    private final BlockEntityNuclearReactor entity;

    public GuiReactorTempVar(IGuiHelper helper, BlockEntityNuclearReactor entity) {
        super(helper, 8, 129, entity.getReactor(), GuiSprite.REACTOR_TEMPERATURE, Direction.HORIZONTAL, false);
        this.entity = entity;
    }

    @Override
    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderTooltip(pPoseStack, Component.translatable("tooltip." + IndReb.MODID + ".core_heat", getProgress().getPercentProgressString() + "%"), pMouseX, pMouseY);
        }
        super.renderToolTip(screen, pPoseStack, pMouseX, pMouseY);
    }

    @Override
    public @NotNull ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    }
}
