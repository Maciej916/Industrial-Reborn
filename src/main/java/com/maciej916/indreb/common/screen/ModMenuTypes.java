package com.maciej916.indreb.common.screen;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.generator.generator.MenuGenerator;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.DeferredRegister.create;

public final class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =create(ForgeRegistries.MENU_TYPES, IndReb.MODID);

    public static final RegistryObject<MenuType<MenuGenerator>> GENERATOR = registerMenuType(MenuGenerator::new, "generator");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
