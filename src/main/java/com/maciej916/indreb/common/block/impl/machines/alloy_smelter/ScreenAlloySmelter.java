package com.maciej916.indreb.common.block.impl.machines.alloy_smelter;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import com.maciej916.indreb.common.screen.progress.GuiProgressFuel;
import com.maciej916.indreb.common.screen.text.GuiTextHeat;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenAlloySmelter extends BetterScreen<ContainerAlloySmelter> {

    public ScreenAlloySmelter(ContainerAlloySmelter container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityAlloySmelter be = (BlockEntityAlloySmelter) getBlockEntity();

        addRenderableOnlyComponent(new GuiProgressFuel(this, 37, 41, be.heatLevel));
        addRenderableOnlyComponent(new GuiProgressArrow(this, 81, 33, be.progress));
        addRenderableOnlyComponent(new GuiTextHeat(this, 20, 10, 20, 60, be.heatLevel));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/alloy_smelter.png");
    }
}
