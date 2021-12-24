package com.maciej916.indreb.common.screen.bar;

import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class GuiFluidBarVertical extends GuiFluidBar {

    public GuiFluidBarVertical(IGuiWrapper wrapper, int leftOffset, int topOffset, FluidTank fluidStorage) {
        super(wrapper, 16, 49, leftOffset, topOffset, fluidStorage, 0, 50);
    }
}
