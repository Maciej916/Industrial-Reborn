package com.maciej916.indreb.common.block.impl.explosive.nuke;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenNuke extends IndRebScreen<MenuNuke> {

    BlockEntityNuke entity;

    public ScreenNuke(MenuNuke menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityNuke) getEntity();
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/nuke.png");
    }
}
