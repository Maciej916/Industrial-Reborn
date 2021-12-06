package com.maciej916.indreb.common.block.impl.machines.alloy_smelter;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.extruder.BlockEntityExtruder;
import com.maciej916.indreb.common.block.impl.machines.extruder.ContainerExtruder;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVertical;
import com.maciej916.indreb.common.screen.button.GuiBackwardButton;
import com.maciej916.indreb.common.screen.button.GuiForwardButton;
import com.maciej916.indreb.common.screen.progress.GuiProgressArrow;
import com.maciej916.indreb.common.screen.progress.GuiProgressExtracting;
import com.maciej916.indreb.common.screen.progress.GuiProgressFuel;
import com.maciej916.indreb.common.screen.text.GuiTextHeat;
import com.maciej916.indreb.common.screen.widgets.GuiText;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenAlloySmelter extends IndRebScreen<ContainerAlloySmelter> {

    public ScreenAlloySmelter(ContainerAlloySmelter container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityAlloySmelter be = (BlockEntityAlloySmelter) getBlockEntity();

        addRenderableOnly(new GuiProgressFuel(this, 37, 41, be.heatLevel));
        addRenderableOnly(new GuiProgressArrow(this, 81, 33, be.progress));
        addRenderableOnly(new GuiTextHeat(this, 20, 10, 20, 60, be.heatLevel));
    }



    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/alloy_smelter.png");
    }
}
