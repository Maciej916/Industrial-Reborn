package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.item.base.BaseItem;
import com.maciej916.indreb.common.recipe.impl.ScrapBoxRecipe;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScrapBox extends BaseItem {
    private static final List<ScrapBox> SCRAP_BOX = new ArrayList<>();

    public ScrapBox() {
        super(new Properties());
        SCRAP_BOX.add(this);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 1800;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            ItemStack stack = openScrap(level);
            ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), stack);
            level.addFreshEntity(item);

            ItemStack scrapBoxStack = player.getItemInHand(interactionHand);
            scrapBoxStack.shrink(1);
        }

        return super.use(level, player, interactionHand);
    }

    public static ItemStack openScrap(Level level) {
        Optional<ScrapBoxRecipe> recipe = level.getRecipeManager().getRecipeFor(ModRecipeType.SCRAP_BOX.get(), new SimpleContainer(new ItemStack(ModItems.SCRAP_BOX)), level);
        if (recipe.isPresent()) {
            return recipe.get().getDrop();
        }
        return ItemStack.EMPTY;
    }

    @Nullable
    protected DispenseItemBehavior createDispenseBehavior() {
        return SCRAP_BOX_DISPENSE_BEHAVIOR;
    }

    private static final DispenseItemBehavior SCRAP_BOX_DISPENSE_BEHAVIOR = (source, stack) -> {
        Direction face = source.getBlockState().getValue(DispenserBlock.FACING);
        try {
            Level level = source.getLevel();
            BlockPos pos = source.getPos().relative(face);
            ItemStack dropStack = openScrap(level);
            ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), dropStack);
            level.addFreshEntity(item);
            level.playSound(null, pos, SoundEvents.DISPENSER_DISPENSE, SoundSource.BLOCKS, 1.0F, 1.0F);
        } catch (Exception exception) {
            DispenseItemBehavior.LOGGER.error("Error while dispensing item from dispenser at {}", source.getPos(), exception);
            return ItemStack.EMPTY;
        }

        stack.shrink(1);
        source.getLevel().gameEvent(GameEvent.ENTITY_PLACE, source.getPos());
        return stack;
    };

    @Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    private static class CommonHandler {
        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
            SCRAP_BOX.forEach(item -> {
                DispenseItemBehavior dispenseBehavior = item.createDispenseBehavior();
                if (dispenseBehavior != null){
                    DispenserBlock.registerBehavior(item, dispenseBehavior);
                }
            });
        }
    }

}
