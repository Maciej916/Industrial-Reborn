package com.maciej916.indreb.common.block.impl.machines.simple.simple_compressor;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.CompressingProgressWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.SmeltingProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenSimpleCompressor extends IndRebScreen<MenuSimpleCompressor> {

    BlockEntitySimpleCompressor entity;

    public ScreenSimpleCompressor(MenuSimpleCompressor menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntitySimpleCompressor) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new SmeltingProgressWidget(this, 21, 26, entity.progressBurn));
        addRenderableOnlyWidget(new CompressingProgressWidget(this, 79, 35, entity.progressRecipe));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.progressBurn.setContainerDataBoth(menu.getData().get(0), menu.getData().get(1));
        entity.progressRecipe.setContainerDataBoth(menu.getData().get(2), menu.getData().get(3));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/simple_machine.png");
    }
}
