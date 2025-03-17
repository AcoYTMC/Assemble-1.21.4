package net.acoyt.assemble.init;

import net.acoyt.assemble.Assemble;
import net.acoyt.assemble.block.*;
import net.acoyt.assemble.block.mechanism.ChargedCopperBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface ModBlocks {
    // Seats
    Block WHITE_SEAT = createBlock("white_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.TERRACOTTA_WHITE).registryKey(keyOf("white_seat"))), true);
    Block LIGHT_GRAY_SEAT = createBlock("light_gray_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.WHITE_GRAY).registryKey(keyOf("light_gray_seat"))), true);
    Block GRAY_SEAT = createBlock("gray_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.DEEPSLATE_GRAY).registryKey(keyOf("gray_seat"))), true);
    Block BLACK_SEAT = createBlock("black_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.BLACK).registryKey(keyOf("black_seat"))), true);
    Block BROWN_SEAT = createBlock("brown_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.TERRACOTTA_BLACK).registryKey(keyOf("brown_seat"))), true);
    Block RED_SEAT = createBlock("red_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.DARK_RED).registryKey(keyOf("red_seat"))), true);
    Block ORANGE_SEAT = createBlock("orange_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.ORANGE).registryKey(keyOf("orange_seat"))), true);
    Block YELLOW_SEAT = createBlock("yellow_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.TERRACOTTA_YELLOW).registryKey(keyOf("yellow_seat"))), true);
    Block LIME_SEAT = createBlock("lime_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.TERRACOTTA_LIME).registryKey(keyOf("lime_seat"))), true);
    Block GREEN_SEAT = createBlock("green_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.EMERALD_GREEN).registryKey(keyOf("green_seat"))), true);
    Block CYAN_SEAT = createBlock("cyan_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.CYAN).registryKey(keyOf("cyan_seat"))), true);
    Block LIGHT_BLUE_SEAT = createBlock("light_blue_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.LIGHT_BLUE).registryKey(keyOf("light_blue_seat"))), true);
    Block BLUE_SEAT = createBlock("blue_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.LAPIS_BLUE).registryKey(keyOf("blue_seat"))), true);
    Block PURPLE_SEAT = createBlock("purple_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.PURPLE).registryKey(keyOf("purple_seat"))), true);
    Block MAGENTA_SEAT = createBlock("magenta_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.MAGENTA).registryKey(keyOf("magenta_seat"))), true);
    Block PINK_SEAT = createBlock("pink_seat", new SeatBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.DULL_PINK).registryKey(keyOf("pink_seat"))), true);

    // Goat Shag
    Block WHITE_SHAG_BLOCK = createBlock("white_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("white_shag_block"))), true);
    Block LIGHT_GRAY_SHAG_BLOCK = createBlock("light_gray_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("light_gray_shag_block"))), true);
    Block GRAY_SHAG_BLOCK = createBlock("gray_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("gray_shag_block"))), true);
    Block BLACK_SHAG_BLOCK = createBlock("black_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("black_shag_block"))), true);
    Block BROWN_SHAG_BLOCK = createBlock("brown_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("brown_shag_block"))), true);
    Block RED_SHAG_BLOCK = createBlock("red_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("red_shag_block"))), true);
    Block ORANGE_SHAG_BLOCK = createBlock("orange_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("orange_shag_block"))), true);
    Block YELLOW_SHAG_BLOCK = createBlock("yellow_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("yellow_shag_block"))), true);
    Block LIME_SHAG_BLOCK = createBlock("lime_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("lime_shag_block"))), true);
    Block GREEN_SHAG_BLOCK = createBlock("green_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("green_shag_block"))), true);
    Block CYAN_SHAG_BLOCK = createBlock("cyan_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("cyan_shag_block"))), true);
    Block LIGHT_BLUE_SHAG_BLOCK = createBlock("light_blue_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("light_blue_shag_block"))), true);
    Block BLUE_SHAG_BLOCK = createBlock("blue_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("blue_shag_block"))), true);
    Block PURPLE_SHAG_BLOCK = createBlock("purple_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("purple_shag_block"))), true);
    Block MAGENTA_SHAG_BLOCK = createBlock("magenta_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("magenta_shag_block"))), true);
    Block PINK_SHAG_BLOCK = createBlock("pink_shag_block", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(keyOf("pink_shag_block"))), true);

    // Copper Glass
    Block COPPER_GLASS = createBlock("copper_glass", new CopperGlass(AbstractBlock.Settings.copy(Blocks.GLASS).nonOpaque().registryKey(keyOf("copper_glass"))), true);

    // Chicken Bucket
    Block CHICKEN_BUCKET = createBlock("chicken_bucket", new ChickenBucket(AbstractBlock.Settings.copy(Blocks.RED_WOOL).nonOpaque().registryKey(keyOf("chicken_bucket"))), true);

    // Flowering Vine
    Block FLOWERING_VINE = createBlock("flowering_vine", new VineBlock(AbstractBlock.Settings.copy(Blocks.VINE).nonOpaque().registryKey(keyOf("flowering_vine"))), true);

    // Legally Distinct Brick
    Block LEGAL_BRICK = createBlock("legal_brick", new LegalBrick(AbstractBlock.Settings.copy(Blocks.BRICKS).nonOpaque().registryKey(keyOf("legal_brick"))), true);

    // Copper Stuff
    Block CHARGED_COPPER = createBlock("charged_copper", new ChargedCopperBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK).registryKey(keyOf("charged_copper"))), true);

    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Assemble.id(id));
    }

    // Create with BlockItem, with normal Item.Settings
    private static Block createBlock(String name, Block block, boolean createBlock) {
        if (createBlock) {
            Registry.register(Registries.ITEM, Assemble.id(name), new BlockItem(block, new Item.Settings().translationKey(block.getTranslationKey()).registryKey(ModItems.keyOf(name))));
        }
        return Registry.register(Registries.BLOCK, Assemble.id(name), block);
    }

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register((entries) -> {
            entries.addAfter(Blocks.PINK_BED,
                    WHITE_SEAT,
                    LIGHT_GRAY_SEAT,
                    GRAY_SEAT,
                    BLACK_SEAT,
                    BROWN_SEAT,
                    RED_SEAT,
                    ORANGE_SEAT,
                    YELLOW_SEAT,
                    LIME_SEAT,
                    GREEN_SEAT,
                    CYAN_SEAT,
                    LIGHT_BLUE_SEAT,
                    BLUE_SEAT,
                    PURPLE_SEAT,
                    MAGENTA_SEAT,
                    PINK_SEAT
            );

            entries.addAfter(Blocks.PINK_WOOL,
                    WHITE_SHAG_BLOCK,
                    LIGHT_GRAY_SHAG_BLOCK,
                    GRAY_SHAG_BLOCK,
                    BLACK_SHAG_BLOCK,
                    BROWN_SHAG_BLOCK,
                    RED_SHAG_BLOCK,
                    ORANGE_SHAG_BLOCK,
                    YELLOW_SHAG_BLOCK,
                    LIME_SHAG_BLOCK,
                    GREEN_SHAG_BLOCK,
                    CYAN_SHAG_BLOCK,
                    LIGHT_BLUE_SHAG_BLOCK,
                    BLUE_SHAG_BLOCK,
                    PURPLE_SHAG_BLOCK,
                    MAGENTA_SHAG_BLOCK,
                    PINK_SHAG_BLOCK
            );
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register((entries) -> {
            entries.addAfter(Blocks. PINK_BED,
                    WHITE_SEAT,
                    LIGHT_GRAY_SEAT,
                    GRAY_SEAT,
                    BLACK_SEAT,
                    BROWN_SEAT,
                    RED_SEAT,
                    ORANGE_SEAT,
                    YELLOW_SEAT,
                    LIME_SEAT,
                    GREEN_SEAT,
                    CYAN_SEAT,
                    LIGHT_BLUE_SEAT,
                    BLUE_SEAT,
                    PURPLE_SEAT,
                    MAGENTA_SEAT,
                    PINK_SEAT
            );
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((entries) -> {
            entries.add(COPPER_GLASS);
            entries.addAfter(Blocks.COPPER_BULB, CHARGED_COPPER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register((entries) -> {
            entries.addAfter(Blocks.VINE, FLOWERING_VINE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register((entries) -> {
            entries.add(LEGAL_BRICK);
        });
    }
}
