package net.acoyt.assemble;

import net.acoyt.assemble.client.payload.EnforceConfigMatchPayload;
import net.acoyt.assemble.init.*;
import net.acoyt.assemble.item.HammerItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public class Assemble implements ModInitializer {
	public static final String MOD_ID = "assemble";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Identifier BABY_VILLAGER_LOOT_TABLE_ID = Identifier.ofVanilla("entities/villager");

	public void onInitialize() {
		ModItems.init();
		ModBlocks.init();

		ModEntities.init();

		ModSoundEvents.init();

		AssembleConfig.init(MOD_ID, AssembleConfig.class);

		DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
						Predicate.isEqual(ModItems.HAMMER),
				(builder, item) -> builder.add(DataComponentTypes.ATTRIBUTE_MODIFIERS, HammerItem.createAttributeModifiers())
		));
		DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
				Predicate.isEqual(ModItems.LEGAL_BRICK),
				(builder, item) -> builder.add(DataComponentTypes.CONSUMABLE, ModConsumables.LEGAL_BRICK)
		));

		// Enforce Configuration Match
		PayloadTypeRegistry.playS2C().register(EnforceConfigMatchPayload.ID, EnforceConfigMatchPayload.CODEC);

		// Modify Baby Villager Loot Table
		AttackEntityCallback.EVENT.register((player, world, hand, entity, hit) -> {

			if (entity instanceof VillagerEntity villager && villager.isBaby() && villager.getHealth() < 10) {
				world.spawnEntity(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.LEGAL_BRICK)));
				world.playSound((PlayerEntity)null, player.getPos().getX(), player.getPos().getY(), player.getPos().getZ(), ModSoundEvents.PLACE_LEGAL, SoundCategory.NEUTRAL, 1.0F, (float)((double)1.0F + player.getRandom().nextGaussian() / (double)10.0F));
			}
            return ActionResult.PASS;
        });
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}