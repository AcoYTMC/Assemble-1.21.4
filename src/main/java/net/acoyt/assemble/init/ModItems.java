package net.acoyt.assemble.init;

import net.acoyt.assemble.Assemble;
import net.acoyt.assemble.item.HammerItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public interface ModItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap();
    Item HAMMER = createItem("hammer", new HammerItem(new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers()).registryKey(keyOf("hammer"))));
    Item FRIED_CHICKEN = createItem("fried_chicken", new Item(new Item.Settings().food(new FoodComponent(3, 6.5f, true), new ConsumableComponent(1.6f, UseAction.EAT, SoundEvents.ENTITY_GENERIC_EAT, true, List.of())).registryKey(keyOf("fried_chicken"))));

    Item GOAT_SHAG = createItem("goat_shag", new Item(new Item.Settings().registryKey(keyOf("goat_shag"))));

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

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((entries) -> {
            entries.addAfter(Items.MACE, HAMMER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register((entries) -> {
            entries.addAfter(Items.COOKED_CHICKEN, FRIED_CHICKEN);
            entries.addAfter(FRIED_CHICKEN, ModBlocks.CHICKEN_BUCKET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((entries) -> {
            entries.add(GOAT_SHAG);
        });
    }
}
