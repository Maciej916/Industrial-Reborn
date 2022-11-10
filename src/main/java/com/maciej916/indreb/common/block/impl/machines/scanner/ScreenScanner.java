package com.maciej916.indreb.common.block.impl.machines.scanner;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.scanner.screen.GuiScannerClearPattern;
import com.maciej916.indreb.common.block.impl.machines.scanner.screen.GuiScannerMode;
import com.maciej916.indreb.common.block.impl.machines.scanner.screen.GuiScannerResult;
import com.maciej916.indreb.common.block.impl.machines.scanner.screen.GuiScannerSavePattern;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.button.GuiUpButton;
import com.maciej916.indreb.common.screen.progress.GuiProgressScanner;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class ScreenScanner extends BetterScreen<MenuScanner> {

    public ScreenScanner(MenuScanner container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityScanner be = (BlockEntityScanner) getBlockEntity();

        addRenderableOnlyComponent(new GuiProgressScanner(this, 7, 15, be.progress));
        addRenderableOnlyComponent(new GuiScannerMode(this, be));
        addRenderableOnlyComponent(new GuiScannerResult(this, be));

        addRenderableComponent(new GuiScannerClearPattern(this, 76, 36, be, be.clientClickCleanScan()));
        addRenderableComponent(new GuiScannerSavePattern(this, 121, 36, be, be.clientClickSaveScan()));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/scanner.png");
    }
}
