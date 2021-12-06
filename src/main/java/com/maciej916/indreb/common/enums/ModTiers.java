//package com.maciej916.indreb.common.enums;
//
//import net.minecraft.util.LazyLoadedValue;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.Tier;
//import net.minecraft.world.item.crafting.Ingredient;
//
//import java.util.function.Supplier;
//
//public enum ModTiers implements Tier {
//    BRONZE(2, 250, 6.0F, 2.0F, 14, () -> {
//        return Ingredient.of(Items.IRON_INGOT);
//    }),
//    NANO(3, 1561, 8.0F, 3.0F, 10, () -> {
//        return Ingredient.of(ItemStack.EMPTY);
//    });
//
//    private final int level;
//    private final int uses;
//    private final float speed;
//    private final float damage;
//    private final int enchantmentValue;
//    private final LazyLoadedValue<Ingredient> repairIngredient;
//
//    private ModTiers(int p_43332_, int p_43333_, float p_43334_, float p_43335_, int p_43336_, Supplier<Ingredient> p_43337_) {
//        this.level = p_43332_;
//        this.uses = p_43333_;
//        this.speed = p_43334_;
//        this.damage = p_43335_;
//        this.enchantmentValue = p_43336_;
//        this.repairIngredient = new LazyLoadedValue<>(p_43337_);
//    }
//
//    public int getUses() {
//        return this.uses;
//    }
//
//    public float getSpeed() {
//        return this.speed;
//    }
//
//    public float getAttackDamageBonus() {
//        return this.damage;
//    }
//
//    public int getLevel() {
//        return this.level;
//    }
//
//    public int getEnchantmentValue() {
//        return this.enchantmentValue;
//    }
//
//    public Ingredient getRepairIngredient() {
//        return this.repairIngredient.get();
//    }
//
//    @javax.annotation.Nullable public net.minecraft.tags.Tag<net.minecraft.world.level.block.Block> getTag() { return net.minecraftforge.common.ForgeHooks.getTagFromVanillaTier(this); }
//}
