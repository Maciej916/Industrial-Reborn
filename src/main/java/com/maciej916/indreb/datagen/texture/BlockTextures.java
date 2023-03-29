package com.maciej916.indreb.datagen.texture;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.multiblock.reactor.ReactorPartIndex;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

import static com.maciej916.indreb.common.util.BlockStateHelper.HORIZONTAL_FACING_PROPERTY;

public class BlockTextures extends BlockStateProvider {

    public BlockTextures(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, IndReb.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerOres();
        registerRawBlock();
        registerMaterial();
        registerCasing();
        registerSheets();
        registerConstructionFoam();
        registerRubberWood();
        registerIron();
        registerDecoration();
        registerGenerators();
        registerMisc();
        registerExplosives();
        registerSimpleMachines();
        registerEnergyStorage();
        registerChargePad();
        registerTransformer();
        registerBasicMachines();
        registerStandardMachines();
        registerAdvancedMachines();
        registerSuperMachines();
    }

    private void registerOres() {
        createCubeAll(ModBlocks.TIN_ORE, "ore/tin");
        createCubeAll(ModBlocks.DEEPSLATE_TIN_ORE, "ore/deepslate_tin");
        createCubeAll(ModBlocks.LEAD_ORE, "ore/lead");
        createCubeAll(ModBlocks.DEEPSLATE_LEAD_ORE, "ore/deepslate_lead");
        createCubeAll(ModBlocks.URANIUM_ORE, "ore/uranium");
        createCubeAll(ModBlocks.DEEPSLATE_URANIUM_ORE, "ore/deepslate_uranium");
        createCubeAll(ModBlocks.SILVER_ORE, "ore/silver");
        createCubeAll(ModBlocks.DEEPSLATE_SILVER_ORE, "ore/deepslate_silver");
    }

    private void registerRawBlock() {
        createCubeAll(ModBlocks.RAW_TIN_BLOCK, "raw/raw_tin_block");
        createCubeAll(ModBlocks.RAW_LEAD_BLOCK, "raw/raw_lead_block");
        createCubeAll(ModBlocks.RAW_URANIUM_BLOCK, "raw/raw_uranium_block");
        createCubeAll(ModBlocks.RAW_SILVER_BLOCK, "raw/raw_silver_block");
    }

    private void registerMaterial() {
        createCubeAll(ModBlocks.TIN_BLOCK, "material/tin_block");
        createCubeAll(ModBlocks.LEAD_BLOCK, "material/lead_block");
        createCubeAll(ModBlocks.URANIUM_BLOCK, "material/uranium_block");
        createCubeAll(ModBlocks.SILVER_BLOCK, "material/silver_block");
        createCubeAll(ModBlocks.STEEL_BLOCK, "material/steel_block");
        createCubeAll(ModBlocks.BRONZE_BLOCK, "material/bronze_block");
    }

    private void registerCasing() {
        createCubeAll(ModBlocks.BASIC_MACHINE_CASING, "casing/basic_machine_casing");
        createCubeAll(ModBlocks.ADVANCED_MACHINE_CASING, "casing/advanced_machine_casing");
    }

    private void registerSheets() {
        createCubeAll(ModBlocks.RUBBER_BLOCK, "sheet/rubber_block");
        createCubeAll(ModBlocks.RESIN_BLOCK, "sheet/resin_block");
    }

    private void registerConstructionFoam() {
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE, "construction_foam/wall/wall_white");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_RED, "construction_foam/wall/wall_red");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE, "construction_foam/wall/wall_orange");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK, "construction_foam/wall/wall_pink");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW, "construction_foam/wall/wall_yellow");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME, "construction_foam/wall/wall_lime");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN, "construction_foam/wall/wall_green");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE, "construction_foam/wall/wall_light_blue");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN, "construction_foam/wall/wall_cyan");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE, "construction_foam/wall/wall_blue");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA, "construction_foam/wall/wall_magenta");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE, "construction_foam/wall/wall_purple");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN, "construction_foam/wall/wall_brown");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY, "construction_foam/wall/wall_gray");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY, "construction_foam/wall/wall_light_gray");
        createCubeAll(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK, "construction_foam/wall/wall_black");

        createCubeAllRenderType(ModBlocks.REINFORCED_GLASS, "construction_foam/reinforced/reinforced_glass", "translucent");
        createCubeAll(ModBlocks.REINFORCED_STONE, "construction_foam/reinforced/reinforced_stone");
        createSlabBlock(ModBlocks.REINFORCED_STONE_SLAB, "construction_foam/reinforced/reinforced_stone");
        createStairsBlock(ModBlocks.REINFORCED_STONE_STAIRS, "construction_foam/reinforced/reinforced_stone");
        createCubeAll(ModBlocks.REINFORCED_STONE_BRICKS, "construction_foam/reinforced/reinforced_stone_bricks");
        createCubeAll(ModBlocks.SMOOTH_REINFORCED_STONE, "construction_foam/reinforced/smooth_reinforced_stone");
        createWallBlock(ModBlocks.REINFORCED_STONE_BRICK_WALL, "construction_foam/reinforced/reinforced_stone_bricks");
        createDoorBlock(ModBlocks.REINFORCED_DOOR, "construction_foam/reinforced/reinforced_door");
    }

    private void registerRubberWood() {
        createCubeAll(ModBlocks.RUBBER_WOOD, "rubber_wood/rubber_log_side");
        createLeavesBlock(ModBlocks.RUBBER_LEAVES, "rubber_wood/rubber_leaves");
        createCubeAll(ModBlocks.RUBBER_PLANKS, "rubber_wood/rubber_planks");
        createSaplingBlock(ModBlocks.RUBBER_SAPLING, "rubber_wood/rubber_sapling");
        createStairsBlock(ModBlocks.RUBBER_STAIRS, "rubber_wood/rubber_planks");
        createSlabBlock(ModBlocks.RUBBER_SLAB, "rubber_wood/rubber_planks");
        createPressurePlateBlock(ModBlocks.RUBBER_PLATE, "rubber_wood/rubber_planks");
        createFenceGateBlock(ModBlocks.RUBBER_GATE, "rubber_wood/rubber_planks");
        createFenceBlock(ModBlocks.RUBBER_FENCE, "rubber_wood/rubber_planks");
        createDoorBlock(ModBlocks.RUBBER_DOOR, "rubber_wood/rubber_door");
        createTrapDoorBlock(ModBlocks.RUBBER_TRAP_DOOR, "rubber_wood/rubber_trap_door", true);
        createCubeAll(ModBlocks.STRIPPED_RUBBER_WOOD, "rubber_wood/stripped_rubber_log_side");
    }

    private void registerIron() {
        createFenceBlock(ModBlocks.IRON_FENCE, "iron/iron_fence");
        createFenceGateBlock(ModBlocks.IRON_GATE, "iron/iron_fence");
    }

    private void registerDecoration() {
        createCubeAll(ModBlocks.YELLOW_STRIPES_BLOCK_LEFT, "decoration/yellow_stripes_block_left");
        createCubeAll(ModBlocks.YELLOW_STRIPES_BLOCK_RIGHT, "decoration/yellow_stripes_block_right");
        createSimpleSideTopBottom(ModBlocks.RADIOACTIVE_HAZARD_SIGN_BLOCK, "decoration/radioactive_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.BIO_HAZARD_SIGN_BLOCK, "decoration/bio_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.EXPLOSION_HAZARD_SIGN_BLOCK, "decoration/explosion_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.FIRE_HAZARD_SIGN_BLOCK, "decoration/fire_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.ACID_HAZARD_SIGN_BLOCK, "decoration/acid_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.MAGIC_HAZARD_SIGN_BLOCK, "decoration/magic_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.FROST_HAZARD_SIGN_BLOCK, "decoration/frost_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
        createSimpleSideTopBottom(ModBlocks.NOISE_HAZARD_SIGN_BLOCK, "decoration/noise_hazard_sign_block", "casing/basic_machine_casing", "casing/basic_machine_casing");
    }

    private void registerGenerators() {
        createFrontActive(ModBlocks.GENERATOR, "generator/generator/generator");
        createSolarPanel(ModBlocks.SOLAR_PANEL, "generator/solar_panel/solar_panel");
        createSolarPanel(ModBlocks.ADVANCED_SOLAR_PANEL, "generator/advanced_solar_panel/advanced_solar_panel");
        createSolarPanel(ModBlocks.HYBRID_SOLAR_PANEL, "generator/hybrid_solar_panel/hybrid_solar_panel");
        createSolarPanel(ModBlocks.QUANTUM_SOLAR_PANEL, "generator/quantum_solar_panel/quantum_solar_panel");
        createFrontActive(ModBlocks.GEO_GENERATOR, "generator/geo_generator/geo_generator");
        createFrontLeftRightActive(ModBlocks.SEMIFLUID_GENERATOR, "generator/semifluid_generator/semifluid_generator");


        createOnlySidesActive(ModBlocks.NUCLEAR_REACTOR, "generator/reactor/nuclear_reactor/nuclear_reactor");
        createReactorMultipartChamber(ModBlocks.REACTOR_CHAMBER, "generator/reactor/reactor_chamber/reactor_chamber","multiblock/reactor/reactor_chamber");
        createReactorMultipartRod(ModBlocks.REACTOR_CONTROL_ROD, "generator/reactor/reactor_control_rod/reactor_control_rod","multiblock/reactor/reactor_control_rod");
        createReactorMultipartFrame(ModBlocks.REACTOR_FRAME, "generator/reactor/reactor_frame/reactor_frame","multiblock/reactor/reactor_frame");
    }

    private void registerMisc() {
        createLuminator(ModBlocks.LUMINATOR, "misc/luminator/luminator");

        createHorizontal(ModBlocks.PATTERN_STORAGE, "misc/pattern_storage/pattern_storage", "_bottom", "_top", "_front", "_side", "_side", "_side");
        createBottomTop(ModBlocks.TELEPORT_ANCHOR, "misc/teleport_anchor/teleport_anchor");
    }

    private void registerExplosives() {
        createSimpleSideTopBottom(ModBlocks.INDUSTRIAL_TNT, "explosive/industrial_tnt/industrial_tnt_side", "explosive/industrial_tnt/industrial_tnt_top", "explosive/industrial_tnt/industrial_tnt_bottom");
        createSimpleSideTopBottom(ModBlocks.NUKE, "explosive/nuke/nuke_side", "explosive/nuke/nuke_top", "explosive/nuke/nuke_bottom");
    }

    private void registerSimpleMachines() {
        createFrontActive(ModBlocks.IRON_FURNACE, "machine/simple/iron_furnace/iron_furnace");
        createActiveHorizontal(ModBlocks.SIMPLE_CRUSHER, "machine/simple/simple_crusher/simple_crusher", "_bottom", "_bottom", "_top", "_top_active", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.SIMPLE_COMPRESSOR, "machine/simple/simple_compressor/simple_compressor", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.SIMPLE_EXTRACTOR, "machine/simple/simple_extractor/simple_extractor", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_back", "_back", "_leftright", "_leftright_active", "_leftright", "_leftright_active");
    }

    private void registerEnergyStorage() {
        createFacing(ModBlocks.BATTERY_BOX, "energy_storage/battery_box/battery_box", "_bottom", "_top", "_front", "_back", "_leftright", "_leftright");
        createFacing(ModBlocks.CESU, "energy_storage/cesu/cesu", "_bottom", "_top", "_front", "_back", "_leftright", "_leftright");
        createFacing(ModBlocks.MFE, "energy_storage/mfe/mfe", "_bottom", "_top", "_front", "_back", "_leftright", "_leftright");
        createFacing(ModBlocks.MFSU, "energy_storage/mfsu/mfsu", "_bottom", "_top", "_front", "_back", "_leftright", "_leftright");
    }

    private void registerChargePad() {
        createActiveHorizontal(ModBlocks.CHARGE_PAD_BATTERY_BOX, "charge_pad/battery_box/battery_box", "_bottom", "_bottom", "_top", "_top_active", "_front", "_front", "_back", "_leftright", "_leftright", "_leftright", "_leftright", "_leftright");
        createActiveHorizontal(ModBlocks.CHARGE_PAD_CESU, "charge_pad/cesu/cesu", "_bottom", "_bottom", "_top", "_top_active", "_front", "_front", "_back", "_back", "_leftright", "_leftright", "_leftright", "_leftright");
        createActiveHorizontal(ModBlocks.CHARGE_PAD_MFE, "charge_pad/mfe/mfe", "_bottom", "_bottom", "_top", "_top_active", "_front", "_front", "_back", "_back", "_leftright", "_leftright", "_leftright", "_leftright");
        createActiveHorizontal(ModBlocks.CHARGE_PAD_MFSU, "charge_pad/mfsu/mfsu", "_bottom", "_bottom", "_top", "_top_active", "_front", "_front", "_back", "_back", "_leftright", "_leftright", "_leftright", "_leftright");
    }

    private void registerTransformer() {
        createFacing(ModBlocks.LV_TRANSFORMER, "transformer/lv_transformer/lv_transformer", "_side", "_side", "_front", "_side", "_side", "_side");
        createFacing(ModBlocks.MV_TRANSFORMER, "transformer/mv_transformer/mv_transformer", "_side", "_side", "_front", "_side", "_side", "_side");
        createFacing(ModBlocks.HV_TRANSFORMER, "transformer/hv_transformer/hv_transformer", "_side", "_side", "_front", "_side", "_side", "_side");
        createFacing(ModBlocks.EV_TRANSFORMER, "transformer/ev_transformer/ev_transformer", "_side", "_side", "_front", "_side", "_side", "_side");
    }

    private void registerBasicMachines() {
        createActiveHorizontal(ModBlocks.ELECTRIC_FURNACE, "machine/basic/electric_furnace/electric_furnace", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.CRUSHER, "machine/basic/crusher/crusher", "_bottom", "_bottom", "_top", "_top_active", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.COMPRESSOR, "machine/basic/compressor/compressor", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.EXTRACTOR, "machine/basic/extractor/extractor", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_back", "_back", "_leftright", "_leftright_active", "_leftright", "_leftright_active");
        createActiveHorizontal(ModBlocks.SAWMILL, "machine/basic/sawmill/sawmill", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.EXTRUDER, "machine/basic/extruder/extruder", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.CANNING_MACHINE, "machine/basic/canning_machine/canning_machine", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.FLUID_ENRICHER, "machine/basic/fluid_enricher/fluid_enricher", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_back", "_back", "_leftright", "_leftright_active", "_leftright", "_leftright_active");
        createActiveHorizontal(ModBlocks.RECYCLER, "machine/basic/recycler/recycler", "_bottom", "_bottom", "_top", "_top_active", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.METAL_FORMER, "machine/basic/metal_former/metal_former", "_bottom", "_bottom", "_top", "_top_active", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");

    }

    private void registerStandardMachines() {
        createActiveHorizontal(ModBlocks.ALLOY_SMELTER, "machine/standard/alloy_smelter/alloy_smelter", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.FERMENTER, "machine/standard/fermenter/fermenter", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_back", "_back", "_leftright", "_leftright_active", "_leftright", "_leftright_active");
        createActiveHorizontal(ModBlocks.ORE_WASHING_PLANT, "machine/standard/ore_washing_plant/ore_washing_plant", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_back", "_back", "_leftright", "_leftright_active", "_leftright", "_leftright_active");
        createActiveHorizontal(ModBlocks.THERMAL_CENTRIFUGE, "machine/standard/thermal_centrifuge/thermal_centrifuge", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_back", "_back", "_leftright", "_leftright_active", "_leftright", "_leftright_active");

    }

    private void registerAdvancedMachines() {
        createActiveHorizontal(ModBlocks.MATTER_FABRICATOR, "machine/advanced/matter_fabricator/matter_fabricator", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");

    }

    private void registerSuperMachines() {
        createActiveHorizontal(ModBlocks.SCANNER, "machine/super/scanner/scanner", "_bottom", "_bottom", "_top", "_top", "_front", "_front_active", "_side", "_side", "_side", "_side", "_side", "_side");
        createActiveHorizontal(ModBlocks.REPLICATOR, "machine/super/replicator/replicator", "_bottom", "_bottom", "_top", "_top_active", "_front", "_front_active", "_back", "_back_active", "_left", "_left_active", "_right", "_right_active");

    }












    private void createCubeAll(RegistryObject<Block> block, String path) {
        simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path)));
    }

    private void createCubeAllRenderType(RegistryObject<Block> block, String path, String renderTyoe) {
        simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path)).renderType(renderTyoe));
    }

    public void createLeavesBlock(RegistryObject<Block> block, String path) {
        simpleBlock(block.get(), models().withExistingParent(block.getId().getPath(), "leaves").texture("all", new ResourceLocation(IndReb.MODID, "block/" + path)).renderType("translucent"));
    }

    public void createStairsBlock(RegistryObject<Block> block, String path) {
        stairsBlock((StairBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createSlabBlock(RegistryObject<Block> block, String path) {
        ResourceLocation side_rl = new ResourceLocation(IndReb.MODID, "block/" + path);
        ResourceLocation bottom_rl = new ResourceLocation(IndReb.MODID, "block/" + path);
        ResourceLocation top_rl = new ResourceLocation(IndReb.MODID, "block/" + path);

        ModelFile bottom = models().slab(block.getId().getPath(), side_rl, bottom_rl, top_rl);
        ModelFile top = models().slabTop(block.getId().getPath() + "_top", side_rl, bottom_rl, top_rl);
        ModelFile doubleslab = models().cubeAll(block.getId().getPath() + "_double", side_rl);

        getVariantBuilder(block.get())
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottom))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(top))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubleslab));
    }

    public void createWallBlock(RegistryObject<Block> block, String path) {
        wallBlockWithRenderType((WallBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path), "translucent");
        models().wallInventory(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createDoorBlock(RegistryObject<Block> block, String path) {
        doorBlockWithRenderType((DoorBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path+ "_top"), "translucent");
    }

    public void createSaplingBlock(RegistryObject<Block> block, String path) {
        simpleBlock(block.get(), models().cross(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path)).renderType("cutout"));
    }

    public void createPressurePlateBlock(RegistryObject<Block> block, String path) {
        pressurePlateBlock((PressurePlateBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createFenceGateBlock(RegistryObject<Block> block, String path) {
        fenceGateBlock((FenceGateBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createFenceBlock(RegistryObject<Block> block, String path) {
        fenceBlock((FenceBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path));
        models().fenceInventory(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    public void createTrapDoorBlock(RegistryObject<Block> block, String path, boolean orientable) {
        trapdoorBlockWithRenderType((TrapDoorBlock) block.get(), new ResourceLocation(IndReb.MODID, "block/" + path), orientable, "translucent");
    }

    private void createFrontActive(RegistryObject<Block> block, String path) {
        BlockModelBuilder notActive = models().orientableWithBottom(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path + "_side"), new ResourceLocation(IndReb.MODID, "block/" + path + "_front"), new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path + "_top"));
        BlockModelBuilder active = models().orientableWithBottom(block.getId().getPath() + "_active", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"), new ResourceLocation(IndReb.MODID, "block/" + path + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path + "_top"));

        orientedBlock(block.get(), state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createSolarPanel(RegistryObject<Block> block, String path) {
        ModelFile notActive = models().withExistingParent(block.getId().getPath(), "slab").texture("top", new ResourceLocation(IndReb.MODID, "block/" + path + "_top")).texture("bottom", new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom")).texture("side", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"));
        ModelFile active = models().withExistingParent(block.getId().getPath() + "_active", "slab").texture("top", new ResourceLocation(IndReb.MODID, "block/" + path + "_top_active")).texture("bottom", new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom")).texture("side", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"));

        Function<BlockState, ModelFile> modelFunc  = state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        };
        getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).build());
    }

    private void createFrontLeftRightActive(RegistryObject<Block> block, String path) {
        BlockModelBuilder notActive = cubeWithParticle(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" +path + "_top"), new ResourceLocation(IndReb.MODID, "block/" + path + "_front"), new ResourceLocation(IndReb.MODID, "block/" +path + "_back"), new ResourceLocation(IndReb.MODID, "block/" + path + "_leftright"), new ResourceLocation(IndReb.MODID, "block/" + path + "_leftright"));
        BlockModelBuilder active = cubeWithParticle(block.getId().getPath() + "_active", new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path + "_top"), new ResourceLocation(IndReb.MODID, "block/" + path + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + path + "_back"), new ResourceLocation(IndReb.MODID, "block/" + path + "_leftright_active"), new ResourceLocation(IndReb.MODID, "block/" + path + "_leftright_active"));
        orientedBlock(block.get(), state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createOnlySidesActive(RegistryObject<Block> block, String path) {
        BlockModelBuilder notActive = cubeWithParticle(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path + "_top"), new ResourceLocation(IndReb.MODID, "block/" + path + "_side"), new ResourceLocation(IndReb.MODID, "block/" + path + "_side"), new ResourceLocation(IndReb.MODID, "block/" + path + "_side"), new ResourceLocation(IndReb.MODID, "block/" + path + "_side"));
        BlockModelBuilder active = cubeWithParticle(block.getId().getPath() + "_active", new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + path + "_top"), new ResourceLocation(IndReb.MODID, "block/" + path + "_side_active"), new ResourceLocation(IndReb.MODID, "block/" + path + "_side_active"), new ResourceLocation(IndReb.MODID, "block/" + path + "_side_active"), new ResourceLocation(IndReb.MODID, "block/" + path + "_side_active"));
        orientedBlock(block.get(), state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createSimpleSideTopBottom(RegistryObject<Block> block, String side, String top, String bottom) {
        simpleBlock(block.get(), models().cubeBottomTop(block.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/" + side),
                new ResourceLocation(IndReb.MODID, "block/" + bottom),
                new ResourceLocation(IndReb.MODID, "block/" + top)
        ));
    }

    private void createSimpleSideTopBottom(RegistryObject<Block> block, String path) {
        simpleBlock(block.get(), models().cubeBottomTop(block.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_side"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_top")
        ));
    }

    private void createLuminator(RegistryObject<Block> block, String path) {
        ModelFile luminatorModel = models().getExistingFile(modLoc("block/luminator"));
        ModelFile luminatorModelActive = models().getExistingFile(modLoc("block/luminator_active"));

        Function<BlockState, ModelFile> modelFunc  = state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return luminatorModelActive;
            } else {
                return luminatorModel;
            }
        };

        getVariantBuilder(block.get())
            .forAllStates(state -> {
                Direction dir = state.getValue(BlockStateHelper.FACING_PROPERTY);
                return ConfiguredModel.builder()
                        .modelFile(modelFunc.apply(state))
                        .rotationX(dir == Direction.DOWN ? 180 : dir == Direction.UP ? 0 : 90)
                        .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                        .build();
            });
    }

    private void createReactorMultipartFrame(RegistryObject<Block> block, String path, String pathFormed) {

        ModelFile unformedModel = models().cubeBottomTop(block.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_side"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_top")
        );

        ModelFile formedSide = models().withExistingParent(block.getId().getPath() + "_formed_side", "cube")
                .texture("down", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_side_flip"))
                .texture("up", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("north", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("south", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("east", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("west", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_side"))
                .texture("particle", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"));

        ModelFile formedCorner = models().withExistingParent(block.getId().getPath() + "_formed_corner", "cube")
                .texture("down", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_angle"))
                .texture("up", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("north", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_corner_flip"))
                .texture("south", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("east", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("west", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_corner"))
                .texture("particle", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"));


        Function<BlockState, ModelFile> modelFunc = state -> {
            ReactorPartIndex part = state.getValue(BlockStateHelper.REACTOR_PART);
            if (part == ReactorPartIndex.P000 ||
                    part == ReactorPartIndex.P002 ||
                    part == ReactorPartIndex.P022 ||
                    part == ReactorPartIndex.P020 ||
                    part == ReactorPartIndex.P220 ||
                    part == ReactorPartIndex.P200 ||
                    part == ReactorPartIndex.P222 ||
                    part == ReactorPartIndex.P202
            ) {
                return formedCorner;
            }

            if (part == ReactorPartIndex.P201 ||
                    part == ReactorPartIndex.P221 ||
                    part == ReactorPartIndex.P102 ||
                    part == ReactorPartIndex.P001 ||
                    part == ReactorPartIndex.P021 ||
                    part == ReactorPartIndex.P100 ||
                    part == ReactorPartIndex.P120 ||
                    part == ReactorPartIndex.P122
            ) {
                return formedSide;
            }

            return unformedModel;
        };

        getVariantBuilder(block.get())
                .forAllStates(state -> {
                    ReactorPartIndex part = state.getValue(BlockStateHelper.REACTOR_PART);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationY(
                                    (part == ReactorPartIndex.P200 ||
                                            part == ReactorPartIndex.P020 ||
                                            part == ReactorPartIndex.P100 ||
                                            part == ReactorPartIndex.P120
                                    ) ? 90 :
                                    (part == ReactorPartIndex.P202 ||
                                            part == ReactorPartIndex.P220 ||
                                            part == ReactorPartIndex.P201 ||
                                            part == ReactorPartIndex.P221
                                    ) ? 180 :
                                    (part == ReactorPartIndex.P002 ||
                                            part == ReactorPartIndex.P222 ||
                                            part == ReactorPartIndex.P102 ||
                                            part == ReactorPartIndex.P122
                                    ) ? 270 : 0
                            )
                            .rotationX(
                                    (part == ReactorPartIndex.P020 ||
                                            part == ReactorPartIndex.P220 ||
                                            part == ReactorPartIndex.P222 ||
                                            part == ReactorPartIndex.P022 ||
                                            part == ReactorPartIndex.P021 ||
                                            part == ReactorPartIndex.P120 ||
                                            part == ReactorPartIndex.P122 ||
                                            part == ReactorPartIndex.P221
                                    ) ? 180 : 0
                            )
                            .build();
                });
    }

    private void createReactorMultipartChamber(RegistryObject<Block> block, String path, String pathFormed) {

        ModelFile unformedModel = models().cubeBottomTop(block.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_side"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_top")
        );

        ModelFile formedSide = models().withExistingParent(block.getId().getPath() + "_formed_side", "cube")
                .texture("down", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_bottom"))
                .texture("up", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_top"))
                .texture("north", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("south", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("east", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("west", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_side"))
                .texture("particle", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"));

        Function<BlockState, ModelFile> modelFunc = state -> {
            ReactorPartIndex part = state.getValue(BlockStateHelper.REACTOR_PART);

            if (part == ReactorPartIndex.P011 ||
                    part == ReactorPartIndex.P110 ||
                    part == ReactorPartIndex.P211 ||
                    part == ReactorPartIndex.P112 ||
                    part == ReactorPartIndex.P121 ||
                    part == ReactorPartIndex.P101
            ) {
                return formedSide;
            }

            return unformedModel;
        };

        getVariantBuilder(block.get())
                .forAllStates(state -> {
                    ReactorPartIndex part = state.getValue(BlockStateHelper.REACTOR_PART);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationY(
                                    part == ReactorPartIndex.P110 ? 90 :
                                    part == ReactorPartIndex.P211 ? 180 :
                                    part == ReactorPartIndex.P112 ? 270 : 0
                            )
                            .build();
                });
    }

    private void createReactorMultipartRod(RegistryObject<Block> block, String path, String pathFormed) {

        ModelFile unformedModel = models().cubeBottomTop(block.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_side"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_top")
        );

        ModelFile formedSide = models().withExistingParent(block.getId().getPath() + "_formed_side", "cube")
                .texture("down", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("up", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("north", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("south", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_side_flip"))
                .texture("east", new ResourceLocation(IndReb.MODID, "block/" + pathFormed + "_side"))
                .texture("west", new ResourceLocation(IndReb.MODID, "block/multiblock/empty"))
                .texture("particle", new ResourceLocation(IndReb.MODID, "block/" + path + "_side"));

        Function<BlockState, ModelFile> modelFunc = state -> {
            ReactorPartIndex part = state.getValue(BlockStateHelper.REACTOR_PART);

            if (part == ReactorPartIndex.P212 ||
                    part == ReactorPartIndex.P012 ||
                    part == ReactorPartIndex.P010 ||
                    part == ReactorPartIndex.P210
            ) {
                return formedSide;
            }

            return unformedModel;
        };

        getVariantBuilder(block.get())
                .forAllStates(state -> {
                    ReactorPartIndex part = state.getValue(BlockStateHelper.REACTOR_PART);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationY(
                                    part == ReactorPartIndex.P012 ? 90 :
                                            part == ReactorPartIndex.P010 ? 180 :
                                                    part == ReactorPartIndex.P210 ? 270 : 0
                            )
                            .build();
                });
    }




    private void createFacing(RegistryObject<Block> block, String path, String down, String up, String front, String back, String left, String right) {
        ModelFile modelFile = models().withExistingParent(block.getId().getPath(), "cube")
                .texture("down", new ResourceLocation(IndReb.MODID, "block/" + path + down))
                .texture("up", new ResourceLocation(IndReb.MODID, "block/" + path + up))
                .texture("north", new ResourceLocation(IndReb.MODID, "block/" + path + front))
                .texture("south", new ResourceLocation(IndReb.MODID, "block/" + path + back))
                .texture("east", new ResourceLocation(IndReb.MODID, "block/" + path + left))
                .texture("west",new ResourceLocation(IndReb.MODID, "block/" + path + right))
                .texture("particle", new ResourceLocation(IndReb.MODID, "block/" + path + front));

        orientedBlock(block.get(), state -> modelFile, BlockStateHelper.FACING_PROPERTY);
    }


    public void createHorizontal(RegistryObject<Block> block, String path, String down, String up, String front, String back, String left, String right) {
        BlockModelBuilder notActive = cubeWithParticle(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path + down), new ResourceLocation(IndReb.MODID, "block/" + path + up), new ResourceLocation(IndReb.MODID, "block/" + path + front), new ResourceLocation(IndReb.MODID, "block/" + path + back), new ResourceLocation(IndReb.MODID, "block/" + path + left), new ResourceLocation(IndReb.MODID, "block/" + path + right));
        orientedBlock(block.get(), state -> notActive);
    }

    public void createActiveHorizontal(RegistryObject<Block> block, String path, String down, String downActive, String up, String upActive, String front, String frontActive, String back, String backActive, String left, String leftActive, String right, String rightActive) {
        BlockModelBuilder notActive = cubeWithParticle(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path + down), new ResourceLocation(IndReb.MODID, "block/" + path + up), new ResourceLocation(IndReb.MODID, "block/" + path + front), new ResourceLocation(IndReb.MODID, "block/" + path + back), new ResourceLocation(IndReb.MODID, "block/" + path + left), new ResourceLocation(IndReb.MODID, "block/" + path + right));
        BlockModelBuilder active = cubeWithParticle(block.getId().getPath() + "_active", new ResourceLocation(IndReb.MODID, "block/" + path + downActive), new ResourceLocation(IndReb.MODID, "block/" + path + upActive), new ResourceLocation(IndReb.MODID, "block/" + path + frontActive), new ResourceLocation(IndReb.MODID, "block/" + path + backActive), new ResourceLocation(IndReb.MODID, "block/" + path + leftActive), new ResourceLocation(IndReb.MODID, "block/" + path + rightActive));

        orientedBlock(block.get(), state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createBottomTop(RegistryObject<Block> block, String path) {
        simpleBlock(block.get(), models().cubeBottomTop(block.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_side"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_bottom"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_top")
        ));
    }




















    private void createRubberLog(RegistryObject<Block> block, String path, String name) {
//        BlockModelBuilder



            ResourceLocation location = new ResourceLocation(IndReb.MODID, "block/" + path);
            ResourceLocation side = new ResourceLocation(location.getNamespace(), location.getPath() + "_side");
            ResourceLocation end = new ResourceLocation(location.getNamespace(), location.getPath() + "_end");



        ModelFile vertical = models().cubeColumn(block.getId().getPath(), side, end);
        ModelFile horizontal = models().cubeColumnHorizontal(block.getId().getPath() + "_horizontal", side, end);

//        axisBlock((RotatedPillarBlock) block.get(),  new ResourceLocation(IndReb.MODID, "block/" + path));
//
        BlockModelBuilder notActive = models().orientableWithBottom(name,
                new ResourceLocation(IndReb.MODID, "block/" + path),
                new ResourceLocation(IndReb.MODID, "block/" + path),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_top"),
                new ResourceLocation(IndReb.MODID, "block/" + path + "_top"));


//        getVariantBuilder(block)
//                .forAllStates(state -> {
//                    Direction dir = state.getValue(horizontalFacingProperty);
//                    return ConfiguredModel.builder()
//                            .modelFile(modelFunc.apply(state))
//                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
//                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
//                            .build();
//                });


        Function<BlockState, ModelFile> modelFunc  = state -> {
            if (state.getValue(BlockStateHelper.WET_PROPERTY)) {
                return notActive;
            } else if (state.getValue(BlockStateHelper.DRY_PROPERTY)) {
                return notActive;
            } else {
                return notActive;
            }
        };


//        getVariantBuilder(block.get())
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
//                .modelForState().modelFile(vertical).addModel()
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
//                .modelForState().modelFile(horizontal).rotationX(90).addModel()
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
//                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel()
//                .forAllStates(state -> {
////                    Direction dir = state.getValue(horizontalFacingProperty);
//                    return ConfiguredModel.builder()
//                            .modelFile(modelFunc.apply(state))
////                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
////                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
//                            .build();
//                })


        ;


        //        BlockModelBuilder active = models().orientableWithBottom(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
//        orientedBlock(block.get(), state -> {
//            if (state.getValue(BlockStateHelper.wetProperty)) {
//                return notActive;
//            } else if (state.getValue(BlockStateHelper.dryProperty)) {
//                return notActive;
//            } else {
//                return notActive;
//            }
//        }, BlockStateHelper.axisProperty);
    }





    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc, EnumProperty<Direction.Axis> axisEnumProperty) {

//        getVariantBuilder(block)
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
//                .modelForState().modelFile(modelFunc).addModel()
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
//                .modelForState().modelFile(horizontal).rotationX(90).addModel()
//                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
//                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();


        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(HORIZONTAL_FACING_PROPERTY);
                    return ConfiguredModel.builder()

                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();



//                    return ConfiguredModel.builder()
//
//                            .modelFile(modelFunc.apply(state))
//                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
//                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
//                            .build();
                });
    }



    // add predefined models
    // clear this junk
    // rename machines to machine


    private void createSimple(Block block, String category, String name) {
        BlockModelBuilder notActive = models().orientableWithBottom(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
        BlockModelBuilder active = models().orientableWithBottom(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));

        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createWithTopActive(Block block, String category, String name) {
        BlockModelBuilder notActive = models().orientableWithBottom(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
        BlockModelBuilder active = models().orientableWithBottom(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createOnlyTopActive(Block block, String category, String name) {
        BlockModelBuilder notActive = models().orientableWithBottom(category + "_" + name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"));
        BlockModelBuilder active = models().orientableWithBottom(category + "_" + name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createWithSidesActive(Block block, String category, String name) {
        BlockModelBuilder notActive = cubeWithParticle(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_back"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_leftright"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_leftright"));
        BlockModelBuilder active = cubeWithParticle(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_back"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_leftright_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_leftright_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createSidesActive(Block block, String category, String name) {
        BlockModelBuilder notActive = cubeWithParticle(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side"));
        BlockModelBuilder active = cubeWithParticle(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_side_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }

    private void createAllActive(Block block, String category, String name) {
        BlockModelBuilder notActive = cubeWithParticle(name, new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_back"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_left"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_right"));
        BlockModelBuilder active = cubeWithParticle(name + "_active", new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_bottom"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_top_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_front_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_back_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_left_active"), new ResourceLocation(IndReb.MODID, "block/" + category + "/" + name + "_right_active"));
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
                return active;
            } else {
                return notActive;
            }
        });
    }


    private void createTransformer(Block block, String name) {
        orientedBlock(block, state -> cubeWithParticle(name, new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_front"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side"), new ResourceLocation(IndReb.MODID, "block/transformer/" + name + "_side")), BlockStateHelper.FACING_PROPERTY);
    }

    private BlockModelBuilder cubeWithParticle(String name, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        return models().withExistingParent(name, "cube")
                .texture("down", down)
                .texture("up", up)
                .texture("north", north)
                .texture("south", south)
                .texture("east", east)
                .texture("west", west)
                .texture("particle", north);
    }




    private void createBottomTopSides(RegistryObject<Block> block, String side, String top, String bottom) {
        simpleBlock(block.get(), models().cubeBottomTop(block.getId().getPath(),
                new ResourceLocation(IndReb.MODID, "block/" + side),
                new ResourceLocation(IndReb.MODID, "block/" + bottom),
                new ResourceLocation(IndReb.MODID, "block/" + top)
        ));
    }



    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc, DirectionProperty directionProperty) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(directionProperty);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();
                });
    }

    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(HORIZONTAL_FACING_PROPERTY);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();
                });
    }
}