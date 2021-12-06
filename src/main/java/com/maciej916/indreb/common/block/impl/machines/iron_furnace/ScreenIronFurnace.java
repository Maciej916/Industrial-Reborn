package com.maciej916.indreb.common.block.impl.machines.iron_furnace;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import com.maciej916.indreb.common.screen.progress.GuiProgressFuel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenIronFurnace extends IndRebScreen<ContainerIronFurnace> {

    public ScreenIronFurnace(ContainerIronFurnace container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnly(new GuiProgressArrow(this, 79, 35, ((BlockEntityIronFurnace) getBlockEntity()).smelting));
        addRenderableOnly(new GuiProgressFuel(this, 56, 35, ((BlockEntityIronFurnace) getBlockEntity()).fuel));

    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/iron_furnace.png");
    }
}
