package com.maciej916.indreb.common.block.impl.machine.standard.thermal_centrifuge;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.ArrowProgressWidget;
import com.maciej916.indreb.common.screen.widget.text.TemperatureTextWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenThermalCentrifuge extends IndRebScreen<MenuThermalCentrifuge> {

    BlockEntityThermalCentrifuge entity;

    public ScreenThermalCentrifuge(MenuThermalCentrifuge menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityThermalCentrifuge) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new ArrowProgressWidget(this, 73, 33, entity.progressRecipe));
        addRenderableOnlyWidget(new TemperatureTextWidget(this, 20, 59, entity.progressTemp));
    }

    @Override
    public void updateData() {
        super.updateData();
        menu.getContainerData().updateProgressFloatData(0, entity.progressRecipe);
        menu.getContainerData().updateProgressFloatData(1, entity.progressTemp);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/thermal_centrifuge.png");
    }
}
