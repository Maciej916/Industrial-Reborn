package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.capabilities.FluidHandler;
import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.fluids.ConstructionFoam;
import com.maciej916.indreb.common.fluids.ReinforcedConstructionFoam;
import com.maciej916.indreb.common.item.base.FluidItem;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.registries.ModItemGroups;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FoamSprayer extends FluidItem {

    public FoamSprayer() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == ModItemGroups.MAIN_ITEM_GROUP || pCategory == CreativeModeTab.TAB_SEARCH) {
            for (Fluid fluid : ForgeRegistries.FLUIDS) {
                if (fluid == ConstructionFoam.STILL_FLUID || fluid == ReinforcedConstructionFoam.STILL_FLUID) {
                    ItemStack stack = new ItemStack(this);
                    FluidHandler cap = (FluidHandler) CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
                    if (cap != null) {
                        cap.setFluidStack(new FluidStack(fluid, ServerConfig.foam_sprayer_capacity.get()));
                        pItems.add(stack);
                    }
                }
            }
        }

        super.fillItemCategory(pCategory, pItems);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new FluidHandler(stack, ServerConfig.foam_sprayer_capacity.get());
    }

    @Override
    public InteractionResult useOn(UseOnContext onContext) {
        Level level = onContext.getLevel();
        BlockPos pos = onContext.getClickedPos();
        Direction clickedFace = onContext.getClickedFace();
        BlockPos relativePos = pos.relative(clickedFace);
        ItemStack stack = onContext.getItemInHand();

        if (onContext.getPlayer() != null) {
            Player player = onContext.getPlayer();
            FluidHandler cap = (FluidHandler) CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                FluidStack fluid = cap.getFluidInTank(0);
                BlockState block = fluid.getFluid() == ConstructionFoam.STILL_FLUID ? ModBlocks.CONSTRUCTION_FOAM.getBlock().defaultBlockState() : fluid.getFluid() == ReinforcedConstructionFoam.STILL_FLUID ? ModBlocks.REINFORCED_CONSTRUCTION_FOAM.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState();
                if (block.getBlock() != Blocks.AIR && fluid.getAmount() >= 100) {
                    List<BlockPos> validLocations;

                    float xRoot = player.getXRot();
                    if (xRoot > 30 || xRoot < -30) {
                        validLocations = placeHorizontal(level, relativePos);
                    } else {
                        validLocations = placeVertical(level, relativePos, clickedFace);
                    }

                    boolean success = false;
                    int max = (int) Math.floor(fluid.getAmount() / 100f);
                    int i = 0;
                    for (BlockPos validPos: validLocations) {
                        if (i <= max) {
                            if (!player.isCreative()) {
                                i++;
                                cap.drain(100, IFluidHandler.FluidAction.EXECUTE);
                                player.setItemInHand(onContext.getHand(), cap.getContainer());
                            }
                            success = true;
                            level.setBlock(validPos, block, 2);
                        } else {
                            break;
                        }
                    }

                    return success ? InteractionResult.SUCCESS :InteractionResult.PASS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    private List<BlockPos> placeHorizontal(Level level, BlockPos startPos) {
        List<BlockPos> blockPos = new ArrayList<>();

        if (level.getBlockState(startPos).getBlock() == Blocks.AIR) {
            blockPos.add(startPos);
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                BlockPos offsetPos = startPos.offset(i, 0, j);
                if (level.getBlockState(offsetPos).getBlock() == Blocks.AIR) {
                    blockPos.add(offsetPos);
                }
            }
        }

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if ((i > 1 || i < -1) || (j > 1 || j < -1)) {
                    if ((i == -2 && j == -2) || (i == 2 && j == -2) || (i == -2 && j == 2) || (i == 2 && j == 2)) {
                        continue;
                    }
                    BlockPos offsetPos = startPos.offset(i, 0, j);
                    if (level.getBlockState(offsetPos).getBlock() == Blocks.AIR) {
                        blockPos.add(offsetPos);
                    }
                }
            }
        }

        return blockPos;
    }

    private List<BlockPos> placeVertical(Level level, BlockPos startPos, Direction clickedFace) {
        List<BlockPos> blockPos = new ArrayList<>();

        if (level.getBlockState(startPos).getBlock() == Blocks.AIR) {
            blockPos.add(startPos);
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                BlockPos offsetPos;
                if (clickedFace == Direction.WEST || clickedFace == Direction.EAST) {
                    offsetPos = startPos.offset(0, i, j);
                } else {
                    offsetPos = startPos.offset(i, j, 0);
                }

                if (level.getBlockState(offsetPos).getBlock() == Blocks.AIR) {
                    blockPos.add(offsetPos);
                }
            }
        }

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if ((i > 1 || i < -1) || (j > 1 || j < -1)) {
                    if ((i == -2 && j == -2) || (i == 2 && j == -2) || (i == -2 && j == 2) || (i == 2 && j == 2)) {
                        continue;
                    }

                    BlockPos offsetPos;
                    if (clickedFace == Direction.WEST || clickedFace == Direction.EAST) {
                        offsetPos = startPos.offset(0, i, j);
                    } else {
                        offsetPos = startPos.offset(i, j, 0);
                    }

                    if (level.getBlockState(offsetPos).getBlock() == Blocks.AIR) {
                        blockPos.add(offsetPos);
                    }
                }
            }
        }

        return blockPos;
    }

    public static float getFillRatio(ItemStack stack) {
        return CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getIfPresentElse(e -> (float) e.getFluidInTank(1).getAmount() / e.getTankCapacity(1), 0f);
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F - ((1 - getFillRatio(stack)) * 13.0F));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float f = Math.max(0.0F, getFillRatio(stack));
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }
}
