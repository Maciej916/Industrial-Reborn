package com.maciej916.indreb.common.block.impl.machines.extractor;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressExtracting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenExtractor extends IndRebScreen<ContainerExtractor> {

    public ScreenExtractor(ContainerExtractor container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnly(new GuiProgressExtracting(this, 71, 35, ((BlockEntityExtractor) getBlockEntity()).progress));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/standard_machine.png");
    }
}
