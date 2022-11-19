package com.maciej916.indreb.common.api.screen.widget.bar;

import com.maciej916.indreb.common.api.fluid.FluidStorage;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseFluidBarWidget;

public class GuiFluidStorageVerticalLargeWidget extends BaseFluidBarWidget {

    public GuiFluidStorageVerticalLargeWidget(IGuiHelper helper, int x, int y, FluidStorage fluidStorage) {
        super(helper, x, y, 36, 49, 36,50, fluidStorage);
    }
}
