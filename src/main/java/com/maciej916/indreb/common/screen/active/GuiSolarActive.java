package com.maciej916.indreb.common.screen.active;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import net.minecraft.resources.ResourceLocation;

public class GuiSolarActive extends GuiActive {

    public GuiSolarActive(IGuiWrapper wrapper, int leftOffset, int topOffset, boolean active) {
        super(wrapper, GuiSprite.SOLAR_SUN, leftOffset, topOffset, active);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/process.png");
    }
}
