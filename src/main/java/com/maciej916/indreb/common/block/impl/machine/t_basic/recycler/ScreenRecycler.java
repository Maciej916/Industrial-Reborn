package com.maciej916.indreb.common.block.impl.machine.t_basic.recycler;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.RecyclingProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenRecycler extends IndRebScreen<MenuRecycler> {

    BlockEntityRecycler entity;

    public ScreenRecycler(MenuRecycler menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityRecycler) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new RecyclingProgressWidget(this, 71, 35, entity.progressRecipe));
    }

    @Override
    public void updateData() {
        super.updateData();
        menu.getContainerData().updateProgressFloatData(0, entity.progressRecipe);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/recycler.png");
    }
}
