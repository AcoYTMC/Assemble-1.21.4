package net.acoyt.assemble.block;

import net.acoyt.assemble.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChickenBucket extends Block {
    public static final EnumProperty<Direction> FACING;
    private static final VoxelShape SHAPE;
    private static float chimken = 0;

    public ChickenBucket(Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack stack = player.getMainHandStack();
        if (stack.isEmpty() && !player.isSneaking() && chimken > 0) {
            world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(ModItems.FRIED_CHICKEN)));
            chimken = chimken - 0.5f;
            return ActionResult.SUCCESS;
        } else if (stack.isOf(ModItems.FRIED_CHICKEN) || stack.isOf(Items.COOKED_CHICKEN)) {
            if (!player.isCreative()) {
                stack.decrement(1);
            }
            chimken = chimken + 0.5f;
            return ActionResult.SUCCESS;
        } else if (stack.isEmpty() && player.isSneaking()) {
            player.sendMessage(Text.literal("There are " + chimken + " Friend Chicken pieces left."), true);
            return ActionResult.PASS;
        } else {
            return ActionResult.FAIL;
        }
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
        return SHAPE;
    }

    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD, Formatting.GOLD));
    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
        SHAPE = createCuboidShape(4.0F, 0.0F, 4.0F, 13.0F, 11.0F, 13.0F);
    }
}
