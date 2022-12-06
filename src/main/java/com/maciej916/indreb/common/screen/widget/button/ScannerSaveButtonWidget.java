package com.maciej916.indreb.common.screen.widget.button;

import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseButtonWidget;
import com.maciej916.indreb.common.block.impl.machine.t_super.scanner.BlockEntityScanner;
import com.maciej916.indreb.common.enums.EnumLang;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ScannerSaveButtonWidget extends BaseButtonWidget {

    private final BlockEntityScanner entity;

    public ScannerSaveButtonWidget(IGuiHelper helper, BlockEntityScanner entity) {
        super(helper, 121, 36, GuiSprite.SCANNER_SAVE, entity.clickSaveScanClient(), null);
        this.entity = entity;
    }

    @Override
    public void renderToolTip(Screen screen, PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHoveredOrFocused() && entity.getMode().getId() >= 4) {
            ResourceLocation rl = ForgeRegistries.ITEMS.getKey(entity.getResult().getResultStack().getItem());
            screen.renderComponentTooltip(pPoseStack, List.of(
                    EnumLang.SAVE_PATTERN.getTranslationComponent(),
                    Component.literal(entity.getResult().getResultStack().getItem().getName(entity.getResult().getResultStack()).getString()).withStyle(ChatFormatting.GRAY),
                    Component.translatable(rl.toString()).withStyle(ChatFormatting.DARK_GRAY)
            ), pMouseX, pMouseY);
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
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        if (entity.getMode().getId() >= 4) {
            super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
        }
    }
}
