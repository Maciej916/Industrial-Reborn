package com.maciej916.indreb.common.block.impl.generator.generator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.SmeltingProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenGenerator extends IndRebScreen<MenuGenerator> {

    BlockEntityGenerator entity;

    public ScreenGenerator(MenuGenerator menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityGenerator) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new SmeltingProgressWidget(this, 80, 57, entity.progressBurn));

    }

    @Override
    protected void containerTick() {
        super.containerTick();
        entity.progressBurn.setData(menu.getData().get(1), menu.getData().get(2));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/generator.png");
    }
}
