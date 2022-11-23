package com.maciej916.indreb.common.blockentity;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.block.impl.explosive.nuke.BlockEntityNuke;
import com.maciej916.indreb.common.block.impl.generator.generator.BlockEntityGenerator;
import com.maciej916.indreb.common.block.impl.generator.geo_generator.BlockEntityGeoGenerator;
import com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor.BlockEntityNuclearReactor;
import com.maciej916.indreb.common.block.impl.generator.reactor.BlockEntityReactorPart;
import com.maciej916.indreb.common.block.impl.generator.semifluid_generator.BlockEntitySemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generator.solar_panel.BlockEntitySolarPanel;
import com.maciej916.indreb.common.block.impl.machines.simple.iron_furnace.BlockEntityIronFurnace;
import com.maciej916.indreb.common.block.impl.misc.luminator.BlockEntityLuminator;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<BlockEntityType<BlockEntityLuminator>> LUMINATOR = BLOCK_ENTITIES.register("luminator", () -> BlockEntityType.Builder.of(BlockEntityLuminator::new, ModBlocks.LUMINATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityNuke>> NUKE = BLOCK_ENTITIES.register("nuke", () -> BlockEntityType.Builder.of(BlockEntityNuke::new, ModBlocks.NUKE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlockEntityIronFurnace>> IRON_FURNACE = BLOCK_ENTITIES.register("iron_furnace", () -> BlockEntityType.Builder.of(BlockEntityIronFurnace::new, ModBlocks.IRON_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlockEntityGenerator>> GENERATOR = BLOCK_ENTITIES.register("generator", () -> BlockEntityType.Builder.of(BlockEntityGenerator::new, ModBlocks.GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntitySolarPanel>> SOLAR_PANEL = BLOCK_ENTITIES.register("solar_panel", () -> BlockEntityType.Builder.of(BlockEntitySolarPanel::new, ModBlocks.SOLAR_PANEL.get(), ModBlocks.ADVANCED_SOLAR_PANEL.get(), ModBlocks.HYBRID_SOLAR_PANEL.get(), ModBlocks.QUANTUM_SOLAR_PANEL.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityGeoGenerator>> GEO_GENERATOR = BLOCK_ENTITIES.register("geo_generator", () -> BlockEntityType.Builder.of(BlockEntityGeoGenerator::new, ModBlocks.GEO_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntitySemifluidGenerator>> SEMIFLUID_GENERATOR = BLOCK_ENTITIES.register("semifluid_generator", () -> BlockEntityType.Builder.of(BlockEntitySemifluidGenerator::new, ModBlocks.SEMIFLUID_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlockEntityNuclearReactor>> NUCLEAR_REACTOR = BLOCK_ENTITIES.register("nuclear_reactor", () -> BlockEntityType.Builder.of(BlockEntityNuclearReactor::new, ModBlocks.NUCLEAR_REACTOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityReactorPart>> REACTOR_PART = BLOCK_ENTITIES.register("reactor_frame", () -> BlockEntityType.Builder.of(BlockEntityReactorPart::new, ModBlocks.REACTOR_FRAME.get(), ModBlocks.REACTOR_CONTROL_ROD.get(), ModBlocks.REACTOR_CHAMBER.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
