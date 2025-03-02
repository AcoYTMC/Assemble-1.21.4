package net.acoyt.assemble.util;

import net.acoyt.assemble.AssembleConfig;
import net.acoyt.assemble.entity.SeatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class SeatUtil {
    private static final Map<Identifier, Map<BlockPos, Pair<SeatEntity, Vec3d>>> OCCUPIED = new HashMap<>();

    public static boolean addSeatEntity(World world, BlockPos pos, SeatEntity seat, Vec3d playerPos) {
        if (!world.isClient) {
            Identifier id = getDimensionTypeId(world);

            if (!OCCUPIED.containsKey(id))
                OCCUPIED.put(id, new HashMap<>());

            OCCUPIED.get(id).put(pos, Pair.of(seat, playerPos));
            return true;
        }

        return false;
    }

    public static boolean removeSeatEntity(World world, BlockPos pos) {
        if (!world.isClient) {
            Identifier id = getDimensionTypeId(world);

            if (OCCUPIED.containsKey(id)) {
                OCCUPIED.get(id).remove(pos);
                return true;
            }
        }

        return false;
    }

    public static SeatEntity getSeatEntity(World world, BlockPos pos) {
        if (!world.isClient) {
            Identifier id = getDimensionTypeId(world);

            if (OCCUPIED.containsKey(id) && OCCUPIED.get(id).containsKey(pos))
                return OCCUPIED.get(id).get(pos).getLeft();
        }

        return null;
    }

    public static Vec3d getPreviousPlayerPosition(PlayerEntity player, SeatEntity seat) {
        if (!player.getWorld().isClient) {
            Identifier id = getDimensionTypeId(player.getWorld());

            if (OCCUPIED.containsKey(id)) {
                for (Pair<SeatEntity, Vec3d> pair : OCCUPIED.get(id).values()) {
                    if (pair.getLeft() == seat)
                        return pair.getRight();
                }
            }
        }

        return null;
    }

    public static boolean isOccupied(World world, BlockPos pos) {
        Identifier id = getDimensionTypeId(world);

        return OCCUPIED.containsKey(id) && OCCUPIED.get(id).containsKey(pos);
    }

    public static boolean isPlayerSitting(PlayerEntity player) {
        for (Identifier i : OCCUPIED.keySet()) {
            for (Pair<SeatEntity, Vec3d> pair : OCCUPIED.get(i).values()) {
                if (pair.getLeft().hasPassenger(player))
                    return true;
            }
        }

        return false;
    }

    public static boolean isPlayerInRange(PlayerEntity player, BlockPos pos) {
        BlockPos playerPos = player.getBlockPos();
        int blockReachDistance = AssembleConfig.seatReach;

        if (blockReachDistance == 0)
            return playerPos.getY() - pos.getY() <= 1 && playerPos.getX() - pos.getX() == 0 && playerPos.getZ() - pos.getZ() == 0;

        Box range = new Box(pos.getX() + blockReachDistance, pos.getY() + blockReachDistance, pos.getZ() + blockReachDistance, pos.getX() - blockReachDistance, pos.getY() - blockReachDistance, pos.getZ() - blockReachDistance);

        return range.minX <= playerPos.getX() && range.minY <= playerPos.getY() && range.minZ <= playerPos.getZ() && range.maxX >= playerPos.getX() && range.maxY >= playerPos.getY() && range.maxZ >= playerPos.getZ();
    }

    private static Identifier getDimensionTypeId(World world) {
        return world.getRegistryKey().getValue();
    }
}
