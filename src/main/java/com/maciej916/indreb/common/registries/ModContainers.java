package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.block.impl.battery_box.ContainerBatteryBox;
import com.maciej916.indreb.common.block.impl.generators.generator.ContainerGenerator;
import com.maciej916.indreb.common.block.impl.generators.geo_generator.ContainerGeoGenerator;
import com.maciej916.indreb.common.block.impl.generators.solar_generator.ContainerSolarGenerator;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.ContainerAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.compressor.ContainerCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.ContainerCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.ContainerElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.ContainerExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.ContainerExtruder;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.ContainerIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.sawmill.ContainerSawmill;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModContainers {

    public static MenuType<ContainerGenerator> GENERATOR;
    public static MenuType<ContainerSolarGenerator> SOLAR_GENERATOR;
    public static MenuType<ContainerGeoGenerator> GEO_GENERATOR;

    public static MenuType<ContainerBatteryBox> BATTERY_BOX;

    public static MenuType<ContainerIronFurnace> IRON_FURNACE;
    public static MenuType<ContainerElectricFurnace> ELECTRIC_FURNACE;

    public static MenuType<ContainerCrusher> CRUSHER;
    public static MenuType<ContainerCompressor> COMPRESSOR;
    public static MenuType<ContainerExtractor> EXTRACTOR;
    public static MenuType<ContainerSawmill> SAWMILL;
    public static MenuType<ContainerExtruder> EXTRUDER;
    public static MenuType<ContainerAlloySmelter> ALLOY_SMELTER;



    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {

        GENERATOR = registerContainer("generator", IForgeContainerType.create(ContainerGenerator::new));
        SOLAR_GENERATOR = registerContainer("solar_generator", IForgeContainerType.create(ContainerSolarGenerator::new));
        GEO_GENERATOR = registerContainer("geo_generator", IForgeContainerType.create(ContainerGeoGenerator::new));

        BATTERY_BOX = registerContainer("battery_box", IForgeContainerType.create(ContainerBatteryBox::new));

        IRON_FURNACE = registerContainer("iron_furnace", IForgeContainerType.create(ContainerIronFurnace::new));
        ELECTRIC_FURNACE = registerContainer("electric_furnace", IForgeContainerType.create(ContainerElectricFurnace::new));

        CRUSHER = registerContainer("crusher", IForgeContainerType.create(ContainerCrusher::new));
        COMPRESSOR = registerContainer("compressor", IForgeContainerType.create(ContainerCompressor::new));
        EXTRACTOR = registerContainer("extractor", IForgeContainerType.create(ContainerExtractor::new));
        SAWMILL = registerContainer("sawmill", IForgeContainerType.create(ContainerSawmill::new));
        EXTRUDER = registerContainer("extruder", IForgeContainerType.create(ContainerExtruder::new));
        ALLOY_SMELTER = registerContainer("alloy_smelter", IForgeContainerType.create(ContainerAlloySmelter::new));





    }


    private static <T extends AbstractContainerMenu> MenuType<T> registerContainer(String name, MenuType<T> container) {
        container.setRegistryName(name);
        ForgeRegistries.CONTAINERS.register(container);
        return container;
    }
    
}
