package com.maciej916.indreb.common.block.impl.generators.generator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressFuel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenGenerator extends BetterScreen<MenuGenerator> {

    public ScreenGenerator(MenuGenerator container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnlyComponent(new GuiProgressFuel(this, 80, 57, ((BlockEntityGenerator) getBlockEntity()).progressBurn));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/generator.png");
    }
}
