package net.acoyt.assemble.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class CopperGlass extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED;
    public static final EnumProperty<Direction> FACING;

    public static final VoxelShape NORTH_SHAPE;
    public static final VoxelShape NORTH_COLLISION_SHAPE;
    public static final VoxelShape EAST_SHAPE;
    public static final VoxelShape EAST_COLLISION_SHAPE;
    public static final VoxelShape SOUTH_SHAPE;
    public static final VoxelShape SOUTH_COLLISION_SHAPE;
    public static final VoxelShape WEST_SHAPE;
    public static final VoxelShape WEST_COLLISION_SHAPE;
    public static final VoxelShape UP_SHAPE;
    public static final VoxelShape UP_COLLISION_SHAPE;
    public static final VoxelShape DOWN_SHAPE;
    public static final VoxelShape DOWN_COLLISION_SHAPE;

    public CopperGlass(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)super.getDefaultState().with(WATERLOGGED, false)).with(FACING, Direction.SOUTH));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context.isHolding(this.asItem())) {
            VoxelShape var10000;
            switch ((Direction)state.get(FACING)) {
                case NORTH -> var10000 = NORTH_SHAPE;
                case EAST -> var10000 = EAST_SHAPE;
                case SOUTH -> var10000 = SOUTH_SHAPE;
                case WEST -> var10000 = WEST_SHAPE;
                case UP -> var10000 = UP_SHAPE;
                case DOWN -> var10000 = DOWN_SHAPE;
                default -> throw new IncompatibleClassChangeError();
            }

            return var10000;
        } else {
            return this.getCollisionShape(state, world, pos, context);
        }
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape var10000;
        switch ((Direction)state.get(FACING)) {
            case NORTH -> var10000 = NORTH_COLLISION_SHAPE;
            case EAST -> var10000 = EAST_COLLISION_SHAPE;
            case SOUTH -> var10000 = SOUTH_COLLISION_SHAPE;
            case WEST -> var10000 = WEST_COLLISION_SHAPE;
            case UP -> var10000 = UP_COLLISION_SHAPE;
            case DOWN -> var10000 = DOWN_COLLISION_SHAPE;
            default -> throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public VoxelShape getCullingShape(BlockState state) {
        return VoxelShapes.empty();
    }

    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Direction facing = ctx.getSide();
        BlockState neighborState = world.getBlockState(pos.offset(facing.getOpposite()));
        if (!ctx.shouldCancelInteraction() && neighborState.isOf(this)) {
            Direction neighborFacing = (Direction)neighborState.get(FACING);
            if (!neighborFacing.getAxis().equals(facing.getAxis())) {
                facing = neighborFacing;
            }
        }

        return (BlockState)((BlockState)this.getDefaultState().with(FACING, facing)).with(WATERLOGGED, world.getFluidState(pos).getFluid().equals(Fluids.WATER));
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, WATERLOGGED});
    }

    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        Direction facing = (Direction)state.get(FACING);
        if (stateFrom.isOf(this)) {
            Direction fromFacing = (Direction)stateFrom.get(FACING);
            if (fromFacing.equals(direction)) {
                return facing.equals(direction.getOpposite());
            }

            if (fromFacing.equals(direction.getOpposite())) {
                return facing.equals(direction);
            }

            if (fromFacing.equals(facing)) {
                return true;
            }
        }

        return super.isSideInvisible(state, stateFrom, direction);
    }

    public boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        FACING = Properties.FACING;

        NORTH_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 14.0F, 16.0F, 16.0F, 16.0F);
        EAST_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 2.0F, 16.0F, 16.0F);
        SOUTH_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 2.0F);
        WEST_SHAPE = Block.createCuboidShape(14.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);
        UP_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 2.0F, 16.0F);
        DOWN_SHAPE = Block.createCuboidShape(0.0F, 14.0F, 0.0F, 16.0F, 16.0F, 16.0F);
        
        NORTH_COLLISION_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 14.0F, 16.0F, 16.0F, 16.0F);
        EAST_COLLISION_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 2.0F, 16.0F, 16.0F);
        SOUTH_COLLISION_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 2.0F);
        WEST_COLLISION_SHAPE = Block.createCuboidShape(14.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);
        UP_COLLISION_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 2.0F, 16.0F);
        DOWN_COLLISION_SHAPE = Block.createCuboidShape(0.0F, 14.0F, 0.0F, 16.0F, 16.0F, 16.0F);
    }
}
