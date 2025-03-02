package net.acoyt.assemble.item;

import net.acoyt.assemble.Assemble;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

public class HammerItem extends MiningToolItem {
    public static final Identifier BASE_ENTITY_REACH_MODIFIER_ID;

    public HammerItem(Settings settings) {
        super(ToolMaterial.IRON, BlockTags.PICKAXE_MINEABLE, 0.0f, -2.9f, settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)8.5f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)-2.9f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(BASE_ENTITY_REACH_MODIFIER_ID, (double)0.25F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }

    static {
        BASE_ENTITY_REACH_MODIFIER_ID = Assemble.id("base_entity_interaction_range");
    }
}
