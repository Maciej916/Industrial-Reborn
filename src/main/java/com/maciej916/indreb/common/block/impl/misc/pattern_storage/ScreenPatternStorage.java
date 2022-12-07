package com.maciej916.indreb.common.block.impl.misc.pattern_storage;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.button.GuiBackwardButton;
import com.maciej916.indreb.common.api.screen.widget.button.GuiDownButton;
import com.maciej916.indreb.common.api.screen.widget.button.GuiForwardButton;
import com.maciej916.indreb.common.api.screen.widget.button.GuiUpButton;
import com.maciej916.indreb.common.capability.scanner.ScannerResult;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.screen.widget.text.PatternStorageInfoTextWidget;
import com.maciej916.indreb.common.screen.widget.text.PatternStoragePageTextWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class ScreenPatternStorage extends IndRebScreen<MenuPatternStorage> {

    BlockEntityPatternStorage entity;

    public ScreenPatternStorage(MenuPatternStorage menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityPatternStorage) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addUssableWidget(new GuiBackwardButton(this, 34, 21, entity.clickPrevPatternClient(), List.of(EnumLang.LAST_PATTERN.getTranslationComponent())));
        addUssableWidget(new GuiForwardButton(this, 48, 21, entity.clickNextPatternClient(), List.of(EnumLang.NEXT_PATTERN.getTranslationComponent())));
        addUssableWidget(new GuiUpButton(this, 123, 21, entity.clientClickExportPattern(), List.of(EnumLang.EXPORT_MEMORY.getTranslationComponent())));
        addUssableWidget(new GuiDownButton(this, 157, 21, entity.clientClickImportPattern(), List.of(EnumLang.IMPORT_MEMORY.getTranslationComponent())));

        addRenderableOnlyWidget(new PatternStoragePageTextWidget(this, entity));
        addRenderableOnlyWidget(new PatternStorageInfoTextWidget(this, entity));
    }

    @Override
    public void updateData() {
        super.updateData();
        if (entity.getResult().getResultStack().getItem() != menu.getContainerData().getItemStackData(0).getItem()) {
            entity.setResult(new ScannerResult(menu.getContainerData().getItemStackData(0), menu.getContainerData().getIntData(1), menu.getContainerData().getIntData(2)));
        }

        entity.setCurrentPattern(menu.getContainerData().getIntData(3));
        entity.setPatternsStored(menu.getContainerData().getIntData(4));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/pattern_storage.png");
    }
}
