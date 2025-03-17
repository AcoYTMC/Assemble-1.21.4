package net.acoyt.assemble.init;

import net.acoyt.assemble.Assemble;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface ModTags {
    TagKey<Item> SHEARS = itemOf("shears");

    private static TagKey<Item> itemOf(String id) {
        return TagKey.of(RegistryKeys.ITEM, Assemble.id(id));
    }
}
