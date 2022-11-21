package com.maciej916.indreb.common.item;

import com.maciej916.indreb.IndReb;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class ModItemGroups {

	public static final CreativeModeTab MAIN = new ModItemGroup(IndReb.MODID, () -> new ItemStack(ModItems.NUKE.get()));

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
