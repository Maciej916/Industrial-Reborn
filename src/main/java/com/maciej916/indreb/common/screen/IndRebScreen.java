package com.maciej916.indreb.common.screen;

import com.maciej916.indreb.common.container.IndRebContainer;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.interfaces.entity.ICooldown;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.bar.GuiElectricBarHorizontal;
import com.maciej916.indreb.common.screen.bar.GuiElectricBarVertical;
import com.maciej916.indreb.common.screen.button.GuiExpButton;
import com.maciej916.indreb.common.screen.slot.*;
import com.maciej916.indreb.common.screen.widgets.GuiCooldown;
import com.maciej916.indreb.common.screen.widgets.GuiUpgrades;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class IndRebScreen <CONTAINER extends IndRebContainer> extends AbstractContainerScreen<CONTAINER> implements IGuiWrapper {

    CONTAINER container;

    public IndRebScreen(CONTAINER container, Inventory inv, Component name) {
        super(container, inv, name);
        this.container = container;
        this.imageHeight = 234;

        if (getBlockEntity() instanceof ISupportUpgrades) {
            this.imageWidth = this.imageWidth + 22;
        }
    }

    public IndRebBlockEntity getBlockEntity() {
        return container.getBlockEntity();
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = ((this.height - this.imageHeight) / 2) + 34;

        if (getBlockEntity() instanceof ISupportUpgrades) {
            this.leftPos = this.leftPos + 11;
            addRenderableOnly(new GuiUpgrades(this));
            getBlockEntity().getUpgradeHandlers().forEach(s -> addRenderableOnly(new GuiItemSlot(GuiSlotType.UPGRADE, this, s.x - 1, s.y - 1)));
        }

        if (getBlockEntity() instanceof ICooldown) {
            addRenderableOnly(new GuiCooldown(this));
        }

        if (getBlockEntity() instanceof IEnergyBlock energyBlock) {
            if (energyBlock.showInGui()) {
                if (energyBlock.showVertical()) {
                    addRenderableOnly(new GuiElectricBarVertical(this, energyBlock.leftOffsetVertical(), energyBlock.topOffsetVertical(), getBlockEntity().getEnergyStorage()));
                } else {
                    addRenderableOnly(new GuiElectricBarHorizontal(this, energyBlock.leftOffsetHorizontal(), energyBlock.topOffsetHorizontal(), getBlockEntity().getEnergyStorage()));
                }
            }
        }


        getBlockEntity().getElectricSlot().forEach(s -> addRenderableOnly(new GuiItemSlot(s.guiSlotType(), this, s.getXPosition() - 1, s.getYPosition() - 1)));

        getBlockEntity().getItem().forEach(s -> {
            if (s.guiSlotType() != null) {
                addRenderableOnly(new GuiSlot(s.guiSlotType(), this, s.getGuiX(), s.getGuiY()));
            }
        });

        if (getBlockEntity() instanceof IExpCollector expCollector) {
            if (expCollector.hasExpButton()) {
                addRenderableWidget(new GuiExpButton(this, expCollector));
            }
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);

//        return render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        super.renderLabels(poseStack, mouseX, mouseY);

//        this.font.func_243248_b(matrixStack, this.title, (float)this.titleX, (float)this.titleY, 4210752);
//        drawString(matrixStack, Minecraft.getInstance().font, "Energy: " + 15, 10, 10, 0xffffff);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, getGuiLocation());
        this.blit(poseStack, getGuiLeft(), getGuiTop(), 0, 0, this.imageWidth, this.imageHeight);
    }



}
