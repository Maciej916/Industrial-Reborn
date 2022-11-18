package com.maciej916.indreb.common.api.screen.widget;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasUpgrades;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GuiUpgradesWidget extends BaseWidget {

    private final IHasUpgrades upgrades;

    public GuiUpgradesWidget(IGuiHelper helper, IHasUpgrades upgrades) {
        super(helper, 4, 175, 24, 8 + (upgrades.getUpgradesSlots() * 18));
        this.upgrades = upgrades;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, getResourceLocation());
        blit(pPoseStack, getX(), getY(), (upgrades.getUpgradesSlots() - 1) * 25, 134, getWidth(), getHeight());
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

    @Override
    public @NotNull ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    }

    @Override
    public boolean addsExtraArea() {
        return true;
    }
}
