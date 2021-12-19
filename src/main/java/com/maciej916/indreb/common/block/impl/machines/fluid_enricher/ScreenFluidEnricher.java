package com.maciej916.indreb.common.block.impl.machines.fluid_enricher;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenFluidEnricher extends BetterScreen<ContainerFluidEnricher> {

    public ScreenFluidEnricher(ContainerFluidEnricher container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/fluid_enricher.png");
    }
}
