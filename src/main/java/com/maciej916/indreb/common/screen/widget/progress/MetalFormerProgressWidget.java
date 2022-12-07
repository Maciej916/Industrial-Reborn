package com.maciej916.indreb.common.screen.widget.progress;

import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseProgressWidget;
import com.maciej916.indreb.common.block.impl.machine.t_basic.metal_former.BlockEntityMetalFormer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;

public class MetalFormerProgressWidget extends BaseProgressWidget {

    private final BlockEntityMetalFormer entity;

    public MetalFormerProgressWidget(IGuiHelper helper, int x, int y, BlockEntityMetalFormer entity) {
        super(helper, x, y, entity.progressRecipe, GuiSprite.CUTTING, Direction.HORIZONTAL, false);
        this.entity = entity;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, Minecraft pMinecraft, int pMouseX, int pMouseY) {
        this.setProgressType(entity.getMode().getSprite());
        super.renderBg(pPoseStack, pMinecraft, pMouseX, pMouseY);
    }
}
