package com.maciej916.indreb.common.block.impl.generators.geo_generator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVerticalLarge;
import com.maciej916.indreb.common.screen.progress.GuiProgressFill;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenGeoGenerator extends BetterScreen<ContainerGeoGenerator> {

    public ScreenGeoGenerator(ContainerGeoGenerator container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityGeoGenerator be = (BlockEntityGeoGenerator) getBlockEntity();

        addRenderableOnlyComponent(new GuiProgressFill(this, 62, 40, be.progressFill));
        addRenderableOnlyComponent(new GuiFluidBarVerticalLarge(this, 80, 19, be.fluidStorage));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/geo_generator.png");
    }
}
