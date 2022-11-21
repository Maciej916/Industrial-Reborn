package com.maciej916.indreb.common.item.impl.tool;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.item.base.BaseElectricDiggerItem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ElectricHoe extends BaseElectricDiggerItem {

    private final int energyCostMine;
    private final int energyCostHurt;
    private final int energyCostTile;

    public ElectricHoe(Tier tier, float attackDamage, float attackSpeed, int energyStored, int maxEnergy, int energyCostMine, int energyCostHurt, int energyCostTile, EnergyTier energyTier) {
        super(tier, attackDamage, attackSpeed, BlockTags.MINEABLE_WITH_HOE, new Item.Properties(), energyStored, maxEnergy, EnergyType.RECEIVE, energyTier);
        this.energyCostMine = energyCostMine;
        this.energyCostHurt = energyCostHurt;
        this.energyCostTile = energyCostTile;
    }

    @Override
    public int getHurtEnergyCost() {
        return energyCostMine;
    }

    @Override
    public int getMineCost() {
        return energyCostHurt;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_TOOLS) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        ItemStack itemstack = useOnContext.getItemInHand();
        IEnergyStorage energy = getEnergyStorage(itemstack);
        if (energy.energyStored() > 1) {
            Level level = useOnContext.getLevel();
            BlockPos blockpos = useOnContext.getClickedPos();
            BlockState toolModifiedState = level.getBlockState(blockpos).getToolModifiedState(useOnContext, net.minecraftforge.common.ToolActions.HOE_TILL, false);
            Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = toolModifiedState == null ? null : Pair.of(ctx -> true, changeIntoState(toolModifiedState));
            if (pair == null) {
                return InteractionResult.PASS;
            } else {
                Predicate<UseOnContext> predicate = pair.getFirst();
                Consumer<UseOnContext> consumer = pair.getSecond();
                if (predicate.test(useOnContext)) {
                    Player player = useOnContext.getPlayer();

                    level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!level.isClientSide) {
                        consumer.accept(useOnContext);
                        if (player != null) {
                            getEnergyStorage(itemstack).consumeEnergy(energyCostTile, false);
                        }
                    }

                    return InteractionResult.sidedSuccess(level.isClientSide);
                } else {
                    return InteractionResult.PASS;
                }
            }
        }

        return InteractionResult.PASS;
    }

    public static Consumer<UseOnContext> changeIntoState(BlockState p_150859_) {
        return (p_150848_) -> p_150848_.getLevel().setBlock(p_150848_.getClickedPos(), p_150859_, 11);
    }

    public static Consumer<UseOnContext> changeIntoStateAndDropItem(BlockState p_150850_, ItemLike p_150851_) {
        return (p_150855_) -> {
            p_150855_.getLevel().setBlock(p_150855_.getClickedPos(), p_150850_, 11);
            Block.popResourceFromFace(p_150855_.getLevel(), p_150855_.getClickedPos(), p_150855_.getClickedFace(), new ItemStack(p_150851_));
        };
    }

    public static boolean onlyIfAirAbove(UseOnContext p_150857_) {
        return p_150857_.getClickedFace() != Direction.DOWN && p_150857_.getLevel().getBlockState(p_150857_.getClickedPos().above()).isAir();
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_HOE_ACTIONS.contains(toolAction);
    }
}
