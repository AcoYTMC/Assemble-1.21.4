package net.acoyt.assemble.block.mechanism;

import net.acoyt.assemble.entity.ChargedCopperEntity;
import net.acoyt.assemble.init.ModBlocks;
import net.acoyt.assemble.init.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChargedCopperBlock extends Block {
    public static final BooleanProperty LIT = Properties.LIT;

    public ChargedCopperBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, false));
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            Entity entity = null;
            List<ChargedCopperEntity> entities = world.getEntitiesByType(ModEntities.CHARGED_COPPER, new Box(pos), chargedCopper -> true);
            if (entities.isEmpty()) {
                entity = ModEntities.CHARGED_COPPER.spawn((ServerWorld) world, pos, SpawnReason.TRIGGERED);
            } else {
                entity = entities.get(0);
            }
        }
    }

    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            List<ChargedCopperEntity> entities = world.getEntitiesByType(ModEntities.CHARGED_COPPER, new Box(pos), chargedCopper -> true);

            for (ChargedCopperEntity chargedCopper : entities) {
                if (world.getBlockState(pos).getBlock() != ModBlocks.CHARGED_COPPER) {
                    chargedCopper.discard();
                }
            }
        }

        return super.onBreak(world, pos, state, player);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{LIT});
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        world.addParticle(ParticleTypes.ELECTRIC_SPARK, pos.getX() + 0.5F + random.nextBetween(-1, 1), pos.getY() + 0.5F + random.nextBetween(-1, 1), pos.getZ() + 0.5F + random.nextBetween(-1, 1), 0, 0, 0);
        world.addParticle(ParticleTypes.ELECTRIC_SPARK, pos.getX() + 0.5F + random.nextBetween(-1, 1), pos.getY() + 0.5F + random.nextBetween(-1, 1), pos.getZ() + 0.5F + random.nextBetween(-1, 1), 0, 0, 0);
        world.addParticle(ParticleTypes.ELECTRIC_SPARK, pos.getX() + 0.5F + random.nextBetween(-1, 1), pos.getY() + 0.5F + random.nextBetween(-1, 1), pos.getZ() + 0.5F + random.nextBetween(-1, 1), 0, 0, 0);
    }

    private static Block upBlock(World world, BlockPos pos) {
        return world.getBlockState(pos.up()).getBlock();
    }

    private static Block downBlock(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock();
    }
}
