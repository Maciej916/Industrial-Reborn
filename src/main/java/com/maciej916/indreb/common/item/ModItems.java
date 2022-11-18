package com.maciej916.indreb.common.item;

import com.google.common.base.Supplier;
import com.maciej916.indreb.common.api.blockitem.BlockItemElectric;
import com.maciej916.indreb.common.api.blockitem.IndRebBlockItem;
import com.maciej916.indreb.common.api.item.base.*;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.blockitem.BlockItemIronScaffolding;
import com.maciej916.indreb.common.item.impl.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> TIN_ORE = fromBlock(ModBlocks.TIN_ORE);
    public static final RegistryObject<Item> DEEPSLATE_TIN_ORE = fromBlock(ModBlocks.DEEPSLATE_TIN_ORE);
    public static final RegistryObject<Item> LEAD_ORE = fromBlock(ModBlocks.LEAD_ORE);
    public static final RegistryObject<Item> DEEPSLATE_LEAD_ORE = fromBlock(ModBlocks.DEEPSLATE_LEAD_ORE);
    public static final RegistryObject<Item> URANIUM_ORE = fromBlock(ModBlocks.URANIUM_ORE);
    public static final RegistryObject<Item> DEEPSLATE_URANIUM_ORE = fromBlock(ModBlocks.DEEPSLATE_URANIUM_ORE);
    public static final RegistryObject<Item> SILVER_ORE = fromBlock(ModBlocks.SILVER_ORE);
    public static final RegistryObject<Item> DEEPSLATE_SILVER_ORE = fromBlock(ModBlocks.DEEPSLATE_SILVER_ORE);

    public static final RegistryObject<Item> RAW_TIN_BLOCK = fromBlock(ModBlocks.RAW_TIN_BLOCK);
    public static final RegistryObject<Item> RAW_LEAD_BLOCK = fromBlock(ModBlocks.RAW_LEAD_BLOCK);
    public static final RegistryObject<Item> RAW_URANIUM_BLOCK = fromBlock(ModBlocks.RAW_URANIUM_BLOCK);
    public static final RegistryObject<Item> RAW_SILVER_BLOCK = fromBlock(ModBlocks.RAW_SILVER_BLOCK);

    public static final RegistryObject<Item> TIN_BLOCK = fromBlock(ModBlocks.TIN_BLOCK);
    public static final RegistryObject<Item> LEAD_BLOCK = fromBlock(ModBlocks.LEAD_BLOCK);
    public static final RegistryObject<Item> URANIUM_BLOCK = fromBlock(ModBlocks.URANIUM_BLOCK);
    public static final RegistryObject<Item> SILVER_BLOCK = fromBlock(ModBlocks.SILVER_BLOCK);
    public static final RegistryObject<Item> STEEL_BLOCK = fromBlock(ModBlocks.STEEL_BLOCK);
    public static final RegistryObject<Item> BRONZE_BLOCK = fromBlock(ModBlocks.BRONZE_BLOCK);

    public static final RegistryObject<Item> RUBBER_LOG = fromBlock(ModBlocks.RUBBER_LOG);
    public static final RegistryObject<Item> RUBBER_WOOD = fromBlock(ModBlocks.RUBBER_WOOD);
    public static final RegistryObject<Item> RUBBER_LEAVES = fromBlock(ModBlocks.RUBBER_LEAVES);
    public static final RegistryObject<Item> RUBBER_PLANKS = fromBlock(ModBlocks.RUBBER_PLANKS);
    public static final RegistryObject<Item> RUBBER_SAPLING = fromBlock(ModBlocks.RUBBER_SAPLING);
    public static final RegistryObject<Item> RUBBER_STAIRS = fromBlock(ModBlocks.RUBBER_STAIRS);
    public static final RegistryObject<Item> RUBBER_SLAB = fromBlock(ModBlocks.RUBBER_SLAB);
    public static final RegistryObject<Item> RUBBER_PLATE = fromBlock(ModBlocks.RUBBER_PLATE);
    public static final RegistryObject<Item> RUBBER_FENCE = fromBlock(ModBlocks.RUBBER_FENCE);
    public static final RegistryObject<Item> RUBBER_GATE = fromBlock(ModBlocks.RUBBER_GATE);
    public static final RegistryObject<Item> RUBBER_DOOR = fromBlock(ModBlocks.RUBBER_DOOR);
    public static final RegistryObject<Item> RUBBER_TRAP_DOOR = fromBlock(ModBlocks.RUBBER_TRAP_DOOR);

    public static final RegistryObject<Item> RESIN_SHEET = fromBlock(ModBlocks.RESIN_SHEET);
    public static final RegistryObject<Item> RUBBER_SHEET = fromBlock(ModBlocks.RUBBER_SHEET);
    public static final RegistryObject<Item> RESIN_BLOCK = fromBlock(ModBlocks.RESIN_BLOCK);
    public static final RegistryObject<Item> RUBBER_BLOCK = fromBlock(ModBlocks.RUBBER_BLOCK);

    public static final RegistryObject<Item> BASIC_MACHINE_CASING = fromBlock(ModBlocks.BASIC_MACHINE_CASING);
    public static final RegistryObject<Item> ADVANCED_MACHINE_CASING = fromBlock(ModBlocks.ADVANCED_MACHINE_CASING);

    public static final RegistryObject<Item> CONSTRUCTION_FOAM = fromBlock(ModBlocks.CONSTRUCTION_FOAM);
    public static final RegistryObject<Item> REINFORCED_CONSTRUCTION_FOAM = fromBlock(ModBlocks.REINFORCED_CONSTRUCTION_FOAM);

    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_WHITE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_RED = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_RED);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_ORANGE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_PINK = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_YELLOW = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_LIME = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_GREEN = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_LIGHT_BLUE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_CYAN = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_BLUE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_MAGENTA = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_PURPLE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_BROWN = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_GRAY = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_LIGHT_GRAY = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_BLACK = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK);

    public static final RegistryObject<Item> REINFORCED_GLASS = fromBlock(ModBlocks.REINFORCED_GLASS);
    public static final RegistryObject<Item> REINFORCED_STONE = fromBlock(ModBlocks.REINFORCED_STONE);
    public static final RegistryObject<Item> REINFORCED_STONE_SLAB = fromBlock(ModBlocks.REINFORCED_STONE_SLAB);
    public static final RegistryObject<Item> REINFORCED_STONE_STAIRS = fromBlock(ModBlocks.REINFORCED_STONE_STAIRS);
    public static final RegistryObject<Item> REINFORCED_STONE_BRICKS = fromBlock(ModBlocks.REINFORCED_STONE_BRICKS);
    public static final RegistryObject<Item> SMOOTH_REINFORCED_STONE = fromBlock(ModBlocks.SMOOTH_REINFORCED_STONE);
    public static final RegistryObject<Item> REINFORCED_STONE_BRICK_WALL = fromBlock(ModBlocks.REINFORCED_STONE_BRICK_WALL);
    public static final RegistryObject<Item> REINFORCED_DOOR = fromBlock(ModBlocks.REINFORCED_DOOR);

    public static final RegistryObject<Item> IRON_SCAFFOLDING = fromBlockIronScaffolding(ModBlocks.IRON_SCAFFOLDING);
    public static final RegistryObject<Item> IRON_FENCE = fromBlockIronScaffolding(ModBlocks.IRON_FENCE);
    public static final RegistryObject<Item> IRON_GATE = fromBlockIronScaffolding(ModBlocks.IRON_GATE);

    public static final RegistryObject<Item> GENERATOR = fromBlockElectric(ModBlocks.GENERATOR);









    public static final RegistryObject<Item> RAW_TIN = registerItem("raw_tin", MaterialItem::new);
    public static final RegistryObject<Item> RAW_LEAD = registerItem("raw_lead", MaterialItem::new);
    public static final RegistryObject<Item> RAW_URANIUM = registerItem("raw_uranium", MaterialItem::new);
    public static final RegistryObject<Item> RAW_SILVER = registerItem("raw_silver", MaterialItem::new);

    public static final RegistryObject<Item> TIN_INGOT = registerItem("tin_ingot", MaterialItem::new);
    public static final RegistryObject<Item> BRONZE_INGOT = registerItem("bronze_ingot", MaterialItem::new);
    public static final RegistryObject<Item> STEEL_INGOT = registerItem("steel_ingot", MaterialItem::new);
    public static final RegistryObject<Item> MIXED_METAL_INGOT = registerItem("mixed_metal_ingot", MaterialItem::new);
    public static final RegistryObject<Item> SILVER_INGOT = registerItem("silver_ingot", MaterialItem::new);
    public static final RegistryObject<Item> LEAD_INGOT = registerItem("lead_ingot", MaterialItem::new);
    public static final RegistryObject<Item> URANIUM_INGOT = registerItem("uranium_ingot", MaterialItem::new);

    public static final RegistryObject<Item> TIN_DUST = registerItem("tin_dust", MaterialItem::new);
    public static final RegistryObject<Item> COPPER_DUST = registerItem("copper_dust", MaterialItem::new);
    public static final RegistryObject<Item> IRON_DUST = registerItem("iron_dust", MaterialItem::new);
    public static final RegistryObject<Item> GOLD_DUST = registerItem("gold_dust", MaterialItem::new);
    public static final RegistryObject<Item> LEAD_DUST = registerItem("lead_dust", MaterialItem::new);
    public static final RegistryObject<Item> URANIUM_DUST = registerItem("uranium_dust", MaterialItem::new);
    public static final RegistryObject<Item> SILVER_DUST = registerItem("silver_dust", MaterialItem::new);
    public static final RegistryObject<Item> COAL_DUST = registerItem("coal_dust", MaterialItem::new);
    public static final RegistryObject<Item> LAPIS_LAZULI_DUST = registerItem("lapis_lazuli_dust", MaterialItem::new);
    public static final RegistryObject<Item> DIAMOND_DUST = registerItem("diamond_dust", MaterialItem::new);
    public static final RegistryObject<Item> ENERGIUM_DUST = registerItem("energium_dust", MaterialItem::new);
    public static final RegistryObject<Item> STONE_DUST = registerItem("stone_dust", MaterialItem::new);
    public static final RegistryObject<Item> DEEPSLATE_DUST = registerItem("deepslate_dust", MaterialItem::new);
    public static final RegistryObject<Item> SAWDUST = registerItem("sawdust", MaterialItem::new);
    public static final RegistryObject<Item> SULFUR_DUST = registerItem("sulfur_dust", MaterialItem::new);
    public static final RegistryObject<Item> MUD_PILE = registerItem("mud_pile", MaterialItem::new);

    public static final RegistryObject<Item> PURIFIED_TIN = registerItem("purified_tin", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_COPPER = registerItem("purified_copper", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_IRON = registerItem("purified_iron", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_GOLD = registerItem("purified_gold", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_LEAD = registerItem("purified_lead", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_URANIUM = registerItem("purified_uranium", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_SILVER = registerItem("purified_silver", MaterialItem::new);

    public static final RegistryObject<Item> TIN_CHUNK = registerItem("tin_chunk", MaterialItem::new);
    public static final RegistryObject<Item> COPPER_CHUNK = registerItem("copper_chunk", MaterialItem::new);
    public static final RegistryObject<Item> IRON_CHUNK = registerItem("iron_chunk", MaterialItem::new);
    public static final RegistryObject<Item> GOLD_CHUNK = registerItem("gold_chunk", MaterialItem::new);
    public static final RegistryObject<Item> LEAD_CHUNK = registerItem("lead_chunk", MaterialItem::new);
    public static final RegistryObject<Item> URANIUM_CHUNK = registerItem("uranium_chunk", MaterialItem::new);
    public static final RegistryObject<Item> SILVER_CHUNK = registerItem("silver_chunk", MaterialItem::new);

    public static final RegistryObject<Item> COPPER_PLATE = registerItem("copper_plate", MaterialItem::new);
    public static final RegistryObject<Item> TIN_PLATE = registerItem("tin_plate", MaterialItem::new);
    public static final RegistryObject<Item> IRON_PLATE = registerItem("iron_plate", MaterialItem::new);
    public static final RegistryObject<Item> LEAD_PLATE = registerItem("lead_plate", MaterialItem::new);
    public static final RegistryObject<Item> GOLD_PLATE = registerItem("gold_plate", MaterialItem::new);
    public static final RegistryObject<Item> BRONZE_PLATE = registerItem("bronze_plate", MaterialItem::new);
    public static final RegistryObject<Item> STEEL_PLATE = registerItem("steel_plate", MaterialItem::new);
    public static final RegistryObject<Item> LAPIS_LAZULI_PLATE = registerItem("lapis_lazuli_plate", MaterialItem::new);
    public static final RegistryObject<Item> ADVANCED_ALLOY = registerItem("advanced_alloy", MaterialItem::new);
    public static final RegistryObject<Item> IRIDIUM_PLATE = registerItem("iridium_plate", MaterialItemRare::new);

    public static final RegistryObject<Item> ELECTRONIC_CIRCUIT = registerItem("electronic_circuit", MaterialItem::new);
    public static final RegistryObject<Item> ADVANCED_CIRCUIT = registerItem("advanced_circuit", MaterialItem::new);
    public static final RegistryObject<Item> CARBON_FIBERS = registerItem("carbon_fibers", MaterialItem::new);
    public static final RegistryObject<Item> COMBINED_CARBON_FIBERS = registerItem("combined_carbon_fibers", MaterialItem::new);
    public static final RegistryObject<Item> CARBON_PLATE = registerItem("carbon_plate", MaterialItem::new);
    public static final RegistryObject<Item> BIO_CHAFF = registerItem("bio_chaff", MaterialItem::new);
    public static final RegistryObject<Item> HEAT_CONDUCTOR = registerItem("heat_conductor", MaterialItem::new);
    public static final RegistryObject<Item> FOAM_POWDER = registerItem("foam_powder", MaterialItem::new);
    public static final RegistryObject<Item> REINFORCED_FOAM_POWDER = registerItem("reinforced_foam_powder", MaterialItem::new);
    public static final RegistryObject<Item> SMALL_POWER_UNIT = registerItem("small_power_unit", RedstoneItem::new);
    public static final RegistryObject<Item> POWER_UNIT = registerItem("power_unit", RedstoneItem::new);
    public static final RegistryObject<Item> COIL = registerItem("coil", RedstoneItem::new);
    public static final RegistryObject<Item> ELECTRIC_MOTOR = registerItem("electric_motor", RedstoneItem::new);
    public static final RegistryObject<Item> SCRAP = registerItem("scrap", Scrap::new);
    public static final RegistryObject<Item> SCRAP_BOX = registerItem("scrap_box", ScrapBox::new);

    public static final RegistryObject<Item> STICKY_RESIN = registerItem("sticky_resin", MaterialItem::new);
    public static final RegistryObject<Item> RUBBER = registerItem("rubber", MaterialItem::new);
    public static final RegistryObject<Item> IRIDIUM_SHARD = registerItem("iridium_shard", MaterialItemRare::new);
    public static final RegistryObject<Item> IRIDIUM = registerItem("iridium", MaterialItemRare::new);

    public static final RegistryObject<Item> FLUID_CELL = registerItem("fluid_cell", FluidCell::new);
    public static final RegistryObject<Item> MEMORY_CARD = registerItem("memory_card", MemoryCard::new);

    public static final RegistryObject<Item> TIN_CAN = registerItem("tin_can", FoodItem::new);
    public static final RegistryObject<Item> FILLED_TIN_CAN = registerItem("filled_tin_can", FilledTinCan::new);

    public static final RegistryObject<Item> FERTILIZER = registerItem("fertilizer", Fertilizer::new);







    public static final RegistryObject<Item> PAINTER = registerItem("painter", () -> new ToolItem(1));
    public static final RegistryObject<Item> PAINTER_WHITE = registerItem("painter_white", () -> new Painter(MaterialColor.WOOL));
    public static final RegistryObject<Item> PAINTER_RED = registerItem("painter_red", () -> new Painter(MaterialColor.COLOR_RED));
    public static final RegistryObject<Item> PAINTER_ORANGE = registerItem("painter_orange", () -> new Painter(MaterialColor.COLOR_ORANGE));
    public static final RegistryObject<Item> PAINTER_PINK = registerItem("painter_pink", () -> new Painter(MaterialColor.COLOR_PINK));
    public static final RegistryObject<Item> PAINTER_YELLOW = registerItem("painter_yellow", () -> new Painter(MaterialColor.COLOR_YELLOW));
    public static final RegistryObject<Item> PAINTER_LIME = registerItem("painter_lime", () -> new Painter(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Item> PAINTER_GREEN = registerItem("painter_green", () -> new Painter(MaterialColor.COLOR_GREEN));
    public static final RegistryObject<Item> PAINTER_LIGHT_BLUE = registerItem("painter_light_blue", () -> new Painter(MaterialColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Item> PAINTER_CYAN = registerItem("painter_cyan", () -> new Painter(MaterialColor.COLOR_CYAN));
    public static final RegistryObject<Item> PAINTER_BLUE = registerItem("painter_blue", () -> new Painter(MaterialColor.COLOR_BLUE));
    public static final RegistryObject<Item> PAINTER_MAGENTA = registerItem("painter_magenta", () -> new Painter(MaterialColor.COLOR_MAGENTA));
    public static final RegistryObject<Item> PAINTER_PURPLE = registerItem("painter_purple", () -> new Painter(MaterialColor.COLOR_PURPLE));
    public static final RegistryObject<Item> PAINTER_BROWN = registerItem("painter_brown", () -> new Painter(MaterialColor.COLOR_BROWN));
    public static final RegistryObject<Item> PAINTER_GRAY = registerItem("painter_gray", () -> new Painter(MaterialColor.COLOR_GRAY));
    public static final RegistryObject<Item> PAINTER_LIGHT_GRAY = registerItem("painter_light_gray", () -> new Painter(MaterialColor.COLOR_LIGHT_GRAY));
    public static final RegistryObject<Item> PAINTER_BLACK = registerItem("painter_black", () -> new Painter(MaterialColor.COLOR_BLACK));








    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return fromBlock(block, Rarity.COMMON);
    }

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block, Rarity rarity) {
        return ITEMS.register(block.getId().getPath(), () -> new IndRebBlockItem(block.get(), rarity));
    }

    public static <B extends Block> RegistryObject<Item> fromBlockIronScaffolding(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItemIronScaffolding(block.get()));
    }

    private static <T extends Block> RegistryObject<Item> fromBlockElectric(RegistryObject<T> block) {
        return fromBlockElectric(block, Rarity.COMMON);
    }

    private static <T extends Block> RegistryObject<Item> fromBlockElectric(RegistryObject<T> block, Rarity rarity) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItemElectric(block.get(), rarity));
    }


    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
