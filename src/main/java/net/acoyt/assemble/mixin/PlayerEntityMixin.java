package net.acoyt.assemble.mixin;

import net.acoyt.assemble.init.ModSoundEvents;
import net.acoyt.assemble.item.HammerItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = {"attack"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V")
            }
    )
    private void bonk(Entity target, CallbackInfo ci) {
        if (this.getStackInHand(Hand.MAIN_HAND).getItem() instanceof HammerItem) {
            if (target instanceof LivingEntity) {
                World world = this.getWorld();
                world.playSound((PlayerEntity)null, this.getBlockPos(), ModSoundEvents.BONK, SoundCategory.PLAYERS);
            }
        }
    }
}
