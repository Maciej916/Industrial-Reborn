package com.maciej916.indreb.common.block.impl.generators.solar_panels;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.active.GuiSolarActive;
import com.maciej916.indreb.common.screen.text.GuiTextSolar;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenSolarGenerator extends BetterScreen<ContainerSolarGenerator> {

    public ScreenSolarGenerator(ContainerSolarGenerator container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnlyComponent(new GuiSolarActive(this, 80, 25, ((BlockEntitySolarGenerator) getBlockEntity()).getActive()));
        addRenderableOnlyComponent(new GuiTextSolar(this, 60, 18, 88, 47, (BlockEntitySolarGenerator) getBlockEntity()));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/solar_generator.png");
    }
}
