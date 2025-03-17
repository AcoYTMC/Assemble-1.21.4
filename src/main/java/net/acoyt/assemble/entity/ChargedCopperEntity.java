package net.acoyt.assemble.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class ChargedCopperEntity extends Entity {
    public ChargedCopperEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public boolean isInvisible() {
        return false;
    }

    public void tick() {
        super.tick();
    }

    // Required Stuff
    protected void initDataTracker(DataTracker.Builder builder) {}
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }
    protected void readCustomDataFromNbt(NbtCompound nbt) {}
    protected void writeCustomDataToNbt(NbtCompound nbt) {}
}
