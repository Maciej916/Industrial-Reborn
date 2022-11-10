package com.maciej916.indreb.common.block.impl.machines.pattern_storage;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.pattern_storage.screen.GuiPatternStorageInfo;
import com.maciej916.indreb.common.block.impl.machines.pattern_storage.screen.GuiPatternStoragePage;
import com.maciej916.indreb.common.block.impl.machines.scanner.screen.GuiScannerResult;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.button.GuiBackwardButton;
import com.maciej916.indreb.common.screen.button.GuiDownButton;
import com.maciej916.indreb.common.screen.button.GuiForwardButton;
import com.maciej916.indreb.common.screen.button.GuiUpButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class ScreenPatternStorage extends BetterScreen<MenuPatternStorage> {

    public ScreenPatternStorage(MenuPatternStorage container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityPatternStorage be = (BlockEntityPatternStorage) getBlockEntity();

        addRenderableComponent(new GuiBackwardButton(this, 34, 21, be.clientClickPrevPattern(), List.of(EnumLang.LAST_PATTERN.getTranslationComponent())));
        addRenderableComponent(new GuiForwardButton(this, 48, 21, be.clientClickNextPattern(), List.of(EnumLang.NEXT_PATTERN.getTranslationComponent())));
        addRenderableComponent(new GuiUpButton(this, 123, 21, be.clientClickExportPattern(), List.of(EnumLang.EXPORT_MEMORY.getTranslationComponent())));
        addRenderableComponent(new GuiDownButton(this, 157, 21, be.clientClickImportPattern(), List.of(EnumLang.IMPORT_MEMORY.getTranslationComponent())));

        addRenderableOnlyComponent(new GuiPatternStorageInfo(this, be));
        addRenderableOnlyComponent(new GuiPatternStoragePage(this, be));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/pattern_storage.png");
    }
}
