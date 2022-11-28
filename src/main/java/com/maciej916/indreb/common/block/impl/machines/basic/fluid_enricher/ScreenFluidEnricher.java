package com.maciej916.indreb.common.block.impl.machines.basic.fluid_enricher;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.bar.GuiFluidStorageVerticalWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.ArrowProgressWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.GuiProgressFillWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenFluidEnricher extends IndRebScreen<MenuFluidEnricher> {

    BlockEntityFluidEnricher entity;

    public ScreenFluidEnricher(MenuFluidEnricher menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityFluidEnricher) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new ArrowProgressWidget(this, 82, 35, entity.progressRecipe));
        addRenderableOnlyWidget(new GuiProgressFillWidget(this, 45, 39, entity.progressFill));
        addRenderableOnlyWidget(new GuiProgressFillWidget(this, 129, 39, entity.progressDrain));
        addRenderableOnlyWidget(new GuiFluidStorageVerticalWidget(this, 63, 18, entity.firstTank));
        addRenderableOnlyWidget(new GuiFluidStorageVerticalWidget(this, 109, 18, entity.secondTank));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.progressRecipe.setContainerDataBoth(menu.getData().get(0), menu.getData().get(1));
        entity.progressFill.setContainerDataBoth(menu.getData().get(2), menu.getData().get(3));
        entity.progressDrain.setContainerDataBoth(menu.getData().get(4), menu.getData().get(5));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/fluid_enricher.png");
    }
}
