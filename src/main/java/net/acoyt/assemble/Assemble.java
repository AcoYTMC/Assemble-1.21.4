package net.acoyt.assemble;

import net.acoyt.assemble.client.payload.EnforceConfigMatchPayload;
import net.acoyt.assemble.init.*;
import net.acoyt.assemble.item.HammerItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public class Assemble implements ModInitializer {
	public static final String MOD_ID = "assemble";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}