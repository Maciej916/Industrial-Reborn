package com.maciej916.indreb.common.block.impl.machine.basic.extruder;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.bar.GuiFluidStorageVerticalWidget;
import com.maciej916.indreb.common.api.screen.widget.button.GuiBackwardButton;
import com.maciej916.indreb.common.api.screen.widget.button.GuiForwardButton;
import com.maciej916.indreb.common.api.screen.widget.progress.ExtractingProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenExtruder extends IndRebScreen<MenuExtruder> {

    BlockEntityExtruder entity;

    public ScreenExtruder(MenuExtruder menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityExtruder) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new ExtractingProgressWidget(this, 76, 35, entity.progressRecipe));
        addRenderableOnlyWidget(new GuiFluidStorageVerticalWidget(this, 7, 18, entity.firstTank));
        addRenderableOnlyWidget(new GuiFluidStorageVerticalWidget(this, 44, 18, entity.secondTank));

        addUssableWidget(new GuiForwardButton(this, 99, 61, entity.changeRecipeClient(false), null));
        addUssableWidget(new GuiBackwardButton(this, 65, 61, entity.changeRecipeClient(true), null));
    }

    @Override
    public void updateData() {
        super.updateData();
        menu.getContainerData().updateProgressFloatData(0, entity.progressRecipe);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/extruder.png");
    }
}
