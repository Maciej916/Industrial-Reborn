package com.maciej916.indreb.common.block.impl.machine.t_advanced.matter_fabricator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.bar.GuiFluidStorageVerticalWidget;
import com.maciej916.indreb.common.api.screen.widget.progress.GuiProgressFillWidget;
import com.maciej916.indreb.common.api.screen.widget.text.CurrentProgressTextWidget;
import com.maciej916.indreb.common.api.screen.widget.text.PercentProgressTextWidget;
import com.maciej916.indreb.common.api.screen.widget.text.SimpleTextWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenMatterFabricator extends IndRebScreen<MenuMatterFabricator> {

    BlockEntityMatterFabricator entity;

    public ScreenMatterFabricator(MenuMatterFabricator menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityMatterFabricator) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new GuiProgressFillWidget(this, 153, 39, entity.progressDrain));
        addRenderableOnlyWidget(new GuiFluidStorageVerticalWidget(this, 133, 18, entity.firstTank));

        addRenderableOnlyWidget(new SimpleTextWidget(this, 8, 20, 45, 5, Component.translatable("gui."+ IndReb.MODID + ".text_progress"), 0.8f, 4210752, false));
        addRenderableOnlyWidget(new SimpleTextWidget(this, 8, 36, 45, 5, Component.translatable("gui."+ IndReb.MODID + ".text_amplifier"), 0.8f, 4210752, false));

        addRenderableOnlyWidget(new PercentProgressTextWidget(this, 59, 20, 37, 5, entity.progressRecipe, "", "%", 0.8f, 4210752, false));
        addRenderableOnlyWidget(new CurrentProgressTextWidget(this, 59, 36, 37, 5, entity.progressAmplifier, "", "", 0.8f, 4210752, false));

    }

    @Override
    public void updateData() {
        super.updateData();
        menu.getContainerData().updateProgressFloatData(0, entity.progressRecipe);
        menu.getContainerData().updateProgressIntData(1, entity.progressDrain);
        menu.getContainerData().updateProgressIntData(2, entity.progressAmplifier);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/matter_fabricator.png");
    }
}
