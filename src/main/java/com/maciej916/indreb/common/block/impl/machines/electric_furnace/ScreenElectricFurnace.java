package com.maciej916.indreb.common.block.impl.machines.electric_furnace;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenElectricFurnace extends BetterScreen<ContainerElectricFurnace> {

    public ScreenElectricFurnace(ContainerElectricFurnace container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnlyComponent(new GuiProgressArrow(this, 71, 35, ((BlockEntityElectricFurnace) getBlockEntity()).progress));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/electric_furnace.png");
    }
}
