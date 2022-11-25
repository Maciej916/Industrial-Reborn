package com.maciej916.indreb.common.screen;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.battery_box.MenuBatteryBox;
import com.maciej916.indreb.common.block.impl.charge_pad.MenuChargePad;
import com.maciej916.indreb.common.block.impl.explosive.nuke.MenuNuke;
import com.maciej916.indreb.common.block.impl.generator.generator.MenuGenerator;
import com.maciej916.indreb.common.block.impl.generator.geo_generator.MenuGeoGenerator;
import com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor.MenuNuclearReactor;
import com.maciej916.indreb.common.block.impl.generator.semifluid_generator.MenuSemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generator.solar_panel.MenuSolarPanel;
import com.maciej916.indreb.common.block.impl.machines.simple.iron_furnace.MenuIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.simple.simple_crusher.MenuSimpleCrusher;
import com.maciej916.indreb.common.block.impl.machines.simple.simple_compressor.MenuSimpleCompressor;
import com.maciej916.indreb.common.block.impl.machines.simple.simple_extractor.MenuSimpleExtractor;
import com.maciej916.indreb.common.block.impl.transformer.MenuTransformer;
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

    public static final RegistryObject<MenuType<MenuIronFurnace>> IRON_FURNACE = registerMenuType(MenuIronFurnace::new, "iron_furnace");
    public static final RegistryObject<MenuType<MenuSimpleCrusher>> SIMPLE_CRUSHER = registerMenuType(MenuSimpleCrusher::new, "simple_crusher");
    public static final RegistryObject<MenuType<MenuSimpleCompressor>> SIMPLE_COMPRESSOR = registerMenuType(MenuSimpleCompressor::new, "simple_compressor");
    public static final RegistryObject<MenuType<MenuSimpleExtractor>> SIMPLE_EXTRACTOR = registerMenuType(MenuSimpleExtractor::new, "simple_extractor");

    public static final RegistryObject<MenuType<MenuGenerator>> GENERATOR = registerMenuType(MenuGenerator::new, "generator");
    public static final RegistryObject<MenuType<MenuSolarPanel>> SOLAR_PANEL = registerMenuType(MenuSolarPanel::new, "solar_panel");
    public static final RegistryObject<MenuType<MenuGeoGenerator>> GEO_GENERATOR = registerMenuType(MenuGeoGenerator::new, "geo_generator");
    public static final RegistryObject<MenuType<MenuSemifluidGenerator>> SEMIFLUID_GENERATOR = registerMenuType(MenuSemifluidGenerator::new, "semifluid_generator");
    public static final RegistryObject<MenuType<MenuNuclearReactor>> NUCLEAR_REACTOR = registerMenuType(MenuNuclearReactor::new, "nuclear_reactor");

    public static final RegistryObject<MenuType<MenuBatteryBox>> BATTERY_BOX = registerMenuType(MenuBatteryBox::new, "battery_box");
    public static final RegistryObject<MenuType<MenuChargePad>> CHARGE_PAD = registerMenuType(MenuChargePad::new, "charge_pad");
    public static final RegistryObject<MenuType<MenuTransformer>> TRANSFORMER = registerMenuType(MenuTransformer::new, "transformer");

//
//    public static final RegistryObject<MenuType<MenuElectricFurnace>> ELECTRIC_FURNACE = registerMenuType("electric_furnace",  MenuElectricFurnace::new);
//    public static final RegistryObject<MenuType<MenuCrusher>> CRUSHER = registerMenuType("crusher",  MenuCrusher::new);
//    public static final RegistryObject<MenuType<MenuCompressor>> COMPRESSOR = registerMenuType("compressor",  MenuCompressor::new);
//    public static final RegistryObject<MenuType<MenuExtractor>> EXTRACTOR = registerMenuType("extractor",  MenuExtractor::new);
//    public static final RegistryObject<MenuType<MenuSawmill>> SAWMILL = registerMenuType("sawmill",  MenuSawmill::new);
//    public static final RegistryObject<MenuType<MenuExtruder>> EXTRUDER = registerMenuType("extruder",  MenuExtruder::new);
//    public static final RegistryObject<MenuType<MenuCanningMachine>> CANNING_MACHINE = registerMenuType("canning_machine",  MenuCanningMachine::new);
//    public static final RegistryObject<MenuType<MenuFluidEnricher>> FLUID_ENRICHER = registerMenuType("fluid_enricher",  MenuFluidEnricher::new);
//    public static final RegistryObject<MenuType<MenuRecycler>> RECYCLER = registerMenuType("recycler",  MenuRecycler::new);
//    public static final RegistryObject<MenuType<MenuFermenter>> FERMENTER = registerMenuType("fermenter",  MenuFermenter::new);
//    public static final RegistryObject<MenuType<MenuAlloySmelter>> ALLOY_SMELTER = registerMenuType("alloy_smelter",  MenuAlloySmelter::new);
//    public static final RegistryObject<MenuType<MenuOreWashingPlant>> ORE_WASHING_PLANT = registerMenuType("ore_washing_plant",  MenuOreWashingPlant::new);
//    public static final RegistryObject<MenuType<MenuMetalFormer>> METAL_FORMER = registerMenuType("metal_former",  MenuMetalFormer::new);
//
//    public static final RegistryObject<MenuType<MenuMatterFabricator>> MATTER_FABRICATOR = registerMenuType("matter_fabricator",  MenuMatterFabricator::new);
//    public static final RegistryObject<MenuType<MenuThermalCentrifuge>> THERMAL_CENTRIFUGE = registerMenuType("thermal_centrifuge",  MenuThermalCentrifuge::new);
//    public static final RegistryObject<MenuType<MenuPatternStorage>> PATTERN_STORAGE = registerMenuType("pattern_storage",  MenuPatternStorage::new);
//    public static final RegistryObject<MenuType<MenuScanner>> SCANNER = registerMenuType("scanner",  MenuScanner::new);
//    public static final RegistryObject<MenuType<MenuReplicator>> REPLICATOR = registerMenuType("replicator",  MenuReplicator::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
