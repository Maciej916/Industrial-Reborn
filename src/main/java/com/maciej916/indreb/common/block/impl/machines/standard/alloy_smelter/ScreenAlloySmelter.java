package com.maciej916.indreb.common.block.impl.machines.standard.alloy_smelter;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.bar.GuiHeatBarHorizontalWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.ArrowProgressWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.SmeltingProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenAlloySmelter extends IndRebScreen<MenuAlloySmelter> {

    BlockEntityAlloySmelter entity;

    public ScreenAlloySmelter(MenuAlloySmelter menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityAlloySmelter) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new SmeltingProgressWidget(this, 37, 41, entity.progressHeat));
        addRenderableOnlyWidget(new ArrowProgressWidget(this, 81, 33, entity.progressRecipe));
        addRenderableOnlyWidget(new GuiHeatBarHorizontalWidget(this, 15, 59, entity.progressHeat));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.progressRecipe.setContainerDataBoth(menu.getData().get(0), menu.getData().get(1));
        entity.progressHeat.setContainerDataBoth(menu.getData().get(2), menu.getData().get(3));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/alloy_smelter.png");
    }
}
