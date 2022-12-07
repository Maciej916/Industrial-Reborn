package com.maciej916.indreb.common.block.impl.machine.t_standard.alloy_smelter;

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
        menu.getContainerData().updateProgressFloatData(0, entity.progressRecipe);
        menu.getContainerData().updateProgressFloatData(1, entity.progressHeat);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/alloy_smelter.png");
    }
}
