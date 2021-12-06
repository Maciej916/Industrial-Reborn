package com.maciej916.indreb.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class SpriteUtil {

    public static TextureAtlasSprite getSprite(ResourceLocation spriteLocation) {
        return Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(spriteLocation);
    }

    public static TextureAtlasSprite getFluidSprite(@Nonnull FluidStack fluidStack) {
        Fluid fluid = fluidStack.getFluid();
        ResourceLocation spriteLocation = fluid.getAttributes().getStillTexture(fluidStack);
        return getSprite(spriteLocation);
    }

}
