package com.maciej916.indreb.common.api.screen.widget.progress;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.blockentity.interfaces.IProgress;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseProgressWidget;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LightProgressWidget extends BaseProgressWidget {

    public LightProgressWidget(IGuiHelper helper, int x, int y, IProgress progress) {
        super(helper, x, y, progress, GuiSprite.SOLAR_SUN, Direction.VERTICAL, true);
    }

    @Override
    public @NotNull ResourceLocation getResourceLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/process.png");
    }
}
