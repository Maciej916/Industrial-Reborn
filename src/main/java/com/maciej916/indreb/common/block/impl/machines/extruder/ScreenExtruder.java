package com.maciej916.indreb.common.block.impl.machines.extruder;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVertical;
import com.maciej916.indreb.common.screen.button.GuiBackwardButton;
import com.maciej916.indreb.common.screen.button.GuiForwardButton;
import com.maciej916.indreb.common.screen.progress.GuiProgressExtracting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenExtruder extends BetterScreen<ContainerExtruder> {

    public ScreenExtruder(ContainerExtruder container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityExtruder be = (BlockEntityExtruder) getBlockEntity();

        addRenderableComponent(new GuiForwardButton(this, 99, 61, be.prevRecipe()));
        addRenderableComponent(new GuiBackwardButton(this, 65, 61, be.nextRecipe()));
        addRenderableOnlyComponent(new GuiProgressExtracting(this, 76, 35, be.progress));
        addRenderableOnlyComponent(new GuiFluidBarVertical(this, 44, 18, be.lavaStorage));
        addRenderableOnlyComponent(new GuiFluidBarVertical(this, 7, 18, be.waterStorage));


        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/extruder.png");
    }
}
