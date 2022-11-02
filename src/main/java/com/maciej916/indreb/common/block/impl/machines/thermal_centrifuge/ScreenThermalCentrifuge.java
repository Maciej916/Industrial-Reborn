package com.maciej916.indreb.common.block.impl.machines.thermal_centrifuge;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import com.maciej916.indreb.common.screen.text.GuiTextTemperature;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenThermalCentrifuge extends BetterScreen<MenuThermalCentrifuge> {

    public ScreenThermalCentrifuge(MenuThermalCentrifuge container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityThermalCentrifuge be = (BlockEntityThermalCentrifuge) getBlockEntity();

        addRenderableOnlyComponent(new GuiProgressArrow(this, 73, 33, be.progress));
        addRenderableOnlyComponent(new GuiTextTemperature(this, 55, 5, 38, 59, be.tempLevel));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/thermal_centrifuge.png");
    }
}
