package com.maciej916.indreb.common.block.impl.machines.scanner.screen;

import com.maciej916.indreb.common.block.impl.machines.scanner.BlockEntityScanner;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import com.maciej916.indreb.common.screen.button.GuiButton;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class GuiScannerSavePattern extends GuiButton {

    BlockEntityScanner entity;

    public GuiScannerSavePattern(IGuiWrapper wrapper, int leftOffset, int topOffset, BlockEntityScanner blockEntityScanner, Runnable leftClick) {
        super(wrapper, leftOffset, topOffset, GuiSprite.SCANNER_SAVE, leftClick, null);
        this.entity = blockEntityScanner;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        if (entity.getMode().getId() >= 4) {
            super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
        }
    }

    @Override
    protected boolean onLeftClick() {
        if (entity.getMode().getId() >= 4) {
            super.onLeftClick();
        }
        return false;
    }

    @Override
    public void renderWidgetToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused() && entity.getMode().getId() >= 4) {
            ResourceLocation rl = ForgeRegistries.ITEMS.getKey(entity.getResult().getResultStack().getItem());
            screen.renderComponentTooltip(pPoseStack, List.of(
                    EnumLang.SAVE_PATTERN.getTranslationComponent(),
                    Component.literal(entity.getResult().getResultStack().getItem().getName(entity.getResult().getResultStack()).getString()).withStyle(ChatFormatting.GRAY),
                    Component.translatable(rl.toString()).withStyle(ChatFormatting.DARK_GRAY)
            ), pMouseX, pMouseY);
        }
    }
}