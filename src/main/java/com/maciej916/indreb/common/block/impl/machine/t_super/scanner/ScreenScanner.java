package com.maciej916.indreb.common.block.impl.machine.t_super.scanner;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.ScannerProgressWidget;
import com.maciej916.indreb.common.capability.scanner.ScannerResult;
import com.maciej916.indreb.common.enums.ScannerMode;
import com.maciej916.indreb.common.screen.widget.button.ScannerClearButtonWidget;
import com.maciej916.indreb.common.screen.widget.button.ScannerSaveButtonWidget;
import com.maciej916.indreb.common.screen.widget.text.ScannerModeTextWidget;
import com.maciej916.indreb.common.screen.widget.text.ScannerResultTextWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenScanner extends IndRebScreen<MenuScanner> {

    BlockEntityScanner entity;

    public ScreenScanner(MenuScanner menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityScanner) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addUssableWidget(new ScannerClearButtonWidget(this, entity));
        addUssableWidget(new ScannerSaveButtonWidget(this, entity));

        addRenderableOnlyWidget(new ScannerProgressWidget(this, 7, 15, entity.progressRecipe));
        addRenderableOnlyWidget(new ScannerModeTextWidget(this, entity));
        addRenderableOnlyWidget(new ScannerResultTextWidget(this, entity));
    }

    @Override
    public void updateData() {
        super.updateData();
        menu.getContainerData().updateProgressFloatData(0, entity.progressRecipe);
        entity.setMode(ScannerMode.getModeFromId(menu.getContainerData().getIntData(1)));
        entity.setCurrentModeTick(menu.getContainerData().getIntData(2));
        if (entity.getResult().getResultStack().getItem() != menu.getContainerData().getItemStackData(3).getItem()) {
            entity.setResult(new ScannerResult(menu.getContainerData().getItemStackData(3), menu.getContainerData().getIntData(4), menu.getContainerData().getIntData(5)));
        }
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/scanner.png");
    }
}
