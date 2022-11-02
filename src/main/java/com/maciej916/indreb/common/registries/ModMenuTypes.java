package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.block.impl.battery_box.MenuBatteryBox;
import com.maciej916.indreb.common.block.impl.charge_pad.MenuChargePad;
import com.maciej916.indreb.common.block.impl.generators.crystalline_generator.MenuCrystallineGenerator;
import com.maciej916.indreb.common.block.impl.generators.generator.MenuGenerator;
import com.maciej916.indreb.common.block.impl.generators.geo_generator.MenuGeoGenerator;
import com.maciej916.indreb.common.block.impl.generators.semifluid_generator.MenuSemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generators.solar_panels.MenuSolarGenerator;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.MenuAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.MenuCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.compressor.MenuCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.MenuCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.MenuElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.MenuExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.MenuExtruder;
import com.maciej916.indreb.common.block.impl.machines.fermenter.MenuFermenter;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.MenuFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.MenuIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.matter_fabricator.MenuMatterFabricator;
import com.maciej916.indreb.common.block.impl.machines.ore_washing_plant.MenuOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machines.recycler.MenuRecycler;
import com.maciej916.indreb.common.block.impl.machines.sawmill.MenuSawmill;
import com.maciej916.indreb.common.block.impl.machines.thermal_centrifuge.MenuThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.transformer.MenuTransformer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);

    public static final RegistryObject<MenuType<MenuGenerator>> GENERATOR = registerMenuType("generator",  MenuGenerator::new);
    public static final RegistryObject<MenuType<MenuSolarGenerator>> SOLAR_GENERATOR = registerMenuType("solar_generator",  MenuSolarGenerator::new);
    public static final RegistryObject<MenuType<MenuGeoGenerator>> GEO_GENERATOR = registerMenuType("geo_generator",  MenuGeoGenerator::new);
    public static final RegistryObject<MenuType<MenuCrystallineGenerator>> CRYSTALLINE_GENERATOR = registerMenuType("crystalline_generator",  MenuCrystallineGenerator::new);
    public static final RegistryObject<MenuType<MenuSemifluidGenerator>> SEMIFLUID_GENERATOR = registerMenuType("semifluid_generator",  MenuSemifluidGenerator::new);

    public static final RegistryObject<MenuType<MenuBatteryBox>> BATTERY_BOX = registerMenuType("battery_box",  MenuBatteryBox::new);
    public static final RegistryObject<MenuType<MenuTransformer>> TRANSFORMER = registerMenuType("transformer",  MenuTransformer::new);
    public static final RegistryObject<MenuType<MenuChargePad>> CHARGE_PAD = registerMenuType("charge_pad",  MenuChargePad::new);

    public static final RegistryObject<MenuType<MenuIronFurnace>> IRON_FURNACE = registerMenuType("iron_furnace",  MenuIronFurnace::new);
    public static final RegistryObject<MenuType<MenuElectricFurnace>> ELECTRIC_FURNACE = registerMenuType("electric_furnace",  MenuElectricFurnace::new);

    public static final RegistryObject<MenuType<MenuCrusher>> CRUSHER = registerMenuType("crusher",  MenuCrusher::new);
    public static final RegistryObject<MenuType<MenuCompressor>> COMPRESSOR = registerMenuType("compressor",  MenuCompressor::new);
    public static final RegistryObject<MenuType<MenuExtractor>> EXTRACTOR = registerMenuType("extractor",  MenuExtractor::new);
    public static final RegistryObject<MenuType<MenuSawmill>> SAWMILL = registerMenuType("sawmill",  MenuSawmill::new);
    public static final RegistryObject<MenuType<MenuExtruder>> EXTRUDER = registerMenuType("extruder",  MenuExtruder::new);
    public static final RegistryObject<MenuType<MenuCanningMachine>> CANNING_MACHINE = registerMenuType("canning_machine",  MenuCanningMachine::new);
    public static final RegistryObject<MenuType<MenuFluidEnricher>> FLUID_ENRICHER = registerMenuType("fluid_enricher",  MenuFluidEnricher::new);
    public static final RegistryObject<MenuType<MenuRecycler>> RECYCLER = registerMenuType("recycler",  MenuRecycler::new);
    public static final RegistryObject<MenuType<MenuFermenter>> FERMENTER = registerMenuType("fermenter",  MenuFermenter::new);

    public static final RegistryObject<MenuType<MenuAlloySmelter>> ALLOY_SMELTER = registerMenuType("alloy_smelter",  MenuAlloySmelter::new);

    public static final RegistryObject<MenuType<MenuOreWashingPlant>> ORE_WASHING_PLANT = registerMenuType("ore_washing_plant",  MenuOreWashingPlant::new);
    public static final RegistryObject<MenuType<MenuMatterFabricator>> MATTER_FABRICATOR = registerMenuType("matter_fabricator",  MenuMatterFabricator::new);
    public static final RegistryObject<MenuType<MenuThermalCentrifuge>> THERMAL_CENTRIFUGE = registerMenuType("thermal_centrifuge",  MenuThermalCentrifuge::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
    
}
