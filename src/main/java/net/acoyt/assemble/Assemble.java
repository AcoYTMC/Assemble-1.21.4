package net.acoyt.assemble;

import net.acoyt.assemble.init.ModBlocks;
import net.acoyt.assemble.init.ModEntities;
import net.acoyt.assemble.init.ModItems;
import net.acoyt.assemble.item.HammerItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
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

		AssembleConfig.init(MOD_ID, AssembleConfig.class);

		DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
						Predicate.isEqual(ModItems.HAMMER),
				(builder, item) -> builder.add(DataComponentTypes.ATTRIBUTE_MODIFIERS, HammerItem.createAttributeModifiers())
		));
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}