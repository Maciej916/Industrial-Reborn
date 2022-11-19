package com.maciej916.indreb.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class SpriteUtil {

    public static TextureAtlasSprite getSprite(ResourceLocation spriteLocation) {
        return Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(spriteLocation);
    }

    public static TextureAtlasSprite getFluidSprite(@Nonnull FluidStack fluidStack) {
        Fluid fluid = fluidStack.getFluid();
        ResourceLocation spriteLocation = IClientFluidTypeExtensions.of(fluid).getStillTexture(fluidStack);
        return getSprite(spriteLocation);
    }

}
