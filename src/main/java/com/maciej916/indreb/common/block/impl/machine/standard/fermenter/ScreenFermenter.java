package com.maciej916.indreb.common.block.impl.machine.standard.fermenter;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.bar.GuiFluidStorageVerticalLargeWidget;
import com.maciej916.indreb.common.api.screen.widget.bar.GuiFluidStorageVerticalWidget;
import com.maciej916.indreb.common.api.screen.widget.bar.GuiHeatBarHorizontalWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.ArrowProgressWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.GuiProgressFillWidget;
import com.maciej916.indreb.common.screen.widget.progress.WasteProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenFermenter extends IndRebScreen<MenuFermenter> {

    BlockEntityFermenter entity;

    public ScreenFermenter(MenuFermenter menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityFermenter) getEntity();
        this.imageHeight = 188;
        this.inventoryLabelY = 94;
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new ArrowProgressWidget(this, 76, 35, entity.progressRecipe));
        addRenderableOnlyWidget(new GuiHeatBarHorizontalWidget(this, 10, 76, entity.progressHeat));

        addRenderableOnlyWidget(new GuiProgressFillWidget(this, 14, 39, entity.progressFill));
        addRenderableOnlyWidget(new GuiProgressFillWidget(this, 128, 39, entity.progressDrain));

        addRenderableOnlyWidget(new GuiFluidStorageVerticalLargeWidget(this, 32, 18, entity.firstTank));
        addRenderableOnlyWidget(new GuiFluidStorageVerticalWidget(this, 108, 18, entity.secondTank));

        addRenderableOnlyWidget(new WasteProgressWidget(this, 88, 74, entity.progressWaste));
    }

    @Override
    public void updateData() {
        super.updateData();
        menu.getContainerData().updateProgressFloatData(0, entity.progressRecipe);
        menu.getContainerData().updateProgressFloatData(1, entity.progressHeat);
        menu.getContainerData().updateProgressIntData(2, entity.progressFill);
        menu.getContainerData().updateProgressIntData(3, entity.progressDrain);
        menu.getContainerData().updateProgressFloatData(4, entity.progressWaste);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/fermenter.png");
    }
}
