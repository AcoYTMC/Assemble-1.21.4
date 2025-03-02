package net.acoyt.assemble.block;

import net.acoyt.assemble.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.TransparentBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
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

public class CopperGlass extends TransparentBlock {
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
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
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

    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            if (!direction.getAxis().isHorizontal()) {
                return true;
            }
        }

        return super.isSideInvisible(state, stateFrom, direction);
    }

    static {
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
