package net.acoyt.assemble.block;

import net.acoyt.assemble.AssembleConfig;
import net.acoyt.assemble.init.ModBlocks;
import net.acoyt.assemble.init.ModDamageSources;
import net.acoyt.assemble.init.ModItems;
import net.acoyt.assemble.init.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class LegalBrick extends Block {
    private static final VoxelShape SMALL_SHAPE;
    private static final VoxelShape LARGE_SHAPE;

    public LegalBrick(Settings settings) {
        super(settings);
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.slowMovement(state, new Vec3d((double)0.8F, (double)0.75F, (double)0.8F));
            if (world instanceof ServerWorld serverWorld) {
                Vec3d vec3d = entity.isControlledByPlayer() ? entity.getMovement() : entity.getLastRenderPos().subtract(entity.getPos());
                if (vec3d.horizontalLengthSquared() > (double)0.0F) {
                    double d = Math.abs(vec3d.getX());
                    double e = Math.abs(vec3d.getZ());
                    if (d >= (double)0.003F || e >= (double)0.003F) {
                        entity.damage(serverWorld, world.getDamageSources().create(ModDamageSources.TRUE_PAIN, (PlayerEntity)null), 1.0F * AssembleConfig.brickDamageMultiplier);
                        Random random = world.getRandom();
                        int radnom = random.nextBetween(1, 40);
                        if (radnom == 3) {
                            world.playSound((PlayerEntity)null, new BlockPos(pos.getX(), pos.getY(), pos.getZ()),  ModSoundEvents.TRUE_PAIN, SoundCategory.PLAYERS);
                        }
                    }
                }
            }

        }
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.playSound((PlayerEntity)null, pos, ModSoundEvents.PLACE_LEGAL, SoundCategory.BLOCKS);
    }

    protected boolean isTransparent(BlockState state) {
        return true;
    }

    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(ModBlocks.LEGAL_BRICK.asItem());
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context.isHolding(this.asItem())) {
            return LARGE_SHAPE;
        } else {
            return SMALL_SHAPE;
        }
    }

    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return type == NavigationType.AIR && !this.collidable || super.canPathfindThrough(state, type);
    }

    static {
        SMALL_SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0);
        LARGE_SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0);
    }
}
