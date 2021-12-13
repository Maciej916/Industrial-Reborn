package com.maciej916.indreb.datagen.loot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import java.util.List;

public class DungeonLootEnhancerModifier extends LootModifier {
    private final float chance;

    public DungeonLootEnhancerModifier(final LootItemCondition[] conditionsIn, final float chance) {
        super(conditionsIn);
        this.chance = chance;
    }

    public DungeonLootEnhancerModifier(final LootItemCondition[] conditionsIn) {
        this(conditionsIn, 0.1F);
    }

    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        double d = Math.random();
        if (d < chance) {
            generatedLoot.add(new ItemStack(ModItems.IRIDIUM_SHARD));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<DungeonLootEnhancerModifier> {
        @Override
        public DungeonLootEnhancerModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
            final float chance = GsonHelper.getAsFloat(object, "chance", 0.1F);
            if (chance <= 0) throw new JsonParseException("Unable to set a chance to a number lower than 1");
            return new DungeonLootEnhancerModifier(conditions, chance);
        }

        @Override
        public JsonObject write(DungeonLootEnhancerModifier instance) {
            final JsonObject obj = this.makeConditions(instance.conditions);
            obj.addProperty("chance", instance.chance);
            return obj;
        }
    }
}