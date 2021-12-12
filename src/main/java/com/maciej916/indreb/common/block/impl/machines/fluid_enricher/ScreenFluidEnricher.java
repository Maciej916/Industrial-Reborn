package com.maciej916.indreb.common.block.impl.machines.fluid_enricher;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVertical;
import com.maciej916.indreb.common.screen.button.GuiBackwardButton;
import com.maciej916.indreb.common.screen.button.GuiForwardButton;
import com.maciej916.indreb.common.screen.progress.GuiProgressExtracting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenFluidEnricher extends IndRebScreen<ContainerFluidEnricher> {

    public ScreenFluidEnricher(ContainerFluidEnricher container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/extruder.png");
    }
}
