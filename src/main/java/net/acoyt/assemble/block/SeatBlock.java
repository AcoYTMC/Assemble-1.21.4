package net.acoyt.assemble.block;

import net.acoyt.assemble.entity.SeatEntity;
import net.acoyt.assemble.init.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeatBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED;
    private static final VoxelShape SHAPE;

    public SeatBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)super.getDefaultState().with(WATERLOGGED, false)));
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            Entity entity = null;
            List<SeatEntity> entities = world.getEntitiesByType(ModEntities.SEAT, new Box(pos), seat -> true);
            if (entities.isEmpty()) {
                entity = ModEntities.SEAT.spawn((ServerWorld) world, pos, SpawnReason.TRIGGERED);
            } else {
                entity = entities.get(0);
            }

            player.startRiding(entity);
        }

        return ActionResult.SUCCESS;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance * 0);
    }

    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        } else {
            this.bounceEntity(entity, 1);
        }
    }

    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Direction facing = ctx.getSide();
        BlockState neighborState = world.getBlockState(pos.offset(facing.getOpposite()));

        return (BlockState)((BlockState)this.getDefaultState()).with(WATERLOGGED, world.getFluidState(pos).getFluid().equals(Fluids.WATER));
    }

    private void bounceEntity(Entity entity, float power) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < (double)0.0F) {
            double d = entity instanceof LivingEntity ? (double)1.0F : 0.8;
            entity.setVelocity(vec3d.x, -vec3d.y * (double)0.66F * d * power, vec3d.z);
        }

    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{WATERLOGGED});
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        SHAPE = createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 8.0F, 16.0F);
    }
}
