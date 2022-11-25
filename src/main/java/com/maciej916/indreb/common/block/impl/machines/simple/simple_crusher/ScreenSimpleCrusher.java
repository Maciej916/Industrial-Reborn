package com.maciej916.indreb.common.block.impl.machines.simple.simple_crusher;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.CrushingProgressWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.SmeltingProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenSimpleCrusher extends IndRebScreen<MenuSimpleCrusher> {

    BlockEntitySimpleCrusher entity;

    public ScreenSimpleCrusher(MenuSimpleCrusher menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntitySimpleCrusher) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new SmeltingProgressWidget(this, 21, 26, entity.progressBurn));
        addRenderableOnlyWidget(new CrushingProgressWidget(this, 79, 35, entity.progressSmelting));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.progressBurn.setContainerDataBoth(menu.getData().get(0), menu.getData().get(1));
        entity.progressSmelting.setContainerDataBoth(menu.getData().get(2), menu.getData().get(3));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/simple_machine.png");
    }
}
