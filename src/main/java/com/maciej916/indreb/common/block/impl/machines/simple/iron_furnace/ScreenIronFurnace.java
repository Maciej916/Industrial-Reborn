package com.maciej916.indreb.common.block.impl.machines.simple.iron_furnace;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.progress.ArrowProgressWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.SmeltingProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenIronFurnace extends IndRebScreen<MenuIronFurnace> {

    BlockEntityIronFurnace entity;

    public ScreenIronFurnace(MenuIronFurnace menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityIronFurnace) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new SmeltingProgressWidget(this, 56, 35, entity.progressBurn));
        addRenderableOnlyWidget(new ArrowProgressWidget(this, 79, 35, entity.progressSmelting));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.progressBurn.setContainerDataBoth(menu.getData().get(0), menu.getData().get(1));
        entity.progressSmelting.setContainerDataBoth(menu.getData().get(2), menu.getData().get(3));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/iron_furnace.png");
    }
}
