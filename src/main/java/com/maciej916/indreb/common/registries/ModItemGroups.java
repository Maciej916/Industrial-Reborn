package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.IndReb;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class ModItemGroups {

	public static final CreativeModeTab MAIN_ITEM_GROUP = new ModItemGroup(IndReb.MODID, () -> new ItemStack(ModItems.MFSU.get()));

	public static final class ModItemGroup extends CreativeModeTab {

		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		public ItemStack makeIcon() {
			return iconSupplier.get();
		}
	}

}
