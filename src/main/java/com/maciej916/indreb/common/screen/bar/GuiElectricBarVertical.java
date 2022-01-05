package com.maciej916.indreb.common.screen.bar;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.energy.impl.BasicEnergyStorage;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.interfaces.entity.IProgress;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.screen.progress.GuiProgress;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class GuiElectricBarVertical extends GuiProgress {

    BasicEnergyStorage basicEnergyStorage;
    IndRebBlockEntity blockEntity;

    public GuiElectricBarVertical(IGuiWrapper wrapper, int leftOffset, int topOffset, BasicEnergyStorage progress, IndRebBlockEntity blockEntity) {
        super(wrapper, leftOffset, topOffset, progress, GuiSprite.ELECTRIC_VERTICAL, Direction.VERTICAL, true);
        this.basicEnergyStorage = progress;
        this.blockEntity = blockEntity;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused()) {


//            System.out.println("getProgress()");
//            System.out.println(getProgress());
//            System.out.println(getProgress().getProgress());
//            System.out.println(getProgress().getProgressMax());
//
//
//
//
//
//            System.out.println("basicEnergyStorage()");
//            System.out.println(basicEnergyStorage.energyStored());
//            System.out.println(basicEnergyStorage.maxEnergy());
//            System.out.println(basicEnergyStorage.getProgress());
//            System.out.println(basicEnergyStorage.getProgressMax());
//
//            System.out.println("blockEntity");
//            System.out.println(blockEntity.getEnergyStorage().energyStored());
//            System.out.println(blockEntity.getEnergyStorage().maxEnergy());
//
//
//            blockEntity.getCapability(ModCapabilities.ENERGY).ifPresent(energy -> {
//                System.out.println("energy");
//                System.out.println(energy.energyStored());
//                System.out.println(energy.maxEnergy());
//
//            });

            screen.renderTooltip(pPoseStack, new TranslatableComponent("gui." + IndReb.MODID + ".energy", TextComponentUtil.getFormattedEnergyUnit(getProgress().getProgress()), TextComponentUtil.getFormattedEnergyUnit(getProgress().getProgressMax())), pMouseX, pMouseY);
        }
    }
}
