package com.maciej916.indreb.datagen.texture;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemTextures extends ItemModelProvider {

    public ItemTextures(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, IndReb.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerOres();
        registerRawBlock();
        registerMaterial();
        registerCasing();
        registerSheets();
        registerConstructionFoam();
        registerPainter();
        registerRubberWood();
        registerIron();
        registerRaw();
        registerIngots();
        registerDusts();
        registerPlates();
        registerPurified();
        registerChunk();
        registerCrafting();
        registerResource();
        registerStorage();
        registerFood();
        registerCrop();
    }

    protected void registerOres() {
        createWithBlock(ModItems.TIN_ORE, "tin_ore");
        createWithBlock(ModItems.DEEPSLATE_TIN_ORE, "deepslate_tin_ore");
        createWithBlock(ModItems.LEAD_ORE, "lead_ore");
        createWithBlock(ModItems.DEEPSLATE_LEAD_ORE, "deepslate_lead_ore");
        createWithBlock(ModItems.URANIUM_ORE, "uranium_ore");
        createWithBlock(ModItems.DEEPSLATE_URANIUM_ORE, "deepslate_uranium_ore");
        createWithBlock(ModItems.SILVER_ORE, "silver_ore");
        createWithBlock(ModItems.DEEPSLATE_SILVER_ORE, "deepslate_silver_ore");
    }

    private void registerRawBlock() {
        createWithBlock(ModItems.RAW_TIN_BLOCK, "raw_tin_block");
        createWithBlock(ModItems.RAW_LEAD_BLOCK, "raw_lead_block");
        createWithBlock(ModItems.RAW_URANIUM_BLOCK, "raw_uranium_block");
        createWithBlock(ModItems.RAW_SILVER_BLOCK, "raw_silver_block");
    }

    private void registerMaterial() {
        createWithBlock(ModItems.TIN_BLOCK, "tin_block");
        createWithBlock(ModItems.LEAD_BLOCK, "lead_block");
        createWithBlock(ModItems.URANIUM_BLOCK, "uranium_block");
        createWithBlock(ModItems.SILVER_BLOCK, "silver_block");
        createWithBlock(ModItems.STEEL_BLOCK, "steel_block");
        createWithBlock(ModItems.BRONZE_BLOCK, "bronze_block");
    }

    private void registerCasing() {
        createWithBlock(ModItems.BASIC_MACHINE_CASING, "basic_machine_casing");
        createWithBlock(ModItems.ADVANCED_MACHINE_CASING, "advanced_machine_casing");
    }

    private void registerSheets() {
        createWithBlock(ModItems.RUBBER_BLOCK, "rubber_block");
        createWithBlock(ModItems.RESIN_BLOCK, "resin_block");
        createWithBlock(ModItems.RUBBER_SHEET, "rubber_sheet2");
        createWithBlock(ModItems.RESIN_SHEET, "resin_sheet2");
    }

    private void registerConstructionFoam() {
        createWithBlock(ModItems.CONSTRUCTION_FOAM, "construction_foam");
        createWithBlock(ModItems.REINFORCED_CONSTRUCTION_FOAM, "reinforced_construction_foam");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_WHITE, "construction_foam_wall_white");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_RED, "construction_foam_wall_red");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_ORANGE, "construction_foam_wall_orange");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_PINK, "construction_foam_wall_pink");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_YELLOW, "construction_foam_wall_yellow");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_LIME, "construction_foam_wall_lime");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_GREEN, "construction_foam_wall_green");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE, "construction_foam_wall_light_blue");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_CYAN, "construction_foam_wall_cyan");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_BLUE, "construction_foam_wall_blue");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_MAGENTA, "construction_foam_wall_magenta");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_PURPLE, "construction_foam_wall_purple");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_BROWN, "construction_foam_wall_brown");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_GRAY, "construction_foam_wall_gray");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY, "construction_foam_wall_light_gray");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_BLACK, "construction_foam_wall_black");

        createWithBlock(ModItems.REINFORCED_GLASS, "reinforced_glass");
        createWithBlock(ModItems.REINFORCED_STONE, "reinforced_stone");
        createWithBlock(ModItems.REINFORCED_STONE_SLAB, "reinforced_stone_slab");
        createWithBlock(ModItems.REINFORCED_STONE_STAIRS, "reinforced_stone_stairs");
        createWithBlock(ModItems.REINFORCED_STONE_BRICKS, "reinforced_stone_bricks");
        createWithBlock(ModItems.SMOOTH_REINFORCED_STONE, "smooth_reinforced_stone");
        createWithBlock(ModItems.REINFORCED_STONE_BRICK_WALL, "reinforced_stone_brick_wall");
        createGeneratedTexture(ModItems.REINFORCED_DOOR, "construction_foam/reinforced_door");
    }

    protected void registerPainter() {
        createHandheldTexture(ModItems.PAINTER, "tool/painter/painter");
        createHandheldTexture(ModItems.PAINTER_WHITE, "tool/painter/painter_white");
        createHandheldTexture(ModItems.PAINTER_RED, "tool/painter/painter_red");
        createHandheldTexture(ModItems.PAINTER_ORANGE, "tool/painter/painter_orange");
        createHandheldTexture(ModItems.PAINTER_PINK, "tool/painter/painter_pink");
        createHandheldTexture(ModItems.PAINTER_YELLOW, "tool/painter/painter_yellow");
        createHandheldTexture(ModItems.PAINTER_LIME, "tool/painter/painter_lime");
        createHandheldTexture(ModItems.PAINTER_GREEN, "tool/painter/painter_green");
        createHandheldTexture(ModItems.PAINTER_LIGHT_BLUE, "tool/painter/painter_light_blue");
        createHandheldTexture(ModItems.PAINTER_CYAN, "tool/painter/painter_cyan");
        createHandheldTexture(ModItems.PAINTER_BLUE, "tool/painter/painter_blue");
        createHandheldTexture(ModItems.PAINTER_MAGENTA, "tool/painter/painter_magenta");
        createHandheldTexture(ModItems.PAINTER_PURPLE, "tool/painter/painter_purple");
        createHandheldTexture(ModItems.PAINTER_BROWN, "tool/painter/painter_brown");
        createHandheldTexture(ModItems.PAINTER_GRAY, "tool/painter/painter_gray");
        createHandheldTexture(ModItems.PAINTER_LIGHT_GRAY, "tool/painter/painter_light_gray");
        createHandheldTexture(ModItems.PAINTER_BLACK, "tool/painter/painter_black");
    }

    private void registerRubberWood() {
        createWithBlock(ModItems.RUBBER_WOOD, "rubber_wood");
        createWithBlock(ModItems.RUBBER_LOG, "rubber_log");
        createWithBlock(ModItems.RUBBER_LEAVES, "rubber_leaves");
        createWithBlock(ModItems.RUBBER_PLANKS, "rubber_planks");
        createGeneratedBlockTexture(ModItems.RUBBER_SAPLING, "rubber_wood/rubber_sapling");
        createWithBlock(ModItems.RUBBER_STAIRS, "rubber_stairs");
        createWithBlock(ModItems.RUBBER_SLAB, "rubber_slab");
        createWithBlock(ModItems.RUBBER_PLATE, "rubber_plate");
        createWithBlock(ModItems.RUBBER_GATE, "rubber_gate");
        createWithBlock(ModItems.RUBBER_FENCE, "rubber_fence");
        createGeneratedTexture(ModItems.RUBBER_DOOR, "rubber_wood/rubber_door");
        createWithBlock(ModItems.RUBBER_TRAP_DOOR, "rubber_trap_door_bottom");
    }

    private void registerIron() {
        createWithBlock(ModItems.IRON_FENCE, "iron_fence");
        createWithBlock(ModItems.IRON_GATE, "iron_gate");
    }

    protected void registerRaw() {
        createGeneratedTexture(ModItems.RAW_TIN, "raw/tin");
        createGeneratedTexture(ModItems.RAW_LEAD, "raw/lead");
        createGeneratedTexture(ModItems.RAW_URANIUM, "raw/uranium");
        createGeneratedTexture(ModItems.RAW_SILVER, "raw/silver");
    }

    protected void registerIngots() {
        createGeneratedTexture(ModItems.TIN_INGOT, "ingot/tin");
        createGeneratedTexture(ModItems.BRONZE_INGOT, "ingot/bronze");
        createGeneratedTexture(ModItems.MIXED_METAL_INGOT, "ingot/mixed_metal");
        createGeneratedTexture(ModItems.SILVER_INGOT, "ingot/silver");
        createGeneratedTexture(ModItems.STEEL_INGOT, "ingot/steel");
        createGeneratedTexture(ModItems.LEAD_INGOT, "ingot/lead");
        createGeneratedTexture(ModItems.URANIUM_INGOT, "ingot/uranium");
    }

    protected void registerDusts() {
        createGeneratedTexture(ModItems.STONE_DUST, "dust/stone");
        createGeneratedTexture(ModItems.DEEPSLATE_DUST, "dust/deepslate");
        createGeneratedTexture(ModItems.COAL_DUST, "dust/coal");
        createGeneratedTexture(ModItems.DIAMOND_DUST, "dust/diamond");
        createGeneratedTexture(ModItems.ENERGIUM_DUST, "dust/energium");
        createGeneratedTexture(ModItems.SAWDUST, "dust/sawdust");
        createGeneratedTexture(ModItems.SULFUR_DUST, "dust/sulfur");
        createGeneratedTexture(ModItems.MUD_PILE, "dust/mud_pile");
        createGeneratedTexture(ModItems.LAPIS_LAZULI_DUST, "dust/lapis_lazuli");

        createGeneratedTexture(ModItems.TIN_DUST, "dust/tin");
        createGeneratedTexture(ModItems.COPPER_DUST, "dust/copper");
        createGeneratedTexture(ModItems.IRON_DUST, "dust/iron");
        createGeneratedTexture(ModItems.GOLD_DUST, "dust/gold");
        createGeneratedTexture(ModItems.LEAD_DUST, "dust/lead");
        createGeneratedTexture(ModItems.URANIUM_DUST, "dust/uranium");
        createGeneratedTexture(ModItems.SILVER_DUST, "dust/silver");
    }

    protected void registerPlates() {
        createGeneratedTexture(ModItems.BRONZE_PLATE, "plate/bronze");
        createGeneratedTexture(ModItems.GOLD_PLATE, "plate/gold");
        createGeneratedTexture(ModItems.IRON_PLATE, "plate/iron");
        createGeneratedTexture(ModItems.IRIDIUM_PLATE, "plate/iridium");
        createGeneratedTexture(ModItems.TIN_PLATE, "plate/tin");
        createGeneratedTexture(ModItems.STEEL_PLATE, "plate/steel");
        createGeneratedTexture(ModItems.COPPER_PLATE, "plate/copper");
        createGeneratedTexture(ModItems.LEAD_PLATE, "plate/lead");
        createGeneratedTexture(ModItems.LAPIS_LAZULI_PLATE, "plate/lapis_lazuli");
        createGeneratedTexture(ModItems.ADVANCED_ALLOY, "plate/advanced_alloy");
    }

    protected void registerPurified() {
        createGeneratedTexture(ModItems.PURIFIED_TIN, "purified/tin");
        createGeneratedTexture(ModItems.PURIFIED_COPPER, "purified/copper");
        createGeneratedTexture(ModItems.PURIFIED_IRON, "purified/iron");
        createGeneratedTexture(ModItems.PURIFIED_GOLD, "purified/gold");
        createGeneratedTexture(ModItems.PURIFIED_LEAD, "purified/lead");
        createGeneratedTexture(ModItems.PURIFIED_URANIUM, "purified/uranium");
        createGeneratedTexture(ModItems.PURIFIED_SILVER, "purified/silver");
    }

    protected void registerChunk() {
        createGeneratedTexture(ModItems.TIN_CHUNK, "chunk/tin");
        createGeneratedTexture(ModItems.COPPER_CHUNK, "chunk/copper");
        createGeneratedTexture(ModItems.IRON_CHUNK, "chunk/iron");
        createGeneratedTexture(ModItems.GOLD_CHUNK, "chunk/gold");
        createGeneratedTexture(ModItems.LEAD_CHUNK, "chunk/lead");
        createGeneratedTexture(ModItems.URANIUM_CHUNK, "chunk/uranium");
        createGeneratedTexture(ModItems.SILVER_CHUNK, "chunk/silver");
    }

    protected void registerCrafting() {
        createGeneratedTexture(ModItems.ELECTRONIC_CIRCUIT, "crafting/electronic_circuit");
        createGeneratedTexture(ModItems.ADVANCED_CIRCUIT, "crafting/advanced_circuit");
        createGeneratedTexture(ModItems.CARBON_FIBERS, "crafting/carbon_fibers");
        createGeneratedTexture(ModItems.COMBINED_CARBON_FIBERS, "crafting/combined_carbon_fibers");
        createGeneratedTexture(ModItems.CARBON_PLATE, "crafting/carbon_plate");
        createGeneratedTexture(ModItems.BIO_CHAFF, "crafting/bio_chaff");
        createGeneratedTexture(ModItems.HEAT_CONDUCTOR, "crafting/heat_conductor");
        createGeneratedTexture(ModItems.FOAM_POWDER, "crafting/foam_powder");
        createGeneratedTexture(ModItems.REINFORCED_FOAM_POWDER, "crafting/reinforced_foam_powder");
        createGeneratedTexture(ModItems.SMALL_POWER_UNIT, "crafting/small_power_unit");
        createGeneratedTexture(ModItems.POWER_UNIT, "crafting/power_unit");
        createGeneratedTexture(ModItems.COIL, "crafting/coil");
        createGeneratedTexture(ModItems.ELECTRIC_MOTOR, "crafting/electric_motor");
        createGeneratedTexture(ModItems.SCRAP, "crafting/scrap");
        createGeneratedTexture(ModItems.SCRAP_BOX, "crafting/scrap_box");
    }

    protected void registerResource() {
        createGeneratedTexture(ModItems.STICKY_RESIN, "resource/sticky_resin");
        createGeneratedTexture(ModItems.RUBBER, "resource/rubber");
        createGeneratedTexture(ModItems.IRIDIUM_SHARD, "resource/iridium_shard");
        createGeneratedTexture(ModItems.IRIDIUM, "resource/iridium");
    }

    protected void registerStorage() {
        createGeneratedTexture(ModItems.MEMORY_CARD, "storage/memory_card");
    }

    protected void registerFood() {
        createGeneratedTexture(ModItems.TIN_CAN, "food/tin_can");
        createGeneratedTexture(ModItems.FILLED_TIN_CAN, "food/filled_tin_can");
    }

    protected void registerCrop() {
        createGeneratedTexture(ModItems.FERTILIZER, "crop/fertilizer");
    }



    private ItemModelBuilder createGeneratedTexture(RegistryObject<Item> item, String path) {
        return singleTexture(item.getId().getPath(), new ResourceLocation("item/generated"),"layer0",new ResourceLocation(IndReb.MODID, "item/" + path));
    }

    private ItemModelBuilder createGeneratedBlockTexture(RegistryObject<Item> block, String path) {
        return singleTexture(block.getId().getPath(), new ResourceLocation("item/generated"),"layer0",new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    private ItemModelBuilder createHandheldTexture(RegistryObject<Item> item, String path) {
        return singleTexture(item.getId().getPath(), new ResourceLocation("item/handheld"),"layer0",new ResourceLocation(IndReb.MODID, "item/" + path));
    }

    private ItemModelBuilder createWithBlock(RegistryObject<Item> block, String path) {
        return withExistingParent(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    private ItemModelBuilder createWithActive(RegistryObject<Item> item, String path) {
        return getBuilder(item.getId().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/" + path)
                .override().predicate(new ResourceLocation(IndReb.MODID, "active"), 0).model(createTestModel("handheld", path, "")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "active"), 1).model(createTestModel("handheld", path, "_active")).end();
    }

    private ItemModelBuilder createWithChargeRatio(RegistryObject<Item> item, String path) {
        return getBuilder(item.getId().getPath())
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/" + path + "_0")
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 0).model(createTestModel("generated", path, "_0")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 1).model(createTestModel("generated", path, "_1")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 2).model(createTestModel("generated", path, "_2")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 3).model(createTestModel("generated", path, "_3")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 4).model(createTestModel("generated", path, "_4")).end();
    }

    private ItemModelBuilder createWithDirections(RegistryObject<Item> item, String path) {
        return getBuilder(item.getId().getPath())
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/" + path)
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), -1).model(createTestModel("generated", path, "")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 0).model(createTestModel("generated", path, "_down")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 1).model(createTestModel("generated", path, "_up")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 2).model(createTestModel("generated", path, "_north")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 3).model(createTestModel("generated", path, "_south")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 4).model(createTestModel("generated", path, "_west")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 5).model(createTestModel("generated", path, "_east")).end();
    }


    private ItemModelBuilder createTestModel(String type, String path, String suffix) {
        return getBuilder("item/" + path + suffix).parent(getExistingFile(mcLoc("item/" + type)))
                .texture("layer0", "item/" + path + suffix);
    }

}
