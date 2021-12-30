package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.UpgradeType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemDirectionalUpgrade extends ItemUpgrade {

    public ItemDirectionalUpgrade(UpgradeType upgradeType) {
        super(upgradeType);
    }

    public static int getDirection(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getAllKeys().contains("Direction") ? tag.getInt("Direction") : -1;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> components, TooltipFlag tooltipFlag) {
        CompoundTag tag = stack.getOrCreateTag();
        int currentDir = tag.getAllKeys().contains("Direction") ? tag.getInt("Direction") : -1;
        components.add(new TranslatableComponent(getUpgradeType().getType() + "." + IndReb.MODID + ".dir", new TranslatableComponent("dir." + IndReb.MODID + "." + (currentDir == -1 ? "all" : Direction.from3DDataValue(currentDir).getName())).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, p_41422_, components, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        if (player.isShiftKeyDown()) {
            CompoundTag tag = stack.getOrCreateTag();

            int currentDir = tag.getAllKeys().contains("Direction") ? tag.getInt("Direction") : -1;
            int newDir = currentDir + 1 > 5 ? -1 : currentDir + 1;
            tag.putInt("Direction", newDir);

            player.displayClientMessage(new TranslatableComponent(getUpgradeType().getType() + "." + IndReb.MODID + ".dir", new TranslatableComponent("dir." + IndReb.MODID + "." + (newDir == -1 ? "all" : Direction.from3DDataValue(newDir).getName())).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY), true);
        }

        return InteractionResultHolder.pass(stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        ItemStack stack = useOnContext.getItemInHand();
        CompoundTag tag = stack.getOrCreateTag();
        Direction dir = useOnContext.getClickedFace();
        Player player = useOnContext.getPlayer();

        int currentDir = tag.getAllKeys().contains("Direction") ? tag.getInt("Direction") : -1;
        int newDir = dir.get3DDataValue() == currentDir && currentDir != -1 ? -1 : dir.get3DDataValue();

        tag.putInt("Direction", newDir);

        if (player != null) {
            player.displayClientMessage(new TranslatableComponent(getUpgradeType().getType() + "." + IndReb.MODID + ".dir", new TranslatableComponent("dir." + IndReb.MODID + "." +(newDir == -1 ? "all" : Direction.from3DDataValue(newDir).getName())).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY), true);
        }

        return InteractionResult.SUCCESS;
    }

}
