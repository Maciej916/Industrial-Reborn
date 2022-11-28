package com.maciej916.indreb.common.block.impl.machines.basic.canning_machine;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.ArrowProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenCanningMachine extends IndRebScreen<MenuCanningMachine> {

    BlockEntityCanningMachine entity;

    public ScreenCanningMachine(MenuCanningMachine menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityCanningMachine) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new ArrowProgressWidget(this, 76, 35, entity.progressRecipe));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.progressRecipe.setContainerDataBoth(menu.getData().get(0), menu.getData().get(1));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/canning_machine.png");
    }
}
