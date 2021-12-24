package com.maciej916.indreb.common.screen.bar;

import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class GuiFluidBarVerticalLarge extends GuiFluidBar {

    public GuiFluidBarVerticalLarge(IGuiWrapper wrapper, int leftOffset, int topOffset, FluidTank fluidStorage) {
        super(wrapper, 36, 49, leftOffset, topOffset, fluidStorage, 36,50);
    }
}
