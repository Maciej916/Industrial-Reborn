package com.maciej916.indreb.common.screen.bar;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.util.SpriteUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import static com.maciej916.indreb.common.util.GuiUtil.*;

public class GuiFluidBarVertical extends GuiElement {

    private final FluidTank fluidStorage;


    public GuiFluidBarVertical(IGuiWrapper wrapper, int leftOffset, int topOffset, FluidTank fluidStorage) {
        super(wrapper, 16, 49, leftOffset, topOffset);
        this.fluidStorage = fluidStorage;
    }

    @Override
    public void renderToolTip(PoseStack pPoseStack, int pMouseX, int pMouseY) {

        if (isHoveredOrFocused()) {
            if (fluidStorage.getFluid().getFluid() != Fluids.EMPTY) {
                Minecraft.getInstance().screen.renderTooltip(pPoseStack, new TranslatableComponent("gui." + IndReb.MODID + ".fluid", fluidStorage.getFluid().getDisplayName().getString(), TextComponentUtil.getFormattedEnergyUnit(fluidStorage.getFluidAmount()), TextComponentUtil.getFormattedEnergyUnit(fluidStorage.getCapacity())), pMouseX, pMouseY);
            } else {
                Minecraft.getInstance().screen.renderTooltip(pPoseStack, new TranslatableComponent("gui." + IndReb.MODID + ".fluid_empty"), pMouseX, pMouseY);
            }
        }

        super.renderToolTip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {

        RenderSystem.setShaderTexture(0, getResourceLocation());
        blit(pPoseStack, getLeftOffset(), getTopOffset(), 0,  50, getWidth(), getHeight());

        final int fluidStored = fluidStorage.getFluid().getAmount();
        if (fluidStored > 0) {

            fluidStorage.getFluid().getFluid().getAttributes().getColor();

            int color = fluidStorage.getFluid().getFluid().getAttributes().getColor();
            RenderSystem.setShaderColor(getRed(color), getGreen(color), getBlue(color), getAlpha(color));

            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);

            int scale = Math.round((float) fluidStored / fluidStorage.getCapacity() * 41);
            int renderCount = (int) Math.ceil((double)scale / 8);

            TextureAtlasSprite icon = SpriteUtil.getFluidSprite(fluidStorage.getFluid());

            for (int i = 0; i < renderCount; i++) {
                int height = Math.min(scale - (i * 8), 8);
                blit(pPoseStack, getLeftOffset() + 4, getTopOffset() + 4 + (48 - (i * 8)) - height - 7, 0, 8, height, icon);
            }

            RenderSystem.setShaderColor(1, 1, 1, 1);
        }

        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    }
}
