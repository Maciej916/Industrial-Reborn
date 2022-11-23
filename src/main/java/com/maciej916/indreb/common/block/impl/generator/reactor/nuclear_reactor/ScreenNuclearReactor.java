package com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenNuclearReactor extends IndRebScreen<MenuNuclearReactor> {

    BlockEntityNuclearReactor entity;

    public ScreenNuclearReactor(MenuNuclearReactor menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityNuclearReactor) getEntity();
        this.imageHeight = 237;
        this.imageWidth = 213;
        this.inventoryLabelY = 143;
        this.inventoryLabelX = 26;
    }

    @Override
    public void initElements() {
        super.initElements();

//        addRenderableOnlyWidget(new SmeltingProgressWidget(this, 80, 57, entity.progressBurn));
    }

    @Override
    public void updateData() {
        super.updateData();
//        entity.progressBurn.setContainerDataBoth(menu.getData().get(1), menu.getData().get(2));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/nuclear_reactor.png");
    }
}
