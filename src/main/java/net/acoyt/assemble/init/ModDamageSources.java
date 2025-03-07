package net.acoyt.assemble.init;

import net.acoyt.assemble.Assemble;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModDamageSources {
    public static final RegistryKey<DamageType> TRUE_PAIN = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Assemble.id("true_pain"));
    public static final RegistryKey<DamageType> MICROPLASTICS = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Assemble.id("microplastics"));

    private final DamageSource truePain;
    private final DamageSource microplastics;

    public ModDamageSources(DamageSources damageSources) {
        this.truePain = damageSources.create(TRUE_PAIN);
        this.microplastics = damageSources.create(MICROPLASTICS);
    }

    public DamageSource truePain() {
        return truePain;
    }

    public DamageSource microplastics() {
        return microplastics;
    }
}
