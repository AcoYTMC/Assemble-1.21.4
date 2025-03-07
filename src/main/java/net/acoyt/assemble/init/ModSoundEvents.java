package net.acoyt.assemble.init;

import net.acoyt.assemble.Assemble;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public interface ModSoundEvents {
    List<SoundEvent> SOUND_EVENTS = new LinkedList();
    // True Pain
    SoundEvent TRUE_PAIN = createSound("block.legal.true_pain");

    // Bonk
    SoundEvent BONK = createSound("item.hammer.bonk");

    // Legally Distinct Bricks
    RegistryEntry<SoundEvent> EAT_LEGAL = createEntrySound("block.legal.eat_legal");
    RegistryEntry<SoundEvent> LEGAL_DEATH = createEntrySound("block.legal.legal_death");
    SoundEvent PLACE_LEGAL = createSound("block.legal.place_legal");

    static RegistryEntry<SoundEvent> createEntrySound(String name) {
        return Registry.registerReference(Registries.SOUND_EVENT, Assemble.id(name), SoundEvent.of(Assemble.id(name)));
    }

    static SoundEvent createSound(String name) {
        SoundEvent soundEvent = SoundEvent.of(Assemble.id(name));
        SOUND_EVENTS.add(soundEvent);
        return soundEvent;
    }

    static void init() {
        SOUND_EVENTS.forEach((soundEvent) -> {
            Registry.register(Registries.SOUND_EVENT, soundEvent.id(), soundEvent);
        });
    }
}
