package net.acoyt.assemble.block;

import net.acoyt.assemble.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.NotNull;

public class CopperGlass extends TransparentBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED;
    public static final EnumProperty<Direction> FACING;

    private static final VoxelShape NORTH_SHAPE;
    private static final VoxelShape EAST_SHAPE;
    private static final VoxelShape SOUTH_SHAPE;
    private static final VoxelShape WEST_SHAPE;

    private static final VoxelShape MORE_NORTH_SHAPE;
    private static final VoxelShape MORE_EAST_SHAPE;
    private static final VoxelShape MORE_SOUTH_SHAPE;
    private static final VoxelShape MORE_WEST_SHAPE;

    public CopperGlass(Settings settings) {
        super(settings);
    }

    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    public BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(WATERLOGGED, fluidState.isOf(Fluids.WATER));
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, WATERLOGGED});
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context.isHolding(ModItems.COPPER_GLASS)) {
            switch ((Direction) state.get(FACING)) {
                case NORTH:
                default:
                    return MORE_NORTH_SHAPE;
                case SOUTH:
                    return MORE_SOUTH_SHAPE;
                case WEST:
                    return MORE_WEST_SHAPE;
                case EAST:
                    return MORE_EAST_SHAPE;
            }
        } else {
            switch ((Direction) state.get(FACING)) {
                case NORTH:
                default:
                    return NORTH_SHAPE;
                case SOUTH:
                    return SOUTH_SHAPE;
                case WEST:
                    return WEST_SHAPE;
                case EAST:
                    return EAST_SHAPE;
            }
        }
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        FACING = Properties.HORIZONTAL_FACING;
        NORTH_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 13.0F, 16.0F, 16.0F, 16.0F);
        EAST_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 3.0F, 16.0F, 16.0F);
        SOUTH_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 3.0F);
        WEST_SHAPE = Block.createCuboidShape(13.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);

        MORE_NORTH_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 8.0F, 16.0F, 16.0F, 16.0F);
        MORE_EAST_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 8.0F, 16.0F, 16.0F);
        MORE_SOUTH_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 8.0F);
        MORE_WEST_SHAPE = Block.createCuboidShape(8.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);
    }
}
