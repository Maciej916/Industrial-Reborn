package com.maciej916.indreb.common.block.impl.reactor.nuclear_reactor;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenNuclearReactor extends BetterScreen<MenuNuclearReactor> {

    public ScreenNuclearReactor(MenuNuclearReactor container, Inventory inv, Component name) {
        super(container, inv, name);
        this.imageHeight = 237;
//        this.imageWidth = 180;
        this.inventoryLabelY = 143;
    }

    @Override
    public void init() {
        super.init();


        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/nuclear_reactor.png");
    }
}
