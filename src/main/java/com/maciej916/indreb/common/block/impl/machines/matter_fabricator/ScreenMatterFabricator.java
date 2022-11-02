package com.maciej916.indreb.common.block.impl.machines.matter_fabricator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.fermenter.BlockEntityFermenter;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVertical;
import com.maciej916.indreb.common.screen.progress.GuiProgressFill;
import com.maciej916.indreb.common.screen.progress.GuiProgressRecycler;
import com.maciej916.indreb.common.screen.widgets.GuiText;
import com.maciej916.indreb.common.screen.widgets.GuiTextCurrentProgress;
import com.maciej916.indreb.common.screen.widgets.GuiTextProgress;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenMatterFabricator extends BetterScreen<MenuMatterFabricator> {

    public ScreenMatterFabricator(MenuMatterFabricator container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityMatterFabricator be = (BlockEntityMatterFabricator) getBlockEntity();

        addRenderableOnlyComponent(new GuiText(this, 45, 5, 8, 20, Component.translatable("gui."+ IndReb.MODID + ".text_progress")));
        addRenderableOnlyComponent(new GuiText(this, 45, 5, 8, 36, Component.translatable("gui."+ IndReb.MODID + ".text_amplifier")));

        addRenderableOnlyComponent(new GuiTextProgress(this, 37, 5, 60, 20, be.progress, "", "%"));
        addRenderableOnlyComponent(new GuiTextCurrentProgress(this, 37, 5, 60, 36, be.progressAmplifier, "", ""));

        addRenderableOnlyComponent(new GuiFluidBarVertical(this, 133, 18, be.fluidMatterStorage));
        addRenderableOnlyComponent(new GuiProgressFill(this, 153, 39, be.progressDrain));
        
        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/matter_fabricator.png");
    }
}
