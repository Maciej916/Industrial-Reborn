package com.maciej916.indreb.common.block.impl.machine.t_simple.iron_furnace;

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
        menu.getContainerData().updateProgressFloatData(0, entity.progressBurn);
        menu.getContainerData().updateProgressFloatData(1, entity.progressSmelting);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/iron_furnace.png");
    }
}
