package net.acoyt.assemble.init;

import net.acoyt.assemble.Assemble;
import net.acoyt.assemble.entity.ChargedCopperEntity;
import net.acoyt.assemble.entity.SeatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModEntities {
    public static final EntityType<SeatEntity> SEAT;
    public static final EntityType<ChargedCopperEntity> CHARGED_COPPER;

    private static <T extends Entity> EntityType<T> register(RegistryKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    private static RegistryKey<EntityType<?>> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Assemble.id(id));
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return register(keyOf(id), type);
    }

    public static void init() {
        Assemble.LOGGER.info("Registering Entities for Assemble(d)");
    }

    static {
        SEAT = register("seat", EntityType.Builder.create(SeatEntity::new, SpawnGroup.MISC).dimensions(0.5F, 0.5F));
        CHARGED_COPPER = register("charged_copper", EntityType.Builder.create(ChargedCopperEntity::new, SpawnGroup.MISC).dimensions(0.5F, 0.5F));
    }
}
