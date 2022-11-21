package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.api.capability.FluidHandlerStack;
import com.maciej916.indreb.common.api.item.base.BaseFluidItem;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.fluid.impl.ConstructionFoam;
import com.maciej916.indreb.common.fluid.impl.ReinforcedConstructionFoam;
import com.maciej916.indreb.common.item.ModItemGroups;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FoamSprayer extends BaseFluidItem {

    public FoamSprayer() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == ModItemGroups.MAIN || pCategory == CreativeModeTab.TAB_SEARCH) {
            for (Fluid fluid : ForgeRegistries.FLUIDS) {
                if (fluid == ConstructionFoam.STILL_FLUID || fluid == ReinforcedConstructionFoam.STILL_FLUID) {
                    ItemStack stack = new ItemStack(this);
                    FluidHandlerStack cap = (FluidHandlerStack) CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
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
        return new FluidHandlerStack(stack, ServerConfig.foam_sprayer_capacity.get());
    }


//    public static BlockHitResult willItemTouchMaterialOnUse(Player player, Level level, int itemReach) {
//        final Vec3 vec3 = player.getEyePosition(1.0F);
//        final Vec3 vec31 = player.getViewVector(1.0F);
//
//        Vec3 prevVector = null;
//        BlockPos prevBlockPos = null;
//        for (int i = 1; i <= itemReach; i++)  {
//            Vec3 vec32 = vec3.add(vec31.x * i, vec31.y * i, vec31.z * i);
//            BlockPos blockPos = new BlockPos(vec32.x, vec32.y, vec32.z);
//            BlockState blockState = level.getBlockState(blockPos);
//            if (blockState.getMaterial() != Material.AIR) {
//
//                System.out.println("diff");
//                System.out.println(prevBlockPos);
//                System.out.println(blockPos);
//                System.out.println(";");
//
//                for (Direction dir : Constants.DIRECTIONS) {
//                    System.out.println(blockPos.relative(dir));
//                    BlockPos relativePos = blockPos.relative(dir);
//
//                    if (relativePos.equals(prevBlockPos)) {
////                        blockState.canBeReplaced(new BlockPlaceContext())
//
//                        if (blockState.getMaterial().isReplaceable()) {
//                            return new BlockHitResult(vec32, dir.getOpposite(), blockPos, false);
//                        } else {
//                            return new BlockHitResult(prevVector, dir.getOpposite(), prevBlockPos, false);
//                        }
//
//
//                    }
//                }
//            }
//            prevBlockPos = blockPos;
//            prevVector = vec32;
//        }
//
//        return null;
//    }
//
//
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
//        BlockHitResult hitResult = willItemTouchMaterialOnUse(player, level, 10);
//        if (hitResult != null) {
////        Level level = onContext.getLevel();
//            BlockPos pos = hitResult.getBlockPos();
//            Direction clickedFace = hitResult.getDirection();
//            BlockPos relativePos = pos.relative(clickedFace);
//            ItemStack stack = player.getItemInHand(usedHand);
//
//            FluidHandlerStack cap = (FluidHandlerStack) CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
//
//            System.out.println(cap);
//
//            if (cap != null) {
//                FluidStack fluid = cap.getFluidInTank(0);
//                BlockState block = fluid.getFluid() == ConstructionFoam.STILL_FLUID ? ModBlocks.CONSTRUCTION_FOAM.get().defaultBlockState() : fluid.getFluid() == ReinforcedConstructionFoam.STILL_FLUID ? ModBlocks.REINFORCED_CONSTRUCTION_FOAM.get().defaultBlockState() : Blocks.AIR.defaultBlockState();
//                if (block.getBlock() != Blocks.AIR && fluid.getAmount() >= 100) {
//                    List<BlockPos> validLocations;
//
//                    float xRoot = player.getXRot();
//                    if (xRoot > 30 || xRoot < -30) {
//                        validLocations = placeHorizontal(level, relativePos);
//                    } else {
//                        validLocations = placeVertical(level, relativePos, clickedFace);
//                    }
//
//                    boolean success = false;
//                    int max = (int) Math.floor(fluid.getAmount() / 100f);
//                    int i = 0;
//                    for (BlockPos validPos: validLocations) {
//                        if (i <= max) {
//                            if (!player.isCreative()) {
//                                i++;
//                                cap.drain(100, IFluidHandler.FluidAction.EXECUTE);
//                                player.setItemInHand(usedHand, cap.getContainer());
//                            }
//                            success = true;
//                            level.setBlock(validPos, block, 2);
//                        } else {
//                            break;
//                        }
//                    }
//
//                    return success ? InteractionResultHolder.success(stack) : InteractionResultHolder.pass(stack);
//                }
//            }
//        }
//
//        return super.use(level, player, usedHand);
//    }

    @Override
    public InteractionResult useOn(UseOnContext onContext) {
        Level level = onContext.getLevel();
        BlockPos pos = onContext.getClickedPos();
        Direction clickedFace = onContext.getClickedFace();
        BlockPos relativePos = pos.relative(clickedFace);
        ItemStack stack = onContext.getItemInHand();
        if (onContext.getPlayer() != null) {
            Player player = onContext.getPlayer();
            FluidHandlerStack cap = (FluidHandlerStack) CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                FluidStack fluid = cap.getFluidInTank(0);
                BlockState block = fluid.getFluid() == ConstructionFoam.STILL_FLUID ? ModBlocks.CONSTRUCTION_FOAM.get().defaultBlockState() : fluid.getFluid() == ReinforcedConstructionFoam.STILL_FLUID ? ModBlocks.REINFORCED_CONSTRUCTION_FOAM.get().defaultBlockState() : Blocks.AIR.defaultBlockState();
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
                            level.playSound(null, validPos.getX(), validPos.getY(), validPos.getZ(), SoundEvents.POWDER_SNOW_PLACE, SoundSource.BLOCKS, 0.5F, 0.4F / (player.getRandom().nextFloat() * 0.4F + 0.8F));
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

        BlockState stateStart = level.getBlockState(startPos);
        if (stateStart.getBlock() == Blocks.AIR || stateStart.getMaterial().isReplaceable()) {
            blockPos.add(startPos);
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                BlockPos offsetPos = startPos.offset(i, 0, j);
                BlockState state = level.getBlockState(offsetPos);
                if (state.getBlock() == Blocks.AIR || state.getMaterial().isReplaceable()) {
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
                    BlockState state = level.getBlockState(offsetPos);
                    if (state.getBlock() == Blocks.AIR || state.getMaterial().isReplaceable()) {
                        blockPos.add(offsetPos);
                    }
                }
            }
        }

        return blockPos;
    }

    private List<BlockPos> placeVertical(Level level, BlockPos startPos, Direction clickedFace) {
        List<BlockPos> blockPos = new ArrayList<>();

        BlockState stateStart = level.getBlockState(startPos);
        if (stateStart.getBlock() == Blocks.AIR || stateStart.getMaterial().isReplaceable()) {
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

                BlockState state = level.getBlockState(offsetPos);
                if (state.getBlock() == Blocks.AIR || state.getMaterial().isReplaceable()) {
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

                    BlockState state = level.getBlockState(offsetPos);
                    if (state.getBlock() == Blocks.AIR || state.getMaterial().isReplaceable()) {
                        blockPos.add(offsetPos);
                    }
                }
            }
        }

        return blockPos;
    }

    public static float getFillRatio(ItemStack stack) {
        return CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getIfPresentElse(e -> (float) e.getFluidInTank(1).getAmount() / e.getTankCapacity(1), 0f);
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
