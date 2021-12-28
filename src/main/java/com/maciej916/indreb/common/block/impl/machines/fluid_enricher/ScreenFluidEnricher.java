package com.maciej916.indreb.common.block.impl.machines.fluid_enricher;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.extruder.BlockEntityExtruder;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.bar.GuiFertilizerBar;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVertical;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import com.maciej916.indreb.common.screen.progress.GuiProgressExtracting;
import com.maciej916.indreb.common.screen.progress.GuiProgressFill;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenFluidEnricher extends BetterScreen<ContainerFluidEnricher> {

    public ScreenFluidEnricher(ContainerFluidEnricher container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityFluidEnricher be = (BlockEntityFluidEnricher) getBlockEntity();

        addRenderableOnlyComponent(new GuiProgressArrow(this, 76, 35, be.progress));
        addRenderableOnlyComponent(new GuiFluidBarVertical(this, 52, 18, be.fluidInputStorage));
        addRenderableOnlyComponent(new GuiFluidBarVertical(this, 108, 18, be.fluidOutputStorage));
        addRenderableOnlyComponent(new GuiProgressFill(this, 128, 39, be.progressDrain));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/fluid_enricher.png");
    }
}
