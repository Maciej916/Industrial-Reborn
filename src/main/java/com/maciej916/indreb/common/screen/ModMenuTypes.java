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
import com.maciej916.indreb.common.block.impl.machine.t_advanced.matter_fabricator.MenuMatterFabricator;
import com.maciej916.indreb.common.block.impl.machine.t_basic.canning_machine.MenuCanningMachine;
import com.maciej916.indreb.common.block.impl.machine.t_basic.compressor.MenuCompressor;
import com.maciej916.indreb.common.block.impl.machine.t_basic.crusher.MenuCrusher;
import com.maciej916.indreb.common.block.impl.machine.t_basic.electric_furnace.MenuElectricFurnace;
import com.maciej916.indreb.common.block.impl.machine.t_basic.extractor.MenuExtractor;
import com.maciej916.indreb.common.block.impl.machine.t_basic.extruder.MenuExtruder;
import com.maciej916.indreb.common.block.impl.machine.t_basic.fluid_enricher.MenuFluidEnricher;
import com.maciej916.indreb.common.block.impl.machine.t_basic.metal_former.MenuMetalFormer;
import com.maciej916.indreb.common.block.impl.machine.t_basic.recycler.MenuRecycler;
import com.maciej916.indreb.common.block.impl.machine.t_basic.sawmill.MenuSawmill;
import com.maciej916.indreb.common.block.impl.machine.t_simple.iron_furnace.MenuIronFurnace;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_compressor.MenuSimpleCompressor;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_crusher.MenuSimpleCrusher;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_extractor.MenuSimpleExtractor;
import com.maciej916.indreb.common.block.impl.machine.t_standard.alloy_smelter.MenuAlloySmelter;
import com.maciej916.indreb.common.block.impl.machine.t_standard.fermenter.MenuFermenter;
import com.maciej916.indreb.common.block.impl.machine.t_standard.ore_washing_plant.MenuOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machine.t_standard.thermal_centrifuge.MenuThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.machine.t_super.replicator.MenuReplicator;
import com.maciej916.indreb.common.block.impl.machine.t_super.scanner.MenuScanner;
import com.maciej916.indreb.common.block.impl.misc.pattern_storage.MenuPatternStorage;
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

    public static final RegistryObject<MenuType<MenuElectricFurnace>> ELECTRIC_FURNACE = registerMenuType(MenuElectricFurnace::new, "electric_furnace");
    public static final RegistryObject<MenuType<MenuCrusher>> CRUSHER = registerMenuType(MenuCrusher::new, "crusher");
    public static final RegistryObject<MenuType<MenuCompressor>> COMPRESSOR = registerMenuType(MenuCompressor::new, "compressor");
    public static final RegistryObject<MenuType<MenuExtractor>> EXTRACTOR = registerMenuType(MenuExtractor::new, "extractor");
    public static final RegistryObject<MenuType<MenuSawmill>> SAWMILL = registerMenuType(MenuSawmill::new, "sawmill");
    public static final RegistryObject<MenuType<MenuExtruder>> EXTRUDER = registerMenuType(MenuExtruder::new, "extruder");
    public static final RegistryObject<MenuType<MenuCanningMachine>> CANNING_MACHINE = registerMenuType(MenuCanningMachine::new, "canning_machine");
    public static final RegistryObject<MenuType<MenuFluidEnricher>> FLUID_ENRICHER = registerMenuType(MenuFluidEnricher::new, "fluid_enricher");
    public static final RegistryObject<MenuType<MenuRecycler>> RECYCLER = registerMenuType(MenuRecycler::new, "recycler");
    public static final RegistryObject<MenuType<MenuMetalFormer>> METAL_FORMER = registerMenuType(MenuMetalFormer::new, "metal_former");

    public static final RegistryObject<MenuType<MenuAlloySmelter>> ALLOY_SMELTER = registerMenuType(MenuAlloySmelter::new, "alloy_smelter");
    public static final RegistryObject<MenuType<MenuFermenter>> FERMENTER = registerMenuType(MenuFermenter::new, "fermenter");
    public static final RegistryObject<MenuType<MenuThermalCentrifuge>> THERMAL_CENTRIFUGE = registerMenuType(MenuThermalCentrifuge::new, "thermal_centrifuge");
    public static final RegistryObject<MenuType<MenuOreWashingPlant>> ORE_WASHING_PLANT = registerMenuType(MenuOreWashingPlant::new, "ore_washing_plant");

    public static final RegistryObject<MenuType<MenuMatterFabricator>> MATTER_FABRICATOR = registerMenuType(MenuMatterFabricator::new, "matter_fabricator");

    public static final RegistryObject<MenuType<MenuScanner>> SCANNER = registerMenuType(MenuScanner::new, "scanner");
    public static final RegistryObject<MenuType<MenuReplicator>> REPLICATOR = registerMenuType(MenuReplicator::new, "replicator");

    public static final RegistryObject<MenuType<MenuPatternStorage>> PATTERN_STORAGE = registerMenuType(MenuPatternStorage::new, "pattern_storage");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
