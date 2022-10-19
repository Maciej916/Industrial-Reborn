package com.maciej916.indreb.common.block.impl.machines.fermenter;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.bar.GuiFertilizerBar;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVertical;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVerticalLarge;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import com.maciej916.indreb.common.screen.progress.GuiProgressFill;
import com.maciej916.indreb.common.screen.text.GuiTextHeat;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenFermenter extends BetterScreen<ContainerFermenter> {

    public ScreenFermenter(ContainerFermenter container, Inventory inv, Component name) {
        super(container, inv, name);
        this.imageHeight = 256;
        this.inventoryLabelY = 94;
    }

    @Override
    public void init() {
        super.init();

        BlockEntityFermenter be = (BlockEntityFermenter) getBlockEntity();

        addRenderableOnlyComponent(new GuiProgressFill(this, 128, 39, be.progressDrain));
        addRenderableOnlyComponent(new GuiFluidBarVertical(this, 108, 18, be.fluidOutputStorage));
        addRenderableOnlyComponent(new GuiProgressArrow(this, 76, 35, be.progress));
        addRenderableOnlyComponent(new GuiFertilizerBar(this, 12, 74, be.progressWaste));
        addRenderableOnlyComponent(new GuiProgressFill(this, 14, 39, be.progressFill));
        addRenderableOnlyComponent(new GuiFluidBarVerticalLarge(this, 32, 18, be.fluidInputStorage));
        addRenderableOnlyComponent(new GuiTextHeat(this, 20, 10, 88, 82, be.heatLevel));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/fermenter.png");
    }
}
