package com.maciej916.indreb.common.api.enums;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum CustomArmorMaterial implements ArmorMaterial {
    BRONZE("bronze", 15, new int[]{3, 6, 7, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F,
            () -> Ingredient.of(ModItemTags.FORGE_INGOTS_BRONZE)),

    HAZMAT("hazmat", 15, new int[]{2, 5, 6, 2}, 4, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
            () -> Ingredient.of(ModItems.RUBBER.get())),

    RUBBER("rubber", 15, new int[]{0, 0, 0, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F,
            () -> Ingredient.of(ModItems.RUBBER.get())),

    NIGHTVISION("nightvision", 15, new int[]{3, 0, 0, 0}, 9, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F,
            () -> Ingredient.of(ItemStack.EMPTY)),

    NANO("nano", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F,
            () -> Ingredient.of(ItemStack.EMPTY));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    CustomArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return IndReb.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}