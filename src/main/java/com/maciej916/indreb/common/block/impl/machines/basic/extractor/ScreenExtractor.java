package com.maciej916.indreb.common.block.impl.machines.basic.extractor;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.ExtractingProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenExtractor extends IndRebScreen<MenuExtractor> {

    BlockEntityExtractor entity;

    public ScreenExtractor(MenuExtractor menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityExtractor) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new ExtractingProgressWidget(this, 71, 35, entity.progressRecipe));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.progressRecipe.setContainerDataBoth(menu.getData().get(0), menu.getData().get(1));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/standard_machine.png");
    }
}
