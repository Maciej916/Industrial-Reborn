package com.maciej916.indreb.common.multiblock.reactor;

import com.maciej916.indreb.common.api.multiblock.TriPredicate;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.tag.ModBlockTags;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public enum ReactorPartIndex implements StringRepresentable {
    UNFORMED("unformed", -1, -1, -1, null),
    P000("p000", 0, 0, 0, ReactorPartIndex::isValidFrame),
    P001("p001", 0, 0, 1,  ReactorPartIndex::isValidFrame),
    P002("p002", 0, 0, 2,  ReactorPartIndex::isValidFrame),
    P010("p010", 0, 1, 0,  ReactorPartIndex::isValidControlRod),
    P011("p011", 0, 1, 1,  ReactorPartIndex::isValidChamber),
    P012("p012", 0, 1, 2,  ReactorPartIndex::isValidControlRod),
    P020("p020", 0, 2, 0,  ReactorPartIndex::isValidFrame),
    P021("p021", 0, 2, 1,  ReactorPartIndex::isValidFrame),
    P022("p022", 0, 2, 2,  ReactorPartIndex::isValidFrame),
    P100("p100", 1, 0, 0,  ReactorPartIndex::isValidFrame),
    P101("p101", 1, 0, 1,  ReactorPartIndex::isValidChamber),
    P102("p102", 1, 0, 2,  ReactorPartIndex::isValidFrame),
    P110("p110", 1, 1, 0,  ReactorPartIndex::isValidChamber),
    P111("p111", 1, 1, 1, ReactorPartIndex::isValidReactor),
    P112("p112", 1, 1, 2,  ReactorPartIndex::isValidChamber),
    P120("p120", 1, 2, 0,  ReactorPartIndex::isValidFrame),
    P121("p121", 1, 2, 1,  ReactorPartIndex::isValidChamber),
    P122("p122", 1, 2, 2,  ReactorPartIndex::isValidFrame),
    P200("p200", 2, 0, 0,  ReactorPartIndex::isValidFrame),
    P201("p201", 2, 0, 1,  ReactorPartIndex::isValidFrame),
    P202("p202", 2, 0, 2,  ReactorPartIndex::isValidFrame),
    P210("p210", 2, 1, 0,  ReactorPartIndex::isValidControlRod),
    P211("p211", 2, 1, 1,  ReactorPartIndex::isValidChamber),
    P212("p212", 2, 1, 2,  ReactorPartIndex::isValidControlRod),
    P220("p220", 2, 2, 0,  ReactorPartIndex::isValidFrame),
    P221("p221", 2, 2, 1,  ReactorPartIndex::isValidFrame),
    P222("p222", 2, 2, 2,  ReactorPartIndex::isValidFrame);

    // Optimization
    public static final ReactorPartIndex[] VALUES = ReactorPartIndex.values();

    private final String name;
    private final int dx;
    private final int dy;
    private final int dz;
    private final TriPredicate<Level, BlockPos, Boolean> predicate;

    ReactorPartIndex(String name, int dx, int dy, int dz, TriPredicate<Level, BlockPos, Boolean> predicate) {
        this.name = name;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.predicate = predicate;
    }

    public static ReactorPartIndex getIndex(int dx, int dy, int dz) {

        // CAN BE OPTIMIZED
        for (ReactorPartIndex i : VALUES) {
            if (i != UNFORMED && i.dx == dx && i.dy == dy && i.dz == dz) {
                return i;
            }
        }

        throw new IllegalStateException("Should never be here! Missing position for state: x: " + dx + " y:" + dy + " z:" + dz);
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getDz() {
        return dz;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public boolean testPlacement(Level level, BlockPos pos, boolean formed) {
        return predicate.test(level, pos, formed);
    }

    private static boolean isValidFrame(Level level, BlockPos pos, boolean formed) {
        BlockState state = level.getBlockState(pos);

        if (state.is(ModBlockTags.REACTOR_PART) && state.getBlock() == ModBlocks.REACTOR_FRAME.get()) {
            if (formed) {
                return state.getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED;
            } else {
                return state.getValue(BlockStateHelper.REACTOR_PART) == ReactorPartIndex.UNFORMED;
            }
        }
        return false;
    }

    private static boolean isValidControlRod(Level level, BlockPos pos, boolean formed) {
        BlockState state = level.getBlockState(pos);

        if (state.is(ModBlockTags.REACTOR_PART) && state.getBlock() == ModBlocks.REACTOR_CONTROL_ROD.get()) {
            if (formed) {
                return state.getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED;
            } else {
                return state.getValue(BlockStateHelper.REACTOR_PART) == ReactorPartIndex.UNFORMED;
            }
        }
        return false;
    }

    private static boolean isValidChamber(Level level, BlockPos pos, boolean formed) {
        BlockState state = level.getBlockState(pos);

        if (state.is(ModBlockTags.REACTOR_PART) && state.getBlock() == ModBlocks.REACTOR_CHAMBER.get()) {
            if (formed) {
                return state.getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED;
            } else {
                return state.getValue(BlockStateHelper.REACTOR_PART) == ReactorPartIndex.UNFORMED;
            }
        }
        return false;
    }
    
    private static boolean isValidReactor(Level level, BlockPos pos, boolean formed) {
        BlockState state = level.getBlockState(pos);

        if (state.is(ModBlockTags.REACTOR_PART) && state.getBlock() == ModBlocks.NUCLEAR_REACTOR.get()) {
            if (formed) {
                return state.getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED;
            } else {
                return state.getValue(BlockStateHelper.REACTOR_PART) == ReactorPartIndex.UNFORMED;
            }
        }
        return false;
    }
}
