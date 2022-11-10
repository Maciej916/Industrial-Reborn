package com.maciej916.indreb.common.block.impl.machines.metal_former;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.metal_former.screen.GuiMetalFormerMode;
import com.maciej916.indreb.common.block.impl.machines.metal_former.screen.GuiMetalFormerProgress;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.button.GuiButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nullable;

public class ScreenMetalFormer extends BetterScreen<MenuMetalFormer> {

    public ScreenMetalFormer(MenuMetalFormer container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityMeralFormer be = (BlockEntityMeralFormer) getBlockEntity();

        addRenderableOnlyComponent(new GuiMetalFormerProgress(this, 71, 34, be));
        addRenderableComponent(new GuiMetalFormerMode(this, 73, 53, be, be.clientClickChangeMode()));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/metal_former.png");
    }
}
