package com.maciej916.indreb.common.block.impl.machines.canning_machine;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.BlockEntityAlloySmelter;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import com.maciej916.indreb.common.screen.progress.GuiProgressExtracting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenCanningMachine extends BetterScreen<ContainerCanningMachine> {

    public ScreenCanningMachine(ContainerCanningMachine container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityCanningMachine be = (BlockEntityCanningMachine) getBlockEntity();

        addRenderableOnlyComponent(new GuiProgressArrow(this, 76, 35, be.progress));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/canning_machine.png");
    }
}
