package com.maciej916.indreb.common.api.screen.widget.bar;

import com.maciej916.indreb.common.api.fluid.FluidStorage;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseFluidBarWidget;

public class GuiFluidStorageVerticalWidget extends BaseFluidBarWidget {

    public GuiFluidStorageVerticalWidget(IGuiHelper helper, int x, int y, FluidStorage fluidStorage) {
        super(helper, x, y, 16, 49, 0, 50, fluidStorage);
    }
}
