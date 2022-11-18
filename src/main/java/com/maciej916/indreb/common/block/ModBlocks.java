package com.maciej916.indreb.common.block;

import com.google.common.base.Supplier;
import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.block.*;
import com.maciej916.indreb.common.block.impl.FoamBlock;
import com.maciej916.indreb.common.block.impl.ReinforcedFoamBlock;
import com.maciej916.indreb.common.block.impl.generator.generator.BlockGenerator;
import com.maciej916.indreb.common.block.impl.iron.BlockIronFence;
import com.maciej916.indreb.common.block.impl.iron.BlockIronGate;
import com.maciej916.indreb.common.block.impl.iron.BlockIronScaffolding;
import com.maciej916.indreb.common.block.impl.reinforced.*;
import com.maciej916.indreb.common.block.impl.wood.*;
import com.maciej916.indreb.common.world.rubber_tree.RubberTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IndReb.MODID);

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore", () -> new BlockOre(Material.STONE, 3f, 5F));
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", () -> new BlockOre(Material.STONE, 3f, 6F));
    public static final RegistryObject<Block> LEAD_ORE = registerBlock("lead_ore", () -> new BlockOre(Material.STONE, 3f, 5F));
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore", () -> new BlockOre(Material.STONE, 3f, 6F));
    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore", () -> new BlockOre(Material.STONE, 3f, 5F));
    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore", () -> new BlockOre(Material.STONE, 3f, 6F));
    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore", () -> new BlockOre(Material.STONE, 3f, 5F));
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore", () -> new BlockOre(Material.STONE, 3f, 6F));

    public static final RegistryObject<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> RAW_LEAD_BLOCK = registerBlock("raw_lead_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> RAW_URANIUM_BLOCK = registerBlock("raw_uranium_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = registerBlock("raw_silver_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));

    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> LEAD_BLOCK = registerBlock("lead_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> URANIUM_BLOCK = registerBlock("uranium_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));

    public static final RegistryObject<Block> RUBBER_LOG = registerBlock("rubber_log", RubberLog::new);
    public static final RegistryObject<Block> RUBBER_WOOD = registerBlock("rubber_wood", RubberWood::new);
    public static final RegistryObject<Block> RUBBER_LEAVES = registerBlock("rubber_leaves", RubberLeaves::new);
    public static final RegistryObject<Block> RUBBER_PLANKS = registerBlock("rubber_planks", RubberPlanks::new);
    public static final RegistryObject<Block> RUBBER_SAPLING = registerBlock("rubber_sapling", () -> new RubberSapling(new RubberTreeGrower()));
    public static final RegistryObject<Block> RUBBER_STAIRS = registerBlock("rubber_stairs", RubberStairs::new);
    public static final RegistryObject<Block> RUBBER_SLAB = registerBlock("rubber_slab", RubberSlab::new);
    public static final RegistryObject<Block> RUBBER_PLATE = registerBlock("rubber_plate", RubberPlate::new);
    public static final RegistryObject<Block> RUBBER_FENCE = registerBlock("rubber_fence", RubberFence::new);
    public static final RegistryObject<Block> RUBBER_GATE = registerBlock("rubber_gate", RubberGate::new);
    public static final RegistryObject<Block> RUBBER_DOOR = registerBlock("rubber_door", RubberDoor::new);
    public static final RegistryObject<Block> RUBBER_TRAP_DOOR = registerBlock("rubber_trap_door", RubberTrapDoor::new);
//    public static final RegistryObject<Block> RUBBER_SIGN = registerBlock("rubber_sign", RubberStandingSign::new);
//    public static final RegistryObject<Block> RUBBER_WALL_SIGN = registerBlock("rubber_wall_sign", RubberWallSign::new);

    public static final RegistryObject<Block> RESIN_SHEET = registerBlock("resin_sheet", () -> new BlockSheetLayer(Material.METAL, MaterialColor.COLOR_ORANGE, 0.5F, 0.7f, SoundType.MUD));
    public static final RegistryObject<Block> RUBBER_SHEET = registerBlock("rubber_sheet", () -> new BlockSheetLayer(Material.METAL, MaterialColor.COLOR_BLACK, 1f, 1.2f, SoundType.WOOL));
    public static final RegistryObject<Block> RESIN_BLOCK = registerBlock("resin_block", () -> new BlockSheet(Material.METAL, MaterialColor.COLOR_ORANGE, 0.5F, 0.7f, SoundType.MUD));
    public static final RegistryObject<Block> RUBBER_BLOCK = registerBlock("rubber_block", () -> new BlockSheet(Material.METAL, MaterialColor.COLOR_BLACK, 1f, 1.2f, SoundType.WOOL));

    public static final RegistryObject<Block> BASIC_MACHINE_CASING = registerBlock("basic_machine_casing", () -> new BlockResource(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY,1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> ADVANCED_MACHINE_CASING = registerBlock("advanced_machine_casing", () -> new BlockResource(Material.METAL, MaterialColor.COLOR_GRAY, 1f, 3f, SoundType.METAL));

    public static final RegistryObject<Block> CONSTRUCTION_FOAM = registerBlock("construction_foam", FoamBlock::new);
    public static final RegistryObject<Block> REINFORCED_CONSTRUCTION_FOAM = registerBlock("reinforced_construction_foam", ReinforcedFoamBlock::new);

    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_WHITE = registerBlock("construction_foam_wall_white", () -> new BlockFoamWall(MaterialColor.WOOL));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_RED = registerBlock("construction_foam_wall_red", () -> new BlockFoamWall(MaterialColor.COLOR_RED));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_ORANGE = registerBlock("construction_foam_wall_orange", () -> new BlockFoamWall(MaterialColor.COLOR_ORANGE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_PINK = registerBlock("construction_foam_wall_pink", () -> new BlockFoamWall(MaterialColor.COLOR_PINK));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_YELLOW = registerBlock("construction_foam_wall_yellow", () -> new BlockFoamWall(MaterialColor.COLOR_YELLOW));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_LIME = registerBlock("construction_foam_wall_lime", () -> new BlockFoamWall(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_GREEN = registerBlock("construction_foam_wall_green", () -> new BlockFoamWall(MaterialColor.COLOR_GREEN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_LIGHT_BLUE = registerBlock("construction_foam_wall_light_blue", () -> new BlockFoamWall(MaterialColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_CYAN = registerBlock("construction_foam_wall_cyan", () -> new BlockFoamWall(MaterialColor.COLOR_CYAN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_BLUE = registerBlock("construction_foam_wall_blue", () -> new BlockFoamWall(MaterialColor.COLOR_BLUE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_MAGENTA = registerBlock("construction_foam_wall_magenta", () -> new BlockFoamWall(MaterialColor.COLOR_MAGENTA));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_PURPLE = registerBlock("construction_foam_wall_purple", () -> new BlockFoamWall(MaterialColor.COLOR_PURPLE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_BROWN = registerBlock("construction_foam_wall_brown", () -> new BlockFoamWall(MaterialColor.COLOR_BROWN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_GRAY = registerBlock("construction_foam_wall_gray", () -> new BlockFoamWall(MaterialColor.COLOR_GRAY));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_LIGHT_GRAY = registerBlock("construction_foam_wall_light_gray", () -> new BlockFoamWall(MaterialColor.COLOR_LIGHT_GRAY));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_BLACK = registerBlock("construction_foam_wall_black", () -> new BlockFoamWall(MaterialColor.COLOR_BLACK));

    public static final RegistryObject<Block> REINFORCED_GLASS = registerBlock("reinforced_glass", ReinforcedGlass::new);
    public static final RegistryObject<Block> REINFORCED_STONE = registerBlock("reinforced_stone", ReinforcedStone::new);
    public static final RegistryObject<Block> REINFORCED_STONE_SLAB = registerBlock("reinforced_stone_slab", ReinforcedStoneSlab::new);
    public static final RegistryObject<Block> REINFORCED_STONE_STAIRS = registerBlock("reinforced_stone_stairs", ReinforcedStoneStairs::new);
    public static final RegistryObject<Block> REINFORCED_STONE_BRICKS = registerBlock("reinforced_stone_bricks", ReinforcedStone::new);
    public static final RegistryObject<Block> SMOOTH_REINFORCED_STONE = registerBlock("smooth_reinforced_stone", ReinforcedStone::new);
    public static final RegistryObject<Block> REINFORCED_STONE_BRICK_WALL = registerBlock("reinforced_stone_brick_wall", ReinforcedWall::new);
    public static final RegistryObject<Block> REINFORCED_DOOR = registerBlock("reinforced_door", ReinforcedDoor::new);

    public static final RegistryObject<Block> IRON_SCAFFOLDING = registerBlock("iron_scaffolding", BlockIronScaffolding::new);
    public static final RegistryObject<Block> IRON_FENCE = registerBlock("iron_fence", BlockIronFence::new);
    public static final RegistryObject<Block> IRON_GATE = registerBlock("iron_gate", BlockIronGate::new);

    // luminator

    public static final RegistryObject<Block> GENERATOR = registerBlock("generator", BlockGenerator::new);



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
