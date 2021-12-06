package com.maciej916.indreb.common.block.impl.generators.solar_generator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.active.GuiSolarActive;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenSolarGenerator extends IndRebScreen<ContainerSolarGenerator> {

    public ScreenSolarGenerator(ContainerSolarGenerator container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnly(new GuiSolarActive(this, 80, 35, ((BlockEntitySolarGenerator) getBlockEntity()).getActive()));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/solar_generator.png");
    }
}
