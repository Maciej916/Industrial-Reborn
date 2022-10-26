package com.maciej916.indreb.common.block.impl.machines.recycler;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressRecycler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenRecycler extends BetterScreen<MenuRecycler> {

    public ScreenRecycler(MenuRecycler container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnlyComponent(new GuiProgressRecycler(this, 71, 35, ((BlockEntityRecycler) getBlockEntity()).progress));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/recycler.png");
    }
}
