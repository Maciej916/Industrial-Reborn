package com.maciej916.indreb.common.block.impl.machines.ore_washing_plant;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVertical;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import com.maciej916.indreb.common.screen.progress.GuiProgressFill;
import com.maciej916.indreb.common.screen.progress.GuiProgressOreWashing;
import com.maciej916.indreb.common.screen.widgets.GuiText;
import com.maciej916.indreb.common.screen.widgets.GuiTextCurrentProgress;
import com.maciej916.indreb.common.screen.widgets.GuiTextProgress;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenOreWashingPlant extends BetterScreen<MenuOreWashingPlant> {

    public ScreenOreWashingPlant(MenuOreWashingPlant container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityOreWashingPlant be = (BlockEntityOreWashingPlant) getBlockEntity();

        addRenderableOnlyComponent(new GuiFluidBarVertical(this, 27, 18, be.fluidStorage));
        addRenderableOnlyComponent(new GuiProgressFill(this, 9, 39, be.progressFill));
        addRenderableOnlyComponent(new GuiProgressOreWashing(this, 90, 32, be.progress));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/ore_washing_plant.png");
    }
}
