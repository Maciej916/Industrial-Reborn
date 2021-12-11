package com.maciej916.indreb.common.block.impl.generators.crystalline_generator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressFuel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenCrystallineGenerator extends IndRebScreen<ContainerCrystallineGenerator> {

    public ScreenCrystallineGenerator(ContainerCrystallineGenerator container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnly(new GuiProgressFuel(this, 80, 57, ((BlockEntityCrystallineGenerator) getBlockEntity()).progressBurn));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/generator.png");
    }
}
