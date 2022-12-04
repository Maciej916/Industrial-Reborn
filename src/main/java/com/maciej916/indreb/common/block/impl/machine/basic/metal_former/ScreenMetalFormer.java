package com.maciej916.indreb.common.block.impl.machine.basic.metal_former;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.enums.MetalFormerMode;
import com.maciej916.indreb.common.screen.widget.button.ChangeModeButtonWidget;
import com.maciej916.indreb.common.screen.widget.progress.MetalFormerProgressWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenMetalFormer extends IndRebScreen<MenuMetalFormer> {

    BlockEntityMetalFormer entity;

    public ScreenMetalFormer(MenuMetalFormer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityMetalFormer) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new MetalFormerProgressWidget(this, 71, 34, entity));
        addUssableWidget(new ChangeModeButtonWidget(this, 14, 33, entity.changeModeClient(), false));
    }

    @Override
    public void updateData() {
        super.updateData();

        menu.getContainerData().updateProgressFloatData(0, entity.progressRecipe);
        entity.setMode(MetalFormerMode.getModeFromId(menu.getContainerData().getIntData(1)));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/metal_former.png");
    }
}
