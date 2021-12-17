package com.maciej916.indreb.common.block.impl.machines.recycler;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.BlockEntityElectricFurnace;
import com.maciej916.indreb.common.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import com.maciej916.indreb.common.screen.progress.GuiProgressRecycler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenRecycler extends IndRebScreen<ContainerRecycler> {

    public ScreenRecycler(ContainerRecycler container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnly(new GuiProgressRecycler(this, 71, 35, ((BlockEntityRecycler) getBlockEntity()).progress));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/recycler.png");
    }
}
