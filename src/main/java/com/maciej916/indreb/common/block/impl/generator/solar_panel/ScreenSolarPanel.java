package com.maciej916.indreb.common.block.impl.generator.solar_panel;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.LightProgressWidget;
import com.maciej916.indreb.common.screen.widget.text.SolarPanelTextWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenSolarPanel extends IndRebScreen<MenuSolarPanel> {

    BlockEntitySolarPanel entity;

    public ScreenSolarPanel(MenuSolarPanel menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntitySolarPanel) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new LightProgressWidget(this, 43, 29, entity.progressAmount));
        addRenderableOnlyWidget(new SolarPanelTextWidget(this, 23, 51, entity.progressAmount));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.progressAmount.setContainerDataBoth(menu.getData().get(0), menu.getData().get(1));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/solar_panel.png");
    }
}
