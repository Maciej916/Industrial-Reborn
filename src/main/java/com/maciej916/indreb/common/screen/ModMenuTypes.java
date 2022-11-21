package com.maciej916.indreb.common.screen;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.explosive.nuke.MenuNuke;
import com.maciej916.indreb.common.block.impl.generator.generator.MenuGenerator;
import com.maciej916.indreb.common.block.impl.generator.geo_generator.MenuGeoGenerator;
import com.maciej916.indreb.common.block.impl.generator.semifluid_generator.MenuSemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generator.solar_panel.MenuSolarPanel;
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

    public static final DeferredRegister<MenuType<?>> MENUS = create(ForgeRegistries.MENU_TYPES, IndReb.MODID);

    public static final RegistryObject<MenuType<MenuNuke>> NUKE = registerMenuType(MenuNuke::new, "nuke");

    public static final RegistryObject<MenuType<MenuGenerator>> GENERATOR = registerMenuType(MenuGenerator::new, "generator");
    public static final RegistryObject<MenuType<MenuSolarPanel>> SOLAR_PANEL = registerMenuType(MenuSolarPanel::new, "solar_panel");
    public static final RegistryObject<MenuType<MenuGeoGenerator>> GEO_GENERATOR = registerMenuType(MenuGeoGenerator::new, "geo_generator");
    public static final RegistryObject<MenuType<MenuSemifluidGenerator>> SEMIFLUID_GENERATOR = registerMenuType(MenuSemifluidGenerator::new, "semifluid_generator");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
