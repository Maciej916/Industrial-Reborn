package com.maciej916.indreb.common.api.screen.widget;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.fluid.FluidStorage;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.util.SpriteUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

import static com.maciej916.indreb.common.util.GuiUtil.*;

public abstract class BaseFluidBarWidget extends BaseWidget {

    private final FluidTank fluidStorage;
    private final int textureX;
    private final int textureY;

    public BaseFluidBarWidget(IGuiHelper helper, int x, int y, int width, int height, int textureX, int textureY, FluidStorage fluidStorage) {
        super(helper, x, y, width, height);
        this.fluidStorage = fluidStorage;
        this.textureX = textureX;
        this.textureY = textureY;
    }

    @Override
    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {
            if (fluidStorage.getFluid().getFluid() != Fluids.EMPTY) {
                screen.renderTooltip(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".fluid", fluidStorage.getFluid().getDisplayName().getString(), TextComponentUtil.getFormattedEnergyUnit(fluidStorage.getFluidAmount()), TextComponentUtil.getFormattedEnergyUnit(fluidStorage.getCapacity())), pMouseX, pMouseY);
            } else {
                screen.renderTooltip(pPoseStack, Component.translatable("gui." + IndReb.MODID + ".fluid_empty"), pMouseX, pMouseY);
            }
        }
        super.renderToolTip(screen, pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, getResourceLocation());
        blit(pPoseStack, getX(), getY(), textureX,  textureY, getWidth(), getHeight());

        final int fluidStored = fluidStorage.getFluid().getAmount();
        if (fluidStored > 0) {
            RenderSystem.enableBlend();

            int color = IClientFluidTypeExtensions.of(fluidStorage.getFluid().getFluid()).getTintColor();

            RenderSystem.setShaderColor(getRed(color), getGreen(color), getBlue(color), getAlpha(color));

            RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
            TextureAtlasSprite icon = SpriteUtil.getFluidSprite(fluidStorage.getFluid());

            int leftOffset = getY() + 4;
            int topOffset = getX() + 4;
            int fluidWidth = width - 8;
            int fluidHeight = height - 8;

            int renderAmount = Math.max(Math.min(fluidHeight, fluidStored * fluidHeight / fluidStorage.getCapacity()), 1);
            int posY = leftOffset + fluidHeight - renderAmount;

            for (int i = 0; i < fluidWidth; i += 16) {
                for (int j = 0; j < renderAmount; j += 16) {
                    int drawWidth = Math.min(fluidWidth - i, 16);
                    int drawHeight = Math.min(renderAmount - j, 16);

                    int drawX = topOffset + i;
                    int drawY = posY + j;

                    float minU = icon.getU0();
                    float maxU = icon.getU1();
                    float minV = icon.getV0();
                    float maxV = icon.getV1();
                    float dH = minV + (maxV - minV) * drawHeight / 16F;
                    float dW = minU + (maxU - minU) * drawWidth / 16F;

                    Tesselator tessellator = Tesselator.getInstance();
                    BufferBuilder tes = tessellator.getBuilder();
                    tes.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    tes.vertex(drawX, drawY + drawHeight, 0).uv(minU, dH).endVertex();
                    tes.vertex(drawX + drawWidth, drawY + drawHeight, 0).uv(dW, dH).endVertex();
                    tes.vertex(drawX + drawWidth, drawY, 0).uv(dW, minV).endVertex();
                    tes.vertex(drawX, drawY, 0).uv(minU, minV).endVertex();
                    tessellator.end();
                }
            }

            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1, 1, 1, 1);
        }

        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }

    @Override
    public @NotNull ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    }
}
