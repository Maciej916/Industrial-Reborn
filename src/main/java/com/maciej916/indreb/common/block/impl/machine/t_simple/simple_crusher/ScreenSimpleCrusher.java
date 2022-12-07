package com.maciej916.indreb.common.block.impl.machine.t_simple.simple_crusher;

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
        addRenderableOnlyWidget(new CrushingProgressWidget(this, 79, 35, entity.progressRecipe));
    }

    @Override
    public void updateData() {
        super.updateData();
        menu.getContainerData().updateProgressFloatData(0, entity.progressBurn);
        menu.getContainerData().updateProgressFloatData(1, entity.progressRecipe);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/simple_machine.png");
    }
}
