package com.maciej916.indreb.common.block.impl.reactor.reactor_chanber;

import com.maciej916.indreb.common.block.impl.reactor.nuclear_reactor.BlockEntityNuclearReactor;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockEntityReactorChamber extends IndRebBlockEntity {

    BlockPos linkedReactor;
    BlockEntityNuclearReactor entityNuclearReactor;

    public BlockEntityReactorChamber(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.REACTOR_CHAMBER.get(), blockPos, blockState);
    }

    @Override
    public void tickClient(BlockState state) {
        if (entityNuclearReactor == null) {
            initNuclearReactorEntity();
        }
    }

    @Override
    public void tickWork(BlockState state) {
        if (entityNuclearReactor == null) {
            initNuclearReactorEntity();
        }
    }

    @Override
    public void load(CompoundTag tag) {
        this.linkedReactor = NbtUtils.readBlockPos(tag.getCompound("linkedReactor"));
        super.load(tag);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("linkedReactor", NbtUtils.writeBlockPos(linkedReactor));
        return super.save(tag);
    }

    @Override
    public void onPlace(boolean isClient) {
        BlockPos reactorBlockPos = BlockPos.findClosestMatch(getBlockPos(), 1, 1, (blockPos) -> level.getBlockState(blockPos).is(ModBlocks.NUCLEAR_REACTOR.get())).orElse(null);
        if (reactorBlockPos != null) {
            linkNuclearReactor(reactorBlockPos);
        } else {
            level.destroyBlock(getBlockPos(), true);
        }

        super.onPlace(isClient);
    }

    @Override
    public void onBreak(boolean isClient) {
        if (!isClient) {
            onBreakChamber();
        }
        unlinkNuclearReactor();
        super.onBreak(isClient);
    }

    public BlockEntityNuclearReactor getEntityNuclearReactor() {
        return entityNuclearReactor;
    }

    public void initNuclearReactorEntity() {
        entityNuclearReactor = (BlockEntityNuclearReactor) level.getBlockEntity(linkedReactor);
    }

    public void linkNuclearReactor(BlockPos pos) {
        linkedReactor = pos;
        entityNuclearReactor = (BlockEntityNuclearReactor) level.getBlockEntity(pos);
        entityNuclearReactor.linkReactorChamber(getBlockPos());
    }

    public void unlinkNuclearReactor() {
        entityNuclearReactor.unlinkReactorChamber(getBlockPos());
    }

    public void onBreakChamber() {
        NonNullList<ItemStack> stacks = entityNuclearReactor.getBreakChamberItems();
        Containers.dropContents(getLevel(), getBlockPos(), stacks);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (entityNuclearReactor != null) {
            return entityNuclearReactor.getCapability(cap, side);
        }
        return LazyOptional.empty();
    }
}
