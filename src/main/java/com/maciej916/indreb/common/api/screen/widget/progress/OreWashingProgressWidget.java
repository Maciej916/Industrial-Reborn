package com.maciej916.indreb.common.api.screen.widget.progress;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseProgressWidget;

public class OreWashingProgressWidget extends BaseProgressWidget {
    public OreWashingProgressWidget(IGuiHelper helper, int x, int y, IBaseProgress progress) {
        super(helper, x, y, progress, GuiSprite.ORE_WASHING, Direction.HORIZONTAL, false);
    }
}
