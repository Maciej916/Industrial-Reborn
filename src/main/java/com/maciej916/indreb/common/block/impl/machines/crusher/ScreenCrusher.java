package com.maciej916.indreb.common.block.impl.machines.crusher;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.progress.GuiProgressCrushing;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenCrusher extends BetterScreen<MenuCrusher> {

    public ScreenCrusher(MenuCrusher container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnlyComponent(new GuiProgressCrushing(this, 71, 35, ((BlockEntityCrusher) getBlockEntity()).progress));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/standard_machine.png");
    }
}
