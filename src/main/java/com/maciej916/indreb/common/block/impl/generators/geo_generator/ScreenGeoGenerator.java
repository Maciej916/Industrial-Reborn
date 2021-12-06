package com.maciej916.indreb.common.block.impl.generators.geo_generator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVertical;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenGeoGenerator extends IndRebScreen<ContainerGeoGenerator> {

    public ScreenGeoGenerator(ContainerGeoGenerator container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnly(new GuiProgressArrow(this, 71, 35, ((BlockEntityGeoGenerator) getBlockEntity()).fill));
        addRenderableOnly(new GuiFluidBarVertical(this, 7, 18, ((BlockEntityGeoGenerator) getBlockEntity()).lavaStorage));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/geo_generator.png");
    }
}
