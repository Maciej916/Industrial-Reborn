package com.maciej916.indreb.common.item.base;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DiggerElectricItem extends TieredElectricItem {
    private final TagKey<Block> blocks;
    protected final float speed;
    private final float attackDamageBaseline;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public DiggerElectricItem(Tier tier, float attackDamage, float attackSpeed, TagKey<Block> blocks, Properties properties, int energyStored, int maxEnergy, EnergyType energyType, EnergyTier energyTier) {
        super(tier, properties, energyStored, maxEnergy, energyType, energyTier);
        this.blocks = blocks;
        this.speed = tier.getSpeed();
        this.attackDamageBaseline = attackDamage + tier.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", this.attackDamageBaseline, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public int getHurtEnergyCost() {
        return 100;
    }

    public int getMineCost() {
        return 50;
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        return blockState.is(this.blocks) && getEnergy(itemStack).consumeEnergy(getMineCost(), true) > 1 ? this.speed : 1.0F;
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity1) {
        getEnergy(itemStack).consumeEnergy(getHurtEnergyCost(), false);
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        if (!level.isClientSide && blockState.getDestroySpeed(level, blockPos) != 0.0F) {
            getEnergy(itemStack).consumeEnergy(getMineCost(), false);
        }
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
    }

    @Override
    @Deprecated // FORGE: Use stack sensitive variant below
    public boolean isCorrectToolForDrops(BlockState pBlock) {
        if (net.minecraftforge.common.TierSortingRegistry.isTierSorted(getTier())) {
            return net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), pBlock) && pBlock.is(this.blocks);
        }
        int i = this.getTier().getLevel();
        if (i < 3 && pBlock.is(BlockTags.NEEDS_DIAMOND_TOOL)) {
            return false;
        } else if (i < 2 && pBlock.is(BlockTags.NEEDS_IRON_TOOL)) {
            return false;
        } else {
            return i < 1 && pBlock.is(BlockTags.NEEDS_STONE_TOOL) ? false : pBlock.is(this.blocks);
        }
    }

    // FORGE START
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(blocks) && net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), state);
    }
}
