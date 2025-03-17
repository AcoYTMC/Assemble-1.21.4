package net.acoyt.assemble.item;

import net.acoyt.assemble.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CleavingScissorsItem extends MiningToolItem {
    public CleavingScissorsItem(TagKey<Block> effectiveBlocks, Settings settings) {
        super(ToolMaterial.IRON, effectiveBlocks, 3.0f, -2.4f, settings);
    }

    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity target, Hand hand) {
        World world = user.getWorld();
        if (target instanceof GoatEntity goat && world instanceof ServerWorld serverWorld) {
            world.spawnEntity(new ItemEntity(world, goat.getX(), goat.getY(), goat.getZ(), new ItemStack(ModItems.GOAT_SHAG)));
        }

        return ActionResult.PASS;
    }
}
