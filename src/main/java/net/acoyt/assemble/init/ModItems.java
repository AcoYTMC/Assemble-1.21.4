package net.acoyt.assemble.init;

import net.acoyt.assemble.Assemble;
import net.acoyt.assemble.item.HammerItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public interface ModItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap();
    // Copper Glass BlockItem
    Item COPPER_GLASS = createItem("copper_glass", new BlockItem(ModBlocks.COPPER_GLASS, new Item.Settings().translationKey(ModBlocks.COPPER_GLASS.getTranslationKey()).registryKey(keyOf("copper_glass"))));

    // Hammer
    Item HAMMER = createItem("hammer", new HammerItem(new Item.Settings().registryKey(keyOf("hammer"))));

    static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Assemble.id(id));
    }

    private static <T extends Item> T createItem(String name, T item) {
        ITEMS.put(item, Assemble.id(name));
        return item;
    }

    static void init() {
        ITEMS.keySet().forEach((item) -> {
            Registry.register(Registries.ITEM, ITEMS.get(item), item);
        });
    }
}
