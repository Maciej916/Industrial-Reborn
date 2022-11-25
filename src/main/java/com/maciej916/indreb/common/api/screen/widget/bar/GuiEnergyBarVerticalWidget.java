package com.maciej916.indreb.common.api.screen.widget.bar;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseProgressWidget;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GuiEnergyBarVerticalWidget extends BaseProgressWidget {

    private final BasicEnergyStorage energyStorage;

    public GuiEnergyBarVerticalWidget(IGuiHelper helper, int x, int y, BasicEnergyStorage energyStorage) {
        super(helper, x, y, energyStorage, GuiSprite.ELECTRIC_VERTICAL, Direction.VERTICAL, true);
        this.energyStorage = energyStorage;
    }

    @Override
    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            screen.renderTooltip(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".energy", TextComponentUtil.getFormattedStorageUnit(energyStorage.energyStored(), isShiftDown()), TextComponentUtil.getFormattedStorageUnit(getProgress().getProgressMax(), isShiftDown())), pMouseX, pMouseY);
        }

        super.renderToolTip(screen, pPoseStack, pMouseX, pMouseY);
    }

    @Override
    public @NotNull ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    }
}
