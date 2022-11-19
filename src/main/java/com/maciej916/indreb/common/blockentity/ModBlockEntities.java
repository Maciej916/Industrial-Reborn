package com.maciej916.indreb.common.blockentity;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.block.impl.generator.generator.BlockEntityGenerator;
import com.maciej916.indreb.common.block.impl.generator.geo_generator.BlockEntityGeoGenerator;
import com.maciej916.indreb.common.block.impl.generator.solar_panel.BlockEntitySolarPanel;
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

    public static final RegistryObject<BlockEntityType<BlockEntityGenerator>> GENERATOR = BLOCK_ENTITIES.register("generator", () -> BlockEntityType.Builder.of(BlockEntityGenerator::new, ModBlocks.GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntitySolarPanel>> SOLAR_PANEL = BLOCK_ENTITIES.register("solar_panel", () -> BlockEntityType.Builder.of(BlockEntitySolarPanel::new, ModBlocks.SOLAR_PANEL.get(), ModBlocks.ADVANCED_SOLAR_PANEL.get(), ModBlocks.HYBRID_SOLAR_PANEL.get(), ModBlocks.QUANTUM_SOLAR_PANEL.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityGeoGenerator>> GEO_GENERATOR = BLOCK_ENTITIES.register("geo_generator", () -> BlockEntityType.Builder.of(BlockEntityGeoGenerator::new, ModBlocks.GEO_GENERATOR.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
