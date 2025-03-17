package net.acoyt.assemble.init;

import net.acoyt.assemble.Assemble;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public interface ModBlockEntities {
    Map<BlockEntityType<?>, Identifier> BLOCK_ENTITIES = new LinkedHashMap();

    private static <T extends BlockEntityType<?>> T createBlockEntity(String name, T blockEntity) {
        BLOCK_ENTITIES.put(blockEntity, Assemble.id(name));
        return blockEntity;
    }

    static void init() {
        BLOCK_ENTITIES.keySet().forEach((blockEntityType) -> {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BLOCK_ENTITIES.get(blockEntityType), blockEntityType);
        });
    }
}
