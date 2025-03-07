package net.acoyt.assemble.init;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;

public class ModConsumables {
    public static final ConsumableComponent LEGAL_BRICK;

    public ModConsumables() {
    }

    public static ConsumableComponent.Builder food() {
        return ConsumableComponent.builder().consumeSeconds(1.6F).useAction(UseAction.EAT).consumeParticles(true);
    }

    static {
        LEGAL_BRICK = food().consumeSeconds(2.0f).sound(ModSoundEvents.EAT_LEGAL).finishSound(ModSoundEvents.LEGAL_DEATH).consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 5, 5))).build();
    }
}
